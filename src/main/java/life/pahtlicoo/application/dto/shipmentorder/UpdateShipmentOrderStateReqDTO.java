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
public class UpdateShipmentOrderStateReqDTO {
    @NotBlank
    private int state;
}
