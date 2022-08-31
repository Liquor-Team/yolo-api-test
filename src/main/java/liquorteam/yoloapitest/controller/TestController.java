package liquorteam.yoloapitest.controller;

import liquorteam.yoloapitest.liquor.Liquor;
import liquorteam.yoloapitest.message.ApiMessageFactory;
import liquorteam.yoloapitest.message.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final ApiMessageFactory apiMessageFactory;      // 얘는 자동 주입 시켜줌

    @GetMapping("/go")
    public ResponseEntity<ApiResponse> getTest() {
        ApiResponse response = apiMessageFactory.sendRequestMessage();
        Liquor[] liquors = response.getLiquors();
        int size = response.getSize();
        System.out.println(size);
        for(Liquor liquor : liquors) {
            System.out.println(liquor.getName());
            System.out.println(liquor.getAccuracy());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
