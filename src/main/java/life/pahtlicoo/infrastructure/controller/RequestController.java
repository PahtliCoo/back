package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.request.*;
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
    @Inject
    GetAllRequestByUserIdAndStateUseCase getAllRequestByUserIdAndStateUseCase;
    @Inject
    GetRequestByUserIdAndStateIdAndDate getRequestByUserIdAndStateIdAndDateUseCase;


    @POST
    @Path("/create")
    public Response createRequest(@Valid CreateRequestReqDTO createRequestReqDTO){
        try{
            if(createRequestUseCase.execute(createRequestReqDTO)){
                return Response.status(Response.Status.CREATED).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

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
    @GET
    @Path("/state/{user_id}/{state}/{page}")
    public Response getRequestByUserIdAndState(@PathParam("user_id") int userId,@PathParam("state") int state, @PathParam("page") int page ){
        try{
            List<Request> request = getAllRequestByUserIdAndStateUseCase.execute(userId,state,page);
            if(request == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(request).build();

        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/date/{user_id}/{state}/{year}/{month}/{day}/{page}")
    public Response getRequestByUserIdAndStateAndDate(@PathParam("user_id") int userId,@PathParam("state") int state,
                                                      @PathParam("year") int year, @PathParam("month") int month,
                                                      @PathParam("day") int day, @PathParam("page") int page){
        try{
            List<Request> requestList = getRequestByUserIdAndStateIdAndDateUseCase.execute(userId,state,year,month,day,page );
            if(requestList == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(requestList).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }




}
