/**
 Use case para crear usuario
 @author Adolfo Hernández Fernández (a01664412@tec.mx)
 @Since: 2025-05-13
 */
package life.pahtlicoo.application.service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.SearchUserRequestsReqDTO;
import life.pahtlicoo.application.dto.shipmentorder.SearchShipmentOrdersReqDTO;
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

    public List<ShipmentOrder> getAllShipmentOrder(int page){
        return shipmentOrderRepository.getAllShipmentOrder(page);
    }

    /*
    public List<ShipmentOrder> getAllShipmentOrdersByUserIdByState(int userId, int state,int page){
        return shipmentOrderRepository.getAllShipmentOrdersByUserIdByState(userId, state,page);
    }

    public List<ShipmentOrder> getAllShipmentOrdersByUserIdByStateAndDate(int sysUserId, int state, int year, int month, int day,int page){
        return shipmentOrderRepository.getAllShipmentOrdersByUserIdByStateAndDate(sysUserId, state, year, month, day,page);
    }

    public List<ShipmentOrder> getAllShipmentOrdersByUserIdByDate(int userId, int year, int month, int day,int page){
        return shipmentOrderRepository.getAllShipmentOrdersByUserIdByDate(userId,year,month,day, page);
    }
    */

    public List<ShipmentOrder> getAllShipmentOrdersByDate(int year, int month, int day,int page){
        return shipmentOrderRepository.getAllShipmentOrdersByDate(year,month,day,page);
    }
    public List<ShipmentOrder> getAllShipmentOrdersByDateByState(int state,int year, int month, int day,int page){
        return shipmentOrderRepository.getAllShipmentOrdersByDateByState(state,year,month,day,page);
    }
    public List<ShipmentOrder> getAllShipmentOrdersByState(int state,int page){
        return shipmentOrderRepository.getAllShipmentOrdersByState(state,page);
    }
    public List<ShipmentOrder> getAllShipmentOrdersBySearch(String search, int page){
        return shipmentOrderRepository.getAllShipmentOrdersBySearch(search,page);
    }

    public List<ShipmentOrder> searchShipmentOrders(SearchShipmentOrdersReqDTO searchShipmentOrdersReqDTO){
        return shipmentOrderRepository.searchShipmentOrders(searchShipmentOrdersReqDTO);
    }
}

