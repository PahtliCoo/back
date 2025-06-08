package life.pahtlicoo.application.dto.shipmentorder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShipmentOrderFormatReqDTO {
    private Integer state;
    private String description;
}
