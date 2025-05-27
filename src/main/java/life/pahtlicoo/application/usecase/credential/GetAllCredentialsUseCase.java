/**
 * Get all Credentials Use Case.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.usecase.credential;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.CredentialService;
import life.pahtlicoo.domain.model.Credential;

import java.util.List;

@ApplicationScoped
public class GetAllCredentialsUseCase {
    @Inject
    CredentialService credentialService;

    public List<Credential> execute() {
        return credentialService.getAllRoles();
    }
}
