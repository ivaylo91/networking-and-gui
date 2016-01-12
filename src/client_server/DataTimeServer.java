package client_server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class DataTimeServer {


    private int port;

    private ServerSocket ss;

    private Socket client;

    public DataTimeServer(int port) {

        this.port = port;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ss = new ServerSocket(port);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        client = ss.accept();

                        System.out.println("Connection Established.");

                        PrintStream out = new PrintStream(client.getOutputStream());

                        out.println("Hello " + new Date());

                        client.close();

                    } catch (IOException e) {

                        System.err.println("Connection not established.");
                    }
                }
            }
        }).start();
    }

    public void stopServer() {
        try {
            if (ss != null) {
                ss.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}