package liquorteam.yoloapitest;

import liquorteam.yoloapitest.schedule.YoloApiCallScheduler;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Runner implements org.springframework.boot.ApplicationRunner {

    private YoloApiCallScheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        scheduler.apiCallTest();
    }
}
