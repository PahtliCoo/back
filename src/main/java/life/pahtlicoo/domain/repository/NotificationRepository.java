package life.pahtlicoo.domain.repository;

import life.pahtlicoo.application.dto.notification.GetNotificationReqDTO;
import life.pahtlicoo.application.dto.notification.GetNotificationsSeenStatusReqDTO;
import life.pahtlicoo.domain.model.Notification;

import java.util.List;

public interface NotificationRepository {
    public boolean createNotification(Notification notification);
    public List<GetNotificationReqDTO> getAllNotificationsByReceiverId(int receiverId, String orderBy);
    public GetNotificationsSeenStatusReqDTO getNotificationsSeenStatus(int receiverId);
    public void updateNotificationStatus(int notificationId, boolean seen);
}
