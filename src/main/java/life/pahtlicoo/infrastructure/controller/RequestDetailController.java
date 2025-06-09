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
import life.pahtlicoo.application.dto.requestdetail.GetRequestDetailResDTO;
import life.pahtlicoo.application.usecase.requestdetail.GetRequestDetailsUseCase;

import java.util.List;

@Path("/request-detail")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestDetailController {
    @Inject
    GetRequestDetailsUseCase getRequestDetailsUseCase;

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
}
