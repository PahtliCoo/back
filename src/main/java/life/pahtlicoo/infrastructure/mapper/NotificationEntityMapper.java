/**
 * Notification Entity Mapper
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @Co-author Nicole Kapellmann lepine (a01664563@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Notification;
import life.pahtlicoo.infrastructure.entity.NotificationEntity;

@ApplicationScoped
public class NotificationEntityMapper {
    public Notification toDomain(NotificationEntity notificationEntity){
        return new Notification(notificationEntity.getNotificationId(), notificationEntity.isSeen(),
                notificationEntity.getDescription(), notificationEntity.getSenderId(),
                notificationEntity.getReceiverId(), notificationEntity.getRequestId(), notificationEntity.getCreatedAt(),
                notificationEntity.getUpdatedAt());
    }

    public NotificationEntity toEntity(Notification notification){
        return new NotificationEntity(notification.getNotificationId(), notification.isSeen(),
                notification.getDescription(), notification.getSenderId(), notification.getReceiverId(),
                notification.getRequestId(), notification.getCreatedAt(), notification.getUpdatedAt());
    }
}
