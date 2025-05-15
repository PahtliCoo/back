package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.request.CreateRequestReqDTO;
import life.pahtlicoo.application.dto.request.UpdateRequestStatusReqDTO;
import life.pahtlicoo.application.usecase.request.*;
import life.pahtlicoo.domain.model.Request;

import java.util.List;

@Path("/request")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestController {
    @Inject
    CreateRequestUseCase createRequestUseCase;
    @Inject
    GetRequestUseCase getRequestUseCase;
    @Inject
    GetAllRequestsByUserIdUseCase getAllRequestsByUserIdUseCase;
    @Inject
    UpdateRequestStatusUseCase updateRequestStatusUseCase;
    @Inject
    DeleteRequestUseCase deleteRequestUseCase;

    @POST
    @Path("/create")
    public Response createRequest(CreateRequestReqDTO createRequestReqDTO){
        try{
            createRequestUseCase.execute(createRequestReqDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{request_id}")
    public Response getRequest(@PathParam("request_id") int requestId){
        Request request = getRequestUseCase.execute(requestId);
        if(request == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(request).build();
    }

    @GET
    @Path("/user/{user_id}")
    public Response getRequestsByUserId(@PathParam("user_id") int user_id){
        List<Request> requests = getAllRequestsByUserIdUseCase.execute(user_id);
        if(requests == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(requests).build();
    }

    @PATCH
    @Path("/{request_id}")
    public Response updateRequestStatus(@PathParam("request_id") int requestId, UpdateRequestStatusReqDTO updateRequestStatusReqDTO){
        updateRequestStatusUseCase.execute(requestId, updateRequestStatusReqDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{request_id}")
    public Response deleteRequest(@PathParam("request_id") int requestId){
        deleteRequestUseCase.execute(requestId);
        return Response.ok().build();
    }
}
