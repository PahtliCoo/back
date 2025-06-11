/**
 * Historic Data Controller
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Rodrigo Rocha Rosales
 * @since 2025-06-08
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.historicdata.*;
import life.pahtlicoo.application.usecase.historicdata.*;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.shared.annotation.NoAuthRequired;
import org.jboss.resteasy.reactive.RestForm;

import java.io.InputStream;
import java.util.List;

@Path("/historic-data")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoricDataController {
    @Inject
    GetHistoricDataByDatesUseCase getHistoricData;
    @Inject
    CreateReportWithHistoricDataUseCase createReportWithHistoricDataUseCase;
    @Inject
    ReadHistoricDataCSVUseCase readHistoricDataCSVUseCase;
    @Inject
    GenerateForecastUseCase generateForecastUseCase;
    @Inject
    GetMostRecentHistoricDataUseCase getMostRecentHistoricDataUseCase;
    @Inject
    GetHistoricDataByMedIdUseCase getHistoricDataByMedIdUseCase;
    @Inject
    GetPredictiveDataByMedIdUseCase getPredictiveDataByMedIdUseCase;

    @POST
    @Path("/range")
    public Response getHistoricDataByRange(GetHistoricDataReqDTO dto) {
        List<HistoricData> data = getHistoricData.execute(dto);
        return Response.ok(data).build();
    }

    @POST
    @Path("/report")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public Response generatePdfReport(GetHistoricDataReqDTO dto) {
        byte[] pdf = createReportWithHistoricDataUseCase.execute(dto);
        return Response.ok(pdf)
                .header("Content-Disposition", "attachment; filename=report-" + dto.getYear() + ".pdf")
                .build();
    }

    @POST
    @Path("/add-data")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response importHistoricData(@RestForm("file") InputStream file){
        try{
            if(readHistoricDataCSVUseCase.execute(file)){
                return Response.ok().build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/forecast")
    public Response generateForecast(GenerateForecastReqDTO generateForecastReqDTO){
        try {
            List<GenerateForecastResDTO> generateForecastResDTOList = generateForecastUseCase.execute(generateForecastReqDTO);
            return Response.ok(generateForecastResDTOList).build();

        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/get/most-recent/{med_id}")
    public Response getMostRecentHistoricData(@PathParam("med_id") int medId) {
        List<GetRecentHistoricDataResDTO> mostRecent = getMostRecentHistoricDataUseCase.execute(medId);
        return Response.ok(mostRecent).build();
    }

    @GET
    @Path("/historic/{med_id}")
    public Response getHistoricDataByMedId(@PathParam("med_id") int medId) {
        List<GetRecentHistoricDataResDTO> recentHistoric = getHistoricDataByMedIdUseCase.execute(medId);
        return Response.ok(recentHistoric).build();
    }

    @GET
    @Path("/predictive/{med_id}")
    public Response getPredictiveDataByMedId(@PathParam("med_id") int medId) {
        List<GetRecentHistoricDataResDTO> recentPredictive = getPredictiveDataByMedIdUseCase.execute(medId);
        return Response.ok(recentPredictive).build();
    }
}
