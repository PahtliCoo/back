package life.pahtlicoo.application.usecase.shipmentorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.ShipmentOrderService;

@ApplicationScoped
public class DeleteShipmentOrderUseCase {
    @Inject
    ShipmentOrderService shipmentOrderService;

    public void execute(int shipmentOrderId) {
        shipmentOrderService.deleteShipmentOrder(shipmentOrderId);
    }
}
