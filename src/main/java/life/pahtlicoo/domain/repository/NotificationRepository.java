package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Notification;

import java.util.List;

public interface NotificationRepository {
    public void createNotification(Notification notification);
    public Notification getNotification(String notificationId);
    public List<Notification> getAllNotifications();
    public void updateNotificationStatus(int notificationId, String status);
    public void deleteNotification(int notificationId);
}
