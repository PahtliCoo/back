package life.pahtlicoo;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.sysuser.CreateSysUserReqDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SysUserRestTest {

    @Test
    @Transactional
    public void testCreateSysUser() {
        CreateSysUserReqDTO dto = new CreateSysUserReqDTO();
        dto.setEmail("pruebita@example.com");
        dto.setPassword("Password123!");
        dto.setName("Pahtli");
        dto.setLastName("Coo");
        dto.setSiteId(1);
        dto.setCredentialId(1);

        given()
                .header("Authorization", "Bearer " + "testtoken")
                .contentType("application/json")
                .body(dto)
                .when()
                .post("/sys-user/create")
                .then()
                .statusCode(201);
    }
}