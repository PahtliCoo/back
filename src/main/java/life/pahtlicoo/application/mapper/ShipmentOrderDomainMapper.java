package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.shipmentorder.CreateShipmentOrderReqDTO;
import life.pahtlicoo.domain.model.ShipmentOrder;

@ApplicationScoped
public class ShipmentOrderDomainMapper {
    public ShipmentOrder createShipmentOrderToDomain(CreateShipmentOrderReqDTO createShipmentOrderReqDTO){
        ShipmentOrder shipmentOrder = new ShipmentOrder();
        shipmentOrder.setUserId(createShipmentOrderReqDTO.getUser_id());
        shipmentOrder.setRequestId(createShipmentOrderReqDTO.getRequest_id());
        shipmentOrder.setDescription(createShipmentOrderReqDTO.getDescription());
        shipmentOrder.setStatus(createShipmentOrderReqDTO.getStatus());
        return shipmentOrder;
    }
}
