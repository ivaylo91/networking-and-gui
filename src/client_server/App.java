package client_server;

/**
 * Created by clouway on 07.01.16.
 */
public class App {

    public static void main(String[] args) {

        Client client = new Client("localhost", 2002);

        Server server = new Server(2002);

        client.start();

        server.start();
    }
}
