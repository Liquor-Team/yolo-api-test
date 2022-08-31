package liquorteam.yoloapitest.image;

import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ImageGenerator {

    public ClassPathResource getImageFile() {
        int randomNumber = getImageTag();
        String filePath = "/img/" + randomNumber + ".jpg";
        return new ClassPathResource(filePath);
    }

    private int getImageTag() {
        int randomNumber = (int) (Math.random() * 19) + 1;
        return randomNumber;
    }
}
