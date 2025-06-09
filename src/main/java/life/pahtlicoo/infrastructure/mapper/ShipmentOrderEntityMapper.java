/**
 * Shipment Order Entity Mapper.
 * @author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @co-author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.ShipmentOrder;
import life.pahtlicoo.infrastructure.entity.ShipmentOrderEntity;

@ApplicationScoped
public class ShipmentOrderEntityMapper {
    public ShipmentOrderEntity toEntity(ShipmentOrder shipmentOrder) {
        return new ShipmentOrderEntity(shipmentOrder.getShipmentOrderId(),
                shipmentOrder.getRequestId(), shipmentOrder.getDescription(),
                shipmentOrder.getState(), shipmentOrder.getCreatedAt(), shipmentOrder.getUpdatedAt());
    }

    public ShipmentOrder toDomain(ShipmentOrderEntity shipmentOrderEntity){
        return new ShipmentOrder(shipmentOrderEntity.getShipmentOrderId(),
                shipmentOrderEntity.getRequestId(), shipmentOrderEntity.getDescription(),
                shipmentOrderEntity.getState(), shipmentOrderEntity.getCreatedAt(), shipmentOrderEntity.getUpdatedAt());
    }

}
