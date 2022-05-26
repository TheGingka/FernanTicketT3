package utils;

import java.util.Scanner;

public class Utils {
    public static void limpiarPantalla(){
        System.out.println("\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n");
    }
    public static void pausa() {
        var s = new Scanner(System.in);
        System.out.println();
        System.out.println("Pulse una tecla para continuar... [ENTER]");
        s.nextLine();
    }

    public static void cargando() throws InterruptedException {
        System.out.print("Cargando");
        for (int x = 0; x <4; x++){
            System.out.print(". ");
            Thread.sleep(500);
        }
        limpiarPantalla();
    }

    public static void cerrarSesion() throws InterruptedException {
        System.out.print("Cerrando sesion");
        for (int x = 0; x <4; x++){
            System.out.print(". ");
            Thread.sleep(500);
        }
        limpiarPantalla();
    }
}
