package life.pahtlicoo.application.usecase.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.notification.CreateNotificationReqDTO;
import life.pahtlicoo.application.mapper.NotificationDomainMapper;
import life.pahtlicoo.application.service.NotificationService;
import life.pahtlicoo.domain.model.Notification;

@ApplicationScoped
public class CreateNotificationUseCase {
    @Inject
    NotificationService notificationService;

    @Inject
    NotificationDomainMapper notificationDomainMapper;

    public void execute(CreateNotificationReqDTO createNotificationReqDTO) {
        Notification notification = notificationDomainMapper.createNotificationToDomain(createNotificationReqDTO);
        notificationService.createNotification(notification);
    }
}
