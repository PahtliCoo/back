package life.pahtlicoo.application.dto.historicdata;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricDataCSVDTO  {
    @CsvBindByPosition(position = 0)
    private String medName;

    @CsvBindByPosition(position = 1)
    private Integer quantity;

    @CsvBindByPosition(position = 2)
    private String siteName;

    @CsvBindByPosition(position = 3)
    private Integer month;

    @CsvBindByPosition(position = 4)
    private Integer year;
}
