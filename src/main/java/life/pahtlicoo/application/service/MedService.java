package life.pahtlicoo.application.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.Med;
import life.pahtlicoo.domain.repository.MedRepository;

@ApplicationScoped
public class MedService {
    @Inject
    MedRepository medRepository;

    public Med getMed(int id) {
        return medRepository.getMed(id);
    }

}
