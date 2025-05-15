package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.notification.CreateNotificationReqDTO;
import life.pahtlicoo.application.dto.notification.UpdateNotificationStatusReqDTO;
import life.pahtlicoo.application.usecase.notification.*;
import life.pahtlicoo.domain.model.Notification;

import java.util.List;

@Path("/notification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationController {
    @Inject
    CreateNotificationUseCase createNotificationUseCase;
    @Inject
    GetNotificationUseCase getNotificationUseCase;
    @Inject
    GetAllNotificationsByReceiverIdUseCase getAllNotificationsByReceiverIdUseCase;
    @Inject
    UpdateNotificationStatusUseCase updateNotificationStatusUseCase;
    @Inject
    DeleteNotificationUseCase deleteNotificationUseCase;

    @POST
    @Path("/create")
    public Response createNotification(CreateNotificationReqDTO createNotificationReqDTO){
        try{
            createNotificationUseCase.execute(createNotificationReqDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{notification_id}")
    public Response getNotification(@PathParam("notification_id") int notificationId){
        Notification notification = getNotificationUseCase.execute(notificationId);
        if (notification == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(notification).build();
    }

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
    public Response updateNotificationStatus(@PathParam("notification_id") int notificationId, UpdateNotificationStatusReqDTO updateNotificationStatusReqDTO){
        updateNotificationStatusUseCase.execute(notificationId, updateNotificationStatusReqDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{notification_id}")
    public Response deleteNotification(@PathParam("notification_id") int notificationId){
        deleteNotificationUseCase.execute(notificationId);
        return Response.ok().build();
    }
}
