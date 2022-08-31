package liquorteam.yoloapitest.result;

import liquorteam.yoloapitest.dto.YoloApiResponseDto;

import java.io.IOException;

public interface ResultRecorder {

    void record400FailedResult() throws IOException;
    void record500FailedResult() throws IOException;
    void destroyFileConnector() throws IOException;
}
