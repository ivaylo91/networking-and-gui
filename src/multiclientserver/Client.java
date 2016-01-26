package multiclientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class Client {

    private String host;

    private int port;

    private Socket socket;

    private Display display;

    public Client(String host, int port, Display display) {

        this.host = host;

        this.port = port;

        this.display = display;
    }

    public void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    socket = new Socket(host, port);

                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String message = input.readLine();

                    while (message != null) {

                        display.show(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
