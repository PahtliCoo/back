package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.historicdata.CreateHistoricDataReqDTO;
import life.pahtlicoo.application.usecase.historicdata.*;
import life.pahtlicoo.domain.model.HistoricData;

import java.util.List;

@Path("/historic-data")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoricDataController {
    @Inject
    CreateHistoricDataUseCase createHistoricDataUseCase;
    @Inject
    GetHistoricDataBySiteIdUseCase getHistoricDataBySiteIdUseCase;
    @Inject
    DeleteHistoricDataUseCase deleteHistoricDataUseCase;
    @Inject
    GetHistoricDataByDatesUseCase getHistoricData;
    @Inject
    CreateReportWithHistoricDataUseCase createReportWithHistoricDataUseCase;

    @POST
    @Path("/create")
    public Response createHistoricData(CreateHistoricDataReqDTO createHistoricDataReqDTO) {
        try{
            createHistoricDataUseCase.execute(createHistoricDataReqDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/site/{site_id}")
    public Response getHistoricDataBySiteId(@PathParam("site_id") int site_id) {
        List<HistoricData> historicData = getHistoricDataBySiteIdUseCase.execute(site_id);
        if(historicData == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(historicData).build();
    }

    @GET
    @Path("/range")
    public Response getHistoricDataByRange(
            @QueryParam("year") int year,
            @QueryParam("startMonth") int startMonth,
            @QueryParam("endMonth") int endMonth) {

        List<HistoricData> data = getHistoricData.execute(year, startMonth, endMonth);
        return Response.ok(data).build();
    }
    @GET
    @Path("/report")
    @Produces("application/pdf")
    public Response generatePdfReport(@QueryParam("year") int year,
                                      @QueryParam("startMonth") int startMonth,
                                      @QueryParam("endMonth") int endMonth) {
        byte[] pdfBytes = createReportWithHistoricDataUseCase.execute(year, startMonth, endMonth);

        return Response.ok(pdfBytes)
                .header("Content-Disposition", "attachment; filename=historical-report-" + year + ".pdf")
                .build();
    }


    @DELETE
    @Path("/{site_id}")
    public Response deleteHistoricData(@PathParam("site_id") int site_id) {
        deleteHistoricDataUseCase.execute(site_id);
        return Response.ok().build();
    }
}
