/**
 * Retrieve all notifications from a recipient
 * @author Luis Enrique Salazar Perez
 * @since 2025-06-08
 */
package life.pahtlicoo.application.usecase.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.notification.GetReceiverNotificationsResDTO;
import life.pahtlicoo.application.service.NotificationService;

import java.util.List;

@ApplicationScoped
public class GetAllNotificationsByReceiverIdUseCase {
    @Inject
    NotificationService notificationService;

    public List<GetReceiverNotificationsResDTO> execute(int receiverId, String orderBy) {
        return notificationService.getAllNotificationsByReceiverId(receiverId, orderBy);
    }
}
