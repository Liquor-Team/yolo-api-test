package liquorteam.yoloapitest.schedule;

import liquorteam.yoloapitest.message.YoloApiMessageFactory;
import liquorteam.yoloapitest.dto.YoloApiResponseDto;
import liquorteam.yoloapitest.result.YoloApiCallResultRecorder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
;import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class YoloApiCallScheduler {

    private int time = 0;
    private final YoloApiMessageFactory yoloApiMessageFactory;
    private final YoloApiCallResultRecorder yoloApiCallResultRecorder;

    private final Logger logger = LoggerFactory.getLogger("Scheduler Log");

    // TODO : 크론식 변경 필요
    @Scheduled(fixedRate = 10000)
    @Async
    public void apiCallTest() throws Exception {
        time += 1;
        System.out.println("API call " + time + LocalDateTime.now().toString());
        logger.info("Current Thread : {}", Thread.currentThread().getName());
        YoloApiResponseDto response = yoloApiMessageFactory.sendRequestMessage();
        if(response != null) {
            yoloApiCallResultRecorder.recordSuccessResult(response);
        }
        if (time == 100) {
            yoloApiCallResultRecorder.destroyFileConnector();
            System.exit(0);
        }
    }

}
