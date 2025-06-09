package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.notification.GetReceiverNotificationsResDTO;
import life.pahtlicoo.application.dto.notification.GetNotificationsSeenStatusResDTO;
import life.pahtlicoo.domain.model.Notification;
import life.pahtlicoo.domain.repository.NotificationRepository;
import life.pahtlicoo.infrastructure.entity.NotificationEntity;
import life.pahtlicoo.infrastructure.mapper.NotificationEntityMapper;

import java.util.List;

@ApplicationScoped
public class NotificationRepositoryImpl implements NotificationRepository, PanacheRepositoryBase<NotificationEntity, Integer> {
    @Inject
    NotificationEntityMapper notificationEntityMapper;

    @Override
    @Transactional
    public boolean createNotification(Notification notification) {
        NotificationEntity notificationEntity = notificationEntityMapper.toEntity(notification);
        persist(notificationEntity);
        if (!notificationEntity.isPersistent()) {
            return false;
        }
        notification.setNotificationId(notification.getNotificationId());
        return true;
    }

    @Override
    @Transactional
    public List<GetReceiverNotificationsResDTO> getAllNotificationsByReceiverId(int receiverId, String orderBy) {
        Sort sort = "asc".equalsIgnoreCase(orderBy)
                ? Sort.ascending("updatedAt")
                : Sort.descending("updatedAt");

        List<NotificationEntity> notificationEntities = find("receiverId = ?1", sort, receiverId).list();

        return notificationEntities.stream()
                .map(e -> new GetReceiverNotificationsResDTO(e.getNotificationId(), e.isSeen(), e.getDescription(), e.getUpdatedAt()))
                .toList();
    }

    @Override
    @Transactional
    public GetNotificationsSeenStatusResDTO getNotificationsSeenStatus(int receiverId) {
        boolean anySeen = find("receiverId = ?1 AND seen = true", receiverId).firstResultOptional().isPresent();
        return new GetNotificationsSeenStatusResDTO(anySeen);
    }

    @Override
    @Transactional
    public void updateNotificationStatus(int notificationId, boolean seen){
        NotificationEntity notificationEntity = findById(notificationId);
        if(notificationEntity == null){
            return;
        }
        notificationEntity.setSeen(seen);
    }
}
