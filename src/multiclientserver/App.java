package multiclientserver;

import client_server.*;

/**
 * @author Ivatlo Penev(ipenev91@gmail.com)
 */
public class App {

    public static void main(String[] args) {

        Display display = new Display() {

            @Override
            public void show(String message) {
                System.out.println(message);

            }
        };
        Server server = new Server(3333);

        Client client = new Client("localhost", 3333, display);

        Client client2 = new Client("localhost", 3333, display);

        Client client3 = new Client("localhost", 3333, display);

        Client client4 = new Client("localhost", 3333, display);

        Client client5 = new Client("localhost", 3333, display);

        server.start();

        client.connect();

        client2.connect();

        client3.connect();

        client4.connect();

        client5.connect();
    }
}
