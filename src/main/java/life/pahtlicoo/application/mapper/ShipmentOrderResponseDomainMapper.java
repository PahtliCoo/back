/**
 * Shipment Order Response Domain Mapper.
 * @Author Nicole Kapellmann Lepine (A01664563.tec.mx)
 * @since 2025-06-04
 */
package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotBlank;
import life.pahtlicoo.application.dto.shipmentorder.GetShipmentOrderReqDTO;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;
import life.pahtlicoo.domain.model.Site;

import java.time.OffsetDateTime;

@ApplicationScoped
public class ShipmentOrderResponseDomainMapper {
    public GetShipmentOrderReqDTO toShipmentOrderResponse(ShipmentOrder shipmentOrder, Request request, Site site) {
        return new GetShipmentOrderReqDTO(request.getName(), shipmentOrder.getCreatedAt(),
                shipmentOrder.getState(), site.getName(), request.getRequestId(), shipmentOrder.getShipmentOrderId(), shipmentOrder.getDescription());
    }

}

