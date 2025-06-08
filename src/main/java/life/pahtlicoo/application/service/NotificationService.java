package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.notification.GetNotificationReqDTO;
import life.pahtlicoo.application.dto.notification.GetNotificationsSeenStatusReqDTO;
import life.pahtlicoo.domain.model.Notification;
import life.pahtlicoo.domain.repository.NotificationRepository;
import life.pahtlicoo.infrastructure.websocket.UpdatesSocket;

import java.util.List;

@ApplicationScoped
public class NotificationService {
    @Inject
    NotificationRepository notificationRepository;

    @Inject
    UpdatesSocket updatesSocket;

    public boolean createNotification(Notification notification) {
        updatesSocket.sendMessage(notification.getReceiverId(), notification.getRequestPhase(), notification.getDescription());

        return notificationRepository.createNotification(notification);
    }

    public List<GetNotificationReqDTO> getAllNotificationsByReceiverId(int receiverId, String orderBy) {
        return notificationRepository.getAllNotificationsByReceiverId(receiverId, orderBy);
    }

    public GetNotificationsSeenStatusReqDTO getNotificationsSeenStatus(int receiverId) {
        return notificationRepository.getNotificationsSeenStatus(receiverId);
    }

    public void updateNotificationStatus(int notificationId, boolean seen) {
        notificationRepository.updateNotificationStatus(notificationId, seen);
    }
}
