package liquorteam.yoloapitest.schedule;

import liquorteam.yoloapitest.message.YoloApiMessageFactory;
import liquorteam.yoloapitest.dto.YoloApiResponseDto;
import liquorteam.yoloapitest.result.YoloApiCallResultRecorder;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class YoloApiCallScheduler {

    private int time = 0;
    private final YoloApiMessageFactory yoloApiMessageFactory;
    private final YoloApiCallResultRecorder yoloApiCallResultRecorder;

    @Scheduled(cron = "0/10 * * * * ?")
    public void apiCallTest() throws Exception {
        time += 10;
        YoloApiResponseDto response = yoloApiMessageFactory.sendRequestMessage();
        if(response != null) {
            yoloApiCallResultRecorder.recordSuccessResult(response);
        }
        if (time == 60) {
            yoloApiCallResultRecorder.destroyFileConnector();
            System.exit(0);
        }
    }

}
