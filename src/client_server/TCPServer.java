package client_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by clouway on 07.01.16.
 */
public class TCPServer extends Thread {
    String clientSentence;
    String capitalizedSentece;

    @Override
    public void run() {

        try {
            ServerSocket welcomeSocket = new ServerSocket(2002);

            while (true) {

                Socket connection = welcomeSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                DataOutputStream out = new DataOutputStream(connection.getOutputStream());

                clientSentence = in.readLine();

                System.out.println("Received:" + clientSentence);

                capitalizedSentece = clientSentence.toUpperCase();

                out.writeBytes(capitalizedSentece);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
