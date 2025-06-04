package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.usecase.site.GetSiteByIdUseCase;

@Path("/sites")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SiteController {

    @Inject
    GetSiteByIdUseCase getSiteByIdUseCase;

    @GET
    @Path("/{id}")
    public Response getSiteNameById(@PathParam("id") int id) {
        try {
            String siteName = getSiteByIdUseCase.execute(id);
            return Response.ok().entity("{\"name\": \"" + siteName + "\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}