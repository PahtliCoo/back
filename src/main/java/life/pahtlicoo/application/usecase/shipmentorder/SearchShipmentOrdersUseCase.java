
/**
 * Search all shipments  and filter if provided.
 * @author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @since 2025-06-06
 */
package life.pahtlicoo.application.usecase.shipmentorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.shipmentorder.GetShipmentOrderReqDTO;
import life.pahtlicoo.application.dto.shipmentorder.SearchShipmentOrdersReqDTO;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.application.service.ShipmentOrderService;
import life.pahtlicoo.application.service.SiteService;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;
import life.pahtlicoo.domain.model.Site;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.application.mapper.ShipmentOrderResponseDomainMapper;


import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SearchShipmentOrdersUseCase {
    @Inject
    ShipmentOrderService shipmentOrderService;
    @Inject
    RequestService requestService;
    @Inject
    SysUserService sysUserService;
    @Inject
    SiteService siteService;
    @Inject
    ShipmentOrderResponseDomainMapper shipmentOrderResponseDomainMapper;

    public List<GetShipmentOrderReqDTO> execute(SearchShipmentOrdersReqDTO searchShipmentOrdersReqDTO) {
        List<ShipmentOrder> shipmentOrderList =shipmentOrderService.searchShipmentOrders(searchShipmentOrdersReqDTO);

        if (shipmentOrderList == null || shipmentOrderList.isEmpty()) {
            return new ArrayList<>();
        }
        // Make change from domain to RequestResponse
        List<GetShipmentOrderReqDTO> shipmentOrderResponeseDTOList = new ArrayList<>();
        for (ShipmentOrder shipmentOrder : shipmentOrderList) {
            //Get the correct site name
            Request request = requestService.getRequest(shipmentOrder.getRequestId());
            SysUser sysUser = sysUserService.getSysUserByUserId(request.getSysUserId());
            Site site = siteService.findSite(sysUser.getSiteId());
            GetShipmentOrderReqDTO getShipmentOrderReqDTO = shipmentOrderResponseDomainMapper.toShipmentOrderResponse(shipmentOrder, request, site);
            shipmentOrderResponeseDTOList.add(getShipmentOrderReqDTO);
        }
        return shipmentOrderResponeseDTOList;
    }
}
