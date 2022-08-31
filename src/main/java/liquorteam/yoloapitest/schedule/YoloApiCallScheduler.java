package liquorteam.yoloapitest.schedule;

import liquorteam.yoloapitest.message.ApiMessageFactory;
import liquorteam.yoloapitest.message.ApiResponse;
import liquorteam.yoloapitest.result.ResultRecorder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class YoloApiCallScheduler {

    private int time = 0;
    private final ApiMessageFactory apiMessageFactory;
    private final ResultRecorder resultRecorder;

    @Scheduled(cron = "0/10 * * * * ?")
    public void apiCallTest() throws Exception {
        time += 10;
        ApiResponse response = apiMessageFactory.sendRequestMessage();
        if(response != null) {
            resultRecorder.recordSuccessResult(response);
        }
        if (time == 60) {
            resultRecorder.destroyFileConnector();
            System.exit(0);
        }
    }

}
