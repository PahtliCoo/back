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
    private int shipmentOrderId;
    @NotBlank
    private String orderName;
    private OffsetDateTime createdAt;
    private int state;
    @NotBlank
    private String siteName;
    @NotBlank
    private int requestId;
    private String description;

}
