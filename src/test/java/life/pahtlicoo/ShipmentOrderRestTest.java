package life.pahtlicoo;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.application.service.ShipmentOrderService;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ShipmentOrderRestTest {
    @Inject
    ShipmentOrderService shipmentOrderService;
    @Inject
    RequestService requestService;

    @Test
    public void testShipmentOrderAppearsInListAll() {
        insertTestRequest(); // inserta Request

        ShipmentOrder order = new ShipmentOrder();
        order.setRequestId(1);
        order.setDescription("Entrega de prueba");
        order.setState(1);
        order.setCreatedAt(OffsetDateTime.now());
        order.setUpdatedAt(OffsetDateTime.now());

        shipmentOrderService.createShipmentOrder(order);

        given()
                .header("Authorization", "Bearer testtoken")
                .when()
                .get("/shipment-order/1")
                .then()
                .statusCode(200)
                .body("description", equalTo("Entrega de prueba"))
                .log().body();
    }

    @Transactional
    void insertTestRequest() {
        Request request = new Request();
        request.setSysUserId(1);
        request.setState(1);
        request.setDescription("Entrega de prueba");

        requestService.createRequest(request);
    }
}