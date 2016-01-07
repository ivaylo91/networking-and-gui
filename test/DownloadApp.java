import com.google.common.io.ByteStreams;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import downloadagent.DownloadAgent;
import downloadagent.DownloadListener;
import org.junit.Test;

import java.io.*;
import java.net.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class DownloadApp {


    @Test
    public void happyPath() throws IOException, URISyntaxException {

        DownloadListener downloadListener = new DownloadListener();

        DownloadAgent downloadAgent = new DownloadAgent(downloadListener, "merry-christmas.jpg");

        URI uri = this.getClass().getResource("merry-christmas.jpg").toURI();

        OutputStream output = new ByteArrayOutputStream();

        assertThat(downloadAgent.downloadFile(uri, output), is(100));

    }
}
