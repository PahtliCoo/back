package life.pahtlicoo.application.usecase.shipmentorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.RequestResponseDTO;
import life.pahtlicoo.application.dto.shipmentorder.GetShipmentOrderReqDTO;
import life.pahtlicoo.application.mapper.RequestResponseDomainMapper;
import life.pahtlicoo.application.mapper.ShipmentOrderResponseDomainMapper;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.application.service.ShipmentOrderService;
import life.pahtlicoo.application.service.SiteService;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;
import life.pahtlicoo.domain.model.Site;
import life.pahtlicoo.domain.model.SysUser;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetShipmentOrderBySearchUseCase {
    @Inject
    ShipmentOrderService shipmentOrderService;
    @Inject
    RequestService requestService;
    @Inject
    SiteService siteService;
    @Inject
    SysUserService sysUserService;
    @Inject
    ShipmentOrderResponseDomainMapper shipmentOrderResponseDomainMapper;

    public List<GetShipmentOrderReqDTO> execute(int page, String search) {
        if(search == null || search.isEmpty()) {
            return null;
        }
        List<ShipmentOrder> shipmentOrderList = shipmentOrderService.getAllShipmentOrdersBySearch(search,page);

        if(shipmentOrderList == null) {
            return null;
        }
        // Make change from domain to ShipmentOrderResponse
        List<GetShipmentOrderReqDTO> shipmentOrderResponseDTOList = new ArrayList<>();
        for (ShipmentOrder shipmentOrder : shipmentOrderList) {
            //Get the correct site name
            Request request = requestService.getRequest(shipmentOrder.getRequestId());
            SysUser sysUser = sysUserService.getSysUserByUserId(request.getSysUserId());
            Site site = siteService.findSite(sysUser.getSiteId());
            GetShipmentOrderReqDTO getShipmentOrderReqDTO = shipmentOrderResponseDomainMapper.toShipmentOrderResponse(shipmentOrder, request, site);
            shipmentOrderResponseDTOList.add(getShipmentOrderReqDTO);
        }
        // Check that it does have data
        if(shipmentOrderResponseDTOList.isEmpty()){
            return null;
        }
        // Return result
        return shipmentOrderResponseDTOList;
    }
}
