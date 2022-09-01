package liquorteam.yoloapitest.schedule;

import liquorteam.yoloapitest.message.YoloApiMessageFactory;
import liquorteam.yoloapitest.dto.YoloApiResponseDto;
import liquorteam.yoloapitest.result.YoloApiCallResultRecorder;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class YoloApiCallScheduler {

    private int time = 0;
    private final YoloApiMessageFactory yoloApiMessageFactory;
    private final YoloApiCallResultRecorder yoloApiCallResultRecorder;

    // TODO : 크론식 변경 필요
    @Async
    @Scheduled(fixedRate = 10000)
    public void apiCallTest() throws Exception {
        time += 1;
        System.out.println("API call " + time);
        YoloApiResponseDto response = yoloApiMessageFactory.sendRequestMessage();
        if(response != null) {
            yoloApiCallResultRecorder.recordSuccessResult(response);
        }
        if (time == 720) {
            yoloApiCallResultRecorder.destroyFileConnector();
            System.exit(0);
        }
    }

}
