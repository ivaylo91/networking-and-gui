import client_server.Client;
import client_server.DataTimeServer;
import client_server.DisplayMessage;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class ClientServer {

    class FakeDisplay implements DisplayMessage {

        String message;

        @Override
        public void newMessage(String message) {

            this.message = message;

            System.out.println(message);
        }
    }

    @Test
    public void happyPath() {

        FakeDisplay fakeDisplay = new FakeDisplay();

        DataTimeServer dataTimeServer = new DataTimeServer(3333);

        Client client = new Client("localhost", 3333, fakeDisplay);

        Date date = new Date();

        dataTimeServer.start();

        client.connect();

        assertEquals(fakeDisplay.message, "Hello " + date);

    }
}
