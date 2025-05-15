package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.ShipmentOrder;

public interface ShipmentOrderRepository {
    public void createShipmentOrder(ShipmentOrder shipmentOrder);
    public ShipmentOrder getShipmentOrder(int shipmentOrderId);
    public void updateShipmentOrderStatus(int shipmentOrderId, String status);
    public void deleteShipmentOrder(int shipmentOrderId);
}
