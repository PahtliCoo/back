package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.ShipmentOrder;
import life.pahtlicoo.domain.repository.ShipmentOrderRepository;

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

    public void updateShipmentOrderStatus(int shipmentOrderId, String status) {
        shipmentOrderRepository.updateShipmentOrderStatus(shipmentOrderId, status);
    }

    public void deleteShipmentOrder(int shipmentOrderId) {
        shipmentOrderRepository.deleteShipmentOrder(shipmentOrderId);
    }
}
