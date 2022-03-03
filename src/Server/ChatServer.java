package src.Server;

import src.ChatControl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 * This class starts a server on the designated port and accepts clients which it adds to a list.
 *
 * @author  Oliver Brottare
 * @version 1.0
 * @since   2022-03-02
 */
public class ChatServer implements  Runnable{
    private final int portNumber;
    private List<ClientServerInfo> ClientServerList;
    private static ChatControl chatControl = new ChatControl(false);

    /**
     *  The constructor for ChatServer. Uses its parameter to choose the port number.
     * @param portNumber The port number for the server
     */
    public ChatServer(int portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * Getter method for ClientServerList.
     * @return ClientServerList A list with all users who have connected to the server.
     */
    public List<ClientServerInfo> getClientServerList() {
        return ClientServerList;
    }

    /**
     * A method that loops and accepts all clients trying to connect to the server.
     * @param serverSocket The socket used to connect with the clients.
     */
    private void acceptClients(ServerSocket serverSocket) {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ServerThread client = new ServerThread(this, socket);
                ClientServerInfo clientServerInfo = new ClientServerInfo(socket);
                Thread thread = new Thread(client);
                thread.start();
                ClientServerList.add(clientServerInfo);
            } catch (IOException ex) {
                System.out.println("Accept failed on : " + portNumber);
            }
        }
    }

    /**
     * The method that runs when ChatServer is made into a thread and then started.
     * Starts the server and accepts clients.
     */
    @Override
    public void run() {
        ClientServerList = new ArrayList<>();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(portNumber);
            chatControl.printText("Server successfully started on port " + portNumber);
            acceptClients(serverSocket);
        } catch (IOException e) {
            chatControl.printText("Could not listen on port " + portNumber);

        }
    }
}
