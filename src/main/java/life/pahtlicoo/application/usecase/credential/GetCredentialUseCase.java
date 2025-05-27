/**
 * Get Credential Use Case.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.usecase.credential;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.CredentialService;
import life.pahtlicoo.domain.model.Credential;

@ApplicationScoped
public class GetCredentialUseCase {
    @Inject
    CredentialService credentialService;

    public Credential execute(int roleId){
        return credentialService.getRole(roleId);
    }
}
