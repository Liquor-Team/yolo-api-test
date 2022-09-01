package liquorteam.yoloapitest.result;

import java.io.IOException;

public interface ResultRecorder {

    void record400FailedResult() throws IOException;
    void record500FailedResult(String statusCode) throws IOException;
    void destroyFileConnector() throws IOException;
}
