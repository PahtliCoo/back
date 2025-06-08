package life.pahtlicoo.application.usecase.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.notification.GetNotificationsSeenStatusReqDTO;
import life.pahtlicoo.application.service.NotificationService;

@ApplicationScoped
public class GetSeenNotificationsStatusUseCase {
    @Inject
    NotificationService notificationService;

    public GetNotificationsSeenStatusReqDTO execute(int receiverId) {
        return notificationService.getNotificationsSeenStatus(receiverId);
    }
}
