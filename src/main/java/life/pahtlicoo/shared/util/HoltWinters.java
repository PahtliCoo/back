/**
 * Holt Winters Methods
 * @author Fernando Emiliano Tavera Moreno (a01663197@tec.mx)
 * @co-author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @since 2025-05-29
 */

package life.pahtlicoo.shared.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HoltWinters {
    private double alpha;
    private double beta;
    private double gamma;
    private int seasonLength;
    private int forecastHorizon;

    public int[] multiplicativeForecast(int[] data) {
        int len = data.length;
        double[] level = new double[len];
        double[] trend = new double[len];
        double[] season = new double[len];
        double[] forecast = new double[len + forecastHorizon];
        double seasonAverage = 0.0;
        for (int i = 0; i < seasonLength; i++) {
            seasonAverage += data[i];
        }
        seasonAverage /= seasonLength;
        for (int i = 0; i < seasonLength; i++) {
            season[i] = data[i] / seasonAverage;
        }
        level[seasonLength - 1] = seasonAverage;
        trend[seasonLength - 1] = (data[seasonLength] / season[0] - seasonAverage) / seasonLength;
        // Suavizado multiplicativo
        for (int t = seasonLength; t < len; t++) {
            level[t] = alpha * (data[t] / season[t - seasonLength]) + (1 - alpha) * (level[t - 1] + trend[t - 1]);
            trend[t] = beta * (level[t] - level[t - 1]) + (1 - beta) * trend[t - 1];
            season[t] = gamma * (data[t] / level[t]) + (1 - gamma) * season[t - seasonLength];
            forecast[t] = (level[t - 1] + trend[t - 1]) * season[t - seasonLength];
        }

        // Pronóstico a futuro
        for (int m = 1; m <= forecastHorizon; m++) {
            int t = len - 1;
            forecast[len + m - 1] = (level[t] + m * trend[t]) * season[t - seasonLength + (m % seasonLength)];
        }

        //Convertir a enteros redondeados
        int[] roundedForecast = new int[forecastHorizon];
        for (int i = 0; i < forecastHorizon; i++) {
            roundedForecast[i] = (int) Math.round(forecast[len + i]);
        }
        return roundedForecast;
    }

    public int[] aditiveForecast(int[] data) {
        int len = data.length;
        double[] level = new double[len];
        double[] trend = new double[len];
        double[] season = new double[len];

        int[] forecast = new int[len + forecastHorizon];

        // Inicializar promedio de estación
        double seasonAverage = 0.0;
        for (int i = 0; i < seasonLength; i++) {
            seasonAverage += data[i];
        }
        seasonAverage /= seasonLength;

        // Inicializar estacionalidad
        for (int i = 0; i < seasonLength; i++) {
            season[i] = data[i] - seasonAverage;
        }

        level[seasonLength - 1] = seasonAverage;
        trend[seasonLength - 1] = ((data[seasonLength] - season[0]) - seasonAverage) / seasonLength;

        // Suavizado aditivo
        for (int t = seasonLength; t < len; t++) {
            level[t] = alpha * (data[t] - season[t - seasonLength]) + (1 - alpha) * (level[t - 1] + trend[t - 1]);
            trend[t] = beta * (level[t] - level[t - 1]) + (1 - beta) * trend[t - 1];
            season[t] = gamma * (data[t] - level[t]) + (1 - gamma) * season[t - seasonLength];
            forecast[t] = (int) Math.round(level[t - 1] + trend[t - 1] + season[t - seasonLength]);
        }
        // Pronóstico futuro
        for (int m = 1; m <= forecastHorizon; m++) {
            int t = len - 1;
            forecast[len + m - 1] = (int) Math.round(level[t] + m * trend[t] + season[t - seasonLength + (m % seasonLength)]);
        }
        return forecast;
    }

}
