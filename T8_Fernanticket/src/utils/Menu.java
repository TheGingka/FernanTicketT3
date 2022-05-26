package utils;

public class Menu {
    public static void menuDeCarga() throws InterruptedException {

        System.out.print("Cargando");
        for (int x = 0; x <4; x++){
            System.out.print(". ");
            Thread.sleep(500);
        }
    }

    public static void menuUsuario(int incidenciasA, int incidenciasP) {

        System.out.print("" +
                "                      ┌────────────────────────────────────┐\n" +
                "                      │       FERNANTICKET - USUARIO       │\n" +
                "                ┌─────┼────────────────────────────────────┼──────┐\n" +
                "                ├─────────────────────────────────────────────────┤\n" +
                "                │ INCIDENCIAS ABIERTAS: " + incidenciasA + "\t    \n" +
                "                ├─────────────────────────────────────────────────┤\n" +
                "                │ INCIDENCIAS EN PROCESO: " + incidenciasP + "\t  \n" +
                "                ├─────────────────────────────────────────────────┤\n" +
                "                │     1.- Registrar una nueva incidencia.         │\n" +
                "                │     2.- Consultar mis incidencias abiertas      │\n" +
                "                │     3.- Consultar mis incidencias cerradas      │\n" +
                "                │     4.- Mostrar mi perfil                       │\n" +
                "                │     5.- Cambiar clave de acceso                 │\n" +
                "                │     6.- Cerrar sesión                           │\n" +
                "                ├─────────────────────────────────────────────────┘\n" +
                "                └─────── INTRODUCE OPCION: ");
    }

    public static void menuTecnico(int incidenciasA, int incidenciasC, double prioridadMedia, String idTecnico) {

        System.out.print("" +
                "                      ┌────────────────────────────────────┐\n" +
                "                      │       FERNANTICKET -"+ idTecnico + "        \n" +
                "                ┌─────┼────────────────────────────────────┼──────┐\n" +
                "                ├─────────────────────────────────────────────────────────────┤\n" +
                "                │ INCIDENCIAS ABIERTAS: " + incidenciasA + "\t    \n" +
                "                ├─────────────────────────────────────────────────────────────┤\n" +
                "                │ INCIDENCIAS CERRADAS: " + incidenciasC + "\t  \n" +
                "                ├─────────────────────────────────────────────────────────────┤\n" +
                "                │ PRIORIDAD MEDIA: " + (prioridadMedia == 0.0f ? prioridadMedia : "0") + "\t  \n" +
                "                ├─────────────────────────────────────────────────────────────┤\n" +
                "                │     1.- Consultar las incidencias asignadas no resueltas    │\n" +
                "                │     2.- Marcar una incidencia como resuelta                 │\n" +
                "                │     3.- Consultar mis incidencias cerradas                  │\n" +
                "                │     4.- Mostrar mi perfil                                   │\n" +
                "                │     5.- Cambiar clave de acceso                             │\n" +
                "                │     6.- Cerrar sesión                                       │\n" +
                "                ├─────────────────────────────────────────────────────────────┘\n" +
                "                └─────── INTRODUCE OPCION: ");
    }


    public static void menuAdmin(int incidenciasSinAsignar, int incidenciasSinResolverse, String idAdmin) {

        System.out.print("" +
                "                      ┌────────────────────────────────────┐\n" +
                "                      │       FERNANTICKET -"+ idAdmin + "        \n" +
                "                ┌─────┼────────────────────────────────────┼──────┐\n" +
                "                ├─────────────────────────────────────────────────────────────┤\n" +
                "                │ INCIDENCIAS ABIERTAS SIN ASIGNAR: " + incidenciasSinAsignar + "\t    \n" +
                "                ├─────────────────────────────────────────────────────────────┤\n" +
                "                │ INCIDENCIAS ASIGNADAS SIN RESOLVERSE: " + incidenciasSinResolverse + "\t  \n" +
                "                ├─────────────────────────────────────────────────────────────┤\n" +
                "                │     1.- Consultar todas las inicencias abiertas.            │\n" +
                "                │     2.- Consultar todas las incidencias cerradas.           │\n" +
                "                │     3.- Consultar incidencias por término.                  │\n" +
                "                │     4.- Consultar los técnicos.                             │\n" +
                "                │     5.- Asignar una incidencia a un técnico.                │\n" +
                "                │     6.- Dar de alta un técnico.                             │\n" +
                "                │     7.- Borrar un técnico.                                  │\n" +
                "                │     8.- Consultar los usuarios.                             │\n" +
                "                │     9.- Estadísticas de la aplicación.                      │\n" +
                "                │     10.- Mandar incidencias pendientes.                     │\n" +
                "                │     11.- Properties.                                        │\n" +
                "                │     12.- Cerrar sesión.                                     │\n" +
                "                ├─────────────────────────────────────────────────────────────┘\n" +
                "                └─────── INTRODUCE OPCION: ");
    }

    public static void menuInicio() {
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
                                                  INTRODUCE OPCION: """ + " ");
    }


}
