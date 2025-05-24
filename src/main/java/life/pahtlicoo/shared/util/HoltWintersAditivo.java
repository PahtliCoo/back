import java.util.Arrays;

public class HoltWinters {
    private double alpha;
    private double beta;
    private double gamma;
    private int seasonLength;
    private int forecastHorizon;

    public HoltWinters(double alpha, double beta, double gamma, int seasonLength, int forecastHorizon) {
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        this.seasonLength = seasonLength;
        this.forecastHorizon = forecastHorizon;
    }
    // Método para pronóstico final con redondeo
    public int[] forecast(int[] data) {
        double[] result = forecastWithRawOutput(data);
        int[] rounded = new int[forecastHorizon];
        int len = data.length;
        for (int i = 0; i < forecastHorizon; i++) {
            rounded[i] = (int) Math.round(result[len + i]);
        }
        return rounded;
    }
    // Método intermedio para optimización
    public double[] forecastWithRawOutput(int[] data) {
        int len = data.length;
        double[] level = new double[len];
        double[] trend = new double[len];
        double[] season = new double[len];
        double[] forecast = new double[len + forecastHorizon];
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
            forecast[t] = level[t - 1] + trend[t - 1] + season[t - seasonLength];
        }
        // Pronóstico futuro
        for (int m = 1; m <= forecastHorizon; m++) {
            int t = len - 1;
            forecast[len + m - 1] = level[t] + m * trend[t] + season[t - seasonLength + (m % seasonLength)];
        }
        return forecast;
    }
}
