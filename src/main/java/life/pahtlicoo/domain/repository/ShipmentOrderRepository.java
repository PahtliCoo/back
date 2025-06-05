package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;

import java.util.List;

public interface ShipmentOrderRepository {
    public void createShipmentOrder(ShipmentOrder shipmentOrder);
    public ShipmentOrder getShipmentOrder(int shipmentOrderId);
    public void updateShipmentOrderStatus(int shipmentOrderId, int state);
    public void deleteShipmentOrder(int shipmentOrderId);
    public List<ShipmentOrder> getAllShipmentOrder(int page);

    /*
    List<ShipmentOrder> getAllShipmentOrdersByUserIdByStateAndDate(int sysUserId, int state, int year, int month, int day, int page);
    List<ShipmentOrder> getAllShipmentOrdersByUserIdByState(int userId, int state, int page);
    List<ShipmentOrder> getAllShipmentOrdersByUserIdByDate(int userId, int year, int month, int day, int page);
     */
    List<ShipmentOrder> getAllShipmentOrdersByDate(int year, int month, int day, int page);
    List<ShipmentOrder> getAllShipmentOrdersByDateByState(int state, int year, int month, int day, int page);
    List<ShipmentOrder> getAllShipmentOrdersByState(int state, int page);
    List<ShipmentOrder> getAllShipmentOrdersBySearch(String search, int page);
}
