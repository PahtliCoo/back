/**
 * Shipment Order Controller
 * @author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @co-author Santiago Moreno Lacalle Quintero (a01663197@tec.mx)
 * @co-author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-06-08
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.shipmentorder.*;
import life.pahtlicoo.application.usecase.shipmentorder.*;

import java.util.List;
import java.util.Set;

@Path("/shipment-order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShipmentOrderController {
    @Inject
    CreateShipmentOrderUseCase createShipmentOrderUseCase;
    @Inject
    SearchShipmentOrdersUseCase searchShipmentOrdersUseCase;
    @Inject
    UpdateShipmentOrderFormat updateShipmentOrderFormat;

    @POST
    @Path("/create")
    public Response createShipmentOrder(CreateShipmentOrderReqDTO createShipmentOrderReqDTO){
        try{
            if(createShipmentOrderUseCase.execute(createShipmentOrderReqDTO)){
                return Response.status(Response.Status.CREATED).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/list-shipments")
    public Response searchShipmentOrders(@QueryParam("name") String name, @QueryParam("page") @DefaultValue("0") int page,
                                       @QueryParam("date") String date, @QueryParam("state") Integer state){

        if (page < 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid page number. Must be >= 0.") //TODO should i keep this?
                    .build();
        }

        SearchShipmentOrdersReqDTO searchShipmentOrdersReqDTO = new SearchShipmentOrdersReqDTO(name, date, state, page);

        try{
            List<GetShipmentOrderReqDTO> shipmentOrders = searchShipmentOrdersUseCase.execute(searchShipmentOrdersReqDTO);
            return Response.ok(shipmentOrders).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @Path("/update-format/{shipment_order_id}")
    public Response updateShipmentOrderFormat(@PathParam("shipment_order_id") int shipment_order_id, UpdateShipmentOrderFormatReqDTO updateShipmentOrderFormatReqDTO) {
        try {
            Set<Integer> validStates = Set.of(1, 2, 3, 4);
            if (!validStates.contains(updateShipmentOrderFormatReqDTO.getState())) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            if (updateShipmentOrderFormat.execute(shipment_order_id, updateShipmentOrderFormatReqDTO)) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

