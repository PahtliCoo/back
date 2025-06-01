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
public class GetHistoricDataByDatesDTO {
    @NotBlank
    private int year;
    private int startMonth;
    private int endMonth;


}
