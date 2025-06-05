package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.shipmentorder.GetShipmentOrderSearchReqDTO;
import life.pahtlicoo.application.dto.request.GetRequestSearchDTO;
import life.pahtlicoo.application.dto.shipmentorder.GetShipmentOrderFilterReqDTO;
import life.pahtlicoo.application.dto.shipmentorder.CreateShipmentOrderReqDTO;
import life.pahtlicoo.application.dto.shipmentorder.GetShipmentOrderReqDTO;
import life.pahtlicoo.application.dto.shipmentorder.UpdateShipmentOrderStatusReqDTO;
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
    @Path("/list_all")
    public Response getAllShipmentOrders(@PathParam("page") int page){
        List<GetShipmentOrderReqDTO> shipmentOrders = getAllShipmentOrderUseCase.execute(page);
        if(shipmentOrders == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(shipmentOrders).build();

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

}
