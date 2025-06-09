/**
 * Shipment Order service
 * @author Nicole Kapellman Lepíne
 * @co-author Adolfo Hernandez Fernandez
 * @co-author Santiago Moreno Lacalle Quintero
 * @Since: 2025-06-08
 */
package life.pahtlicoo.application.service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.shipmentorder.SearchShipmentOrdersReqDTO;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.RequestDetail;
import life.pahtlicoo.domain.model.ShipmentOrder;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.domain.repository.*;

import java.util.List;

@ApplicationScoped
public class ShipmentOrderService {
    @Inject
    ShipmentOrderRepository shipmentOrderRepository;
    @Inject
    RequestRepository requestRepository;
    @Inject
    RequestDetailRepository requestDetailRepository;
    @Inject
    SysUserRepository sysUserRepository;
    @Inject
    MedSiteRepository medSiteRepository;

    public void createShipmentOrder(ShipmentOrder shipmentOrder) {
        shipmentOrderRepository.createShipmentOrder(shipmentOrder);
    }

    public ShipmentOrder getShipmentOrderById(int shipmentOrderId) {
        return shipmentOrderRepository.getShipmentOrder(shipmentOrderId);
    }

    public void updateShipmentOrderStatus(int shipmentOrderId, int state) {
        ShipmentOrder shipmentOrder = shipmentOrderRepository.getShipmentOrder(shipmentOrderId);

        // Validar si ya tiene el estado deseado
        if (shipmentOrder == null) {
            throw new IllegalArgumentException("Shipment order no encontrada con ID: " + shipmentOrderId);
        }

        if (shipmentOrder.getState() == state) {
            return;
        }

        if (shipmentOrder.getState() == 3 || shipmentOrder.getState() == 4) {
            throw new IllegalArgumentException("La shipment order ya no es modificable");
        }

        shipmentOrderRepository.updateShipmentOrderStatus(shipmentOrderId, state);

        if (state == 3){
            Request request = requestRepository.getRequest(shipmentOrder.getRequestId());
            List<RequestDetail> requestDetails = requestDetailRepository.getRequestDetailsByRequestId(
                    shipmentOrder.getRequestId());
            SysUser sysUser = sysUserRepository.getSysUser(request.getSysUserId());
            int siteId = sysUser.getSiteId();

            // Por cada detalle de la request, añadir al inventario
            for (RequestDetail detail : requestDetails) {
                int medId = detail.getMedId();
                int quantity = detail.getQuantity();
                medSiteRepository.registerNewMedSiteAddition(medId, siteId, quantity);
            }
        }
    }

    public void deleteShipmentOrder(int shipmentOrderId) {
        shipmentOrderRepository.deleteShipmentOrder(shipmentOrderId);
    }

    public List<ShipmentOrder> getAllShipmentOrder(int page){
        return shipmentOrderRepository.getAllShipmentOrder(page);
    }

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
    public void updateShipmentOrderDetails(int shipmentOrderId, String shipmentOrderDetails){
        shipmentOrderRepository.updateShipmentOrderDetails(shipmentOrderId, shipmentOrderDetails);
    }
    public ShipmentOrder getShipmentOrderByRequestId(int requestId){
        return shipmentOrderRepository.getShipmentOrderByRequestId(requestId);
    }
}

