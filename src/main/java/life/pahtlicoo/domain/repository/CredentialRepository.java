package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Credential;

import java.util.List;

public interface CredentialRepository {
    public void createCredential(Credential credential);
    public Credential getCredential(int credentialId);
    public List<Credential> getAllCredentials();
    public void updateCredentialName(int credentialId, String name);
    public void deleteCredential(int credentialId);
}
