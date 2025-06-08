package life.pahtlicoo.application.dto.historicdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenerateForecastResDTO {
    private int site_id;
    private int med_id;
    private boolean saved;
    private String status_message;
    private List<PredictionDTO> predictions;
}
