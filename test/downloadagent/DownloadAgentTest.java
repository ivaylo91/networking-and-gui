package downloadagent;

import com.google.common.io.ByteStreams;
import downloadagent.DownloadAgent;
import downloadagent.DownloadListener;
import downloadagent.ProgressListener;
import org.junit.Test;

import java.io.*;
import java.net.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertArrayEquals;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class DownloadAgentTest {

    public class DownloadListener implements ProgressListener {

        private int percent = 1;

        @Override
        public void progressUpdate(int progress) {

            System.out.println("Download:" + progress + "%");

            if (percent < 100) {

                percent++;
            }
        }

        public void assertLastPercent(int lastPercent) {

            assertThat(percent, is(equalTo(lastPercent)));
        }
    }

    @Test
    public void happyPath() throws IOException, URISyntaxException {

        DownloadListener downloadListener = new DownloadListener();

        DownloadAgent downloadAgent = new DownloadAgent(downloadListener);

        URI uri = this.getClass().getResource("/merry-christmas.jpg").toURI();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        downloadAgent.download(uri, outputStream);

        assertArrayEquals(outputStream.toByteArray(), getBytes(uri));

        downloadListener.assertLastPercent(100);
    }

    private byte[] getBytes(URI uri) throws IOException {

        URL url = uri.toURL();

        URLConnection connection = url.openConnection();

        InputStream inputStream = connection.getInputStream();

        return ByteStreams.toByteArray(inputStream);
    }
}
