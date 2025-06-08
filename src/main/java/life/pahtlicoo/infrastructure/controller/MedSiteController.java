/**
 * Med site (current inventory) Controller
 * @author Santiago Moreno Lacalle Quintero (a01663197@tec.mx)
 * @co-author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @since 2025-06-06
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.medsite.*;
import life.pahtlicoo.application.usecase.medsite.*;
import life.pahtlicoo.domain.model.MedSite;
import life.pahtlicoo.shared.annotation.NoAuthRequired;

import java.util.List;

@Path("/med-site")
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
    @Inject
    GetMedSiteByUserIdUseCase getMedSiteByUserIdUseCase;
    @Inject
    RegisterNewMedSiteConsumptionUseCase registerNewMedSiteConsumptionUseCase;
    @Inject
    RegisterNewMedSiteAdditionUseCase registerNewMedSiteAdditionUseCase;

    @POST
    @Path("/create")
    public Response createMedSite(CreateMedSiteReqDTO createMedSiteReqDTO) {
        try {
            createMedSiteUseCase.execute(createMedSiteReqDTO);
            //TODO capaz se puede añadir solo algo para que valide que los datos estan bien y tal
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete")
    public Response deleteMedSite(DeleteMedSiteReqDTO deleteMedSiteReqDTO) {
        try {
            if (deleteMedSiteUseCase.execute(deleteMedSiteReqDTO)) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/get/medandsitebyid")
    public Response getMedSiteByMedIdAndSiteId(GetMedSiteByMedIdAndSiteIdReqDTO getMedSiteByMedIdAndSiteIdReqDTO) { //TODO DEPRECATED
        try {
            MedSite medSite = getMedSiteByMedIdAndSiteIdUseCase.execute(getMedSiteByMedIdAndSiteIdReqDTO);
            if (medSite == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(medSite).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/med/{medId}")
    public Response getMedSiteByMedIdUseCase(@PathParam("medId") int medId) { //TODO DEPRECATED
        try {
            List<MedSite> medSites = getMedSiteByMedIdUseCase.execute(medId);
            if (medSites == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(medSites).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{siteId}")
    public Response getMedSiteBySiteIdUseCase(@PathParam("siteId") int siteId) { //TODO DEPRECATED
        try {
            List<MedSite> medSites = getMedSiteBySiteIdUseCase.execute(siteId);
            if (medSites == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(medSites).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    //TODO esta ruta esta medio insegura ajjaja porq literal ocupas el site id para que te diga, no necesita saber que
    //el usuario de menos pertenece o tiene ese site, maybe un fix

    @PATCH
    @Path("/update/quantity")
    public Response updateMedSiteCurrentQuantity(UpdateMedSiteQuantityReqDTO updateMedSiteQuantityReqDTO) { //TODO DEPRECATED
        try {
            if (updateMedSiteCurrentQuantityUseCase.execute(updateMedSiteQuantityReqDTO)) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //TODO básiocamente estas dos de arriba son lo mismo, pero, la de abajo le pone al initial y al current el mismo dato
    @PATCH
    @Path("/update/inventory")
    public Response updateMedSiteInventory(UpdateMedSiteQuantityReqDTO updateMedSiteQuantityReqDTO) { //TODO DEPRECATED
        try {
            if (updateMedSiteInventoryUseCase.execute(updateMedSiteQuantityReqDTO)) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/sys-user/{sys_user_id}")
    public Response getInventoryBySysUserId(@PathParam("sys_user_id") int sysUserId,
                                            @QueryParam("page") @DefaultValue("0") int page,
                                            @QueryParam("med_name") String med_name) {

        if (page < 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid page number. Must be >= 0.")
                    .build();
        }

        GetUserMedSiteReqDTO getUserMedSiteReqDTO = new GetUserMedSiteReqDTO(sysUserId, med_name, page);

        try {
            List<MedSiteResDTO> medSiteResList = getMedSiteByUserIdUseCase.execute(getUserMedSiteReqDTO);
            return Response.ok(medSiteResList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @Path("/register-consumption/{site_id}/{med_id}")
    public Response registerNewMedSiteConsumption(@PathParam("site_id") int siteId, @PathParam("med_id") int medId,
                                                  RegisterNewMedSiteConsumptionReqDTO registerNewMedSiteConsumptionReqDTO) {
        RegisterMedSiteConsumptionDTO registerMedSiteConsumptionDTO = new RegisterMedSiteConsumptionDTO(medId, siteId,
                registerNewMedSiteConsumptionReqDTO.getConsumption());

        try {
            registerNewMedSiteConsumptionUseCase.execute(registerMedSiteConsumptionDTO);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @Path("/register-addition/{site_id}/{med_id}")
    public Response registerNewMedSiteAddition(@PathParam("site_id") int siteId, @PathParam("med_id") int medId, RegisterNewMedSiteAdditionReqDTO registerNewMedSiteAdditionReqDTO) {
        RegisterMedSiteAdditionDTO registerMedSiteAdditionDTO = new RegisterMedSiteAdditionDTO(medId, siteId, registerNewMedSiteAdditionReqDTO.getAddition());
        try{
            registerNewMedSiteAdditionUseCase.execute(registerMedSiteAdditionDTO);
            return Response.status(Response.Status.OK).build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
