package client_server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class DataTimeServer {


    private int port;

    private ServerSocket ss;

    private Socket client;

    private CurrentDate currentDate;

    public DataTimeServer(int port, CurrentDate currentDate) {

        this.port = port;

        this.currentDate = currentDate;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ss = new ServerSocket(port);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        client = ss.accept();

                        System.out.println("Connection Established.");

                        PrintStream out = new PrintStream(client.getOutputStream());

                        Date date = currentDate.now();

                        out.println("Hello " + date);

                        client.close();

                    } catch (IOException e) {

                        System.err.println("Connection not established.");
                    }
                }
            }
        }).start();
    }
    public void stopServer() {
        try {
            if (ss != null) {
                ss.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}