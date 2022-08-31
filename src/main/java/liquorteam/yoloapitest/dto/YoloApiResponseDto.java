package liquorteam.yoloapitest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class YoloApiResponseDto {
    private Liquor[] liquors;
    private int size;
}
