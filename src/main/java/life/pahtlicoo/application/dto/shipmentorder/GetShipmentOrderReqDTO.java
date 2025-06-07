/**
 * Shipment Order Request DTO.
 * @Author Nicole Kapellmann Lepine (A01664563.tec.mx)
 * @since 2025-06-04
 */
package life.pahtlicoo.application.dto.shipmentorder;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GetShipmentOrderReqDTO {
    @NotBlank
    private String order_name;

    private OffsetDateTime created_at;
    private int state;
    @NotBlank
    private String site_name;
    @NotBlank
    private int request_id;
    @NotBlank
    private int shipment_id;
    private String description;
}
