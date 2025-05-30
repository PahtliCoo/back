package life.pahtlicoo;

import io.quarkus.test.junit.QuarkusTest;
import life.pahtlicoo.shared.util.HoltWinters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class HoltWintersTest {

    @Test
    void testAditiveForecast() {
        HoltWinters hw = new HoltWinters(0.2, 0.1, 0.3, 12, 3);

        int[] data = {
                120, 135, 150, 160, 145, 170, 180, 190, 175, 160, 150, 140,
                125, 140, 155, 165, 150, 175, 185, 195, 180, 165, 155, 145
        };

        int[] forecast = hw.aditiveForecast(data);

        assertNotNull(forecast, "El resultado no debe ser null");
        assertEquals(data.length + 3, forecast.length, "El tamaño del resultado debe incluir el horizonte");

        System.out.println("Pronóstico aditivo: ");
        for (int i = forecast.length - 3; i < forecast.length; i++) {
            System.out.println("Mes " + (i + 1) + ": " + forecast[i]);
        }
    }

    @Test
    void testMultiplicativeForecast() {
        HoltWinters hw = new HoltWinters(0.2, 0.1, 0.3, 12, 3);

        int[] data = {
                120, 135, 150, 160, 145, 170, 180, 190, 175, 160, 150, 140,
                125, 140, 155, 165, 150, 175, 185, 195, 180, 165, 155, 145
        };

        int[] forecast = hw.multiplicativeForecast(data);

        assertNotNull(forecast, "El resultado no debe ser null");
        assertEquals(3, forecast.length, "El resultado debe contener solo el horizonte");

        System.out.println("Pronóstico multiplicativo: ");
        for (int i = 0; i < forecast.length; i++) {
            System.out.println("Mes futuro " + (i + 1) + ": " + forecast[i]);
        }
    }
}
