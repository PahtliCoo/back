/**
 * Delta Processing (mix our data with user provided data)
 * @author Fernando Emiliano Tavera Moreno (a01663197@tec.mx)
 * @co-author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @since 2025-06-05
 */
package life.pahtlicoo.shared.util;

public class DeltaProcess {
    public static int delta0(int x) {
        return (x == 0) ? 1 : 0;
    }

    public static double generateDeltaForecast(int x, int z) {
        int delta = delta0(x);
        return delta * z + (1 - delta) * (0.59 * z + 0.41 * x);
    }
}
