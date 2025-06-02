package life.pahtlicoo.domain.repository;


import life.pahtlicoo.domain.model.MedDisease;

public interface MedDiseaseRepository {
    public boolean createMedDisease(MedDisease medDisease);
    public boolean deleteMedDiseaseByMedId(int medId);
    public boolean deleteMedDiseaseByDiseaseId(int diseaseId);
    public boolean updateMedDiseaseByMedId(int oldMedId, int newMedId);
    public boolean updateMedDiseaseByDiseaseId(int oldDiseaseId,int newDiseaseId);
    public MedDisease getMedDiseaseByMedId(int medId);
    public MedDisease getMedDiseaseByDiseaseId(int diseaseId);
}
