/**
 * SysUserController.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.Credential;
import life.pahtlicoo.domain.repository.CredentialRepository;


import java.util.List;

@ApplicationScoped
public class CredentialService {
    @Inject
    CredentialRepository credentialRepository;

    public void createRole(Credential role) {
        credentialRepository.createCredential(role);
    }

    public Credential getRole(int roleId) {
        return credentialRepository.getCredential(roleId);
    }

    public List<Credential> getAllRoles() {
        return credentialRepository.getAllCredentials();
    }

    public void updateRoleName(int roleId, String name){
        credentialRepository.updateCredentialName(roleId, name);
    }

    public void deleteRole(int roleId) {
        credentialRepository.deleteCredential(roleId);
    }
}
