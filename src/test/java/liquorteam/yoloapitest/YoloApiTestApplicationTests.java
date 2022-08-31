package liquorteam.yoloapitest;

import liquorteam.yoloapitest.message.ApiMessageFactory;
import liquorteam.yoloapitest.message.ApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class YoloApiTestApplicationTests {

	@Autowired
	private ApiMessageFactory apiMessageFactory;

	@Test
	void apiSendTest() {
		System.out.println(apiMessageFactory.getString());
	}

}
