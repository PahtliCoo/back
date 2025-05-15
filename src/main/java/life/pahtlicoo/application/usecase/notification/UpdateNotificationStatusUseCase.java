package life.pahtlicoo.application.usecase.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.notification.UpdateNotificationStatusReqDTO;
import life.pahtlicoo.application.service.NotificationService;

@ApplicationScoped
public class UpdateNotificationStatusUseCase {
    @Inject
    NotificationService notificationService;

    public void execute(int notificationId, UpdateNotificationStatusReqDTO updateNotificationStatusReqDTO) {
        notificationService.updateNotificationStatus(notificationId, updateNotificationStatusReqDTO.getStatus());
    }
}
