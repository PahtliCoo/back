package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.Disease;
import life.pahtlicoo.domain.repository.DiseaseRepository;

@ApplicationScoped
public class DiseaseService {
    @Inject
    DiseaseRepository diseaseRepository;

    public void createDisease(Disease disease){
        diseaseRepository.createDisease(disease);
    }

    public Disease getDiseaseById(int id){
        return diseaseRepository.getDiseaseById(id);
    }

    public void updateDiseaseName(int id, String name){
        diseaseRepository.updateDiseaseName(id, name);
    }

    public void deleteDisease(int id){
        diseaseRepository.deleteDisease(id);
    }
}
