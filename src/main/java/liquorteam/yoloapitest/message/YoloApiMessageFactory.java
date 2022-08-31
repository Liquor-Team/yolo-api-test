package liquorteam.yoloapitest.message;

import liquorteam.yoloapitest.image.ImageGenerator;
import liquorteam.yoloapitest.dto.YoloApiResponseDto;
import liquorteam.yoloapitest.result.YoloApiCallResultRecorder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class YoloApiMessageFactory implements ApiMessageFactory {

    private final ImageGenerator imageGenerator;
    private final YoloApiCallResultRecorder yoloApiCallResultRecorder;
    static private final WebClient webClient = WebClient.builder().baseUrl().build();

    @Override
    public YoloApiResponseDto sendRequestMessage() {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", imageGenerator.getImageFile());
        YoloApiResponseDto response = webClient.post()
                .uri("/")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    try {
                        yoloApiCallResultRecorder.record400FailedResult();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    try {
                        yoloApiCallResultRecorder.record500FailedResult();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                })
                .bodyToMono(YoloApiResponseDto.class)
                .block();
        return response;
    }

}
