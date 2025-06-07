package life.pahtlicoo.application.usecase.shipmentorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.shipmentorder.UpdateShipmentOrderFormatReqDTO;
import life.pahtlicoo.application.service.ShipmentOrderService;

@ApplicationScoped
@Transactional
public class UpdateShipmentOrderFormat {
    @Inject
    ShipmentOrderService shipmentOrderService;

    public boolean execute(int shipment_order_id, UpdateShipmentOrderFormatReqDTO updateShipmentOrderFormatReqDTO) {
        try {
            if(updateShipmentOrderFormatReqDTO.getState() != null){
                shipmentOrderService.updateShipmentOrderStatus(shipment_order_id, updateShipmentOrderFormatReqDTO.getState());
            }
            if(updateShipmentOrderFormatReqDTO.getDescription() != null){
                shipmentOrderService.updateShipmentOrderDetails(shipment_order_id, updateShipmentOrderFormatReqDTO.getDescription());
            }
            return true;

        }catch (Exception e) {
            return false;
        }
    }
}
