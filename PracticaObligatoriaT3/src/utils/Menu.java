package utils;

public class Menu {
    public static void menuPrincipal() {
        System.out.println("\n" +
                "  _____                         _____ _      _        _   \n" +
                " |  ___|__ _ __ _ __   __ _ _ _|_   _(_) ___| | _____| |_ \n" +
                " | |_ / _ \\ '__| '_ \\ / _` | '_ \\| | | |/ __| |/ / _ \\ __|\n" +
                " |  _|  __/ |  | | | | (_| | | | | | | | (__|   <  __/ |_ \n" +
                " |_|  \\___|_|  |_| |_|\\__,_|_| |_|_| |_|\\___|_|\\_\\___|\\__|");
        System.out.println("                1. Inicio de sesión             ");
        System.out.println();
        System.out.println("                  2. Registrarse");
        System.out.print("Elija una opción: ");
    }

    public static void menuInciarSesion() {
        System.out.println(" Con que tipo de usuario desea inciar sesion:\n" +
                "    1.- Usuario normal\n" +
                "    2.- Tecnico\n" +
                "    3.- Administrador");
        System.out.print("Elija una opción: ");
    }
    public static void menuUsuario() {
        System.out.println();
        System.out.println(" Menú:\n" +
                "    1.- Registrar una nueva incidencia\n" +
                "    2.- Consultar mis incidencias abiertas\n" +
                "    3.- Consultar mis incidencias cerradas\n" +
                "    4.- Mostrar mi perfil\n" +
                "    5.- Cambiar clave de acceso\n" +
                "    6.- Cerrar sesión");
        System.out.print("Elija una opción: ");
    }

    public static void menuTecnico() {
        System.out.println();
        System.out.println(" Menú:\n" +
                "    1.- Consultar las incidencias que tengo asignadas\n" +
                "    2.- Marcar una incidencia como cerrada\n" +
                "    3.- Consultar las incidencias que he resuelto\n" +
                "    4.- Mostrar mi perfil\n" +
                "    5.- Cambiar clave de acceso\n" +
                "    6.- Cerrar sesión");
        System.out.print("Elija una opción: ");
    }

    public static void menuAdmin() {
        System.out.println();
        System.out.println(" Menú:\n" +
                "    1.- Consultar todas las incidencias\n" +
                "    2.- Consultar todos los usuarios\n" +
                "    3.- Consultar todos los tecnicos\n" +
                "    4.- Asignar una incidencia a un tecnico\n" +
                "    5.- Dar de alta un tecnico\n" +
                "    6.- Borrar un tecnico\n" +
                "    7.- Cerrar sesión\n" +
                "    8.- Cerrar programa");
        System.out.print("Elija una opción: ");
    }
}
