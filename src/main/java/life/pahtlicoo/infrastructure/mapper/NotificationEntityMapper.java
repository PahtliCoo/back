package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Notification;
import life.pahtlicoo.infrastructure.entity.NotificationEntity;

@ApplicationScoped
public class NotificationEntityMapper {
    public Notification toDomain(NotificationEntity notificationEntity){
        return new Notification(notificationEntity.getNotificationId(), notificationEntity.getSeen(),
                notificationEntity.getDescription(), notificationEntity.getSenderId(),
                notificationEntity.getReceiverId(), notificationEntity.getRequestId(), notificationEntity.getCreatedAt(),
                notificationEntity.getUpdatedAt());
    }

    public NotificationEntity toEntity(Notification notification){
        return new NotificationEntity(notification.getNotificationId(), notification.getSeen(),
                notification.getDescription(), notification.getSenderId(), notification.getReceiverId(),
                notification.getRequestId(), notification.getCreatedAt(), notification.getUpdatedAt());
    }
}
