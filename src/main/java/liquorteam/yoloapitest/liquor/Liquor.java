package liquorteam.yoloapitest.liquor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Liquor {
    private double accuracy;
    private int index;
    private String name;
}
