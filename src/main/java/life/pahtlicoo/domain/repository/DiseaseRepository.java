package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Disease;

public interface DiseaseRepository {
    public void createDisease(Disease disease); //Usar create, evitar statements tipo SQL
    public Disease getDisease(int diseaseId);
    public void updateDiseaseName(int diseaseId, String name);
    public void deleteDisease(int diseaseId);
}

//En secciones de domain solo usar elementos de domain
