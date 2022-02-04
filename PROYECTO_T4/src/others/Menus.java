package others;

import java.util.Scanner;

public class Menus {
    public static Scanner s = new Scanner(System.in);
    public static int menuPrincipal() {
        int opc = 0;
        do{
            System.out.println();
            System.out.print("""
                    ┌────────────────────────────────────────────────────────────────────────────┐
                    │   _____ _____ ____  _   _    _    _   _ _____ ___ ____ _  _______ _____    │
                    │  |  ___| ____|  _ \\| \\ | |  / \\  | \\ | |_   _|_ _/ ___| |/ / ____|_   _|   │
                    │  | |_  |  _| | |_) |  \\| | / _ \\ |  \\| | | |  | | |   | ' /|  _|   | |     │
                    │  |  _| | |___|  _ <| |\\  |/ ___ \\| |\\  | | |  | | |___| . \\| |___  | |     │
                    │  |_|   |_____|_| \\_\\_| \\_/_/   \\_\\_| \\_| |_| |___\\____|_|\\_\\_____| |_|     │
                    └───────────────────────┬────────────────────────────┬───────────────────────┘
                                            │      1.INICIAR SESION      │
                                            ├────────────────────────────┤
                                            │       2.REGISTRARSE        │
                                            └────────────────────────────┘
                                                  INTRODUCE OPCION: """);
            try{
                return opc = Integer.parseInt(s.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Selecciona una opcion posible");
            }
        }while(opc != 1 || opc != 2);
        return 0;
    }
    public static void menuUsuario() {
        System.out.println();
        System.out.print("""
                      ┌────────────────────────────────────┐
                      │       FERNANTICKET - USUARIO       │
                ┌─────┼────────────────────────────────────┼──────┐
                ├─────────────────────────────────────────────────┤
                │     1.- Registrar una nueva incidencia.         │
                │     2.- Consultar mis incidencias abiertas      │
                │     3.- Consultar mis incidencias cerradas      │
                │     4.- Mostrar mi perfil                       │
                │     5.- Cambiar clave de acceso                 │
                │     6.- Cerrar sesión                           │
                ├─────────────────────────────────────────────────┘
                └─────── INTRODUCE OPCION:""");
    }

    public static void menuTecnico() {
        System.out.println();
        System.out.print("""
                      ┌────────────────────────────────────┐
                      │       FERNANTICKET - TECNICO       │
                ┌─────┼────────────────────────────────────┼──────────────┐
                ├─────────────────────────────────────────────────────────┤
                │     1.- Consultar las incidencias que tengo asignadas   │
                │     2.- Marcar una incidencia como cerrada              │
                │     3.- Consultar las incidencias que he resuelto       │
                │     4.- Mostrar mi perfil                               │
                │     5.- Cambiar clave de acceso                         │
                │     6.- Cerrar sesión                                   │
                ├─────────────────────────────────────────────────────────┘
                └─────── INTRODUCE OPCION:""");

    }

    public static void menuAdmin() {
        System.out.println();
        System.out.print("""
                      ┌────────────────────────────────────┐
                      │     FERNANTICKET - ADMINISTRADOR   │
                ┌─────┼────────────────────────────────────┼──────────────┐
                ├─────────────────────────────────────────────────────────┤
                ├─────────────────────────────────────────────────────────┤
                │     1.- Consultar todas las incidencias                 │
                │     2.- Consultar todos los usuarios                    │
                │     3.- Consultar todos los tecnicos                    │
                │     4.- Asignar una incidencia a un tecnico             │
                │     5.- Dar de alta un tecnico                          │
                │     6.- Borrar un tecnico                               │
                │     7.- Cerrar sesión                                   │
                │     8.- Cerrar programa                                 │
                ├─────────────────────────────────────────────────────────┘
                └─────── INTRODUCE OPCION:""");
    }
}
