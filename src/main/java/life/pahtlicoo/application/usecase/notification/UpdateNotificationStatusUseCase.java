package life.pahtlicoo.application.usecase.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.notification.UpdateNotificationSeenReqDTO;
import life.pahtlicoo.application.service.NotificationService;

@ApplicationScoped
public class UpdateNotificationStatusUseCase {
    @Inject
    NotificationService notificationService;

    public void execute(int notificationId, UpdateNotificationSeenReqDTO updateNotificationSeenReqDTO) {
        notificationService.updateNotificationStatus(notificationId, updateNotificationSeenReqDTO.isSeen());
    }
}
