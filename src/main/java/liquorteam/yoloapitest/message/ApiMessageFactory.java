package liquorteam.yoloapitest.message;

import liquorteam.yoloapitest.image.ImageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.awt.*;

@RequiredArgsConstructor
@Component
public class ApiMessageFactory {

    private final ImageGenerator imageGenerator;
    static private final WebClient webClient = WebClient.builder().baseUrl("http://ec2-43-200-62-27.ap-northeast-2.compute.amazonaws.com:80").build();


    // TODO : 406 에러에 대한 처리 필요 (request time out)
    public ApiResponse sendRequestMessage() {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", imageGenerator.getImageFile());
        ApiResponse response = webClient.post()
                .uri("/")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .block();
        return response;
    }


}
