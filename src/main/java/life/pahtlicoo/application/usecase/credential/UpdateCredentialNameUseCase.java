package life.pahtlicoo.application.usecase.credential;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.credential.UpdateCredentialNameReqDTO;
import life.pahtlicoo.application.service.CredentialService;

@ApplicationScoped
public class UpdateCredentialNameUseCase {
    @Inject
    CredentialService credentialService;

    public void execute(int roleId, UpdateCredentialNameReqDTO updateCredentialNameReqDTO) {
        credentialService.updateRoleName(roleId, updateCredentialNameReqDTO.getName());
    }
}
