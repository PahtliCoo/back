package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.Med;
import life.pahtlicoo.domain.repository.MedRepository;
import life.pahtlicoo.infrastructure.entity.MedEntity;
import life.pahtlicoo.infrastructure.mapper.MedEntityMapper;

@ApplicationScoped
public class MedRepositoryImpl implements MedRepository, PanacheRepositoryBase<MedEntity, Integer> {

    @Inject
    MedEntityMapper medEntityMapper;

    @Override
    @Transactional
    public void createMed(Med med) {
        MedEntity medEntity = medEntityMapper.toEntity(med);
        persist(medEntity);
        med.setMedId(medEntity.getMedId());
    }

    @Override
    public Med getMed(int medId) {
        MedEntity medEntity = findById(medId);
        if (medEntity == null) {
            return null;
        }
        return medEntityMapper.toDomain(medEntity);
    }

    @Override
    @Transactional
    public void updateMedName(int medId, String name) {
        MedEntity medEntity = findById(medId);
        if (medEntity == null) {
            return;
        }
        medEntity.setName(name);
    }

    @Override
    @Transactional
    public void deleteMed(int medId) {
        deleteById(medId);
    }
}
