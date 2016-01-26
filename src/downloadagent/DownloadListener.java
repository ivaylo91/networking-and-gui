package downloadagent;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Ivaylo Penev(ipenev91@gmail.com)
 */
public class DownloadListener implements ProgressListener {
    @Override
    public void progressUpdate(int progress) {

        System.out.println("Completed:" + progress + "%");

    }
}
