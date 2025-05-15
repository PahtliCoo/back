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
public class CreateShipmentOrderReqDTO {
    @NotBlank
    private int user_id;
    @NotBlank
    private int request_id;
    private String description;
    private String status;
}
