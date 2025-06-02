package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.domain.model.Med;

@Path("/meds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedController {

    @Inject
    MedService medService;

    @GET
    @Path("/{id}")
    public Response getMedById(@PathParam("id") int id) {
        Med med = medService.getMed(id);
        if (med == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Medicamento no encontrado\"}")
                    .build();
        }

        return Response.ok("{\"name\": \"" + med.getName() + "\"}").build();
    }
}
