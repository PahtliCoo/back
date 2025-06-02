package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Med;


public interface MedRepository {
    public void createMed(Med med);
    public Med getMed(int medId);
    public void updateMedName(int medId, String name);
    public void deleteMed(int medId);
}
