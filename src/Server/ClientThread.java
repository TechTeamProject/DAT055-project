package src.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread implements Runnable {
    private Socket socket;
    private ChatServer server;

    public ClientThread(ChatServer server, Socket socket){
        this.server = server;
        this.socket = socket;
    }

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
