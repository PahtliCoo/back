package life.pahtlicoo.application.usecase.shipmentorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.ShipmentOrderService;
import life.pahtlicoo.domain.model.ShipmentOrder;

@ApplicationScoped
public class GetShipmentOrderUseCase {
    @Inject
    ShipmentOrderService shipmentOrderService;

    public ShipmentOrder execute(int shipmentOrderId) {
        return  shipmentOrderService.getShipmentOrderById(shipmentOrderId); //TODO refactor remove ById
    }
}