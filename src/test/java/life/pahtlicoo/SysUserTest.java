package life.pahtlicoo;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.SysUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.wildfly.common.Assert.assertNotNull;

@QuarkusTest
public class SysUserTest {
    @Inject
    SysUserService sysUserService;

    @Test
    public void testInsertSysUser() {
        SysUser user = new SysUser();
        user.setName("Pahtli");
        user.setLastName("Coo");
        user.setEmail("test@example.com");
        user.setSiteId(1);
        user.setCredentialId(1);
        user.setFirebaseId("test-firebase-id");

        SysUser saved = sysUserService.createUser(user);

        assertNotNull(saved);
        assertEquals("Pahtli", saved.getName());
        assertEquals("test@example.com", saved.getEmail());
    }
}