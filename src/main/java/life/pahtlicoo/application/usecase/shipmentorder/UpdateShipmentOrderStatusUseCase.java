package life.pahtlicoo.application.usecase.shipmentorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.shipmentorder.UpdateShipmentOrderStateReqDTO;
import life.pahtlicoo.application.service.ShipmentOrderService;

@ApplicationScoped
public class UpdateShipmentOrderStatusUseCase {
    @Inject
    ShipmentOrderService shipmentOrderService;

    public void execute(int shipmentOrderId, UpdateShipmentOrderStateReqDTO updateShipmentOrderStatusReqDTO) {
        shipmentOrderService.updateShipmentOrderStatus(shipmentOrderId, updateShipmentOrderStatusReqDTO.getState());
    }
}
