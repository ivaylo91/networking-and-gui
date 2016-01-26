package multiclientserver;

import client_server.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class MultClientServerTest {

    class TestClient {

        private final int port;
        private String server;
        private String receivedMessage;
        private BufferedReader input;

        public TestClient(String server, int port) {
            this.port = port;
            this.server = server;
        }

        public void connect() {
            try {
                Socket socket = new Socket(server, port);

                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void assertReceivedMessage(String message) {

            try {
                receivedMessage = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assertThat(receivedMessage, is(message));
        }
    }

    @Test
    public void clientsConnect() {

        Server server = new Server(3333);

        server.start();

        TestClient client1 = new TestClient("localhost", 3333);

        TestClient client2 = new TestClient("localhost", 3333);

        TestClient client3 = new TestClient("localhost", 3333);

        client1.connect();

        client2.connect();

        client3.connect();

        client1.assertReceivedMessage("Client:1 connected.");
        client1.assertReceivedMessage("Hello you're a client: 1");

        client2.assertReceivedMessage("Client:2 connected.");
        client2.assertReceivedMessage("Hello you're a client: 2");

        client3.assertReceivedMessage("Client:3 connected.");
        client3.assertReceivedMessage("Hello you're a client: 3");
    }
}
