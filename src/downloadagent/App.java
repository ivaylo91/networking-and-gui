package downloadagent;

import java.io.IOException;

/**
 * Created by clouway on 17.12.15.
 */
public class App {

    public static void main(String[] args) throws IOException {

        DownloadAgent downloadAgent = new DownloadAgent();

        downloadAgent.downloadFile("https://www.softuni.bg","file.txt");
    }
}
