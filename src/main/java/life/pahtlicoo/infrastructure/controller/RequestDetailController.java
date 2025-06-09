/**
 * Request Detail Controller.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-08
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.requestdetail.CreateRequestDetailReqDTO;
import life.pahtlicoo.application.dto.requestdetail.GetRequestDetailResDTO;
import life.pahtlicoo.application.usecase.requestdetail.CreateRequestDetailUseCase;
import life.pahtlicoo.application.usecase.requestdetail.DeleteAllRequestDetailUseCase;
import life.pahtlicoo.application.usecase.requestdetail.GetRequestDetailsUseCase;

import java.util.List;

@Path("/request-detail")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestDetailController {
    @Inject
    CreateRequestDetailUseCase createRequestDetailUseCase;
    @Inject
    GetRequestDetailsUseCase getRequestDetailsUseCase;
    @Inject
    DeleteAllRequestDetailUseCase deleteAllRequestDetailUseCase;

    //DEV ENVIRONMENT ONLY
    @POST
    @Path("/create")
    public Response createRequestDetail(CreateRequestDetailReqDTO createRequestDetailReqDTO) {
        try{
            createRequestDetailUseCase.execute(createRequestDetailReqDTO);
            return Response.status(Response.Status.CREATED).build();
        }catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{request_id}")
    public Response getRequestDetail(@PathParam("request_id") int requestId) {
        try {
            List<GetRequestDetailResDTO> requestDetailResponseList =  getRequestDetailsUseCase.execute(requestId);
            return Response.ok(requestDetailResponseList).build();
        }catch (Exception e) {
            return Response.serverError().entity("Error").build();


        }
    }

    //DEV ENVIRONMENT ONLY
    @DELETE
    @Path("delete/{request_id}")
    public Response deleteRequestDetail(@PathParam("request_id") int requestId) {
        try {
            deleteAllRequestDetailUseCase.execute(requestId);
            return Response.ok().build();
        }catch (Exception e) {
            return Response.serverError().entity("Error").build();
        }
    }
}
