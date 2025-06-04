/**
 * Request Controller.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-06-04
 */
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
    GetRequestByFilterUseCase getRequestByFilterUseCase;
    @Inject
    GetRequestBySearchUseCase getRequestBySearchUseCase;
    @Inject
    SearchUserRequestsByNameUseCase searchUserRequestsByNameUseCase;

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
       try {
           if(deleteRequestUseCase.execute(requestId)){
               return Response.status(Response.Status.NO_CONTENT).build();
           }
           return Response.status(Response.Status.NOT_FOUND).build();
       }catch (Exception e) {
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
       }
    }

    @POST
    @Path("/filter/{page}")
    public Response filterRequest(@PathParam("page") int page,GetRequestFilterReqDTO getRequestFilterReqDTO){
        try{
            List<RequestResponseDTO> requestList = getRequestByFilterUseCase.execute(page,getRequestFilterReqDTO);
            if(requestList == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(requestList).build();

        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/search/{page}")
    public Response searchRequest(@PathParam("page") int page,GetRequestSearchDTO getRequestSearchDTO){
        try{
            List<RequestResponseDTO> requestResponseList = getRequestBySearchUseCase.execute(page,getRequestSearchDTO.getSearch());
            if(requestResponseList == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(requestResponseList).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/sys_user/{sys_user_id}")
    public Response searchUserRequests(@QueryParam("name") String name, @QueryParam("page") @DefaultValue("0") int page,
                                       @PathParam("sys_user_id") int sys_user_id) {

        if (page < 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid page number. Must be >= 0.")
                    .build();
        }

        try{
            List<RequestResponseDTO> requestResponseList = searchUserRequestsByNameUseCase.execute(sys_user_id, page, name);
            return Response.ok(requestResponseList).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
