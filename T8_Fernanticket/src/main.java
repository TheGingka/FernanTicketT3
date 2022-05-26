import jdk.jshell.execution.Util;
import models.GestionAPP;
import models.Incidencia;
import models.IncidenciaDataClass;
import models.comunicaciones.Gmail;
import models.comunicaciones.Telegram;
import models.tipo.Admin;
import models.tipo.Tecnico;
import models.tipo.Usuario;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Menu;
import utils.Persistencia;
import utils.Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static Scanner s = new Scanner(System.in);
    public static GestionAPP gestionAPP = new GestionAPP();

    // Declaración de objetos y usuarios.
    public static Object userTemp;
    public static Usuario user;
    public static Tecnico tech;
    public static Admin admin;

    // Declaracion de salidas de menú.

    public static int opcionMenuUsuario = 0;
    public static int opcionMenuTecnico = 0;
    public static int opcionMenuAdmin = 0;

    // Declaración de email, contraseña.
    public static String email;
    public static String clave;
    public static String clave2;

    // Información para Telegram.
    public static String API = "bot5178503748:AAEXwKV-nR468nDkUFqgDuYe6y4a20tkvys";
    public static String CHATID = "785131120";


    public static void main(String[] args) {
        //Comprobar si estamos en modo invitado
        if (!Persistencia.esInvitado()) {

            //Declaramos la opción
            int opcionMenuInicio;




            while (true){
                resetear();
                //Empezamos con el menu de Inicio
                Menu.menuInicio();
                try {
                    opcionMenuInicio = Integer.parseInt(s.nextLine());
                    switch (opcionMenuInicio){

                        case 1:
                            iniciarSesion();
                            if (userTemp != null){
                                logueoCorrecto();
                            }
                            break;
                        case 2:
                            registrarNuevoUsuario();
                            if (userTemp != null){
                                logueoCorrecto();
                            }
                            break;
                        default:
                            System.out.println("No ha seleccionado ninguna opción de las posibles");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("No se ha introducido un valor valido");
                    opcionMenuInicio = 0;
                    Utils.pausa();
                    Utils.limpiarPantalla();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        }else{
            System.out.println("=== Modo INVITADO ===");
            System.out.println("Incidencias abiertas generales: ");
            imprimirIncidenciasAbiertas();
            Utils.pausa();
            Utils.limpiarPantalla();
        }
    }




    //Doom
    /*
 _____      _      _                            _
|_   _|    (_)    (_)                          (_)
  | | _ __  _  ___ _  __ _ _ __   ___  ___  ___ _  ___  _ __
  | || '_ \| |/ __| |/ _` | '__| / __|/ _ \/ __| |/ _ \| '_ \
 _| || | | | | (__| | (_| | |    \__ \  __/\__ \ | (_) | | | |
 \___/_| |_|_|\___|_|\__,_|_|    |___/\___||___/_|\___/|_| |_|

     */
    private static void iniciarSesion() {
        do { // Introducir el email.
            System.out.print("Introduce email: ");
            email = s.nextLine();
            if (email.equals("")) System.out.println("Introduce información en el campo.");
        } while (email.equals(""));


        do { // Introducir la clave.
            System.out.print("Introduce clave: ");
            clave = s.nextLine();

            if (clave.equals("")) System.out.println("Introduce información en el campo.");
        } while (clave.equals(""));

        userTemp = gestionAPP.login(email, clave);
        if (userTemp == null){
            System.out.println("No se ha encontrado ningun usuario con ese correo y clave");
            Utils.pausa();
            Utils.limpiarPantalla();
        }

    }
    /*
    ______           _     _
    | ___ \         (_)   | |
    | |_/ /___  __ _ _ ___| |_ _ __ __ _ _ __
    |    // _ \/ _` | / __| __| '__/ _` | '__|
    | |\ \  __/ (_| | \__ \ |_| | | (_| | |
    \_| \_\___|\__, |_|___/\__|_|  \__,_|_|
               __/ |
               |___/
     */
    private static void registrarNuevoUsuario() {
        do {
        //Pedimos el email hasta que sea unico
            System.out.print("Introduce email: ");
            email = s.nextLine();

            if (!gestionAPP.comprobarEmailUsuario(email))
                System.out.println("Email ya existente en la base, elija otro.");

        } while (!gestionAPP.comprobarEmailUsuario(email));

        do {
            System.out.print("Introduce clave: ");
            clave = s.nextLine();

            System.out.print("Introduce clave otra vez: ");
            clave2 = s.nextLine();

            if (!clave.equals(clave2))
                System.out.println("Claves diferentes, introduce de nuevo la clave.");
        } while (!clave.equals(clave2));

        System.out.print("Introduce nombre: ");
        String nombre = s.nextLine();

        System.out.print("Introduce apellido: ");
        String apellido = s.nextLine();

        //Generamos el token para pode seguir con el registro del usuario
        int token = generaToken(); // Generamos el token.

        String mensaje = Gmail.mensaje(token);


        if (Gmail.enviaMail(email, mensaje, "Token de registro - FernanTicket")) {
            System.out.println("Token enviado con éxito a su correo.");

        } else System.out.println("Fallo al enviar el token.");

        System.out.println("Introduce el token enviado al correo: ");
        int respuestaToken = Integer.parseInt(s.nextLine());


        if (token != respuestaToken) {
            System.out.println("Token inválido. Vuelva a crear la cuenta.");
        } else {

            //Comprobamos si se ha registrado correctamente el usuario
            if (gestionAPP.introducirUsuario(nombre,apellido,clave,email)){
                System.out.println("Usuario creado correctamente");
                userTemp = gestionAPP.login(email,clave);
            }else System.out.println("El usuario no se ha podido crear correctamente");

        }

    }

    public static int generaToken(){
        int token = 0;
        token = (int) (Math.random()*100);
        token +=(int) (Math.random()*100);
        return token ;

    }


/*

 _____      _      _             _        _
|_   _|    (_)    (_)           | |      | |
  | | _ __  _  ___ _  ___     __| | ___  | | ___   __ _ _   _  ___  ___
  | || '_ \| |/ __| |/ _ \   / _` |/ _ \ | |/ _ \ / _` | | | |/ _ \/ _ \
 _| || | | | | (__| | (_) | | (_| |  __/ | | (_) | (_| | |_| |  __/ (_) |
 \___/_| |_|_|\___|_|\___/   \__,_|\___| |_|\___/ \__, |\__,_|\___|\___/
                                                   __/ |
                                                  |___/

 */
    private static void logueoCorrecto() throws InterruptedException {
        if (userTemp != null) { // Comprobación de objetos, admin, tech o usuario.

                        /*
  _    _  _____ _    _         _____  _____ ____
 | |  | |/ ____| |  | |  /\   |  __ \|_   _/ __ \
 | |  | | (___ | |  | | /  \  | |__) | | || |  | |
 | |  | |\___ \| |  | |/ /\ \ |  _  /  | || |  | |
 | |__| |____) | |__| / ____ \| | \ \ _| || |__| |
  \____/|_____/ \____/_/    \_\_|  \_\_____\____/


*/
            if (userTemp instanceof Usuario) { // Comprobación de objetos, usuario.

                user = (Usuario) userTemp;
                if (Persistencia.ultimaVez(user.getNombre()) != null) {
                    System.out.println("          | Usted inicio sesion por ultima vez el " + Persistencia.ultimaVez(user.getNombre()) + " |");
                    Persistencia.ultimoInicio(user.getNombre());
                } else Persistencia.ultimoInicio(user.getNombre());


                //LOG
                Persistencia.inicioSesion(user.getNombre());

                do {
                    if (opcionMenuUsuario == 6)break;
                    Menu.menuUsuario(user.getIncidencias().size(), gestionAPP.incidenciasEnProceso(user));

                    do { // Introducir menú usuario.
                        try {
                            opcionMenuUsuario = Integer.parseInt(s.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Introduce una opción válida.");
                        }
                        if (opcionMenuUsuario < 1 || opcionMenuUsuario > 6)
                            System.out.println("Introduce una opción disponible: ");
                    } while (opcionMenuUsuario < 1 || opcionMenuUsuario > 6);

                    switch (opcionMenuUsuario) {

                        case 1: //CREAR INCIDENCIA
                            System.out.println(crearIncidencia(user));
                            Utils.pausa();
                            Utils.limpiarPantalla();
                            break;

                        case 2: // Incidencias abiertas en USUARIO.
                            incidenciasAbiertasDeUsuario(user);
                            Utils.pausa();
                            break;

                        case 3: // Incidencias cerradas de USUARIO.
                            incidenciasCerradas(user);
                            Utils.pausa();
                            break;

                        case 4: // Muestra la información de USUARIO actual.

                            System.out.println(user);
                            Utils.pausa();
                            break;

                        case 5: // Cambia la clave de USUARIO.
                            cambiarContrasena(user);
                            Utils.pausa();
                            //guardarCambiosUsuario(user);
                            break;

                        case 6: // Cerrar sesión de USUARIO.
                            Utils.cerrarSesion();
                            Persistencia.cerrarSesion(user.getNombre());
                            break;

                        default:
                            System.out.println("No ha seleccionado ninguna opción de las posibles.");
                    }
                } while (opcionMenuUsuario != 6);
            } else if (userTemp instanceof Tecnico){
               /*
  _______ ______ _____ _   _ _____ _____ ____
 |__   __|  ____/ ____| \ | |_   _/ ____/ __ \
    | |  | |__ | |    |  \| | | || |   | |  | |
    | |  |  __|| |    | . ` | | || |   | |  | |
    | |  | |___| |____| |\  |_| || |___| |__| |
    |_|  |______\_____|_| \_|_____\_____\____/


*/

                    tech = (Tecnico) userTemp;
                    if (Persistencia.ultimaVez(tech.getNombre()) != null) {
                        System.out.println("          | Usted inicio sesion por ultima vez el " + Persistencia.ultimaVez(tech.getNombre()) + " |");
                        Persistencia.ultimoInicio(tech.getNombre());
                    } else Persistencia.ultimoInicio(tech.getNombre());

                    do { // Menú de TÉCNICO.

                        Menu.menuTecnico(gestionAPP.incidenciasAbiertasNumero(tech),gestionAPP.incidenciasCerradasNumero(tech), gestionAPP.incidenciasMedia(tech), tech.getNombre());
                        try {
                            opcionMenuTecnico = Integer.parseInt(s.nextLine());
                        }catch (NumberFormatException e){
                            System.out.println("Introduce una opcion correcta");
                            opcionMenuTecnico = 0;
                        }

                        Persistencia.inicioSesion(tech.getNombre());

                        switch (opcionMenuTecnico) {

                            case 1: // Comprobar incidencias asignadas no resueltas en TÉCNICO.
                                incidenciasTecnicoSinResolver(tech);
                                Utils.pausa();
                                Utils.limpiarPantalla();
                                break;

                            case 2: // Marcar una incidencia como resuelta.
                                marcarIncidenciaComoResuelta(tech);
                                guardarCambiosTecnico(tech);
                                break;

                            case 3: //Consultar mis incidencias cerradas.
                                consultarIncidenciasCerradas(tech);
                                Utils.pausa();
                                Utils.limpiarPantalla();
                                break;

                            case 4: //Mostar datos de tecnico
                                System.out.println(tech);
                                Utils.pausa();
                                break;
                            case 5: //Cambiar contrasena  de tecnico
                                cambiarContrasenaTecnio(tech);
                                guardarCambiosTecnico(tech);
                                break;

                            case 6: //Cerrar sesion
                                Utils.cerrarSesion();
                                Utils.limpiarPantalla();
                                Persistencia.cerrarSesion(tech.getNombre());
                                break;

                        }
                    } while (opcionMenuTecnico != 6);
            } else {
                /*
           _____  __  __ _____ _   _
     /\   |  __ \|  \/  |_   _| \ | |
    /  \  | |  | | \  / | | | |  \| |
   / /\ \ | |  | | |\/| | | | | . ` |
  / ____ \| |__| | |  | |_| |_| |\  |
 /_/    \_\_____/|_|  |_|_____|_| \_|


*/

                if (userTemp instanceof Admin) { // Comprobación de objetos, admin.
                    opcionMenuAdmin = 0;
                    admin = (Admin) userTemp;
                    if (Persistencia.ultimaVez(admin.getNombre()) != null) {
                        System.out.println("          | Usted inicio sesion por ultima vez el " + Persistencia.ultimaVez(admin.getNombre()) + " |");
                        Persistencia.ultimoInicio(admin.getNombre());
                    } else Persistencia.ultimoInicio(admin.getNombre());
                    Persistencia.inicioSesion(admin.getNombre());
                    while(opcionMenuAdmin != 12){

                        Menu.menuAdmin(gestionAPP.incidenciasAbiertasSinAsignar().size(), gestionAPP.incidenciasSinResolverse(), admin.getNombre());
                        try {
                            opcionMenuAdmin = Integer.parseInt(s.nextLine());
                        }catch (NumberFormatException e){
                            System.out.println("Introduce una opcion correcta.");
                            opcionMenuAdmin = 0;
                        }

                        switch (opcionMenuAdmin) {

                            case 1: // Incidencias abiertas GENERALES.

                                System.out.println();
                                System.out.println("Todas las incidencias abiertas: \n");
                                imprimirIncidenciasAbiertas();
                                Utils.pausa();
                                Utils.limpiarPantalla();
                                break;
                            case 2: //Incidencias cerradas GENERALES.
                                incidenciasCerradasGenerales();
                                Utils.pausa();
                                Utils.limpiarPantalla();
                                break;
                            case 3: // Busqueda por término.
                                buscarTermino();
                                break;
                            case 4: // Información de TECNICO.
                                mostrarTecnicos();
                                Utils.pausa();
                                Utils.limpiarPantalla();
                                break;
                            case 5: // Asignar incidencia a un TECNICO.
                                asignarIncidencia();
                                break;

                            case 6: // Crear un TECNICO.
                                crearTecnico();
                                Utils.pausa();
                                break;

                            case 7: //Borrar tecnico. //TODO: No borrar el técnico con cerradas, borrar el técnico y mover sus incidencias abiertas.
                                //borrarTecnico(); //TODO: Sin completar.
                                break;

                            case 8: //Consultar usuarios.
                                consultarUsuarios();
                                Utils.pausa();
                                Utils.limpiarPantalla();
                                break;

                            case 9: //Estadísticas.
                                Utils.limpiarPantalla();
                                System.out.println("--- Estadísticas de la aplicación --- ");
                                System.out.println();
                                System.out.println("- Hay " + gestionAPP.numeroDeTecnicos() + " técnicos. ");
                                System.out.println("- Hay " + gestionAPP.numeroDeUsuarios() + " usuarios. ");
                                System.out.println("- Prioridad media total: " + gestionAPP.prioridadMediaApp());

                                Utils.pausa();
                                Utils.limpiarPantalla();
                                break;

                            case 10:
                                //enviarIncidenciasPrendientes(); TODO: Tiene funciones hechas a medias, o lo eliminas o sigues.
                                Utils.limpiarPantalla();
                                Utils.limpiarPantalla();
                                break;

                            case 11:
                                imprimirProperties(); //TODO: Sin completar.
                                break;

                            case 12:
                                Utils.cerrarSesion();
                                Persistencia.cerrarSesion(admin.getNombre());
                                break;
                        }

                    }

                }
            }
        }


    }

    private static void buscarTermino() {

        System.out.print("Introduce el término para buscar: ");
        String termino = s.nextLine();

        incidenciasTermino(termino);

    }


    /*

     _____               _              __                  _
    |_   _|             (_)            / _|                (_)
      | | ___  ___ _ __  _  ___ ___   | |_ _   _ _ __   ___ _  ___  _ __   ___  ___
      | |/ _ \/ __| '_ \| |/ __/ _ \  |  _| | | | '_ \ / __| |/ _ \| '_ \ / _ \/ __|
      | |  __/ (__| | | | | (_| (_) | | | | |_| | | | | (__| | (_) | | | |  __/\__ \
      \_/\___|\___|_| |_|_|\___\___/  |_|  \__,_|_| |_|\___|_|\___/|_| |_|\___||___/



     */
    //Case 1
    private static void incidenciasTecnicoSinResolver(Tecnico tech) {
        if (gestionAPP.comprobarIncidenciasAbiertas(tech).size() != 0){
            ArrayList<IncidenciaDataClass> inci = gestionAPP.comprobarIncidenciasAbiertas(tech);
            for (IncidenciaDataClass i:
                 inci) {
                System.out.println(i.toStringTech());
            }
        }else System.out.println("No tiene incidencias abiertas.");;
    }

    //Case 2
    private static void marcarIncidenciaComoResuelta(Tecnico tech) {
        incidenciasTecnicoSinResolver(tech);
        System.out.print("Escriba la ID de la incidencia que desea resolver: ");
        try {
            int num = Integer.parseInt(s.nextLine());
            System.out.print("Solución del ticket: ");
            String solucion = s.nextLine();
            System.out.println(gestionAPP.resolverIncidencia(solucion,num) ? "Incidencia resuelta." : "La incidencia no ha podido resolverse.");
        }catch (NumberFormatException e){
            System.out.println("No ha escrito un valor correcto.");
        }
        Utils.pausa();
    }

    //Case 3
    private static void consultarIncidenciasCerradas(Tecnico tech) {
        if (gestionAPP.comprobarIncidenciasCerradas(tech).size() != 0){
            ArrayList<IncidenciaDataClass> inci = gestionAPP.comprobarIncidenciasCerradas(tech);
            for (IncidenciaDataClass i:
                    inci) {
                System.out.println(i.toStringTech());
            }
        }else System.out.println("No tiene incidencias cerradas.");;
    }


    //Case 5
    private static void cambiarContrasenaTecnio(Tecnico tech) {
        System.out.print("Introduce nueva clave: ");
        String clavenueva;

        do {
            clavenueva = s.nextLine();
            if (clavenueva.equals("")) System.out.println("ERROR. Introduce información en el campo.");
        }while(clavenueva.equals(""));


        System.out.print("Introduce nueva clave de nuevo: ");
        String clavenueva2;

        do {
            clavenueva2 = s.nextLine();
            if (clavenueva2.equals("")) System.out.println("ERROR. Introduce información en el campo.");
        }while(clavenueva2.equals(""));

        if(clavenueva.equals(clavenueva2)) {
            gestionAPP.cambiarClaveTecnico(clavenueva,clavenueva2, tech);
            String mensaje = "El tecnico: " + tech.getNombre() + " ha cambiado su contraseña.";

            if (Telegram.enviaMensajeTelegram(mensaje, API, CHATID)) {
                System.out.println("Mensaje enviado con éxito.");
            } else System.out.println("Fallo para enviar el mensaje.");
        }else System.out.println("Las constraseñas no coinciden.");
    }

    /*

  ___      _           _          __                  _
 / _ \    | |         (_)        / _|                (_)
/ /_\ \ __| |_ __ ___  _ _ __   | |_ _   _ _ __   ___ _  ___  _ __   ___  ___
|  _  |/ _` | '_ ` _ \| | '_ \  |  _| | | | '_ \ / __| |/ _ \| '_ \ / _ \/ __|
| | | | (_| | | | | | | | | | | | | | |_| | | | | (__| | (_) | | | |  __/\__ \
\_| |_/\__,_|_| |_| |_|_|_| |_| |_|  \__,_|_| |_|\___|_|\___/|_| |_|\___||___/



     */
    //Case 1
    private static void imprimirIncidenciasAbiertas() {
        if (gestionAPP.imprimirIncidenciasAbiertas().size()!=0){
            ArrayList<IncidenciaDataClass> imprimir = gestionAPP.imprimirIncidenciasAbiertas();
            for (IncidenciaDataClass i:
                 imprimir) {
                System.out.println(i.toStringAdmin());
            }
        }else System.out.println("No hay incidencias abiertas. ");
        System.out.println();

    }

    //Case 2
    private static void incidenciasCerradasGenerales() {
        if (gestionAPP.imprimirIncidenciasCerradas().size()!=0){
            ArrayList<IncidenciaDataClass> imprimir = gestionAPP.imprimirIncidenciasCerradas();
            for (IncidenciaDataClass i:
                    imprimir) {
                System.out.println(i.toStringAdmin());
            }
        }else System.out.println("No hay incidencias cerradas");

    }

    //Case 3
    private static void incidenciasTermino(String termino) {
        if (gestionAPP.imprimirIncidenciasTermino(termino).size()!=0){
            ArrayList<IncidenciaDataClass> imprimir = gestionAPP.imprimirIncidenciasTermino(termino);
            for (IncidenciaDataClass i:
                    imprimir) {
                System.out.println(i.toStringAdmin());
            }
        }else System.out.println("No hay incidencias con este término.");
        Utils.pausa();
    }

    //Case 4
    private static void mostrarTecnicos() {
        if (gestionAPP.tecnicos().size() != 0){
            ArrayList<Tecnico> tecnicos = gestionAPP.tecnicos();
            for (Tecnico t:
                 tecnicos) {
                System.out.println(t);
            }
        }else System.out.println("No hay tecnicos");
    }


    //Case 5
    private static void asignarIncidencia() {
        if (gestionAPP.incidenciasAbiertasSinAsignar().size() != 0){
            ArrayList<IncidenciaDataClass> inci = gestionAPP.incidenciasAbiertasSinAsignar();
            int x = 0;
            int num = -1;
            for (IncidenciaDataClass i:
                 inci) {
                x++;
                System.out.println(x + ": ──┐");
                System.out.println(i.toStringAdmin());
            }
            while(!(num > 0 && num <= x)){
                System.out.print("Seleccione el numero de la incidencia que desea asignar: ");
                try{
                    num = Integer.parseInt(s.nextLine());
                }catch (NumberFormatException e){
                    System.out.println("No ha introducido una valor correcto.");
                }
            }
            int p = 0;
            int per = 0;
            if (gestionAPP.tecnicos().size() != 0){
                ArrayList<Tecnico> tecnicos = gestionAPP.tecnicos();
                for (Tecnico t:
                        tecnicos) {
                    p++;
                    System.out.println(p + ": ──┐");
                    System.out.println(t);
                }
                while(!(per > 0 && per <= p)){
                    System.out.print("Seleccione el numero del tecnico que desea asignar: ");
                    try{
                        per = Integer.parseInt(s.nextLine());
                    }catch (NumberFormatException e){
                        System.out.println("No ha introducido una valor correcto");
                    }
                }
                x = 0;
                for (IncidenciaDataClass i:
                        inci) {
                    x++;
                    if (x == num){
                        p = 0;
                        for (Tecnico t:
                                tecnicos) {
                            p++;
                            if (p == per){
                                System.out.println(gestionAPP.asignarIncidencia(i,t)? "Incidencia asignada correctamente." : "No se puedo asignar la incidencia.");
                            }
                        }
                    }
                }
            }else System.out.println("No hay tecnicos");

        }else System.out.println("No hay indicendias abiertas sin asignar.");
        System.out.println();
        Utils.pausa();
    }


    //Case 6
    private static void crearTecnico() {
        do {
            //Pedimos el email hasta que sea unico
            System.out.print("Introduce email: ");
            email = s.nextLine();

            if (!gestionAPP.comprobarTecnicoPorEmail(email))
                System.out.println("Email ya existente en la base, elija otro.");

        } while (!gestionAPP.comprobarTecnicoPorEmail(email));


        do {
                System.out.print("Introduce clave: ");
                clave = s.nextLine();

                System.out.print("Introduce clave otra vez: ");
                clave2 = s.nextLine();

                if (!clave.equals(clave2))
                    System.out.println("Claves diferentes, introduce de nuevo la clave.");
            } while (!clave.equals(clave2));


            System.out.print("Introduce nombre: ");
            String nombre = s.nextLine();

            System.out.print("Introduce apellido: ");
            String apellido = s.nextLine();
            //Comprobamos si se ha registrado correctamente el usuario
            if (gestionAPP.introducirTecnico(nombre,apellido,clave,email)){
                System.out.println("Tecnico creado correctamente");
            }else System.out.println("El tecnico no se ha podido crear correctamente");


    }

    //Case 7


    //Case 8
    private static void consultarUsuarios() {
        if (gestionAPP.usuarios().size() != 0){
            ArrayList<Usuario> usuarios = gestionAPP.usuarios();
            for (Usuario u:
                    usuarios) {
                System.out.println(u);
            }
        }else System.out.println("No hay usuarios");
    }

    private static void enviarIncidenciasPrendientes() {

        int contadorTecnicos = 1;
        if (gestionAPP.tecnicos().size() != 0) {
            if (gestionAPP.tecnicos().size() != 0 && gestionAPP.tecnicosConIncidenciasAbiertas().size() != 0) {
                for (Tecnico t :
                        gestionAPP.tecnicosConIncidenciasAbiertas()) {
                    System.out.println(contadorTecnicos + ":" + t);
                    contadorTecnicos++;
                }
                System.out.println("¿Que tecnico desea enviarle sus incidencias pendientes?: ");
                try {
                    int seleccionTecnico = Integer.parseInt(s.nextLine());
                    if (seleccionTecnico < gestionAPP.tecnicosConIncidenciasAbiertas().size() + 1 && seleccionTecnico > 0){
                        System.out.println("hola");
                        Tecnico temp = gestionAPP.tecnicosConIncidenciasAbiertas().get(seleccionTecnico - 1);
                        enviarIncidencias(temp);
                    } else System.out.println("No ha introducido un tecnico posible.");

                } catch (NumberFormatException  e) {
                    System.out.println("No ha introducido una valor correcto.");
                }

            } else System.out.println("No hay tecnicos disponibles para enviar incidencias.");
        }else{
            System.out.println("No hay ningun tecnico creado.");
            Utils.pausa();
        }
    }

    private static void enviarIncidencias(Tecnico temp) {
        crearExcel(temp);
        Gmail.enviaMailExcel(temp.getEmail(), "Incidencias abiertas por resolver, adjuntas en el correo. ", "Incidencias de FernanTicket");
    }

    //EXCEL
    public static void crearExcel(Tecnico temp){
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Incidencias");

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Incidencias");
        row.createCell(1).setCellValue("Incidencia con ID");
        row.createCell(2).setCellValue("Comentario del usuario");
        row.createCell(3).setCellValue("Prioridad");
        row.createCell(4).setCellValue("Fecha de inicio");
        row.createCell(5).setCellValue("Días desde su creación");


        int x = 0;

        for (Incidencia i:
                temp.getIncidencias()) {
            if (i.isEstaResuelta() == 0) {
                x++;
                Row rowe = sheet.createRow(x);
                rowe.createCell(0).setCellValue("Incidencias " + x);
                rowe.createCell(1).setCellValue(String.valueOf(i.getId()));
                rowe.createCell(2).setCellValue(String.valueOf(i.getDescripcion()));
                rowe.createCell(3).setCellValue(String.valueOf(i.getPrioridad()));
                rowe.createCell(4).setCellValue(String.valueOf(i.getFechaInicio()));
                rowe.createCell(5).setCellValue("0");
            }
        }


        try{
            FileOutputStream fileout = new FileOutputStream("Excel.xlsx");
            book.write(fileout);

            fileout.close();
        } catch (IOException e){
            System.out.println("No se pudo crear.");
        }


    }

    //Case 11
    private static void imprimirProperties() {
    }

    /*

 _   _                      _          __                  _
| | | |                    (_)        / _|                (_)
| | | |___ _   _  __ _ _ __ _  ___   | |_ _   _ _ __   ___ _  ___  _ __   ___  ___
| | | / __| | | |/ _` | '__| |/ _ \  |  _| | | | '_ \ / __| |/ _ \| '_ \ / _ \/ __|
| |_| \__ \ |_| | (_| | |  | | (_) | | | | |_| | | | | (__| | (_) | | | |  __/\__ \
 \___/|___/\__,_|\__,_|_|  |_|\___/  |_|  \__,_|_| |_|\___|_|\___/|_| |_|\___||___/



     */
    //Case 1 crear Incidencia
    private static String crearIncidencia(Usuario user) {
        System.out.print("Introduce el problema: ");
        String descripcion;

        do {
            descripcion = s.nextLine();
            if (descripcion.equals("")) System.out.println("Introduce información: ");
        }while(descripcion.equals(""));


        int prioridad; // Introduce la prioridad.

        do {
            System.out.print("Introduce prioridad. Su rango es de [0 - 10]: ");
            try {
                prioridad = Integer.parseInt(s.nextLine());

                if (prioridad < 0 || prioridad > 10) System.out.println("Introduce un valor dentro del rango.");
            }catch (NumberFormatException e){
                System.out.println("Introduce un valor valido.");
                prioridad = -1;
            }
        }while(prioridad < 0 || prioridad > 10);

        if (gestionAPP.insertaIncidencia(descripcion, user, prioridad)) { // Inserta incidencia.
            String mensaje = "El usuario: " + user.getNombre() + " ha insertado una nueva incidencia.";
            Persistencia.nuevaIncidencia(user.getNombre());
            if (Telegram.enviaMensajeTelegram(mensaje, API, CHATID)){
                return ("Incidencia creada correctamente \n" +
                        "Mensaje enviado con éxito.");

            }else return ("Incidencia creada correctamente \n" +
                    "Fallo al enviar el mensaje");

        } else {
            System.out.println("Incidencia no subida.");
        }
        return "No se pudo crear la incidencia";
    }

    //Case 2 incidencias Abiertas
    private static void incidenciasAbiertasDeUsuario(Usuario user) {
        ArrayList<IncidenciaDataClass> incidencias = new ArrayList<>();
        incidencias = gestionAPP.incidenciasAbiertasDeUsuario(user);
        if (incidencias.size() != 0){
            for (IncidenciaDataClass i:
                 incidencias) {
                System.out.println(i.toString());
            }
        }else System.out.println("No tiene ninguna incidencia pendiente.");
    }

    //Case 3
    private static void incidenciasCerradas(Usuario user) {
        ArrayList<IncidenciaDataClass> incidencias = new ArrayList<>();
        incidencias = gestionAPP.incidenciasCerradasDeUsuario(user);
        if (incidencias.size() != 0){
            for (IncidenciaDataClass i:
                    incidencias) {
                System.out.println(i);
            }
        }else System.out.println("No tiene ninguna incidencia cerrada");
    }
    private static void cambiarContrasena(Usuario user) {
        System.out.print("Introduce nueva clave: ");
        String clavenueva;

        do {
            clavenueva = s.nextLine();
            if (clavenueva.equals("")) System.out.println("ERROR. Introduce información en el campo.");
        }while(clavenueva.equals(""));


        System.out.print("Introduce nueva clave de nuevo: ");
        String clavenueva2;

        do {
            clavenueva2 = s.nextLine();
            if (clavenueva2.equals("")) System.out.println("ERROR. Introduce información en el campo.");
        }while(clavenueva2.equals(""));

        if(clavenueva.equals(clavenueva2)) {
            gestionAPP.cambiaClaveUsuario(clavenueva,clavenueva2, user);
            String mensaje = "El usuario: " + user.getEmail() + " ha cambiado su contraseña.";

            if (Telegram.enviaMensajeTelegram(mensaje, API, CHATID)) {
                System.out.println("Mensaje enviado con éxito.");
            } else System.out.println("Fallo para enviar el mensaje.");
        }else System.out.println("Las contraseñas no coinciden");
    }


    /*

 _____ _                    __                  _
|  _  | |                  / _|                (_)
| | | | |_ _ __ __ _ ___  | |_ _   _ _ __   ___ _  ___  _ __   ___  ___
| | | | __| '__/ _` / __| |  _| | | | '_ \ / __| |/ _ \| '_ \ / _ \/ __|
\ \_/ / |_| | | (_| \__ \ | | | |_| | | | | (__| | (_) | | | |  __/\__ \
 \___/ \__|_|  \__,_|___/ |_|  \__,_|_| |_|\___|_|\___/|_| |_|\___||___/



     */
    private static void resetear() {
        opcionMenuUsuario = 0;
        opcionMenuAdmin = 0;
        opcionMenuTecnico = 0;
        userTemp = null;
    }

    private static void guardarCambiosTecnico(Tecnico tech) {
    }
}
