package com.clouway.multiclientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class MultiServerThread extends Thread {
    private Socket socket = null;
    private String message;

    public MultiServerThread(MultiThreadedServer multiThreadedServer, Socket socket) {
        this.socket = socket;
    }

    public void run() {
        sendToUser();
    }

    public void send(String message) {
        this.message = message;
        run();
    }

    public void sendToUser() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
