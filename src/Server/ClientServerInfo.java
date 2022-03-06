package src.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * This class contains the necessary information to send out messages to a client.
 *
 * @author  Oliver Brottare
 * @version 1.0
 * @since   2022-03-06
 */
public class ClientServerInfo {
    private Socket socket;
    private PrintWriter clientOut;

    /**
     * Constructor method for ClientServerInfo
     * @param socket The socket for the client.
     * @throws IOException If something is wrong with the input or output.
     */
    public ClientServerInfo(Socket socket) throws IOException {
        this.socket = socket;
        clientOut = new PrintWriter(this.socket.getOutputStream(), false);
    }

    /**
     * Getter method for clientOut. Used to send a message to the client.
     * @return clientOut
     */
    public PrintWriter getWriter(){
        return clientOut;
    }
}
