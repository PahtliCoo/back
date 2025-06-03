/**
 * Med Service
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-1
 */
package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.Med;
import life.pahtlicoo.domain.repository.MedRepository;

import java.util.List;

@ApplicationScoped
public class MedService {
    @Inject
    MedRepository medRepository;

    public boolean createMed(Med med){
        return medRepository.createMed(med);
    }
    public Med getMed(int medId){
        return medRepository.getMed(medId);
    }
    public Med updateMedName(int medId, String name){
        return medRepository.updateMedName(medId, name);
    }
    public boolean deleteMed(int medId){
        return medRepository.deleteMed(medId);
    }
    public List<Med> getAllMeds(){
        return medRepository.getAllMeds();
    }
    public List<Med> getMedsBySearchName(String name){
        return medRepository.getMedsBySearchName(name);
    }


}
