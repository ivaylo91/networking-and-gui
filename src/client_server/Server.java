package client_server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class Server extends Thread {
    private int port;

    private ServerSocket ss;

    private Socket socket;

    private boolean listening = true;

    public Server(int port) {

        this.port = port;
    }

    @Override
    public void run() {

        try {
            ss = new ServerSocket(port);

            while (listening) {

                socket = ss.accept();

                OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());

                out.write(new Date() + "\n");

                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        try {
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
