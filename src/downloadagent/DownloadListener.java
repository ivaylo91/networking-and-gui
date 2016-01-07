package downloadagent;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class DownloadListener implements ProgressListener {

    @Override
    public void progressUpdate(int progress) {

        System.out.println("Completed:" + progress + "%");
    }
}
