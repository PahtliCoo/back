package life.pahtlicoo.application.usecase.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.notification.GetNotificationReqDTO;
import life.pahtlicoo.application.service.NotificationService;
import life.pahtlicoo.domain.model.Notification;

import java.util.List;

@ApplicationScoped
public class GetAllNotificationsByReceiverIdUseCase {
    @Inject
    NotificationService notificationService;

    public List<GetNotificationReqDTO> execute(int receiverId, String orderBy) {
        return notificationService.getAllNotificationsByReceiverId(receiverId, orderBy);
    }
}
