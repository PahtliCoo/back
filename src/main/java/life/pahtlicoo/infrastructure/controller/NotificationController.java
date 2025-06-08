package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.notification.GetNotificationReqDTO;
import life.pahtlicoo.application.dto.notification.GetNotificationsSeenStatusReqDTO;
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
    @Path("/receiver/{receiver_id}/{order_by}")
    public Response getAllNotificationsByReceiverId(
            @PathParam("receiver_id") int receiverId,
            @PathParam("order_by") String orderBy) {

        // Optional: validate orderBy to only accept expected values
        if (!orderBy.equalsIgnoreCase("asc") && !orderBy.equalsIgnoreCase("desc")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid order_by parameter. Use 'asc' or 'desc'.")
                    .build();
        }

        List<GetNotificationReqDTO> notifications = getAllNotificationsByReceiverIdUseCase
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
        GetNotificationsSeenStatusReqDTO seenStatus = getSeenNotificationsStatusUseCase.execute(receiverId);
        return Response.ok(seenStatus).build();
    }

    @PATCH
    @Path("/{notification_id}")
    public Response updateNotificationSeen(@PathParam("notification_id") int notificationId, UpdateNotificationSeenReqDTO updateNotificationSeenReqDTO){
        updateNotificationStatusUseCase.execute(notificationId, updateNotificationSeenReqDTO);
        return Response.ok().build();
    }
}
