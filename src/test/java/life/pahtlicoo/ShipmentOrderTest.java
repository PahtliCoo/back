package life.pahtlicoo;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.ShipmentOrderService;
import life.pahtlicoo.domain.model.ShipmentOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ShipmentOrderTest {

    @Inject
    ShipmentOrderService shipmentOrderService;

    @Test
    public void testInsertShipmentOrder() {

        ShipmentOrder shipmentOrder = new ShipmentOrder();
        shipmentOrder.setRequestId(1);
        shipmentOrder.setState(1);

        shipmentOrderService.createShipmentOrder(shipmentOrder);

        ShipmentOrder saved = shipmentOrderService.getShipmentOrderByRequestId(1);
        assertNotNull(saved);
        assertEquals(1, saved.getState());
    }
}