package life.pahtlicoo.application.dto.historicdata;

import com.opencsv.bean.CsvBindByName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricDataCSVDTO  {
    @CsvBindByName(column = "nombre_medicamento")
    @NotBlank
    private String medName;

    @CsvBindByName(column = "cantidad_medicamento")
    @NotBlank
    private int quantity;

    @CsvBindByName(column = "nombre_hospital")
    @NotBlank
    private String siteName;

    @CsvBindByName(column = "numero_mes")
    @NotBlank
    private int month;

    @CsvBindByName(column = "año")
    @NotBlank
    private int year;

}
