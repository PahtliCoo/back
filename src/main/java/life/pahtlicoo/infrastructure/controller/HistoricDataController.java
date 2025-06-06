package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.historicdata.CreateHistoricDataReqDTO;
import life.pahtlicoo.application.dto.historicdata.GetHistoricDataReqDTO;
import life.pahtlicoo.application.usecase.historicdata.*;
import life.pahtlicoo.domain.model.HistoricData;
import org.jboss.resteasy.reactive.RestForm;


import java.io.InputStream;
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
    @Inject
    ReadHistoricDataCSVUseCase readHistoricDataCSVUseCase;

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

    @POST
    @Path("/range")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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



    @DELETE
    @Path("/{site_id}")
    public Response deleteHistoricData(@PathParam("site_id") int site_id) {
        deleteHistoricDataUseCase.execute(site_id);
        return Response.ok().build();
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

}
