package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.site.GetAllSitesResDTO;
import life.pahtlicoo.application.usecase.site.GetAllSitesUseCase;
import life.pahtlicoo.application.usecase.site.GetSiteByIdUseCase;
import life.pahtlicoo.domain.model.Site;
import life.pahtlicoo.shared.annotation.NoAuthRequired;

import java.util.List;

@Path("/sites")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SiteController {

    @Inject
    GetSiteByIdUseCase getSiteByIdUseCase;
    @Inject
    GetAllSitesUseCase getAllSitesUseCase;

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

    @GET
    @Path("/all")
    public Response getAllSites() {
        try{
            List<GetAllSitesResDTO> sitesList = getAllSitesUseCase.execute();
            return Response.ok(sitesList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}