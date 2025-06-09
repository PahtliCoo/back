package life.pahtlicoo;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.credential.CreateCredentialReqDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class CredentialRestTest {

   @Test
   @Transactional
   public void testCreateCredentialEndpoint()
   {
       CreateCredentialReqDTO dto = new CreateCredentialReqDTO();
       dto.setName("hospital_admin");

       given()
               .header("Authorization", "Bearer " + "testtoken")
               .contentType("application/json")
               .body(dto)
               .when()
               .post("/credential/create")
               .then()
               .statusCode(201);
   }

    @Test
    @Transactional
    public void testCredentialExistsAfterCreation() {
        CreateCredentialReqDTO dto = new CreateCredentialReqDTO();
        dto.setName("logistics_admin");

        // Creamos credencial
        given()
                .header("Authorization", "Bearer " + "testtoken")
                .contentType("application/json")
                .body(dto)
                .when()
                .post("/credential/create")
                .then()
                .statusCode(201);

        // Obtenemos todas las credenciales
        given()
                .header("Authorization", "Bearer " + "testtoken")
                .when()
                .get("/credential/all")
                .then()
                .statusCode(200)
                .body("name", hasItem("logistics_admin"));
    }
}
