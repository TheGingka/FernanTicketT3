package utils;

import java.util.Scanner;

public class Utils {
    //Cargar
    public static void cargando() throws InterruptedException {
        for (int x = 0; x <4; x++){
            System.out.print(". ");
            Thread.sleep(500);
        }

    }

    public static void espacio() {
        System.out.println();
        System.out.println();
        System.out.println();
    }
    public static void comporbarIncidencias(){

    }
    public static  void crearIncidencia(){
        var s = new Scanner(System.in);
        String id, comentario, prioridad;
        System.out.println("Digame la id de la incidencia");
        id = s.nextLine();
        System.out.println("Cual es el motivo por el que abre la incidencia");
        comentario = s.nextLine();
        System.out.println("Que prioridad tiene");
        prioridad = s.nextLine();
    }
}
