package life.pahtlicoo.application.dto.historicdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredictionDTO {
    private int year;
    private int month;
    private int predicted_quantity;
}
