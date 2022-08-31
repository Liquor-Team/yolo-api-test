package liquorteam.yoloapitest.message;

import liquorteam.yoloapitest.liquor.Liquor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse {
    private Liquor[] liquors;
    private int size;
}
