package src.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/**
 * This class receives and outputs the messages sent from the clients.
 *
 * @author  Oliver Brottare
 * @version 1.0
 * @since   2022-03-02
 */
public class ClientThread implements Runnable {
    private Socket socket;
    private ChatServer server;

    /**
     * Constructor method for the ClientThread class.
     * Uses its parameter to establish which server and socket it is being used for.
     * @param server The server used for the ClientThread.
     * @param socket The socket used for the ClientThread.
     */
    public ClientThread(ChatServer server, Socket socket){
        this.server = server;
        this.socket = socket;
    }

    /**
     * The method that runs when ClientThread is made into a thread and then started.
     * Scans for inputs into the server and then sends that input out to all clients connected.
     */
    @Override
    public void run() {
        try{
            Scanner in = new Scanner(socket.getInputStream());
            while(!socket.isClosed()){
                if(in.hasNextLine()){
                    String input = in.nextLine();

                    for(ClientServerInfo thatClient : server.getClientServerList()){
                        PrintWriter thatClientOut = thatClient.getWriter();
                        if(thatClientOut != null){
                            thatClientOut.write(input + "\r\n");
                            thatClientOut.flush();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
