/**
 * Creates a notification from a created request
 * @author Luis Enrique Salazar Perez
 * @since 2025-06-08
 */
package life.pahtlicoo.application.usecase.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.mapper.NotificationDomainMapper;
import life.pahtlicoo.application.service.NotificationService;
import life.pahtlicoo.application.service.SiteService;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.Notification;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.Site;
import life.pahtlicoo.domain.model.SysUser;

@ApplicationScoped
public class CreateNotificationFromRequestUseCase {
    @Inject
    NotificationDomainMapper notificationDomainMapper;

    @Inject
    SysUserService sysUserService;

    @Inject
    SiteService siteService;

    @Inject
    NotificationService notificationService;

    public boolean execute(Request request) {
        try {
            SysUser sysUser = sysUserService.getSysUserByUserId(request.getSysUserId());

            Site site = siteService.findSite(sysUser.getSiteId());

            Notification notification = notificationDomainMapper.requestToNotification(request, site.getName());

            return notificationService.createNotification(notification);
        } catch (Exception e){
            return false;
        }
    }
}
