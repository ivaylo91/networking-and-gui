package multiclientserverguava;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
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
public class MultiClientServerTest {

    class FakeClient extends AbstractExecutionThreadService {

        private String host;
        private final int port;
        private String receivedMessage;
        private BufferedReader input;

        public FakeClient(int port, String host) {
            this.port = port;
            this.host = host;
        }

        @Override
        protected void run() throws Exception {

            Socket socket = new Socket(host, port);

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        public void assertNextReceivedMessage(String message) {
            try {
                receivedMessage = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assertThat(receivedMessage, is(message));
        }
    }

    @Test
    public void connectClients() {
        FakeDisplay display = new FakeDisplay();

        Server server = new Server(3333, display);
        server.startAsync().awaitRunning();

        FakeClient client1 = new FakeClient(3333, "localhost");
        FakeClient client2 = new FakeClient(3333, "localhost");
        FakeClient client3 = new FakeClient(3333, "localhost");
        client1.startAsync().awaitTerminated();
        client2.startAsync().awaitTerminated();
        client3.startAsync().awaitTerminated();

        client1.assertNextReceivedMessage("Hello client:1");
        client2.assertNextReceivedMessage("Hello client:2");
        client3.assertNextReceivedMessage("Hello client:3");
    }

    class FakeDisplay implements DisplayMessage {
        String message;

        @Override
        public void show(String message) {
            this.message = message;

            System.out.println(message);
        }
    }
}
