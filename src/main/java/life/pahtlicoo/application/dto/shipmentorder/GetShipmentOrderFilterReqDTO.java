package life.pahtlicoo.application.dto.shipmentorder;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class GetShipmentOrderFilterReqDTO {
    @NotBlank
    private int userId; //maaaybe no lo necesitas
    private Integer state;
    private Integer day;
    private Integer month;
    private Integer year;
}
