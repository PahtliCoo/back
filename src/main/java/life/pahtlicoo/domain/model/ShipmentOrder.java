/**
 * Shipment Order class.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-11
 */
package life.pahtlicoo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentOrder {
    private int shipmentOrderId;
    private int userId;
    private int requestId;
    private String description;
    private String status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
