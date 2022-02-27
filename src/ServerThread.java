package src;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private static Socket socket;
    private static LinkedList<String> messagesToSend = new LinkedList<>();
    private static boolean hasMessages = false;
    private Client client;

    private static ChatControl chatControl;
    public ServerThread(String userName, String host, int portNumber){
        client = new Client(userName, host, portNumber);
        messagesToSend = new LinkedList<>();

        chatControl = new ChatControl(false);
        try{
            socket = new Socket(client.getServerHost(), client.getServerPort());

            Thread.sleep(1000); // Waiting for network communicating.

            Thread serverAccessThread = new Thread(this);
            serverAccessThread.start();
            chatControl.printText("Successfully connected to host " + host + " port " + portNumber);
        }catch(IOException ex){
            chatControl.printText("Fatal Connection error!");
        }catch(InterruptedException ex){
            chatControl.printText("Interrupted");
        }
    }

    public static void addNextMessage(String message){
        synchronized (messagesToSend){
            hasMessages = true;
            messagesToSend.push(message);
        }
    }

    public static boolean Alive(){
        try {
            return !socket.isClosed();
        }catch (NullPointerException n){
            return false;
        }

    }

    @Override
    public void run(){
        try{
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), false);
            InputStream serverInStream = socket.getInputStream();
            Scanner serverIn = new Scanner(serverInStream);

            while(!socket.isClosed()){
                if(serverInStream.available() > 0){
                    if(serverIn.hasNextLine()){
                        chatControl.printText(serverIn.nextLine());
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
            chatControl.printText("Input/Output failed!");
        }
    }
}
