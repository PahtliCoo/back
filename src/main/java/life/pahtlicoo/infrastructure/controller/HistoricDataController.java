package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.historicdata.CreateHistoricDataReqDTO;
import life.pahtlicoo.application.usecase.historicdata.CreateHistoricDataUseCase;
import life.pahtlicoo.application.usecase.historicdata.DeleteHistoricDataUseCase;
import life.pahtlicoo.application.usecase.historicdata.GetHistoricDataBySiteIdUseCase;
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

    @DELETE
    @Path("/{site_id}")
    public Response deleteHistoricData(@PathParam("site_id") int site_id) {
        deleteHistoricDataUseCase.execute(site_id);
        return Response.ok().build();
    }
}
