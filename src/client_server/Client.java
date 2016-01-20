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

    private MessageDisplay display;

    public Client(String host, int port, MessageDisplay display) {

        this.host = host;

        this.port = port;

        this.display = display;
    }

    public void connect() {
        try {
            Socket socket = new Socket(host, port);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String fromServer = input.readLine();

            display.newMessage(fromServer);

            input.close();

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

