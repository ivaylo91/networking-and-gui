package client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @ author Ivaylo Penev(ipenev91@gmail.com)
 */
public class Client {

    private String host;

    private int port;

    private BufferedReader input;

    private Socket socket;

    private DisplayMessage display;

    public Client(String host, int port, DisplayMessage display) {

        this.host = host;

        this.port = port;

        this.display = display;
    }

    public void connect() {

        try {
            socket = new Socket(host, port);

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String fromServer = input.readLine();

            display.newMessage(fromServer);

            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stop() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}