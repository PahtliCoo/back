package life.pahtlicoo;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.CredentialService;
import life.pahtlicoo.domain.model.Credential;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.wildfly.common.Assert.assertNotNull;

@QuarkusTest
public class CredentialTest {

    @Inject
    CredentialService credentialService;

    @Test
    public void testInsertCredential() {
        // Crear nuevo modelo de Credential
        Credential credential = new Credential();
        credential.setName("logistics_admin");

        // Guardar usando el service
        credentialService.createRole(credential);

        // Recuperar lista y validar
        List<Credential> credentials = credentialService.getAllRoles();
        assertNotNull(credentials);

        Credential savedCredential = credentials.get(0);
        assert savedCredential.getName().equals("logistics_admin");

        assert credentials.size()==1;
    }
}
