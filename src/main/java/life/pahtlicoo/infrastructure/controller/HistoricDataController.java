package life.pahtlicoo.infrastructure.controller;

import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.FileUpload;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.historicdata.CreateHistoricDataReqDTO;
import life.pahtlicoo.application.usecase.historicdata.CreateHistoricDataUseCase;
import life.pahtlicoo.application.usecase.historicdata.DeleteHistoricDataUseCase;
import life.pahtlicoo.application.usecase.historicdata.GetHistoricDataBySiteIdUseCase;
import life.pahtlicoo.application.usecase.historicdata.ReadHistoricDataCSVUseCase;
import life.pahtlicoo.domain.model.HistoricData;
import org.jboss.resteasy.reactive.MultipartForm;

import java.io.File;
import java.io.FileInputStream;
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

    @DELETE
    @Path("/{site_id}")
    public Response deleteHistoricData(@PathParam("site_id") int site_id) {
        deleteHistoricDataUseCase.execute(site_id);
        return Response.ok().build();
    }

    @POST
    @Path("/add-data")
   // @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response importHistoricData(){
        try{
            File file = new File("src/main/resources/test.csv");
            InputStream inputStream = new FileInputStream(file);
            if(readHistoricDataCSVUseCase.execute(inputStream)){
                return Response.ok().build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();


        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

}
