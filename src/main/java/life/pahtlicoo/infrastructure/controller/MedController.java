/**
 * Med Controller
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @since 2025-06-05
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.med.CreateMedReqDTO;
import life.pahtlicoo.application.dto.med.MedResponseDTO;
import life.pahtlicoo.application.dto.med.MedUpdateNameReqDTO;
import life.pahtlicoo.application.usecase.med.*;
import life.pahtlicoo.domain.model.Med;

import java.util.List;

@Path("/med")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedController {
    @Inject
    GetAllMedsUseCase getAllMedsUseCase;
    @Inject
    GetMedsBySearchNameUseCase getMedsBySearchNameUseCase;
    @Inject
    DeleteMedUseCase deleteMedUseCase;
    @Inject
    CreateMedUseCase createMedUseCase;
    @Inject
    GetMedUseCase getMedUseCase;
    @Inject
    UpdateMedNameUseCase updateMedNameUseCase;

    //DEV ENVIRONMENT ONLY
    @POST
    @Path("/create")
    public Response createMed(CreateMedReqDTO createMedReqDTO) {
        try{
            if(createMedUseCase.execute(createMedReqDTO.getName())){
                return Response.status(Response.Status.CREATED).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/all")
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

    @GET
    @Path("")
    public Response searchMeds(@QueryParam("name") String name) {
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

    //DEV ENVIRONMENT ONLY
    @DELETE
    @Path("/{med_id}")
    public Response deleteMed(@PathParam("med_id") int med_id) {
        try {
            if(deleteMedUseCase.execute(med_id)) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DEV ENVIRONMENT ONLY
    @GET
    @Path("/{med_id}")
    public Response getMed(@PathParam("med_id") int med_id) {
        try{
            Med med = getMedUseCase.execute(med_id);
            if(med == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(med).build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DEV ENVIRONMENT ONLY
    @PATCH
    @Path("/update")
    public Response updateMedName(MedUpdateNameReqDTO medUpdateNameReqDTO) {
        try {
             Med med = updateMedNameUseCase.execute(medUpdateNameReqDTO.getMedId(),medUpdateNameReqDTO.getName());
             if(med == null) {
                 return Response.status(Response.Status.NOT_FOUND).build();
             }
             return Response.ok(med).build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
