package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    public void createNotification(Notification notification) {
        NotificationEntity notificationEntity = notificationEntityMapper.toEntity(notification);
        persist(notificationEntity);
        notification.setNotificationId(notification.getNotificationId());
    }

    @Override
    public Notification getNotification(int notificationId){
        NotificationEntity notificationEntity = findById(notificationId);
        if(notificationEntity == null){
            return null;
        }
        return notificationEntityMapper.toDomain(notificationEntity);
    }

    @Override
    public List<Notification> getAllNotificationsByReceiverId(int receiverId){
        List<NotificationEntity> notificationEntities = find("receiverId", receiverId).list();
        return notificationEntities.stream()
                .map(notificationEntityMapper::toDomain)
                .toList();
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

    @Override
    @Transactional
    public void deleteNotification(int notificationId){
        deleteById(notificationId);
    }
}
