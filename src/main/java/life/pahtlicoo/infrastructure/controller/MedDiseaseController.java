/**
 * DEV ENVIRONMENT ONLY
 * MedDisease Controller
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-1
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.meddisease.CreateMedDiseaseReqDTO;
import life.pahtlicoo.application.dto.meddisease.UpdateMedDiseaseByDiseaseIdReqDTO;
import life.pahtlicoo.application.dto.meddisease.UpdateMedDiseaseByMedIdReqDTO;
import life.pahtlicoo.application.usecase.meddisease.*;
import life.pahtlicoo.domain.model.MedDisease;

@Path("/med-disease")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedDiseaseController {
    @Inject
    GetMedDiseaseByMedIdUseCase getMedDiseaseByMedIdUseCase;
    @Inject
    GetMedDiseaseByDiseaseIdUseCase getMedDiseaseByDiseaseIdUseCase;
    @Inject
    CreateMedDiseaseUseCase createMedDiseaseUseCase;
    @Inject
    DeleteMedDiseaseByMedIdUseCase deleteMedDiseaseByMedIdUseCase;
    @Inject
    DeleteMedDiseaseByDiseaseIdUseCase deleteMedDiseaseByDiseaseIdUseCase;
    @Inject
    UpdateMedDiseaseByMedIdUseCase updateMedDiseaseByMedIdUseCase;
    @Inject
    UpdateMedDiseaseByDiseaseIdUseCase updateMedDiseaseByDiseaseIdUseCase;

    @POST
    @Path("/create")
    public Response createMedDisease(CreateMedDiseaseReqDTO createMedDiseaseReqDTO){
        try{
            if(createMedDiseaseUseCase.execute(createMedDiseaseReqDTO)){
                return Response.status(Response.Status.CREATED).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/by-med/{med_id}")
    public Response deleteMedDiseaseByMedId(@PathParam("med_id") int medId){
        try {
            if(deleteMedDiseaseByMedIdUseCase.execute(medId)){
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/by-disease/{disease_id}")
    public Response deleteMedDiseaseByDiseaseId(@PathParam("disease_id") int diseaseId){
        try {
            if(deleteMedDiseaseByDiseaseIdUseCase.execute(diseaseId)){
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @Path("/by-med")
    public Response updateMedDiseaseByMedId(UpdateMedDiseaseByMedIdReqDTO medDiseaseByMedIdReqDTO){
        try {
            if(updateMedDiseaseByMedIdUseCase.execute(medDiseaseByMedIdReqDTO.getOldMedId(),medDiseaseByMedIdReqDTO.getNewMedId())){
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @Path("/by-disease")
    public Response updateMedDiseaseByMedId(UpdateMedDiseaseByDiseaseIdReqDTO updateMedDiseaseByDiseaseIdReqDTO){
        try {
            if(updateMedDiseaseByDiseaseIdUseCase.execute(updateMedDiseaseByDiseaseIdReqDTO.getOldDiseaseId(),updateMedDiseaseByDiseaseIdReqDTO.getNewDiseaseId())){
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GET
    @Path("/by-med/{med_id}")
    public Response getByMedId(@PathParam("med_id") int medId) {
        try {
            MedDisease medDisease = getMedDiseaseByMedIdUseCase.execute(medId);
            if(medDisease == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(medDisease).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/by-disease/{disease_id}")
    public Response getByDiseaseId(@PathParam("disease_id") int diseaseId) {
        try {
            MedDisease medDisease = getMedDiseaseByDiseaseIdUseCase.execute(diseaseId);
            if(medDisease == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(medDisease).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
