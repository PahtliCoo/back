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
import life.pahtlicoo.domain.model.ShipmentOrder;

import java.util.List;

@Path("/shipment-order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShipmentOrderController {
    @Inject
    CreateShipmentOrderUseCase createShipmentOrderUseCase;
    @Inject
    GetAllShipmentOrderUseCase getAllShipmentOrderUseCase;
    @Inject
    UpdateShipmentOrderStatusUseCase updateShipmentOrderStatusUseCase;
    @Inject
    DeleteShipmentOrderUseCase deleteShipmentOrderUseCase;
    @Inject
    GetShipmentOrderUseCase getShipmentOrderUseCase;
    @Inject
    GetShipmentOrderByFilterUseCase getShipmentOrderByFilterUseCase;
    @Inject
    GetShipmentOrderBySearchUseCase getShipmentOrderBySearchUseCase;
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

    //TODO DELETE
    @GET
    @Path("/list_all")
    public Response getAllShipmentOrders(@QueryParam("page") int page){
        List<GetShipmentOrderReqDTO> shipmentOrders = getAllShipmentOrderUseCase.execute(page);
        if(shipmentOrders == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(shipmentOrders).build();
    }

    @GET
    @Path("/list-shipments") //SI
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

    //TODO DELETE
    @GET
    @Path("/{shipment_order_id}")
    public Response getShipmentOrder(@PathParam("shipment_order_id") int shipmentOrderId){
        ShipmentOrder shipmentOrder = getShipmentOrderUseCase.execute(shipmentOrderId);
        if (shipmentOrder == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(shipmentOrder).build();
    }

    //TODO DELETE
    @PATCH
    @Path("/{shipment_order_id}")
    public Response updateShipmentOrderStatus(@PathParam("shipment_order_id") int shipmentOrderId,
                                              UpdateShipmentOrderStateReqDTO updateShipmentOrderStateReqDTO){
        updateShipmentOrderStatusUseCase.execute(shipmentOrderId, updateShipmentOrderStateReqDTO);
        return Response.ok().build();
    }

    //TODO DELETE
    @DELETE
    @Path("/{shipment_order_id}")
    public Response deleteShipmentOrder(@PathParam("shipment_order_id") int shipmentOrderId){
        deleteShipmentOrderUseCase.execute(shipmentOrderId);
        return Response.ok().build();
    }

    //TODO DELETE
    @POST
    @Path("/filter/{page}")
    public Response filterRequest(@PathParam("page") int page, GetShipmentOrderFilterReqDTO getShipmentOrderFilterReqDTO){
        try{
            List<GetShipmentOrderReqDTO> shipmentOrders = getShipmentOrderByFilterUseCase.execute(page,getShipmentOrderFilterReqDTO);
            if(shipmentOrders == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(shipmentOrders).build();

        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //TODO DELETE
    @POST
    @Path("/search/{page}")
    public Response searchRequest(@PathParam("page") int page, GetShipmentOrderSearchReqDTO getShipmentOrderSearchReqDTO){
        try{
            List<GetShipmentOrderReqDTO> shipmentOrders = getShipmentOrderBySearchUseCase.execute(page,getShipmentOrderSearchReqDTO.getSearch());
            if(shipmentOrders == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(shipmentOrders).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @Path("/update-format/{shipment_order_id}") //SI
    public Response updateShipmentOrderFormat(@PathParam("shipment_order_id") int shipment_order_id, UpdateShipmentOrderFormatReqDTO updateShipmentOrderFormatReqDTO) {
        try {
            if (updateShipmentOrderFormat.execute(shipment_order_id, updateShipmentOrderFormatReqDTO)) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}

