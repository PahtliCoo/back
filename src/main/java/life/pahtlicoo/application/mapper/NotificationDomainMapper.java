/**
 * Mapper notification from request and shipment orders
 * @author Luis Enrique Salazar Perez
 * @since 2025-06-08
 */
package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Notification;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;

@ApplicationScoped
public class NotificationDomainMapper {
    public Notification requestToNotification(Request request, String siteName, int receiverId) {
        Notification notification = new Notification();

        String capitalizedSiteName = siteName == null || siteName.isBlank()
                ? ""
                : siteName.substring(0, 1).toUpperCase() + siteName.substring(1);

        notification.setDescription(capitalizedSiteName + " creó un pedido");
        notification.setSenderId(request.getSysUserId());
        notification.setReceiverId(receiverId);
        notification.setRequestPhase(1);
        notification.setRequestId(request.getRequestId());
        return notification;
    }

    public Notification shipmentOrderToNotification(ShipmentOrder shipmentOrder, int senderId, int receiverId) {
        Notification notification = new Notification();
        notification.setDescription("administrador creó una orden de envío");
        notification.setSenderId(senderId);
        notification.setReceiverId(receiverId);
        notification.setRequestPhase(2);
        notification.setRequestId(shipmentOrder.getRequestId());
        return notification;
    }
}
