package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.Notification;
import life.pahtlicoo.domain.repository.NotificationRepository;

import java.util.List;

@ApplicationScoped
public class NotificationService {
    @Inject
    NotificationRepository notificationRepository;

    public boolean createNotification(Notification notification) {
        return notificationRepository.createNotification(notification);
    }

    public List<Notification> getAllNotificationsByReceiverId(int receiverId) {
        return notificationRepository.getAllNotificationsByReceiverId(receiverId);
    }

    public void updateNotificationStatus(int notificationId, boolean seen) {
        notificationRepository.updateNotificationStatus(notificationId, seen);
    }
}
