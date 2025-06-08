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
public class GenerateForecastReqDTO {
    private List<Integer> site_ids;
    private List<Integer> med_ids;
    private int forecast_horizon;
    private int current_month;
    private int current_year;
    private boolean save;
}
