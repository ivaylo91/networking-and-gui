package multiclientserver;

/**
 * Created by clouway on 21.01.16.
 */
public class DemoClient {
    public static void main(String[] args) {

        Display display = new Display() {
            @Override
            public void show(String message) {
                System.out.println(message);
            }
        };

        Client client = new Client("localhost", 3333, display);

        client.connect();
    }
}
