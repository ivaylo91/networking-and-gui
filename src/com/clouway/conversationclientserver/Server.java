package com.clouway.conversationclientserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class Server {
    private final int port;
    private final Clock clock;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private ServerSocket serverSocket;

    public Server(int port, Clock clock) {
        this.port = port;
        this.clock = clock;
    }

    public void start() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Socket clientSocket = null;
                while (true) {
                    PrintWriter out;
                    try {
                        clientSocket = serverSocket.accept();
                        out = new PrintWriter(clientSocket.getOutputStream());
                        String messageToSend = "Hello! " + dateFormat.format(clock.currentDate());
                        out.write(messageToSend);
                        out.close();
                    } catch (IOException e) {
                        break;
                    }
                }
            }
        });
        thread.start();
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
