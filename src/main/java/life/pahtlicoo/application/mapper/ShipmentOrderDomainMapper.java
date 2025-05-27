/**
 * Credentials Req DTO
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-Author Santiago Moreno Lacalle Quintero (A01663197)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.shipmentorder.CreateShipmentOrderReqDTO;
import life.pahtlicoo.domain.model.ShipmentOrder;

@ApplicationScoped
public class ShipmentOrderDomainMapper {
    public ShipmentOrder createShipmentOrderToDomain(CreateShipmentOrderReqDTO createShipmentOrderReqDTO){
        ShipmentOrder shipmentOrder = new ShipmentOrder();
        shipmentOrder.setRequestId(createShipmentOrderReqDTO.getRequest_id());
        shipmentOrder.setDescription(createShipmentOrderReqDTO.getDescription());
        shipmentOrder.setState(createShipmentOrderReqDTO.getState());
        return shipmentOrder;
    }
}
