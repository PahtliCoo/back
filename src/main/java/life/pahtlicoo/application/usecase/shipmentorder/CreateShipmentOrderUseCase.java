package life.pahtlicoo.application.usecase.shipmentorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.shipmentorder.CreateShipmentOrderReqDTO;
import life.pahtlicoo.application.mapper.NotificationDomainMapper;
import life.pahtlicoo.application.mapper.ShipmentOrderDomainMapper;
import life.pahtlicoo.application.service.NotificationService;
import life.pahtlicoo.application.service.ShipmentOrderService;
import life.pahtlicoo.domain.model.Notification;
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

    public void execute(CreateShipmentOrderReqDTO createShipmentOrderReqDTO) {
        ShipmentOrder shipmentOrder = shipmentOrderDomainMapper.createShipmentOrderToDomain(createShipmentOrderReqDTO);
        shipmentOrderService.createShipmentOrder(shipmentOrder);

        Notification notification = notificationDomainMapper.shipmentOrderToNotification(shipmentOrder);
        notificationService.createNotification(notification);
    }

}
