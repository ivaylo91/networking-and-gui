package multiclientserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class Server {

    private int clientCounter = 0;

    private final int port;

    private ServerSocket server;

    private List<Socket> clientsList = new ArrayList<>();

    public Server(int port) {

        this.port = port;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server = new ServerSocket(port);

                    while (true) {

                        Socket client = server.accept();

                        synchronized (clientsList) {

                            clientsList.add(client);
                        }

                        increaseClients();

                        PrintStream print = new PrintStream(client.getOutputStream());

                        print.println("Client:" + clientCounter + " connected.");

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (clientsList.size() > 0) {

                            sendMessage();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendMessage() {

        for (Socket each : clientsList) {

            try {
                PrintStream printStream = new PrintStream(each.getOutputStream());

                printStream.println("Hello you're a client: " + clientCounter);

                printStream.flush();

            } catch (IOException e) {

                try {
                    server.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private synchronized void increaseClients() {

        clientCounter++;
    }
}
