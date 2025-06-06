package life.pahtlicoo.application.usecase.shipmentorder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.shipmentorder.GetShipmentOrderReqDTO;
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
public class GetAllShipmentOrderUseCase {
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

    public List<GetShipmentOrderReqDTO> execute(int page){

        //check if there are any shipmentOrders yet
        List<ShipmentOrder> shipmentOrderList = shipmentOrderService.getAllShipmentOrder(page);
        if(shipmentOrderList == null || shipmentOrderList.isEmpty()){
            return null;
        }

        List<GetShipmentOrderReqDTO> shipmentOrderResponeseDTOList = new ArrayList<>();
        for (ShipmentOrder shipmentOrder : shipmentOrderList) {
            Request request = requestService.getRequest(shipmentOrder.getRequestId());
            SysUser sysUser = sysUserService.getSysUserByUid(request.getSysUserId());
            Site site = siteService.findSite(sysUser.getSiteId());
            GetShipmentOrderReqDTO getShipmentOrderReqDTO = shipmentOrderResponseDomainMapper.toShipmentOrderResponse(shipmentOrder, request, site);
            shipmentOrderResponeseDTOList.add(getShipmentOrderReqDTO);
        }

        return shipmentOrderResponeseDTOList;
    }
}