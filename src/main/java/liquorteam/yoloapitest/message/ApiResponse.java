package liquorteam.yoloapitest.message;

import liquorteam.yoloapitest.liquor.Liquor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private Liquor[] liquors;
    private int size;
}
