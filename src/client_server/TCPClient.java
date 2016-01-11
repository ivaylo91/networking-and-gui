package client_server;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by clouway on 07.01.16.
 */
public class TCPClient extends Thread {

    @Override
    public void run() {

        try {
            String sentence;

            String modifiedSentence;

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            Socket clientSocket = new Socket("localhost", 6789);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sentence = inFromUser.readLine();

            outToServer.writeBytes(sentence + '\n');

            modifiedSentence = inFromServer.readLine();

            System.out.println("FROM SERVER: " + modifiedSentence);

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
