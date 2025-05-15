package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.disease.CreateDiseaseReqDTO;
import life.pahtlicoo.application.dto.disease.UpdateDiseaseNameReqDTO;
import life.pahtlicoo.application.usecase.disease.CreateDiseaseUseCase;
import life.pahtlicoo.application.usecase.disease.DeleteDiseaseUseCase;
import life.pahtlicoo.application.usecase.disease.GetDiseaseUseCase;
import life.pahtlicoo.application.usecase.disease.UpdateDiseaseNameUseCase;
import life.pahtlicoo.domain.model.Disease;

@Path("/disease")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DiseaseController {
    @Inject
    CreateDiseaseUseCase createDiseaseUseCase;
    @Inject
    GetDiseaseUseCase getDiseaseUseCase;
    @Inject
    UpdateDiseaseNameUseCase updateDiseaseNameUseCase;
    @Inject
    DeleteDiseaseUseCase deleteDiseasUseCase;

    @POST
    @Path("/create")
    public Response createDisease(CreateDiseaseReqDTO createDiseaseRequest){ //TODO add @Valid annotation at param beginning
        try {
            createDiseaseUseCase.execute(createDiseaseRequest);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{disease_id}")
    public Response getDisease(@PathParam("disease_id") int diseaseId){
        Disease disease = getDiseaseUseCase.execute(diseaseId);
        if(disease == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(disease).build();
    }

    @PATCH
    @Path("/update-name/{disease_id}")
    public Response updateDiseaseName(@PathParam("disease_id") int diseaseId, UpdateDiseaseNameReqDTO updateDiseaseNameReqDTO){
        try{
            updateDiseaseNameUseCase.execute(diseaseId, updateDiseaseNameReqDTO);
            return Response.ok().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{disease_id}")
    public Response deleteDisease(@PathParam("disease_id") int diseaseId){
        deleteDiseasUseCase.execute(diseaseId);
        return Response.ok().build();
    }
}
