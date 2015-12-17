package downloadagent;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class DownloadAgent {

    public void downloadFile(String url, String filename) throws IOException {

        URL myUrl = new URL(url);
        URLConnection connection = myUrl.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

        int readBytes;

        while ((readBytes = br.read()) != -1) {

            bw.write(readBytes);
        }
    }
}
