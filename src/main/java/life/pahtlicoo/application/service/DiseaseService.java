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

    public Disease getDisease(int diseaseId){
        return diseaseRepository.getDisease(diseaseId);
    }

    public void updateDiseaseName(int diseaseId, String name){
        diseaseRepository.updateDiseaseName(diseaseId, name);
    }

    public void deleteDisease(int diseaseId){
        diseaseRepository.deleteDisease(diseaseId);
    }
}
