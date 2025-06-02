package life.pahtlicoo.infrastructure.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.medsite.CreateMedSiteReqDTO;
import life.pahtlicoo.application.dto.medsite.DeleteMedSiteReqDTO;
import life.pahtlicoo.application.dto.medsite.GetMedSiteByMedIdAndSiteId;
import life.pahtlicoo.application.dto.medsite.UpdateMedSiteQuantityReqDTO;
import life.pahtlicoo.application.usecase.medsite.*;
import life.pahtlicoo.domain.model.MedSite;

import java.util.List;

@Path("/med/site")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedSiteController {
    @Inject
    CreateMedSiteUseCase createMedSiteUseCase;
    @Inject
    DeleteMedSiteUseCase deleteMedSiteUseCase;
    @Inject
    GetMedSiteByMedIdAndSiteIdUseCase getMedSiteByMedIdAndSiteIdUseCase;
    @Inject
    GetMedSiteByMedIdUseCase getMedSiteByMedIdUseCase;
    @Inject
    GetMedSiteBySiteIdUseCase getMedSiteBySiteIdUseCase;
    @Inject
    UpdateMedSiteCurrentQuantityUseCase updateMedSiteCurrentQuantityUseCase;
    @Inject
    UpdateMedSiteInventoryUseCase updateMedSiteInventoryUseCase;

    @POST
    @Path("/create")
    public Response createMedSite(CreateMedSiteReqDTO createMedSiteReqDTO) {
        try{
            if(createMedSiteUseCase.execute(createMedSiteReqDTO)){
                return Response.status(Response.Status.CREATED).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete")
    public Response deleteMedSite(DeleteMedSiteReqDTO deleteMedSiteReqDTO) {
        try {
            if(deleteMedSiteUseCase.execute(deleteMedSiteReqDTO)){
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/get/medandsitebyid")
    public Response getMedSiteByMedIdAndSiteId(GetMedSiteByMedIdAndSiteId getMedSiteByMedIdAndSiteId) {
        try {
            MedSite medSite = getMedSiteByMedIdAndSiteIdUseCase.execute(getMedSiteByMedIdAndSiteId);
            if(medSite == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(medSite).build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/med/{medId}")
    public Response getMedSiteByMedIdUseCase(@PathParam("medId") int medId) {
        try {
            List<MedSite> medSites = getMedSiteByMedIdUseCase.execute(medId);
            if(medSites == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(medSites).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{siteId}")
    public Response getMedSiteBySiteIdUseCase(@PathParam("siteId") int siteId) {
        try {
            List<MedSite> medSites = getMedSiteBySiteIdUseCase.execute(siteId);
            if(medSites == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(medSites).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @Path("/update/quantity")
    public Response updateMedSiteCurrentQuantity(UpdateMedSiteQuantityReqDTO updateMedSiteQuantityReqDTO) {
        try{
            if(updateMedSiteCurrentQuantityUseCase.execute(updateMedSiteQuantityReqDTO)){
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @Path("/update/inventory")
    public Response updateMedSiteInventory(UpdateMedSiteQuantityReqDTO updateMedSiteQuantityReqDTO ) {
        try {
            if(updateMedSiteInventoryUseCase.execute(updateMedSiteQuantityReqDTO)){
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
