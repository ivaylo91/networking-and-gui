package com.clouway.multiclientserver;

import com.google.common.util.concurrent.AbstractExecutionThreadService;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class MultiThreadedServer extends AbstractExecutionThreadService {
    private final int port;
    private List<ClientConnection> acceptedClient = new ArrayList<>();
    private ServerSocket serverSocket;
    public MultiThreadedServer(int port) {
        this.port = port;
    }

    @Override
    protected void run() {
        try {
            while (isRunning()) {
                ClientConnection clientConnection = new ClientConnection(serverSocket.accept());
                sendMessagesToAllClient();
                acceptedClient.add(clientConnection);
                clientConnection.sendMessage("Hello you are client number " + acceptedClient.size() + "!");
            }
        } catch (IOException e) {

        }
    }

    @Override
    protected void startUp() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void triggerShutdown() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessagesToAllClient() {
        for (Iterator<ClientConnection> i = acceptedClient.iterator(); i.hasNext();) {
            ClientConnection clientConn=i.next();
            clientConn.sendMessage("client number " + acceptedClient.size() + " is connect");
        }
    }
}
