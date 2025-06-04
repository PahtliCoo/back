/**
 Use case para crear usuario
 @author Adolfo Hernández Fernández (a01664412@tec.mx)
 @Since: 2025-05-13
 */
package life.pahtlicoo.application.service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;
import life.pahtlicoo.domain.repository.ShipmentOrderRepository;

import java.util.List;

@ApplicationScoped
public class ShipmentOrderService {
    @Inject
    ShipmentOrderRepository shipmentOrderRepository;

    public void createShipmentOrder(ShipmentOrder shipmentOrder) {
        shipmentOrderRepository.createShipmentOrder(shipmentOrder);
    }

    public ShipmentOrder getShipmentOrderById(int shipmentOrderId) {
        return shipmentOrderRepository.getShipmentOrder(shipmentOrderId);
    }

    public void updateShipmentOrderStatus(int shipmentOrderId, int state) {
        shipmentOrderRepository.updateShipmentOrderStatus(shipmentOrderId, state);
    }

    public void deleteShipmentOrder(int shipmentOrderId) {
        shipmentOrderRepository.deleteShipmentOrder(shipmentOrderId);
    }

    public List<ShipmentOrder> getAllShipmentOrder(){
        return shipmentOrderRepository.getAllShipmentOrder();
    }

}
