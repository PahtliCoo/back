public class MiFuncion {
    
    public static int delta0(int x) {
        return (x == 0) ? 1 : 0;
    }
    
    public static double f(int x, int z) {
        int delta = delta0(x);
        return delta * z + (1 - delta) * (0.59 * z + 0.41 * x);
    }




    public static void main(String[] args) {
        int z = 1;                                  //Aquí se sustituye por el resultado del método Holt Winters
        int[] valoresX = {0, 1, 2, 10};              //Aquí se sustituye el valor entregado por el usuario

        for (int x : valoresX) {
            System.out.println("f(" + x + ") = " + f(x, z));
        }
    }
}
