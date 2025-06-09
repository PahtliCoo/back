/**
 * Notification Controller
 * @co-author Luis Enrique Salazar Perez
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-06-08
 */

package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.notification.GetReceiverNotificationsResDTO;
import life.pahtlicoo.application.dto.notification.GetNotificationsSeenStatusResDTO;
import life.pahtlicoo.application.dto.notification.UpdateNotificationSeenReqDTO;
import life.pahtlicoo.application.usecase.notification.*;

import java.util.List;

@Path("/notification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationController {
    @Inject
    GetAllNotificationsByReceiverIdUseCase getAllNotificationsByReceiverIdUseCase;
    @Inject
    UpdateNotificationStatusUseCase updateNotificationStatusUseCase;
    @Inject
    GetSeenNotificationsStatusUseCase getSeenNotificationsStatusUseCase;

    @GET
    @Path("/receiver/all/{receiver_id}")
    public Response getAllNotificationsByReceiverId(
            @PathParam("receiver_id") int receiverId,
            @QueryParam("order_by") @DefaultValue("desc") String orderBy) {

        if (!orderBy.equalsIgnoreCase("asc") && !orderBy.equalsIgnoreCase("desc")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid order_by parameter. Use 'asc' or 'desc'.")
                    .build();
        }

        List<GetReceiverNotificationsResDTO> notifications = getAllNotificationsByReceiverIdUseCase
                .execute(receiverId, orderBy.toLowerCase());

        if (notifications == null || notifications.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No notifications found for receiver ID " + receiverId)
                    .build();
        }

        return Response.ok(notifications).build();
    }

    @GET
    @Path("/receiver/{receiver_id}")
    public Response getSeenNotificationsStatus(@PathParam("receiver_id") int receiverId) {
        GetNotificationsSeenStatusResDTO seenStatus = getSeenNotificationsStatusUseCase.execute(receiverId);
        return Response.ok(seenStatus).build();
    }

    @PATCH
    @Path("/{notification_id}")
    public Response updateNotificationSeen(@PathParam("notification_id") int notificationId, UpdateNotificationSeenReqDTO updateNotificationSeenReqDTO){
        updateNotificationStatusUseCase.execute(notificationId, updateNotificationSeenReqDTO);
        return Response.ok().build();
    }
}
