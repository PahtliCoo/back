/**
 * MedDisease service
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-1
 */
package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.MedDisease;
import life.pahtlicoo.domain.repository.MedDiseaseRepository;

@ApplicationScoped
public class MedDiseaseService {
    @Inject
    MedDiseaseRepository medDiseaseRepository;

    public boolean createMedDisease(MedDisease medDisease){
        return medDiseaseRepository.createMedDisease(medDisease);
    }
    public boolean deleteMedDiseaseByMedId(int medId){
        return medDiseaseRepository.deleteMedDiseaseByMedId(medId);
    }
    public boolean deleteMedDiseaseByDiseaseId(int diseaseId){
        return medDiseaseRepository.deleteMedDiseaseByDiseaseId(diseaseId);
    }
    public boolean updateMedDiseaseByMedId(int oldMedId, int newMedId){
        return  medDiseaseRepository.updateMedDiseaseByMedId(oldMedId, newMedId);
    }
    public boolean updateMedDiseaseByDiseaseId(int oldDiseaseId,int newDiseaseId){
        return medDiseaseRepository.updateMedDiseaseByMedId(oldDiseaseId,newDiseaseId);
    }
    public MedDisease getMedDiseaseByMedId(int medId){
        return medDiseaseRepository.getMedDiseaseByMedId(medId);
    }
    public MedDisease getMedDiseaseByDiseaseId(int diseaseId){
        return medDiseaseRepository.getMedDiseaseByDiseaseId(diseaseId);
    }

}
