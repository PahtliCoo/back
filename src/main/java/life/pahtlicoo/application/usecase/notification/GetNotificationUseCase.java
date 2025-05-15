package life.pahtlicoo.application.usecase.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.NotificationService;
import life.pahtlicoo.domain.model.Notification;

@ApplicationScoped
public class GetNotificationUseCase {
    @Inject
    NotificationService notificationService;

    public Notification execute(int notificationId) {
        return notificationService.getNotification(notificationId);
    }
}
