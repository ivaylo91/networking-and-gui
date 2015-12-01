package com.clouway.multiclientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class ClientSender {
    private Socket socket = null;

    public ClientSender(Socket socket) {
        this.socket = socket;
    }

    public void sendMessage(String message) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
