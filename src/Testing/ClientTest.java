package src.Testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Server.Client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClientTest {
    Client client1;
    Client client2;
    @BeforeEach
    void setUp() {
        client1 = new Client("Tester1","localhost1", 23476);
        client2 = new Client("Tester2","localhost2", 23476);
    }

    @Test
    void getUsername(){
        assertNotEquals(client1.getUserName(), client2.getUserName());
    }

    @Test
    void getServerHost(){
        assertNotEquals(client1.getServerHost(), client2.getServerHost());
    }

    @Test
    void getServerPort(){
        assertEquals(client1.getServerPort(), client2.getServerPort());
    }
}
