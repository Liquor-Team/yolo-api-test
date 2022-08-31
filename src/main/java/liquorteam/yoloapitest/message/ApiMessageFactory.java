package liquorteam.yoloapitest.message;

import liquorteam.yoloapitest.image.ImageGenerator;
import liquorteam.yoloapitest.result.ResultRecorder;
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
public class ApiMessageFactory {

    private final ImageGenerator imageGenerator;
    private final ResultRecorder resultRecorder;
    static private final WebClient webClient = WebClient.builder().baseUrl("").build();

    public ApiResponse sendRequestMessage() {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", imageGenerator.getImageFile());
        ApiResponse response = webClient.post()
                .uri("/")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    try {
                        resultRecorder.record400FailedResult();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    try {
                        resultRecorder.record500FailedResult();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                })
                .bodyToMono(ApiResponse.class)
                .block();
        return response;
    }

}
