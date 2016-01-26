package multiclientserverguava;

import com.google.common.util.concurrent.AbstractExecutionThreadService;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class Server extends AbstractExecutionThreadService {
    private int counter = 0;
    private final int port;
    private List<Socket> clientsList = new ArrayList<>();
    private ServerSocket serverSocket;
    private DisplayMessage display;

    public Server(int port, DisplayMessage display) {
        this.port = port;
        this.display = display;
    }

    @Override
    protected void run() throws Exception {

        serverSocket = new ServerSocket(port);

        while (isRunning()) {

            Socket client = serverSocket.accept();

            increaseClients();

            synchronized (clientsList) {

                clientsList.add(client);

            }
            display.show("Client:" + counter + " has been connected.");

            if (clientsList.size() > 0) {

                sendMessageToAll();
            }
        }
    }

    public void sendMessageToAll() {
        for (Socket each : clientsList) {
            try {
                PrintStream print = new PrintStream(each.getOutputStream());

                print.println("Hello client:" + counter);

                print.flush();

            } catch (IOException e) {
                break;
            }
        }
    }

    private void increaseClients() {

        counter++;

    }

    @Override
    protected void triggerShutdown() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
