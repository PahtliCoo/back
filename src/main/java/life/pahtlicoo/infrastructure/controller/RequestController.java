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
    SearchUserRequestsByNameUseCase searchUserRequestsByNameUseCase; //TODO remove, deprecated
    @Inject
    SearchUserRequestsUseCase searchUserRequestsUseCase;

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
    @Path("/all/sys-user/{sys_user_id}")
    public Response getRequestsByUserId(@PathParam("sys_user_id") int sys_user_id){
        List<Request> requests = getAllRequestsByUserIdUseCase.execute(sys_user_id);
        if(requests == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(requests).build();
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

    //Todas las de arriba creo que si van

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
            List<RequestResponseDTO> requestResponseList = getRequestBySearchUseCase.execute(page,
                    getRequestSearchDTO.getSearch());
            if(requestResponseList == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(requestResponseList).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Estas dos de arriba me parece que no se ocupan realmente

    @GET
    @Path("/sys-user/{sys_user_id}")
    public Response searchUserRequests(@QueryParam("name") String name, @QueryParam("page") @DefaultValue("0") int page,
                                       @QueryParam("date") String date, @QueryParam("state") Integer state,
                                       @PathParam("sys_user_id") int sysUserId) {

        if (page < 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid page number. Must be >= 0.") //TODO should i keep this?
                    .build();
        }

        //TODO regex validation for date
        //TODO validation of state? que sea uno válido

        SearchUserRequestsReqDTO searchUserRequestsReqDTO = new SearchUserRequestsReqDTO(sysUserId, name, date, state,
                page);

        try{
            List<RequestResponseDTO> requestResponseList = searchUserRequestsUseCase.execute(searchUserRequestsReqDTO);
            return Response.ok(requestResponseList).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

//TODO El filtro de fecha debería correr basado en el updated at o created at? De momento lo vamos a dejar con created at pero siento que debería ser updated at, si si, cambiamos el DTO
//TODO debería hacerse que la fecha recibida sea en UTC, porque ahora mismo lo hacemos en UTC o guardarlo ya mejor todo en time zone de mexico
//Si no, nos va a pasar que se guarde con fecha 2025-04-03 y en realidad todavía era 2025-04-02