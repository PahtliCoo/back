package life.pahtlicoo.application.dto.historicdata;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistoricDataReqDTO {
    @NotBlank
    private int siteId;
    @NotBlank
    private int medId;
    @NotBlank
    private int dateMonth;
    @NotBlank
    private int dateYear;
    @NotBlank
    private int quantity;
}
