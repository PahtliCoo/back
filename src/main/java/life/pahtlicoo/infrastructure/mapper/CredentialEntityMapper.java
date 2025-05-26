package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Credential;
import life.pahtlicoo.infrastructure.entity.RoleEntity;

@ApplicationScoped
public class CredentialEntityMapper {
    public Credential toDomain(CredentialEntity credentialEntity){
        return new Credential(credentialEntity.getCredentialId(), credentialEntity.getName(), credentialEntity.getCreatedAt(), credentialEntity.getUpdatedAt());
    }

    public CredentialEntity toEntity(Credential credential){
        return new CredentialEntity(credential.getCredentialId(), credential.getName(), credential.getCreatedAt(), credential.getUpdatedAt());
    }


}
