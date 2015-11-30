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
    private List<MultiServerThread> acceptedClient = new ArrayList<>();
    private ServerSocket serverSocket;
    public MultiThreadedServer(int port) {
        this.port = port;
    }

    @Override
    protected void run() {
        try {
            while (isRunning()) {
                MultiServerThread multiServerThread = new MultiServerThread(this,serverSocket.accept());
                acceptedClient.add(multiServerThread);
                multiServerThread.send("Hello you are client number " + acceptedClient.size() + "!");
                sendMessagesToAllClient(multiServerThread);
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

    public void sendMessagesToAllClient(MultiServerThread multiServerThread) {
        for (Iterator<MultiServerThread> i = acceptedClient.iterator(); i.hasNext();) {
            MultiServerThread serverThr=i.next();
            if (!serverThr.equals(multiServerThread)){
                serverThr.send("client number " + acceptedClient.size() + " is connect");
            }
        }
    }
}
