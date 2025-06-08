package life.pahtlicoo.infraestructure.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.service.HistoricDataService;
import life.pahtlicoo.domain.model.HistoricData;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class HistoricDataControllerTest {
    @Inject
    HistoricDataService historicDataService;

    @Test
    public void HistoricDataControllerTest() {



    }


}
