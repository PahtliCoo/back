package life.pahtlicoo.application.usecase.shipmentorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.shipmentorder.CreateShipmentOrderReqDTO;
import life.pahtlicoo.application.mapper.ShipmentOrderDomainMapper;
import life.pahtlicoo.application.service.ShipmentOrderService;
import life.pahtlicoo.domain.model.ShipmentOrder;

@ApplicationScoped
public class CreateShipmentOrderUseCase {
    @Inject
    ShipmentOrderService shipmentOrderService;

    @Inject
    ShipmentOrderDomainMapper shipmentOrderDomainMapper;

    public void execute(CreateShipmentOrderReqDTO createShipmentOrderReqDTO) {
        ShipmentOrder shipmentOrder = shipmentOrderDomainMapper.createShipmentOrderToDomain(createShipmentOrderReqDTO);
        shipmentOrderService.createShipmentOrder(shipmentOrder);
    }

}
