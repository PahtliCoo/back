/**
 * Notification Repository
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Luis Enrique Salazar Perez
 * @since 2025-06-08
 */
package life.pahtlicoo.domain.repository;

import life.pahtlicoo.application.dto.notification.GetReceiverNotificationsResDTO;
import life.pahtlicoo.application.dto.notification.GetNotificationsSeenStatusResDTO;
import life.pahtlicoo.domain.model.Notification;

import java.util.List;

public interface NotificationRepository {
    public boolean createNotification(Notification notification);
    public List<GetReceiverNotificationsResDTO> getAllNotificationsByReceiverId(int receiverId, String orderBy);
    public GetNotificationsSeenStatusResDTO getNotificationsSeenStatus(int receiverId);
    public void updateNotificationStatus(int notificationId, boolean seen);
}
