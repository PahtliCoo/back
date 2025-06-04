package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;

import java.util.List;

public interface ShipmentOrderRepository {
    public void createShipmentOrder(ShipmentOrder shipmentOrder);
    public ShipmentOrder getShipmentOrder(int shipmentOrderId);
    public void updateShipmentOrderStatus(int shipmentOrderId, int state);
    public void deleteShipmentOrder(int shipmentOrderId);
    public List<ShipmentOrder> getAllShipmentOrder();
}
