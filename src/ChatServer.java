package src;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer implements  Runnable{

    private static int portNumber = 23476;
    private List<ClientServerInfo> ClientServerList;
    private static ChatControl cc = new ChatControl(false);
    public ChatServer(int portNumber) {
        this.portNumber = portNumber;
    }
    public List<ClientServerInfo> getClientServerList() {
        return ClientServerList;
    }

    private void acceptClients(ServerSocket serverSocket) {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("accepts : " + socket.getRemoteSocketAddress());
                ClientThread client = new ClientThread(this, socket);
                ClientServerInfo clienters = new ClientServerInfo(socket);
                Thread thread = new Thread(client);
                thread.start();
                ClientServerList.add(clienters);
            } catch (IOException ex) {
                System.out.println("Accept failed on : " + portNumber);
            }
        }
    }

    @Override
    public void run() {
        ClientServerList = new ArrayList<ClientServerInfo>();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
            cc.printText("Server successfully started on port " + portNumber);
            acceptClients(serverSocket);
        } catch (IOException e) {
            cc.printText("Could not listen on port " + portNumber);

        }
    }
}
