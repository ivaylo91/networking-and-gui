package downloadagent;



import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by clouway on 06.01.16.
 */
public class DownloadDemo {

    static class DownloadAgent {
        public void download(URL url, final String outputFile) {
            try {
                URLConnection connection = url.openConnection();

                InputStream inputStream = connection.getInputStream();
                OutputStream out = new FileOutputStream(new File(outputFile));

                byte[] buffer = new byte[2048];

                int read;

                while ((read = inputStream.read(buffer)) > 0) {
                    out.write(buffer, 0, read);
                }
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        DownloadAgent agent = new DownloadAgent();

        agent.download(new URL("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTUDQgbT9PFaERFaLbqP8sFsyq2r3sSYu6BtCj63z90tLEpALgo"), "image.jpg");



    }
}
