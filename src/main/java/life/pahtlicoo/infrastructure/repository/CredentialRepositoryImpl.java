package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.Credential;
import life.pahtlicoo.domain.repository.CredentialRepository;
import life.pahtlicoo.infrastructure.entity.CredentialEntity;
import life.pahtlicoo.infrastructure.mapper.CredentialEntityMapper;

import java.util.List;

@ApplicationScoped
public class CredentialRepositoryImpl implements CredentialRepository, PanacheRepositoryBase<CredentialEntity, Integer> {
    @Inject
    CredentialEntityMapper credentialEntityMapper;

    @Override
    @Transactional
    public void createCredential(Credential credential){
        CredentialEntity credentialEntity = credentialEntityMapper.toEntity(credential);
        persist(credentialEntity);
        credential.setCredentialId(credentialEntity.getCredentialId());
    }

    @Override
    public Credential getCredential(int credentialId){
        CredentialEntity credentialEntity = findById(credentialId);
        if(credentialEntity == null){
            return null;
        }
        return credentialEntityMapper.toDomain(credentialEntity);
    }

    @Override
    public List<Credential> getAllCredentials(){
        List<CredentialEntity> credentialEntities = CredentialEntity.listAll();
        return credentialEntities.stream()
                .map(credentialEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void updateCredentialName(int credentialId, String newCredentialName){
        CredentialEntity credentialEntity = findById(credentialId);
        if(credentialEntity == null){
            return;
        }
        credentialEntity.setName(newCredentialName);
    }

    @Override
    @Transactional
    public void deleteCredential(int credentialId){
        deleteById(credentialId);
    }
}

