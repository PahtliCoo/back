package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.notification.CreateNotificationReqDTO;
import life.pahtlicoo.domain.model.Notification;

@ApplicationScoped
public class NotificationDomainMapper {
    public Notification createNotificationToDomain(CreateNotificationReqDTO createNotificationReqDTO){
        Notification notification = new Notification();
        notification.setSeen(createNotificationReqDTO.isSeen());
        notification.setDescription(createNotificationReqDTO.getDescription());
        notification.setSenderId(createNotificationReqDTO.getSender_id());
        notification.setReceiverId(createNotificationReqDTO.getReceiver_id());
        notification.setRequestId(createNotificationReqDTO.getRequest_id());
        return notification;
    }
}
