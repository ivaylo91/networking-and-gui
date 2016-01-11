import com.google.common.io.ByteStreams;
import downloadagent.DownloadAgent;
import downloadagent.DownloadListener;
import downloadagent.ProgressListener;
import org.junit.Test;

import java.io.*;
import java.net.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class DownloadApp {
    ProgressListener progressListener = new ProgressListener() {
        @Override
        public void progressUpdate(int progress) {
//            int percent=0;
//            assertThat(progress,is(equals(percent)));

        }
    };

    @Test
    public void happyPath() throws IOException, URISyntaxException {

        DownloadListener downloadListener = new DownloadListener();

        DownloadAgent downloadAgent = new DownloadAgent(progressListener);

        URI uri = this.getClass().getResource("merry-christmas.jpg").toURI();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        downloadAgent.download(uri, outputStream);

        assertArrayEquals(outputStream.toByteArray(), getBytes(uri));
    }

    private byte[] getBytes(URI uri) throws IOException {

        URL url = uri.toURL();

        URLConnection connection = url.openConnection();

        InputStream inputStream = connection.getInputStream();

        return ByteStreams.toByteArray(inputStream);
    }

}
