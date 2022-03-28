import models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        // Información para Telegram.
        String API = "bot5178503748:AAEXwKV-nR468nDkUFqgDuYe6y4a20tkvys";
        String CHATID = "785131120";

        var s = new Scanner(System.in);
        int opcionMenuInicio;

        // Declaración de objetos y usuarios.
        Object userTemp;
        Usuario user;
        Tecnico tech;
        Admin admin;

        // Declaración de email, contraseña.
        String email;
        String clave;
        String clave2;

        // Declaracion de salidas de menú.

        int opcionMenuUsuario = 0;
        int opcionMenuTecnico = 0;
        int opcionMenuAdmin = 0;

        GestionAPP gestionAPP = new GestionAPP();

        do {
            menuInicio();
            opcionMenuInicio = Integer.parseInt(s.nextLine());

            switch (opcionMenuInicio) {

                case 1: // Inicio de sesión.


                    do { // Introducir el email.
                        System.out.println("Introduce email: ");
                        email = s.nextLine();
                        if (email.equals("")) System.out.println("Introduce información en el campo.");
                    }while(email.equals(""));


                    do { // Introducir la clave.
                        System.out.println("Introduce clave: ");
                        clave = s.nextLine();

                        if (clave.equals("")) System.out.println("Introduce información en el campo.");
                    }while(clave.equals(""));

                    userTemp = gestionAPP.login(email, clave);

                    if (userTemp != null) { // Comprobación de objetos, admin, tech o usuario.
                        if (userTemp instanceof Usuario) { // Comprobación de objetos, usuario.

                            user = (Usuario) userTemp;

                            do {

                                menuUsuario(gestionAPP.incidenciasAbiertasByUsuario(user.getId()), gestionAPP.incidenciasAsignadasByUsuario(user.getId()));

                                do { // Introducir menú usuario.
                                    try{
                                        opcionMenuUsuario = Integer.parseInt(s.nextLine());
                                    }catch (NumberFormatException e){
                                        System.out.println("Introduce una opción válida.");
                                    }
                                    if (opcionMenuUsuario < 1 || opcionMenuUsuario > 6) System.out.println("Introduce una opción disponible.");
                                }while(opcionMenuUsuario < 1 || opcionMenuUsuario > 6);

                                switch (opcionMenuUsuario) {

                                    case 1:

                                        System.out.println("Introduce el problema: ");
                                        String descripcion;

                                        do {
                                            descripcion = s.nextLine();
                                            if (descripcion.equals("")) System.out.println("Introduce información.");
                                        }while(descripcion.equals(""));

                                        System.out.println("Introduce prioridad: ");
                                        int prioridad = Integer.parseInt(s.nextLine());

                                        if (gestionAPP.insertaIncidencia(descripcion, user.getEmail(), prioridad)) { // Inserta incidencia.
                                            System.out.println("Incidencia subida.");

                                            String mensaje = "El usuario: " + user.getEmail() + " ha insertado una nueva incidencia.";

                                            if (enviaMensajeTelegram(mensaje, API, CHATID)){
                                                System.out.println("Mensaje enviado con éxito.");
                                            }else System.out.println("Fallo para enviar el mensaje.");

                                        } else {
                                            System.out.println("Incidencia no subida.");
                                        }

                                        break;

                                    case 2: // Incidencias abiertas en USUARIO.

                                        if (user.getIncidencias().size() == 0){
                                            System.out.println("No hay incidencias abiertas.");
                                        }else{
                                            for (IncidenciaDataClass i:
                                                    gestionAPP.buscaIncidenciasSinAsignar()) {
                                                if (i.getIdUsuario() == user.getId())
                                                    System.out.println(i);
                                            }
                                        }

                                        System.out.println();
                                        break;

                                    case 3: // Incidencias cerradas de USUARIO.

                                        System.out.println("Incidencias cerradas: \n");

                                        for (IncidenciaDataClass i:
                                                gestionAPP.buscaIncidenciasCerradasbyUsuario(user.getId())) {
                                            System.out.println(i);
                                        }

                                        break;

                                    case 4: // Muestra la información de USUARIO actual.

                                        System.out.println(user);

                                        break;

                                    case 5: // Cambia la clave de USUARIO.

                                        System.out.println("Introduce la clave anterior: ");

                                        String claveantigua;

                                        do {
                                            claveantigua = s.nextLine();
                                            if (claveantigua.equals("")) System.out.println("ERROR. Introduce información en el campo.");
                                        }while(claveantigua.equals(""));

                                        System.out.println("Introduce nueva clave: ");
                                        String clavenueva;

                                        do {
                                            clavenueva = s.nextLine();
                                            if (clavenueva.equals("")) System.out.println("ERROR. Introduce información en el campo.");
                                        }while(clavenueva.equals(""));


                                        System.out.println("Introduce nueva clave de nuevo: ");
                                        String clavenueva2;

                                        do {
                                            clavenueva2 = s.nextLine();
                                            if (clavenueva2.equals("")) System.out.println("ERROR. Introduce información en el campo.");
                                        }while(clavenueva2.equals(""));

                                        System.out.println(gestionAPP.cambiaClaveUsuario(claveantigua, clavenueva, clavenueva2) ? "Cambiada con éxito" : "Error en algún parámetro.");

                                        String mensaje = "El usuario: " + user.getEmail() + " ha cambiado su contraseña.";

                                        if (enviaMensajeTelegram(mensaje, API, CHATID)){
                                            System.out.println("Mensaje enviado con éxito.");
                                        }else System.out.println("Fallo para enviar el mensaje.");

                                        break;

                                    case 6: // Cerrar sesión de USUARIO.
                                        System.out.println("Cerrando sesión.");
                                        break;
                                }
                            } while (opcionMenuUsuario != 6);
                        } else {

                            if (userTemp instanceof Tecnico) { // Comprobación de objetos, tecnico.

                                tech = (Tecnico) userTemp;

                                do { // Menú de TÉCNICO.

                                menuTecnico(gestionAPP.incidenciasAbiertasAsignadasByTecnico(tech.getId()), gestionAPP.incidenciasCerradasByTecnico(tech.getId()), gestionAPP.prioridadMediaAppByTecnico(tech.getId()), tech.getNombre());
                                opcionMenuTecnico = Integer.parseInt(s.nextLine());

                                /*
                                  1. Consultar las incidencias asignadas no resueltas.
                                  2. Marcar una incidencia como resuelta.
                                  3. Consultar mis incidencia cerradas.
                                  4. Mostrar perfil.
                                  5. Cambiar clave de acceso.
                                  6. Cerrar sesion.*/

                                switch (opcionMenuTecnico){

                                    case 1: // Comprobar incidencias asignadas no resueltas en TÉCNICO.

                                        System.out.println("Incidencias asignadas no resueltas: \n");

                                        for (IncidenciaDataClass i:
                                                gestionAPP.buscaIncidenciasAbiertasbyTecnico(tech.getId())) {
                                            System.out.println(i);
                                        }

                                        break;

                                    case 2: // Marcar una incidencia como resuelta.

                                        ArrayList<IncidenciaDataClass> incidenciaResolver = new ArrayList<>(gestionAPP.buscaIncidenciasAbiertasbyTecnico(tech.getId()));

                                        if (incidenciaResolver.size() == 0){
                                            System.out.println("No hay incidencias para resolver en este momento. \n");

                                        }else{

                                            for (int i = 0; i < incidenciaResolver.size() ; i++) {
                                                System.out.println((i+1) + " : " + incidenciaResolver.get(i));
                                            }

                                            System.out.println("¿Qué incidencia quiere marcar como resuelta?: ");
                                            int opcionIncidencia;

                                            do {
                                                opcionIncidencia = Integer.parseInt(s.nextLine());
                                                if (opcionIncidencia > incidenciaResolver.size() || opcionIncidencia < 1)
                                                    System.out.println("Introduce una opción en el rango.");
                                            }while(opcionIncidencia > incidenciaResolver.size() || opcionIncidencia < 1);


                                            int idIncidencia = incidenciaResolver.get(opcionIncidencia - 1).getId();

                                            System.out.println("Solución: ");
                                            String solucion = s.nextLine();

                                            gestionAPP.cierraIncidencia(idIncidencia,tech.getId(),solucion);

                                            String mensaje = "Se ha resuelto la incidencia " + idIncidencia + " por el técnico: " + tech.getNombre();

                                            if (enviaMensajeTelegram(mensaje, API, CHATID)){
                                                System.out.println("Mensaje enviado con éxito.");
                                            }else System.out.println("Fallo para enviar el mensaje.");

                                        }
                                        break;

                                    case 3: //

                                        if (gestionAPP.buscaIncidenciasCerradassbyTecnico(tech.getId()).size() == 0){
                                            System.out.println("No ha resuelto ninguna incidencia.");
                                        }else{
                                            for (IncidenciaDataClass i:
                                                    gestionAPP.buscaIncidenciasCerradassbyTecnico(tech.getId())) {
                                                System.out.println(i);
                                            }
                                        }

                                        break;

                                    case 4:

                                        System.out.println(tech);

                                        break;
                                    case 5:

                                        System.out.println("Introduce la clave anterior: ");
                                        String claveantigua = s.nextLine();

                                        System.out.println("Introduce nueva clave: ");
                                        String clavenueva = s.nextLine();

                                        System.out.println("Introduce nueva clave de nuevo: ");
                                        String clavenueva2 = s.nextLine();

                                        System.out.println(gestionAPP.cambiaClaveTecnico(claveantigua, clavenueva, clavenueva2) ? "Cambiada con éxito" : "Error en algún parámetro.");

                                        String mensaje = "El técnico: " + tech.getEmail() + " ha cambiado su contraseña.";

                                        if (enviaMensajeTelegram(mensaje, API, CHATID)){
                                            System.out.println("Mensaje enviado con éxito.");
                                        }else System.out.println("Fallo para enviar el mensaje.");

                                        break;

                                    case 6:
                                        System.out.println("Bye.");
                                        break;

                                }
                                }while(opcionMenuTecnico != 6);


                            } else {
                                if (userTemp instanceof Admin) { // Comprobación de objetos, admin.
                                    admin = (Admin) userTemp;

                                    do {

                                    menuAdmin(gestionAPP.incidenciasAbiertas(), gestionAPP.incidenciasSinAsignar(), admin.getNombre());
                                    opcionMenuAdmin = Integer.parseInt(s.nextLine());

                                    switch (opcionMenuAdmin) {

                                        case 1: // Incidencias abiertas GENERALES.
                                            System.out.println("Todas las incidencias abiertas: \n");

                                            for (IncidenciaDataClass i:
                                                    gestionAPP.buscaTodasIncidenciasAbiertas()) {
                                                System.out.println(i);
                                            }

                                            break;
                                        case 2: //Incidencias cerradas GENERALES.

                                            System.out.println("Todas las incidencias cerradas: \n");

                                            for (IncidenciaDataClass i:
                                                 gestionAPP.buscaIncidenciasCerradas()) {
                                                System.out.println(i);
                                            }
                                            break;
                                        case 3: // Busqueda por término.

                                            System.out.println("Introduce el termino: ");

                                            String termino =  s.nextLine();

                                            for (IncidenciaDataClass i:
                                                    gestionAPP.buscaIncidenciasbyTerm(termino)) {
                                                System.out.println(i);
                                            }

                                            break;
                                        case 4: // Información de TECNICO.

                                            for (Tecnico t:
                                                 gestionAPP.getTecnicos()) {
                                                System.out.println(t);
                                            }

                                            break;
                                        case 5: // Asignar incidencia a un TECNICO.

                                            // Variables para la opción 5.

                                            int contadorI = 1;
                                            int contadorT = 1;
                                            int idIncidencia = 0;
                                            int idTecnico = 0;

                                            for (IncidenciaDataClass i:
                                                 gestionAPP.buscaIncidenciasSinAsignar()) {
                                                System.out.println(contadorI + ": " + i);
                                                contadorI++;
                                            }
                                            
                                            System.out.println("¿Qué incidencia quiere asignar?: ");

                                            int asignarIncidencia;

                                            do {
                                                asignarIncidencia = Integer.parseInt(s.nextLine());
                                            }while(asignarIncidencia < 1 || asignarIncidencia > gestionAPP.buscaIncidenciasSinAsignar().size());


                                            for (int i = 0; i < gestionAPP.buscaIncidenciasSinAsignar().size(); i++) {
                                                if (asignarIncidencia - 1 == i)
                                                    idIncidencia = gestionAPP.buscaIncidenciasSinAsignar().get(i).getId();
                                            }

                                            for (Tecnico t:
                                                 gestionAPP.getTecnicos()) {
                                                System.out.println(contadorT + ": " + t);
                                                contadorT++;
                                            }
                                            
                                            System.out.println("¿A qué técnico?: ");
                                            int asignarTecnico;

                                            do {
                                                asignarTecnico = Integer.parseInt(s.nextLine());
                                            }while(asignarTecnico < 1 || asignarTecnico > gestionAPP.getTecnicos().size());

                                            for (int i = 0; i < gestionAPP.getTecnicos().size(); i++) {
                                                if (asignarTecnico - 1 == i)
                                                    idTecnico = gestionAPP.getTecnicos().get(i).getId();
                                            }
                                            
                                            gestionAPP.asignaIncidencia(idIncidencia,idTecnico);

                                            String mensaje = "Se ha asignado la incidencia " + idIncidencia + " al tecnico: " + gestionAPP.buscaTecnico(idTecnico).getEmail();

                                            if (enviaMensajeTelegram(mensaje, API, CHATID)){
                                                System.out.println("Mensaje enviado con éxito.");
                                            }else System.out.println("Fallo para enviar el mensaje.");

                                            break;

                                        case 6: // Crear un TECNICO.

                                            System.out.println("Creación de perfil tecnico.");

                                            do {

                                                System.out.println("Introduce email: ");
                                                email = s.nextLine();

                                                if (!gestionAPP.comprobarEmailTecnico(email))
                                                    System.out.println("Email ya existente en la base, elija otro.");

                                            }while(!gestionAPP.comprobarEmailUsuario(email));


                                            do {
                                                System.out.println("Introduce clave: ");
                                                clave = s.nextLine();

                                                System.out.println("Introduce clave otra vez: ");
                                                clave2 = s.nextLine();

                                                if (!gestionAPP.comprobarClave(clave, clave2))
                                                    System.out.println("Claves diferentes, introduce de nuevo la clave.");
                                            } while (!gestionAPP.comprobarClave(clave, clave2));


                                            System.out.println("Introduce nombre: ");
                                            String nombre = s.nextLine();

                                            System.out.println("Introduce apellido: ");
                                            String apellido = s.nextLine();

                                            System.out.println(gestionAPP.introducirTecnico(nombre, apellido, clave, email) ? "Tecnico creado." : "Tecnico no creado.");

                                            mensaje = "Se ha creado un nuevo técnico, con email: " + email;

                                            if (enviaMensajeTelegram(mensaje, API, CHATID)){
                                                System.out.println("Mensaje enviado con éxito.");
                                            }else System.out.println("Fallo para enviar el mensaje.");

                                            break;

                                        case 7: //Borrar tecnico. //TODO: No borrar el técnico con cerradas, borrar el técnico y mover sus incidencias abiertas.

                                            int contadorTecnicos = 1;

                                            for (Tecnico t:
                                                 gestionAPP.getTecnicos()) {
                                                System.out.println(contadorTecnicos + ":" + t);
                                            }

                                            System.out.println("¿Qué técnico desea borrar?: ");
                                            int deleteTecnico = Integer.parseInt(s.nextLine());

                                            if (gestionAPP.sePuedeBorrarTecnico(gestionAPP.getTecnicos().get(deleteTecnico - 1).getId())){
                                                System.out.println("Este técnico se borrará.");
                                                gestionAPP.borrarTecnico(gestionAPP.getTecnicos().get(deleteTecnico - 1).getId());
                                            }else{
                                                System.out.println("Este técnico no puede borrarse porque tiene incidencias resueltas.");
                                            }

                                            break;

                                        case 8: //Consultar usuarios.

                                            for (Usuario u:
                                                 gestionAPP.getUsuarios()) {
                                                System.out.println(u);
                                            }

                                            break;

                                        case 9: //Estadísticas.

                                            System.out.println("Hay " + gestionAPP.getTecnicos().size()+ " técnicos.");
                                            System.out.println("Hay " + gestionAPP.getUsuarios().size()+ " usuarios.");
                                            System.out.println("Incidencias abiertas: " + gestionAPP.incidenciasAbiertas());
                                            System.out.println("Indicencias sin asignar" + gestionAPP.incidenciasSinAsignar());


                                            break;

                                        case 10:
                                            System.out.println("Cerrando.");
                                            break;

                                    }

                                    }while(opcionMenuAdmin != 10);

                                }
                            }
                        }
                    } else {
                        System.out.println("No hay coincidencias.");
                    }
                    break;

                case 2: //Registro con token.

                    do {

                        System.out.println("Introduce email: ");
                        email = s.nextLine();

                        if (!gestionAPP.comprobarEmailUsuario(email))
                            System.out.println("Email ya existente en la base, elija otro.");

                    }while(!gestionAPP.comprobarEmailUsuario(email));

                    int token = generaToken(); // Generamos el token.

                    String mensaje = mensaje(token);


                    if (enviaMail(email,mensaje,"Token de registro - FernanTicket")){
                        System.out.println("Token enviado con éxito a su correo.");

                    }else System.out.println("Fallo al enviar el token.");

                    System.out.println("Introduce el token enviado al correo: ");
                    int respuestaToken = Integer.parseInt(s.nextLine());


                    if (token != respuestaToken){
                        System.out.println("Token inválido. Vuelva a crear la cuenta.");
                    }else{

                        do {
                            System.out.println("Introduce clave: ");
                            clave = s.nextLine();

                            System.out.println("Introduce clave otra vez: ");
                            clave2 = s.nextLine();

                            if (!gestionAPP.comprobarClave(clave, clave2))
                                System.out.println("Claves diferentes, introduce de nuevo la clave.");
                        } while (!gestionAPP.comprobarClave(clave, clave2));


                        System.out.println("Introduce nombre: ");
                        String nombre = s.nextLine();

                        System.out.println("Introduce apellido: ");
                        String apellido = s.nextLine();

                        System.out.println(gestionAPP.introducirUsuario(nombre, apellido, clave, email) ? "Usuario creado." : "Usuario no creado.");

                    }

                    break;
                case 3:
                    break;

            }

        } while (opcionMenuInicio != 3);
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
                "                │ PRIORIDAD MEDIA: " + prioridadMedia + "\t  \n" +
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
                "                │     3.- Consultar los técnicos.                             │\n" +
                "                │     4.- Consultar incidencias por término.                  │\n" +
                "                │     5.- Asignar una incidencia a un técnico.                │\n" +
                "                │     6.- Dar de alta un técnico.                             │\n" +
                "                │     7.- Borrar un técnico.                                  │\n" +
                "                │     8.- Consultar los usuarios.                             │\n" +
                "                │     9.- Estadísticas de la aplicación.                      │\n" +
                "                │     10.- Cerrar sesión.                                     │\n" +
                "                ├─────────────────────────────────────────────────────────────┘\n" +
                "                └─────── INTRODUCE OPCION: ");
    }

    private static void menuInicio() {
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
                                                  INTRODUCE OPCION:""");
    }

    public static boolean enviaMensajeTelegram(String mensaje, String API, String CHATID){
        String direccion;
        String fijo = "https://api.telegram.org/" + API + "/sendMessage?chat_id=" + CHATID + "&text=";
        direccion =  fijo+mensaje;
        URL url;
        boolean dev;
        dev = false;
        try{
            url = new URL(direccion);
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            dev = true;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return dev;

    }

    public static int generaToken(){

        return (int) (Math.random()*100);

    }

    static boolean enviaMail(String destino, String mensaje, String asunto){

        boolean resultado;
        resultado = false;

        String emisor = "Txnio";
        String username = "dam6@carlosprofe.es";
        String password = "Olivo.2022";

        String host = "ssl0.ovh.net";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emisor));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));

            message.setSubject(asunto);

            message.setContent(mensaje, "text/html; charset=utf-8");

            Transport.send(message);
            resultado = true;
        }catch(MessagingException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public static String mensaje(int token){
        return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "<!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "  <o:OfficeDocumentSettings>\n" +
                "    <o:AllowPNG/>\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "  </o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]-->\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n" +
                "  <title></title>\n" +
                "  \n" +
                "    <style type=\"text/css\">\n" +
                "      table, td { color: #000000; } a { color: #01499d; text-decoration: underline; } @media (max-width: 480px) { #u_content_heading_1 .v-container-padding-padding { padding: 20px !important; } #u_content_heading_1 .v-font-size { font-size: 23px !important; } #u_content_text_2 .v-container-padding-padding { padding: 40px 30px 50px 15px !important; } #u_content_button_1 .v-size-width { width: 81% !important; } }\n" +
                "@media only screen and (min-width: 620px) {\n" +
                "  .u-row {\n" +
                "    width: 600px !important;\n" +
                "  }\n" +
                "  .u-row .u-col {\n" +
                "    vertical-align: top;\n" +
                "  }\n" +
                "\n" +
                "  .u-row .u-col-100 {\n" +
                "    width: 600px !important;\n" +
                "  }\n" +
                "\n" +
                "}\n" +
                "\n" +
                "@media (max-width: 620px) {\n" +
                "  .u-row-container {\n" +
                "    max-width: 100% !important;\n" +
                "    padding-left: 0px !important;\n" +
                "    padding-right: 0px !important;\n" +
                "  }\n" +
                "  .u-row .u-col {\n" +
                "    min-width: 320px !important;\n" +
                "    max-width: 100% !important;\n" +
                "    display: block !important;\n" +
                "  }\n" +
                "  .u-row {\n" +
                "    width: calc(100% - 40px) !important;\n" +
                "  }\n" +
                "  .u-col {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "  .u-col > div {\n" +
                "    margin: 0 auto;\n" +
                "  }\n" +
                "}\n" +
                "body {\n" +
                "  margin: 0;\n" +
                "  padding: 0;\n" +
                "}\n" +
                "\n" +
                "table,\n" +
                "tr,\n" +
                "td {\n" +
                "  vertical-align: top;\n" +
                "  border-collapse: collapse;\n" +
                "}\n" +
                "\n" +
                "p {\n" +
                "  margin: 0;\n" +
                "}\n" +
                "\n" +
                ".ie-container table,\n" +
                ".mso-container table {\n" +
                "  table-layout: fixed;\n" +
                "}\n" +
                "\n" +
                "* {\n" +
                "  line-height: inherit;\n" +
                "}\n" +
                "\n" +
                "a[x-apple-data-detectors='true'] {\n" +
                "  color: inherit !important;\n" +
                "  text-decoration: none !important;\n" +
                "}\n" +
                "\n" +
                "</style>\n" +
                "  \n" +
                "  \n" +
                "\n" +
                "<!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Raleway:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><link href=\"https://fonts.googleapis.com/css?family=Rubik:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #e7e7e7;color: #000000\">\n" +
                "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #e7e7e7;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "  <tbody>\n" +
                "  <tr style=\"vertical-align: top\">\n" +
                "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
                "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #e7e7e7;\"><![endif]-->\n" +
                "    \n" +
                "\n" +
                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #01499d;\">\n" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #01499d;\"><![endif]-->\n" +
                "      \n" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
                "  \n" +
                "<table id=\"u_content_heading_1\" style=\"font-family:'Rubik',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 25px;font-family:'Rubik',sans-serif;\" align=\"left\">\n" +
                "        \n" +
                "  <h1 class=\"v-font-size\" style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Raleway',sans-serif; font-size: 27px;\">\n" +
                "    FernanTicket\n" +
                "  </h1>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n" +
                "\n" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "  </div>\n" +
                "</div>\n" +
                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f5f5f5;\">\n" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #f5f5f5;\"><![endif]-->\n" +
                "      \n" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"598\" style=\"width: 598px;padding: 0px;border-top: 1px solid #CCC;border-left: 1px solid #CCC;border-right: 1px solid #CCC;border-bottom: 1px solid #CCC;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 1px solid #CCC;border-left: 1px solid #CCC;border-right: 1px solid #CCC;border-bottom: 1px solid #CCC;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
                "  \n" +
                "<table id=\"u_content_text_2\" style=\"font-family:'Rubik',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 50px 50px;font-family:'Rubik',sans-serif;\" align=\"left\">\n" +
                "        \n" +
                "  <div style=\"color: #5c5c5c; line-height: 170%; text-align: left; word-wrap: break-word;\">\n" +
                "    <p style=\"font-size: 14px; line-height: 170%;\">Hola,</p>\n" +
                "<p style=\"font-size: 14px; line-height: 170%;\">&nbsp;</p>\n" +
                "<p style=\"font-size: 14px; line-height: 170%;\">Hemos recibido un registro con este correo.</p>\n" +
                "<p style=\"font-size: 14px; line-height: 170%;\">&nbsp;</p>\n" +
                "<p style=\"font-size: 14px; line-height: 170%;\">Para poder registrarte correctamente tienes que utilizar el siguiente token que aparece justo debajo:</p>\n" +
                "  </div>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n" +
                "\n" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "  </div>\n" +
                "</div>\n" +
                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #01499d;\">\n" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #01499d;\"><![endif]-->\n" +
                "      \n" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
                "  \n" +
                "<table id=\"u_content_button_1\" style=\"font-family:'Rubik',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:35px 10px;font-family:'Rubik',sans-serif;\" align=\"left\">\n" +
                "        \n" +
                "<div align=\"center\">\n" +
                "  <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;font-family:'Rubik',sans-serif;\"><tr><td style=\"font-family:'Rubik',sans-serif;\" align=\"center\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"\" style=\"height:51px; v-text-anchor:middle; width:267px;\" arcsize=\"0%\" strokecolor=\"#ffffff\" strokeweight=\"3px\" fillcolor=\"#01499d\"><w:anchorlock/><center style=\"color:#FFFFFF;font-family:'Rubik',sans-serif;\"><![endif]-->\n" +
                "    <a target=\"_blank\" class=\"v-size-width\" style=\"box-sizing: border-box;display: inline-block;font-family:'Rubik',sans-serif;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #FFFFFF; background-color: #01499d; border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px; width:47%; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;border-top-color: #ffffff; border-top-style: solid; border-top-width: 3px; border-left-color: #ffffff; border-left-style: solid; border-left-width: 3px; border-right-color: #ffffff; border-right-style: solid; border-right-width: 3px; border-bottom-color: #ffffff; border-bottom-style: solid; border-bottom-width: 3px;\">\n" +
                "      <span style=\"display:block;padding:16px 20px;line-height:120%;\"><strong><span style=\"font-size: 16px; line-height: 19.2px; font-family: Raleway, sans-serif;\">"+ token +"</span></strong></span>\n" +
                "    </a>\n" +
                "  <!--[if mso]></center></v:roundrect></td></tr></table><![endif]-->\n" +
                "</div>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n" +
                "\n" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "  </div>\n" +
                "</div>\n" +
                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #1a2e35;\">\n" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #1a2e35;\"><![endif]-->\n" +
                "      \n" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
                "  \n" +
                "<table style=\"font-family:'Rubik',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 44px 35px;font-family:'Rubik',sans-serif;\" align=\"left\">\n" +
                "        \n" +
                "  <div style=\"color: #ecf0f1; line-height: 180%; text-align: center; word-wrap: break-word;\">\n" +
                "    <p style=\"font-size: 14px; line-height: 180%;\"><span style=\"font-family: Raleway, sans-serif;\">2022 - FERNANTICKET - COPYRIGHT</span></p>\n" +
                "  </div>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n" +
                "\n" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "  </div>\n" +
                "</div>\n" +
                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  </tbody>\n" +
                "  </table>\n" +
                "  <!--[if mso]></div><![endif]-->\n" +
                "  <!--[if IE]></div><![endif]-->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

}
