package downloadagent;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class DownloadAgent {

    private ProgressListener progressListener;

    private int counter = 0;

    public DownloadAgent(ProgressListener progressListener) {

        this.progressListener = progressListener;

    }

    public int download(URI uri, OutputStream out) {

        try {

            URL url = uri.toURL();

            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();

            int filesize = connection.getContentLength();

            byte[] buffer = new byte[2048];

            int readBytes;

            int totalReadBytes = 0;

            while ((readBytes = inputStream.read(buffer)) > 0) {

                totalReadBytes += readBytes;

                int percent = (totalReadBytes * 100) / filesize;

                out.write(buffer, 0, readBytes);

                if (counter != percent) {

                    progressListener.progressUpdate(percent);

                    counter++;
                }
            }
            inputStream.close();

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return counter;
    }
}
