package liquorteam.yoloapitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YoloApiTestApplication  {

	public static void main(String[] args) {
		SpringApplication.run(YoloApiTestApplication.class, args);
	}


}
