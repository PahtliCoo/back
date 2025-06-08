package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.notification.UpdateNotificationSeenReqDTO;
import life.pahtlicoo.application.usecase.notification.*;
import life.pahtlicoo.domain.model.Notification;

import java.util.List;

@Path("/notification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationController {
    @Inject
    GetAllNotificationsByReceiverIdUseCase getAllNotificationsByReceiverIdUseCase;
    @Inject
    UpdateNotificationStatusUseCase updateNotificationStatusUseCase;

    @GET
    @Path("/receiver/{receiver_id}")
    public Response getAllNotificationsByReceiverId(@PathParam("receiver_id") int receiverId){
        List<Notification> notifications = getAllNotificationsByReceiverIdUseCase.execute(receiverId);
        if(notifications == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(notifications).build();
    }

    @PATCH
    @Path("/{notification_id}")
    public Response updateNotificationStatus(@PathParam("notification_id") int notificationId, UpdateNotificationSeenReqDTO updateNotificationSeenReqDTO){
        updateNotificationStatusUseCase.execute(notificationId, updateNotificationSeenReqDTO);
        return Response.ok().build();
    }
}
