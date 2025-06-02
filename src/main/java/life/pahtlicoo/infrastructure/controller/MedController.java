/**
 * Med Controller
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-1
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.med.MedResponseDTO;
import life.pahtlicoo.application.usecase.med.GetAllMedsUseCase;
import life.pahtlicoo.application.usecase.med.GetMedsBySearchNameUseCase;

import java.util.List;

@Path("/med")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedController {
    @Inject
    GetAllMedsUseCase getAllMedsUseCase;
    @Inject
    GetMedsBySearchNameUseCase getMedsBySearchNameUseCase;

    @GET
    @Path("/allmeds")
    public Response getAllMeds() {
        try{
            List<MedResponseDTO> medResponseDTOList = getAllMedsUseCase.execute();
            if(medResponseDTOList == null || medResponseDTOList.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(medResponseDTOList).build();

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    // TODO: ESTO PUEDE SER UN GET CON UN PATHPARAM pero para probar lo dejare con get
    @GET
    @Path("/searchmeds/{name}")
    public Response searchMeds(@PathParam("name") String name) {
        try{
            List<MedResponseDTO> medResponseDTOList = getMedsBySearchNameUseCase.execute(name);
            if(medResponseDTOList == null || medResponseDTOList.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(medResponseDTOList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
