package multiclientserverguava;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class App {
    public static void main(String[] args) {
        DisplayMessage displayMessage = new DisplayMessage() {
            @Override
            public void show(String message) {
                System.out.println(message);
            }
        };

        Server server = new Server(3333, displayMessage);
        server.startAsync().awaitRunning();

        Client client = new Client(3333, "localhost", displayMessage);
        Client client1 = new Client(3333, "localhost", displayMessage);

        client.startAsync().awaitTerminated();
        client1.startAsync().awaitTerminated();
    }
}
