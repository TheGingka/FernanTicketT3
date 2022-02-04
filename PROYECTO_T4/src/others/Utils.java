package others;

import java.util.Scanner;

public class Utils {
    public static Scanner s = new Scanner(System.in);
    public static void limpiarPantalla(){
        System.out.println("\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n");
    }

    public static String esperar(){
        System.out.println("Pulse tecla para continuar");
        return s.nextLine();
    }

    public static void cargando() throws InterruptedException {
        for (int x = 0; x <4; x++){
            System.out.print(". ");
            Thread.sleep(500);
        }

    }
}
