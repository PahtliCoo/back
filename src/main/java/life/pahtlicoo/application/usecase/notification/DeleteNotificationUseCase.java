package life.pahtlicoo.application.usecase.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.NotificationService;

@ApplicationScoped
public class DeleteNotificationUseCase {
    @Inject
    NotificationService notificationService;

    public void execute(int notificationId) {
        notificationService.deleteNotification(notificationId);
    }
}
