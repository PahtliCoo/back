/**
 * Retrieve recipient notifications marked as seen
 * @author Luis Enrique Salazar Perez
 * @since 2025-06-08
 */
package life.pahtlicoo.application.usecase.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.notification.GetNotificationsSeenStatusResDTO;
import life.pahtlicoo.application.service.NotificationService;

@ApplicationScoped
public class GetSeenNotificationsStatusUseCase {
    @Inject
    NotificationService notificationService;

    public GetNotificationsSeenStatusResDTO execute(int receiverId) {
        return notificationService.getNotificationsSeenStatus(receiverId);
    }
}
