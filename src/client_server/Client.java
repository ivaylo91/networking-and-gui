package client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class Client extends Thread {

    private String server;

    private int port;

    public Client(String server, int port) {
        this.server = server;
        this.port = port;
    }

    @Override
    public void run() {

        try {
            Socket socket = new Socket(server, port);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Hello," + input.readLine());

            input.close();

            socket.close();

            interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
