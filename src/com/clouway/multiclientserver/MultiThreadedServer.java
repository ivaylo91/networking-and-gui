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
    private List<ClientSender> acceptedClient = new ArrayList<>();
    private ServerSocket serverSocket;
    public MultiThreadedServer(int port) {
        this.port = port;
    }

    @Override
    protected void run() {
        try {
            while (isRunning()) {
                ClientSender clientSender = new ClientSender(serverSocket.accept());
                acceptedClient.add(clientSender);
                clientSender.sendMessage("Hello you are client number " + acceptedClient.size() + "!");
                sendMessagesToAllClient(clientSender);
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

    public void sendMessagesToAllClient(ClientSender clientSender) {
        for (Iterator<ClientSender> i = acceptedClient.iterator(); i.hasNext();) {
            ClientSender serverThr=i.next();
            if (!serverThr.equals(clientSender)){
                serverThr.sendMessage("client number " + acceptedClient.size() + " is connect");
            }
        }
    }
}
