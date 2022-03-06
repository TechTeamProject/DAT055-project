package src.Server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * The thread used by the client to send messages to the server.
 *
 * @author  Oliver Brottare
 * @version 1.0
 * @since   2022-03-02
 */
public class ClientThread implements Runnable {
    private static Socket socket;
    private LinkedList<String> messagesToSend;
    private boolean hasMessages = false;
    private Client client;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private static final LinkedList<String> messages = new LinkedList<>();

    /**
     * Getter method for the top element in messages.
     * @return String The top element in messages.
     */
    public String getMessage(){
        return messages.pop();
    }

    /**
     * Adds a PropertyChangeListener to PropertyChangeSupport.
     * @param listener The PropertyChangeListener that is added to the support.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    /**
     * Constructor for ServerThread. Tries to connect to the server using the parameters.
     * @param userName The name of the client.
     * @param host  The host adress of the server.
     * @param portNumber The port of the server.
     */

    public ClientThread(String userName, String host, int portNumber){
        client = new Client(userName, host, portNumber);
        messagesToSend = new LinkedList<>();
        try{
            socket = new Socket(client.getServerHost(), client.getServerPort());

            Thread.sleep(1000); //Wait for the network to communicate

            Thread serverAccessThread = new Thread(this);
            serverAccessThread.start();
        }catch(IOException | InterruptedException ex){
        }
    }

    /**
     * A method used to add a message to the LinkedList that sends messages to the server.
     * @param message The message being added to the list.
     */
    public void addNextMessage(String message){
        synchronized (messagesToSend){
            hasMessages = true;
            messagesToSend.push(message);
        }
    }

    /**
     * A method used to see whether the socket is open or closed.
     * If the socket is null then it will catch the NullPointerException and return false.
     * @return boolean To describe if the socket is open or closed.
     */
    public static boolean Alive(){
        try {
            return !socket.isClosed();
        }catch (NullPointerException n){
            return false;
        }

    }

    /**
     * The method is used when ServerThread is made into a thread and starts running.
     * Send messages to the server and prints the texts it receives from the server.
     */
    @Override
    public void run(){
        try{
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), false);
            InputStream serverInStream = socket.getInputStream();
            Scanner serverIn = new Scanner(serverInStream);

            while(!socket.isClosed()){
                if(serverInStream.available() > 0){
                    if(serverIn.hasNextLine()){
                        messages.add(serverIn.nextLine());
                        support.firePropertyChange("NewMessage", 1 ,0);
                    }
                }
                if(hasMessages){
                    String nextSend;
                    synchronized(messagesToSend){
                        nextSend = messagesToSend.pop();
                        hasMessages = !messagesToSend.isEmpty();
                    }
                    serverOut.println(client.getUserName() + " > " + nextSend);
                    serverOut.flush();
                }
            }
        }
        catch(IOException ex){
            messages.add("Input/Output failed!");
            support.firePropertyChange("NewMessage", 1 ,0);
        }
    }
}
