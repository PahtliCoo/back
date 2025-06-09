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

import java.util.List;

@Path("/med-site")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedSiteController {
    @Inject
    GetMedSiteByUserIdUseCase getMedSiteByUserIdUseCase;
    @Inject
    RegisterNewMedSiteConsumptionUseCase registerNewMedSiteConsumptionUseCase;
    @Inject
    RegisterNewMedSiteAdditionUseCase registerNewMedSiteAdditionUseCase;
    @Inject
    GetMedSiteQuantityRequiredPerStateUseCase getMedSiteQuantityRequiredPerStateUseCase;

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
