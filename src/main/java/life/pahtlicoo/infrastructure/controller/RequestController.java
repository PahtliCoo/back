/**
 * Request Controller.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-06-05
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.request.*;
import life.pahtlicoo.application.usecase.request.*;

import java.util.List;

@Path("/request")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestController {
    @Inject
    CreateRequestUseCase createRequestUseCase;
    @Inject
    UpdateRequestStatusUseCase updateRequestStatusUseCase;
    @Inject
    DeleteRequestUseCase deleteRequestUseCase;
    @Inject
    SearchUserRequestsUseCase searchUserRequestsUseCase;
    @Inject
    UpdateRequestFormatUseCase updateRequestFormatUseCase;

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

    @PATCH
    @Path("/{request_id}")
    public Response updateRequestStatus(@PathParam("request_id") int requestId,
                                        UpdateRequestStatusReqDTO updateRequestStatusReqDTO){
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

    @GET
    @Path("/sys-user/{sys_user_id}")
    public Response searchUserRequests(@QueryParam("name") String name, @QueryParam("page") @DefaultValue("0") int page,
                                       @QueryParam("date") String date, @QueryParam("state") Integer state,
                                       @PathParam("sys_user_id") int sysUserId) {

        if (page < 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid page number. Must be >= 0.")
                    .build();
        }

        SearchUserRequestsReqDTO searchUserRequestsReqDTO = new SearchUserRequestsReqDTO(sysUserId, name, date, state,
                page);

        try{
            List<RequestResponseDTO> requestResponseList = searchUserRequestsUseCase.execute(searchUserRequestsReqDTO);
            return Response.ok(requestResponseList).build();
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @Path("update-format/{request_id}")
    public Response updateOrderFormat(@PathParam("request_id") int request_id,UpdateRequestFormatReqDTO updateRequestFormatReqDTO){
        try {
            if(updateRequestFormatUseCase.execute(request_id, updateRequestFormatReqDTO)){
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}