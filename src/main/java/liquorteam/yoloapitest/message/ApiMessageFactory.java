package liquorteam.yoloapitest.message;

import liquorteam.yoloapitest.image.ImageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.awt.*;

@RequiredArgsConstructor
public class ApiMessageFactory {

    private final ImageGenerator imageGenerator;
    @Value("${AWS_EC2_ADDRESS}")
    private final String ec2Address;
    private final WebClient webClient = WebClient.builder().baseUrl(ec2Address).build();

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
