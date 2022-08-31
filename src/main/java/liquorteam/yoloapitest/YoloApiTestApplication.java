package liquorteam.yoloapitest;

import liquorteam.yoloapitest.message.ApiMessageFactory;
import liquorteam.yoloapitest.schedule.YoloApiCallScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class YoloApiTestApplication  {

	public static void main(String[] args) {
		SpringApplication.run(YoloApiTestApplication.class, args);
	}


}
