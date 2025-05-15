package life.pahtlicoo.application.dto.historicdata;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateHistoricDataReqDTO {
    @NotBlank
    private int site_id;
    private int year;
    private int month;
    private int med_id;
    private int quantity;
}
