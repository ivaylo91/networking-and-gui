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

    private int clientsCounter = 0;

    private int port;

    private ServerSocket server;

    private final List<Socket> clientsList = new ArrayList<>();

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

                        clientsCounter++;

                        PrintStream print = new PrintStream(client.getOutputStream());

                        print.println("Client:" + clientsCounter + " connected.");

                        Thread.sleep(10);

                        sendMessage();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendMessage() {

        for (Socket cli : clientsList) {

            try {
                PrintStream printStream = new PrintStream(cli.getOutputStream());

                printStream.println("Hello you're a client: " + clientsCounter);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
