package life.pahtlicoo.application.usecase.shipmentorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.shipmentorder.CreateShipmentOrderReqDTO;
import life.pahtlicoo.application.mapper.NotificationDomainMapper;
import life.pahtlicoo.application.mapper.ShipmentOrderDomainMapper;
import life.pahtlicoo.application.service.NotificationService;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.application.service.ShipmentOrderService;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.Notification;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;

@ApplicationScoped
public class CreateShipmentOrderUseCase {
    @Inject
    ShipmentOrderService shipmentOrderService;

    @Inject
    ShipmentOrderDomainMapper shipmentOrderDomainMapper;

    @Inject
    NotificationService notificationService;

    @Inject
    NotificationDomainMapper notificationDomainMapper;

    @Inject
    SysUserService sysUserService;

    public boolean execute(CreateShipmentOrderReqDTO createShipmentOrderReqDTO) {
        // TO Check if there is already a shipment order Created
        if(shipmentOrderService.getShipmentOrderByRequestId(createShipmentOrderReqDTO.getRequest_id()) != null){
            return false;
        }

        ShipmentOrder shipmentOrder = shipmentOrderDomainMapper.createShipmentOrderToDomain(createShipmentOrderReqDTO);
        shipmentOrderService.createShipmentOrder(shipmentOrder);

        int logisticsSenderId = sysUserService.getSysUserByCredentialId(2);
        int warehouseReceiverId = sysUserService.getSysUserByCredentialId(3);

        Notification notification = notificationDomainMapper.shipmentOrderToNotification(shipmentOrder, logisticsSenderId, warehouseReceiverId);
        notificationService.createNotification(notification);
        return true;
    }

}
