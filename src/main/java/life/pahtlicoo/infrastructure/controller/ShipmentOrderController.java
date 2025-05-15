package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.shipmentorder.CreateShipmentOrderReqDTO;
import life.pahtlicoo.application.dto.shipmentorder.UpdateShipmentOrderStatusReqDTO;
import life.pahtlicoo.application.usecase.shipmentorder.CreateShipmentOrderUseCase;
import life.pahtlicoo.application.usecase.shipmentorder.DeleteShipmentOrderUseCase;
import life.pahtlicoo.application.usecase.shipmentorder.GetShipmentOrderUseCase;
import life.pahtlicoo.application.usecase.shipmentorder.UpdateShipmentOrderStatusUseCase;
import life.pahtlicoo.domain.model.ShipmentOrder;

@Path("/shipment-order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShipmentOrderController {
    @Inject
    CreateShipmentOrderUseCase createShipmentOrderUseCase;
    @Inject
    GetShipmentOrderUseCase getShipmentOrderUseCase;
    @Inject
    UpdateShipmentOrderStatusUseCase updateShipmentOrderStatusUseCase;
    @Inject
    DeleteShipmentOrderUseCase deleteShipmentOrderUseCase;

    @POST
    @Path("/create")
    public Response createShipmentOrder(CreateShipmentOrderReqDTO createShipmentOrderReqDTO){
        try{
            createShipmentOrderUseCase.execute(createShipmentOrderReqDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{shipment_order_id}")
    public Response getShipmentOrder(@PathParam("shipment_order_id") int shipmentOrderId){
        ShipmentOrder shipmentOrder = getShipmentOrderUseCase.execute(shipmentOrderId);
        if (shipmentOrder == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(shipmentOrder).build();
    }

    @PATCH
    @Path("/{shipment_order_id}")
    public Response updateShipmentOrderStatus(@PathParam("shipment_order_id") int shipmentOrderId,
                                              UpdateShipmentOrderStatusReqDTO updateShipmentOrderStatusReqDTO){
        updateShipmentOrderStatusUseCase.execute(shipmentOrderId, updateShipmentOrderStatusReqDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{shipment_order_id}")
    public Response deleteShipmentOrder(@PathParam("shipment_order_id") int shipmentOrderId){
        deleteShipmentOrderUseCase.execute(shipmentOrderId);
        return Response.ok().build();
    }
}
