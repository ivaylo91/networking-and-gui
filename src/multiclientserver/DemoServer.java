package multiclientserver;

import client_server.*;

/**
 * @author Ivatlo Penev(ipenev91@gmail.com)
 */
public class DemoServer {

    public static void main(String[] args) {

        Server server = new Server(3333);

        server.start();
    }
}
