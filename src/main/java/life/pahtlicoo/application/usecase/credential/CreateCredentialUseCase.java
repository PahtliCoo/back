/**
 * Create Credentials Use Case.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.usecase.credential;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.credential.CreateCredentialReqDTO;
import life.pahtlicoo.application.mapper.CredentialDomainMapper;
import life.pahtlicoo.application.service.CredentialService;
import life.pahtlicoo.domain.model.Credential;

@ApplicationScoped
public class CreateCredentialUseCase {
    @Inject
    CredentialService credentialService;

    @Inject
    CredentialDomainMapper credentialDomainMapper;

    public void execute(CreateCredentialReqDTO createCredentialReqDTO) {
        Credential role = credentialDomainMapper.createRoleToDomain(createCredentialReqDTO);
        credentialService.createRole(role);
    }
}
