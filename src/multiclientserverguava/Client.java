package multiclientserverguava;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import multiclientserver.Display;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class Client extends AbstractExecutionThreadService {
    private final int port;
    private String server;
    private Socket socket;
    private DisplayMessage display;

    public Client(int port, String server, DisplayMessage display) {
        this.port = port;
        this.server = server;
        this.display = display;
    }

    @Override
    protected void run() throws Exception {

        socket = new Socket(server, port);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String message = input.readLine();

        display.show(message);

    }

    @Override
    protected void shutDown() throws Exception {
        if (socket != null) {
            socket.close();
        }
    }
}
