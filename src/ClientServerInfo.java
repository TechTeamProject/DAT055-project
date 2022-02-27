package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientServerInfo {
    private Socket socket;
    private PrintWriter clientOut;

    public ClientServerInfo(Socket socket) throws IOException {
        this.socket = socket;
        clientOut = new PrintWriter(socket.getOutputStream(), false);
    }

    public PrintWriter getWriter(){
        return clientOut;
    }
}
