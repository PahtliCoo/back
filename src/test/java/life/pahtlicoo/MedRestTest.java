package life.pahtlicoo;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.domain.model.Med;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class MedTest {

    @Inject
    MedService medService;

    @Test
    public void testInsertMed() {
        Med med = new Med();
        med.setName("Ibuprofeno");

        medService.createMed(med);

        List<Med> meds = medService.getAllMeds();
        assertNotNull(meds);

        Med saved = meds.stream()
                .filter(m -> m.getName().equals("Ibuprofeno"))
                .findFirst()
                .orElse(null);

        assertNotNull(saved);
        assertEquals("Ibuprofeno", saved.getName());
    }
}