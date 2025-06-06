package life.pahtlicoo.application.usecase.shipmentorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.shipmentorder.GetShipmentOrderFilterReqDTO;
import life.pahtlicoo.application.dto.shipmentorder.GetShipmentOrderReqDTO;
import life.pahtlicoo.application.mapper.ShipmentOrderResponseDomainMapper;
import life.pahtlicoo.application.service.*;
import life.pahtlicoo.domain.model.*;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetShipmentOrderByFilterUseCase {
    @Inject
    ShipmentOrderService shipmentOrderService;
    @Inject
    RequestService requestService;
    @Inject
    SysUserService sysUserService;
    @Inject
    CredentialService credentialService;
    @Inject
    SiteService siteService;
    @Inject
    ShipmentOrderResponseDomainMapper shipmentOrderResponseDomainMapper;

    public List<GetShipmentOrderReqDTO> execute(int page, GetShipmentOrderFilterReqDTO getShipmentOrderFilterReqDTO) {
        try {
            int userId = getShipmentOrderFilterReqDTO.getUserId();
            Integer state = getShipmentOrderFilterReqDTO.getState();
            Integer year = getShipmentOrderFilterReqDTO.getYear();
            Integer month = getShipmentOrderFilterReqDTO.getMonth();
            Integer day = getShipmentOrderFilterReqDTO.getDay();

            // 1. Filter / validate data
            SysUser user = sysUserService.getSysUserByUid(userId);
            Credential credential = credentialService.getRole(3); // ROLE warehouse_admin
            boolean isWarehouseAdmin = (user.getCredentialId() == credential.getCredentialId());


            List<ShipmentOrder> shipmentOrderList = new ArrayList<>();

            // Case for warehouse admin filter
            if (isWarehouseAdmin) {
                if (state == null && year == null && month == null && day == null) {
                    // Case 1: No filters selected
                    shipmentOrderList = shipmentOrderService.getAllShipmentOrder(page); //change this in service
                } else if (state == null) {
                    // Case 2: Only date filter selected
                    shipmentOrderList = shipmentOrderService.getAllShipmentOrdersByDate(year, month, day, page);
                } else if (year == null || month == null || day == null) {
                    // Case 3: Only state filter selected
                    shipmentOrderList = shipmentOrderService.getAllShipmentOrdersByState(state, page);
                } else {
                    // Case 4: All filters selected
                    shipmentOrderList = shipmentOrderService.getAllShipmentOrdersByDateByState(state, year, month, day, page);
                }
            }

            // Validate that we have the correct data
            if (shipmentOrderList == null) {
                return null;
            }

            // Make change from domain to ShipmentOrderResponse
            List<GetShipmentOrderReqDTO> shipmentOrderResponseDTOList = new ArrayList<>();
            for (ShipmentOrder shipmentOrder : shipmentOrderList) {
                //Get the correct site name
                Request request = requestService.getRequest(shipmentOrder.getRequestId());
                SysUser sysUser = sysUserService.getSysUserByUid(request.getSysUserId());
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

        } catch (Exception e ){
            // 1. Data not valid.
            return null;
        }

    }

}

