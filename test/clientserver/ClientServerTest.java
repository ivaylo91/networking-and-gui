package clientserver;

import client_server.Client;
import client_server.CurrentDate;
import client_server.DataTimeServer;
import client_server.MessageDisplay;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Rule;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class ClientServerTest {

    class FakeDisplay implements MessageDisplay {

        String message;

        @Override
        public void show(String message) {

            this.message = message;

            System.out.println(message);
        }
    }
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery() {{
        setThreadingPolicy(new Synchroniser());
    }};

    @Mock
    CurrentDate currentDate;

    private FakeDisplay fakeDisplay = new FakeDisplay();

    @Test
    public void happyPath() throws InterruptedException {

        DataTimeServer dataTimeServer = new DataTimeServer(3333, currentDate);

        dataTimeServer.start();

        Client client = new Client("localhost", 3333, fakeDisplay);

        Date date = new Date();

        isCurrentDate(date);

        client.connect();

        assertEquals(fakeDisplay.message, "Hello " + date);

    }
    public void isCurrentDate(Date date) {

        context.checking(new Expectations() {
            {
                oneOf(currentDate).now();

                will(returnValue(date));

            }
        });
    }
}
