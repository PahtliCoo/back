package life.pahtlicoo;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.domain.model.Med;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class MedRestTest {

    @Inject
    MedService medService;

    @Test
    public void testMedAppearsInAll() {
        insertTestMed();

        given()
                .header("Authorization", "Bearer testtoken")
                .when()
                .get("/med/all")
                .then()
                .statusCode(200)
                .body("med_name", hasItem("Paracetamol"))
                .log().body();
    }

    @Transactional
    void insertTestMed() {
        Med med = new Med();
        med.setName("Paracetamol");
        medService.createMed(med);
    }
}