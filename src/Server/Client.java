package src.Server;
/**
 * This class contains the information of a client.
 *
 * @author  Oliver Brottare
 * @version 1.0
 * @since   2022-03-02
 */
public class Client{
    private String userName;
    private String serverHost;
    private int serverPort;

    /**
     * Constructor method for Client.
     * @param userName The name of the client
     * @param host The adress of the server that the client wants to connect to
     * @param portNumber The port of the server the client wants to connect to
     */
    public Client(String userName, String host, int portNumber){
        this.userName = userName;
        this.serverHost = host;
        this.serverPort = portNumber;
    }

    /**
     * Getter method for username
     * @return String Returns the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter method for serverHost
     * @return String Returns the serverHost
     */
    public String getServerHost() {
        return serverHost;
    }

    /**
     * Getter method for serverPort
     * @return int Returns the serverPort
     */
    public int getServerPort() {
        return serverPort;
    }
}