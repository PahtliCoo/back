/**
 * MedDisease Controller
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-1
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.usecase.meddisease.GetMedDiseaseByDiseaseIdUseCase;
import life.pahtlicoo.application.usecase.meddisease.GetMedDiseaseByMedIdUseCase;
import life.pahtlicoo.domain.model.MedDisease;

@Path("/med/disease")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedDiseaseController {
    @Inject
    GetMedDiseaseByMedIdUseCase getMedDiseaseByMedIdUseCase;
    @Inject
    GetMedDiseaseByDiseaseIdUseCase getMedDiseaseByDiseaseIdUseCase;

    @GET
    @Path("/bymed/{medId}")
    public Response getByMedId(@PathParam("medId") int medId) {
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
    @Path("/bydis/{diseaseId}")
    public Response getByDiseaseId(@PathParam("diseaseId") int diseaseId) {
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
