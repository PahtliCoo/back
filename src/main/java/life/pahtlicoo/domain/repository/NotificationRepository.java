package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Notification;

import java.util.List;

public interface NotificationRepository {
    public void createNotification(Notification notification);
    public Notification getNotification(int notificationId);
    public List<Notification> getAllNotificationsByReceiverId(int receiverId);
    public void updateNotificationStatus(int notificationId, int seen);
    public void deleteNotification(int notificationId);
}
