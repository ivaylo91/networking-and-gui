package com.clouway.multiclientserver;

import com.google.common.util.concurrent.Service;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class MultiThreadedServerTest {
    private MultiThreadedServer server;
    private Service service;

    private class TestClient {
        private final String hostName;
        private final int port;
        private String receivedMessage = "";
        private BufferedReader fromServer;

        public TestClient(String hostName, int port) {
            this.hostName = hostName;
            this.port = port;
        }

        public void connect() {
            try {
                Socket socket = new Socket(hostName, port);
                fromServer= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to ");
            }
        }

        public void assertLastReceivedMessageIs(String message) {
            try {
                receivedMessage = fromServer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assertThat(receivedMessage, is(message));
            return;
        }
    }

    @Before
    public void setUp() {
        server = new MultiThreadedServer(7777);
        service = server.startAsync();
        service.awaitRunning();
    }

    @Test
    public void happyPath() {
        TestClient testClient1 = new TestClient("localhost", 7777);
        TestClient testClient2 = new TestClient("localhost", 7777);
        TestClient testClient3 = new TestClient("localhost", 7777);
        testClient1.connect();
        testClient1.assertLastReceivedMessageIs("Hello you are client number 1!");
        testClient2.connect();
        testClient2.assertLastReceivedMessageIs("Hello you are client number 2!");
        testClient1.assertLastReceivedMessageIs("client number 2 is connect");
        testClient3.connect();
        testClient3.assertLastReceivedMessageIs("Hello you are client number 3!");
        testClient1.assertLastReceivedMessageIs("client number 3 is connect");
        testClient2.assertLastReceivedMessageIs("client number 3 is connect");
    }

    @After
    public void stopServer() {
        service.stopAsync().awaitTerminated();
    }

}
