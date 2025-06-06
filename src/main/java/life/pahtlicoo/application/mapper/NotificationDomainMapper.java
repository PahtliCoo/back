package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Notification;
import life.pahtlicoo.domain.model.Request;

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
}
