package liquorteam.yoloapitest.message;

import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ApiMessageFactory {

    WebClient webClient;

    public MultipartBodyBuilder createMultipartBodyBuilder() {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", "/path");
        return builder;
    }

    public void sendRequestMessage() {
        MultipartBodyBuilder builder = createMultipartBodyBuilder();
        Mono<Void> result = webClient.post()
                .uri("path", id)
                .body(builder.build())
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
