package life.pahtlicoo.application.usecase.credential;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.CredentialService;

@ApplicationScoped
public class DeleteCredentialUseCase {
    @Inject
    CredentialService credentialService;

    public void execute(int roleId) {
        credentialService.deleteRole(roleId);
    }
}
