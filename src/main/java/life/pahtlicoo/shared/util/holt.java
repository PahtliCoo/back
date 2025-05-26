package life.pahtlicoo.shared.util;

import java.util.Arrays;


public class HoltWinters {
    private double alpha;  //Alpha, beta y gamma son valores "rango" que afectan a los cálculos de pronostico. Son valores fijos de 0-1. Para Alpha, mientras más bajo, es más estable (reparte el peso a todos los valores)
    private double beta; //Mientras más alto es, más agresiva es la tendencia.
    private double gamma;// Mientras más baja, más constante es nuestro patrón.
    private int seasonLength; //cuantos periodos hay en nuestros datos, como lo estamos trabajando en un año, son 12 meses, el valor debe ser fijo en 12.
    private int forecastHorizon; //cuantos periodos queremos calcular a futuro, en este caso el "3" se puede dejar fijo.

    public HoltWinters(double alpha, double beta, double gamma, int seasonLength, int forecastHorizon) { //Constructor
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        this.seasonLength = seasonLength;
        this.forecastHorizon = forecastHorizon;
    }

    public int[] forecast(int[] data) {
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

    public static void main(String[] args) {								//valores datos
        int[] data = {
            120, 130, 150, 160, 170, 200, 190, 210, 230, 250, 270, 300,
            130, 135, 160, 175, 180, 205, 195, 215, 240, 255, 280, 310
        };

        HoltWinters model = new HoltWinters(0.2, 0.1, 0.3, 12, 3);					//valores métricas, no cambiar el 12 y 3
        int[] forecast = model.forecast(data);

        System.out.println("Pronóstico (enteros redondeados):");
        for (int i = 0; i < forecast.length; i++) {
            System.out.printf("Mes %d: %d\n", data.length + i + 1, forecast[i]);
        }
    }
}
