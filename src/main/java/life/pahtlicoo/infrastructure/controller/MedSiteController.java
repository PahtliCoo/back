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
import life.pahtlicoo.application.dto.notification.GetNotificationsSeenStatusResDTO;
import life.pahtlicoo.application.usecase.medsite.*;
import life.pahtlicoo.domain.model.MedSite;

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
    @Inject
    GetMedSiteQuantityRequiredPerStateUseCase getMedSiteQuantityRequiredPerStateUseCase;

    //DEV ENVIRONMENT ONLY
    @POST
    @Path("/create")
    public Response createMedSite(CreateMedSiteReqDTO createMedSiteReqDTO) {
        try {
            createMedSiteUseCase.execute(createMedSiteReqDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DEV ENVIRONMENT ONLY
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

    //DEV ENVIRONMENT ONLY
    @POST
    @Path("/get/med-site-by-id")
    public Response getMedSiteByMedIdAndSiteId(GetMedSiteByMedIdAndSiteIdReqDTO getMedSiteByMedIdAndSiteIdReqDTO) {
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

    //DEV ENVIRONMENT ONLY
    @GET
    @Path("/med/{med_id}")
    public Response getMedSiteByMedIdUseCase(@PathParam("med_id") int medId) {
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

    //DEV ENVIRONMENT ONLY
    @GET
    @Path("/{site_id}")
    public Response getMedSiteBySiteIdUseCase(@PathParam("site_id") int siteId) {
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

    //DEV ENVIRONMENT ONLY
    @PATCH
    @Path("/update/quantity")
    public Response updateMedSiteCurrentQuantity(UpdateMedSiteQuantityReqDTO updateMedSiteQuantityReqDTO) {
        try {
            if (updateMedSiteCurrentQuantityUseCase.execute(updateMedSiteQuantityReqDTO)) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DEV ENVIRONMENT ONLY
    @PATCH
    @Path("/update/inventory")
    public Response updateMedSiteInventory(UpdateMedSiteQuantityReqDTO updateMedSiteQuantityReqDTO) {
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

    @GET
    @Path("/quantity-required/state/{med_id}")
    public Response getMedSiteQuantityRequiredPerState(@PathParam("med_id") int medId) {
        List<GetMedSiteQuantityRequiredPerStateResDTO> quantityRequired =
                getMedSiteQuantityRequiredPerStateUseCase.execute(medId);

        if (quantityRequired == null || quantityRequired.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontraron sitios con cantidades para el medicamento indicado.")
                    .build();
        }

        return Response.ok(quantityRequired).build();
    }

}
