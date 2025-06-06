package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Notification;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;

@ApplicationScoped
public class NotificationDomainMapper {
    public Notification requestToNotification(Request request, String siteName) {
        Notification notification = new Notification();
        notification.setDescription(siteName + " creó un pedido");
        notification.setSenderId(request.getSysUserId());
        notification.setReceiverId(2);
        notification.setRequestPhase(1);
        notification.setRequestId(request.getRequestId());
        return notification;
    }

    public Notification shipmentOrderToNotification(ShipmentOrder shipmentOrder) {
        Notification notification = new Notification();
        notification.setDescription("administrador creó una orden de envío");
        notification.setSenderId(2);
        notification.setReceiverId(3);
        notification.setRequestPhase(2);
        notification.setRequestId(shipmentOrder.getRequestId());
        return notification;
    }
}
