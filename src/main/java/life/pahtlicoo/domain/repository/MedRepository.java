package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Med;

import java.util.List;

public interface MedRepository {
    public boolean createMed(Med med);
    public Med getMed(int medId);
    public Med updateMedName(int medId, String name);
    public boolean deleteMed(int medId);
    public List<Med> getAllMeds();
    public List<Med> getMedsBySearchName(String name);
}
