import model.Administrador;
import model.Incidencia;
import model.Tecnico;
import model.Usuario;
import others.Menus;
import others.Utils;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class main {

    public static Scanner s = new Scanner(System.in);

    //Usuario
    public static Usuario usuario1 = new Usuario("user","bytxnio@gmail.com","user","0");
    public static Usuario usuario2 = null;
    //Tecnico
    public static Tecnico tecnico1 = new Tecnico("tech", "tech", "tech");
    public static Tecnico tecnico2 = null;
    //Admin
    public static Administrador admin = new Administrador("admin","hermosolmanuel131@gmail.com","admin");

    //Variables
    public static String nombre,correo,contra1,contra2,token;
    public static int menu = 0;
    //Incidencias
    public static Incidencia inci1 = null;
    public static Incidencia inci2 = null;
    public static Incidencia inci3 = null;
    public static Incidencia inci4 = null;
    public static Incidencia inci5 = null;
    public static Incidencia inci6 = null;

    //Incidencias resueltas
    public static Incidencia inci7 = null;
    public static Incidencia inci8 = null;
    //Temporales de los distintos usuarios
    public static Usuario tempUsuario = null;
    public static Tecnico tempTecnico = null;
    public static Administrador tempAdmin = null;

    //Declaro la variable de fecha actual
    public static LocalDate hoy = LocalDate.now();

    public static void main(String[] args) throws InterruptedException {
        boolean inicio = false;
        do {
            do {
                switch (Menus.menuPrincipal()){
                    case 1:
                        resetearTemp();
                        System.out.println("Digame el nombre del usuario: ");
                        nombre = s.nextLine();
                        if (iniciarSesion(nombre) == null){
                            System.out.println("Usuario no encontrado");
                        }else{
                            if (tempUsuario != null){
                                //Para validar token
                                if (!tempUsuario.getInicioSesion()){
                                    System.out.println("Ingrese el token");
                                    token = s.nextLine();
                                    if (token.equals(tempUsuario.getToken())){
                                        System.out.println("Digame la contrasena");
                                        contra1 = s.nextLine();
                                        if (contra1.equals(tempUsuario.getContrasena())){
                                            System.out.println();
                                            inioSesion();
                                            menu = 1;
                                        }
                                    }else System.out.println("Token incorrecto");
                                }else{
                                    if (token.equals(tempUsuario.getToken())){
                                        System.out.println("Digame la contrasena");
                                        contra1 = s.nextLine();
                                        if (contra1.equals(tempUsuario.getContrasena())){
                                            System.out.println();
                                            menu = 1;
                                        }
                                    }
                                }

                            }
                            if (tempTecnico != null){
                                System.out.println("Digame la contrasena");
                                contra1 = s.nextLine();
                                if (contra1.equals(tempTecnico.getContrasena())) System.out.println("Logueado correctamente");
                                menu = 2;
                            }
                            if (tempAdmin != null){
                                System.out.println("Digame la contrasena");
                                contra1 = s.nextLine();
                                if (contra1.equals(tempAdmin.getContrasena())) System.out.println("Logueado correctamente");
                                menu = 3;
                            }

                        }
                        break;
                    case 2:
                        nuevoUsuario();
                        Utils.limpiarPantalla();
                        break;
                    default:
                        System.out.println("No ha seleccionado ninguna opcion de las mostradas por pantalla");
                        Utils.esperar();
                        Utils.limpiarPantalla();
                }
            }while (menu == 0);

            //Parte de ya una vez iniciada sesion
            int opc = 0;
            String contrasena = "";
            switch (menu){
                case 1:
                    do {
                        System.out.println("Bienvenido " + tempUsuario.getApodo() + " tiene usted el perfil de usuario normal");
                        Menus.menuUsuario();
                        opc = opc();
                        switch (opc){
                            case 1:
                                //Cosas para crear incidencias
                                String comentario = "", prioridad = "";

                                if (comprobarIncidenciasDisponibles(tempUsuario) == 0){
                                    System.out.println("No se pueden crear mas incidencias");
                                }else{
                                    System.out.println("Que desea poner como comentario en la incidencia");
                                    comentario = s.nextLine();
                                    System.out.println("Que prioridad tiene");
                                    prioridad = s.nextLine();
                                    System.out.println((crearIncidencia(tempUsuario.getApodo(),comentario,prioridad,hoy)? "Se ha creado correctamente" : "No se ha podido crear la incidencia"));
                                    System.out.println(enviaMensajeTelegram("El usuario, " + tempUsuario.getApodo() + ", ha generado una incidencia.",766869962)? "Información enviada." : "Información no enviada.");
                                }
                                break;
                            case 2:
                                System.out.println(tempUsuario.comprobarIncidencias());
                                break;
                            case 3:
                                if (tempUsuario.getIncidencia1() != null){
                                    if (tempUsuario.getIncidencia1().getEstado() == true) System.out.println(tempUsuario.getIncidencia1());
                                }
                                if (tempUsuario.getIncidencia2() != null){
                                    if (tempUsuario.getIncidencia2().getEstado() == true) System.out.println(tempUsuario.getIncidencia3());
                                }
                                if (tempUsuario.getIncidencia3() != null){
                                    if (tempUsuario.getIncidencia3().getEstado() == true) System.out.println(tempUsuario.getIncidencia2());
                                }

                                break;
                            case 4:
                                System.out.println(tempUsuario.usuario());
                                break;
                            case 5:
                                do {
                                    System.out.print("Dime la contraseña del usuario: ");
                                    usuario1.setContrasena(s.nextLine());
                                    System.out.print("Repite la contraseña: ");
                                    contrasena = s.nextLine();
                                }while(!tempUsuario.comprobarContraseña(contrasena));

                                break;
                            case 6:
                                cerrarSesion();
                                break;
                            default:
                                System.out.println("Seleccione una opcion posible");
                                break;
                        }
                        System.out.println(opc());
                    }while (opc != 6);
                    break;
                case 2:
                    System.out.println("Bienvenido " + tempTecnico.getApodo() + " tiene usted el perfil de tecnico");
                    Menus.menuTecnico();
                    opc = 0;
                    do {
                        Menus.menuTecnico();
                        opc = opc();
                        switch (opc){
                            case 1:
                                if (tempTecnico.getIncidencia1() != null){
                                    System.out.println(tempTecnico.getIncidencia1());
                                }
                                if (tempTecnico.getIncidencia2() != null){
                                    System.out.println(tempTecnico.getIncidencia2());
                                }
                                if (tempTecnico.getIncidencia1() == null && tempTecnico.getIncidencia2() == null)
                                    System.out.println("No tiene ninguna incidencia asignada");

                                break;
                            case 2:
                                if (tempTecnico.getIncidencia1() != null){
                                    System.out.println(" 1. " + tempTecnico.getIncidencia1());
                                }else System.out.println("No hay ninguna primera incidencia asignada");

                                if (tempTecnico.getIncidencia2() != null){
                                    System.out.println(" 2. " + tempTecnico.getIncidencia2());
                                }else System.out.println("No hay ninguna segunda incidencia asignada");

                                if (tempTecnico.getIncidencia1() != null || tempTecnico.getIncidencia2() != null)
                                    System.out.println("Que incidencia desea marcar como resuelta");
                                try{
                                    opc = Integer.parseInt(s.nextLine());
                                    switch (opc){
                                        case 1:
                                            String comentario = "";
                                            if (tempTecnico.getIncidencia1() != null) {
                                                if (inci7 == null){
                                                    System.out.println("Que comentario desea poner");
                                                    comentario = s.nextLine();
                                                    tempTecnico.getIncidencia1().setComentarioTecnico(comentario);
                                                    inci7 = tempTecnico.getIncidencia1();
                                                    tempTecnico.getIncidencia1().setEstado(true);
                                                    if (Objects.equals(tempTecnico.getIncidencia1().getCreador(), usuario1.getApodo())){
                                                        System.out.println(enviaMail(usuario1.getEmail(),"La incidencia: " + tempTecnico.getIncidencia1() + " se ha resuelto.","Incidencia RESUELTA") ? "Correo enviado correctamente correctamente" : "No enviado");
                                                    }else{
                                                        System.out.println(enviaMail(usuario2.getEmail(),"La incidencia: " + tempTecnico.getIncidencia1() + " se ha resuelto.","Incidencia RESUELTA") ? "Correo enviado correctamente correctamente" : "No enviado");
                                                    }
                                                    System.out.println(enviaMensajeTelegram("El tecnico, " + tempTecnico.getApodo() + ", ha cerrado una incidencia con ID: " + tempTecnico.getIncidencia1(),766869962)? "Información enviada." : "Información no enviada.");

                                                }else if (inci8 == null){
                                                    inci8 = tempTecnico.getIncidencia1();
                                                    tempTecnico.getIncidencia1().setEstado(true);
                                                    if (Objects.equals(tempTecnico.getIncidencia1().getCreador(), usuario1.getApodo())){
                                                        System.out.println(enviaMail(usuario1.getEmail(),"La incidencia: " + tempTecnico.getIncidencia1() + " se ha resuelto.","Incidencia RESUELTA") ? "Correo enviado correctamente correctamente" : "No enviado");
                                                    }else{
                                                        System.out.println(enviaMail(usuario2.getEmail(),"La incidencia: " + tempTecnico.getIncidencia1() + " se ha resuelto.","Incidencia RESUELTA") ? "Correo enviado correctamente correctamente" : "No enviado");
                                                    }
                                                    System.out.println(enviaMensajeTelegram("El tecnico, " + tempTecnico.getApodo() + ", ha cerrado una incidencia con ID: " + tempTecnico.getIncidencia1(),766869962)? "Información enviada." : "Información no enviada.");

                                                }
                                            }
                                            break;
                                        case 2:
                                            comentario = "";
                                            if (tempTecnico.getIncidencia2() != null) {
                                                if (inci7 == null){
                                                    System.out.println("Que comentario desea poner");
                                                    comentario = s.nextLine();
                                                    tempTecnico.getIncidencia2().setComentarioTecnico(comentario);
                                                    inci7 = tempTecnico.getIncidencia2();
                                                    tempTecnico.getIncidencia2().setEstado(true);
                                                    if (Objects.equals(tempTecnico.getIncidencia1().getCreador(), usuario1.getApodo())){
                                                        System.out.println(enviaMail(usuario1.getEmail(),"La incidencia: " + tempTecnico.getIncidencia1() + " se ha resuelto.","Incidencia RESUELTA") ? "Correo enviado correctamente correctamente" : "No enviado");
                                                    }else{
                                                        System.out.println(enviaMail(usuario2.getEmail(),"La incidencia: " + tempTecnico.getIncidencia1() + " se ha resuelto.","Incidencia RESUELTA") ? "Correo enviado correctamente correctamente" : "No enviado");
                                                    }
                                                    System.out.println(enviaMensajeTelegram("El tecnico, " + tempTecnico.getApodo() + ", ha cerrado una incidencia con ID: " + tempTecnico.getIncidencia2(),766869962)? "Información enviada." : "Información no enviada.");

                                                }else if (inci8 == null){
                                                    inci8 = tempTecnico.getIncidencia2();
                                                    tempTecnico.getIncidencia2().setEstado(true);
                                                    if (Objects.equals(tempTecnico.getIncidencia1().getCreador(), usuario1.getApodo())){
                                                        System.out.println(enviaMail(usuario1.getEmail(),"La incidencia: " + tempTecnico.getIncidencia1() + " se ha resuelto.","Incidencia RESUELTA") ? "Correo enviado correctamente correctamente" : "No enviado");
                                                    }else{
                                                        System.out.println(enviaMail(usuario2.getEmail(),"La incidencia: " + tempTecnico.getIncidencia1() + " se ha resuelto.","Incidencia RESUELTA") ? "Correo enviado correctamente correctamente" : "No enviado");
                                                    }
                                                    System.out.println(enviaMensajeTelegram("El tecnico, " + tempTecnico.getApodo() + ", ha cerrado una incidencia con ID: " + tempTecnico.getIncidencia2(),766869962)? "Información enviada." : "Información no enviada.");

                                                }
                                            }
                                            break;
                                        default:
                                            System.out.println("No ha seleccionado una opcion posible");
                                    }
                                }catch (NumberFormatException e){
                                    System.out.println("No ha selecciona una opcion posible");
                                }

                                break;
                            case 3:
                                if (inci7 != null) System.out.println(inci7);
                                if (inci8 != null) System.out.println(inci8);
                                break;
                            case 4:
                                System.out.println(tempTecnico.toString());
                                break;
                            case 5:
                                do {
                                    System.out.print("Dime la contraseña del usuario: ");
                                    usuario1.setContrasena(s.nextLine());
                                    System.out.print("Repite la contraseña: ");
                                    contrasena = s.nextLine();
                                }while(!tempTecnico.comprobarContraseña(contrasena));
                                break;
                            case 6:
                                cerrarSesion();
                                break;
                        }
                    }while (opc != 6);
                    switch (opc()){

                    }
                    break;
                case 3:
                    System.out.println("Bienvenido " + tempAdmin.getApodo() + " tiene usted el perfil de administrador");
                    System.out.println("Hay un total de " + totalUsuarios() + " de usarios en el programa");
                    System.out.println("Hay un total de " + totalIncidencias() + " de incidencias en el programa");
                    opc = 0;
                    do {
                        Menus.menuAdmin();
                        opc = opc();
                        switch (opc){
                            case 1:
                                mostrar();
                                break;
                            case 2:
                                mostrarUsuarios();
                                break;
                            case 3:
                                mostrarTecnicos();
                                break;
                            case 4:
                                if (tecnico1 == null && tecnico2 == null){
                                    System.out.println("No shay tecnicos para asignar incidencias");
                                }else {
                                    String id = "";
                                    mostrar();
                                    System.out.println("Seleccione la id de la incidencia que desea asignar");
                                    id = s.nextLine();
                                    if (encontrarId(id) == null){
                                        System.out.println("No se ha encontrado ninguna incidencia con esa id");
                                    }else{
                                        mostrarTecnicos();
                                        System.out.println("Seleccione el apodo del tecnico que desea asignar la incidencia");
                                        nombre = s.nextLine();
                                        System.out.println(asignarIncidenciaTecnico(nombre,encontrarId(id))? "Asignado correctamente": "No se ha podido asignar la incidencia");
                                    }

                                }

                                break;
                            case 5:
                                if (tecnico1 != null && tecnico2 != null){
                                    System.out.println("No se pueden crear mas tecnicos");
                                }else {
                                    System.out.println("Que apodo desea poner al tecnico");
                                    nombre = s.nextLine();
                                    System.out.println("Que correo desea ponerle");
                                    correo = s.nextLine();
                                    System.out.println("Que contraseña desea ponerle");
                                    contrasena = s.nextLine();
                                    System.out.println(crearTecnico(nombre,correo,contrasena)?"Tecnico creado correctamente" : "No se ha podido crear el tecnico");
                                }
                                break;
                            case 6:
                                mostrarTecnicos();
                                System.out.println("Seleccione el apodo del tecnico que desea eliminar");
                                nombre = s.nextLine();
                                System.out.println(eliminarTecnico(nombre)? "Tecnico eliminado correctamente" : "No se ha encontrado dicho tecnico con ese apodo");
                                break;
                            case 7:
                                cerrarSesion();
                                break;
                            case 8:
                                int salir = 0;
                                System.out.println("Esta seguro de querer cerrar el programa");
                                System.out.println("        1. SI                2.NO");
                                if (salir == 1){
                                    inicio = true;
                                }
                                break;
                        }
                    }while(opc != 7);

                    break;
            }
        }while(!inicio);
    }
    //Asignar incidencia
    public static boolean asignarIncidenciaTecnico(String nombre, Incidencia inci){
        if (tecnico1 != null){
            if (nombre.equals(tecnico1.getApodo())){
                if (tecnico1.getIncidencia1() == null){
                    tecnico1.setIncidencia1(inci);
                    return true;
                }
            }

            if (nombre.equals(tecnico1.getApodo())){
                if (tecnico1.getIncidencia2() == null){
                    tecnico1.setIncidencia2(inci);
                    return true;
                }
            }

            return false;
        }
        if (tecnico2 != null){
            if (nombre.equals(tecnico2.getApodo())){
                if (tecnico2.getIncidencia1() == null){
                    tecnico2.setIncidencia1(inci);
                    return true;
                }
            }

            if (nombre.equals(tecnico2.getApodo())){
                if (tecnico2.getIncidencia2() == null){
                    tecnico2.setIncidencia2(inci);
                    return true;
                }
            }

            return false;
        }
        return false;
    }
    //Comprobar incidencia
    public static Incidencia encontrarId(String id){
        if (id.equals(inci1.getId()))return inci1;
        if (id.equals(inci2.getId()))return inci2;
        if (id.equals(inci3.getId()))return inci3;
        if (id.equals(inci4.getId()))return inci4;
        if (id.equals(inci5.getId()))return inci5;
        if (id.equals(inci6.getId()))return inci6;
        return null;
    }
    //Crear tecnico
    public static boolean crearTecnico(String nombre, String correo, String contra){
        if (tecnico1 == null){
            tecnico1 = new Tecnico(nombre,correo,contra);
            return true;
        }
        if (tecnico2 == null){
            tecnico2 = new Tecnico(nombre,correo,contra);
            return true;
        }
        return false;
    }
    //Mostrar los tecnicos disponibles
    public static void mostrarTecnicos(){
        if (tecnico1 != null) System.out.println(tecnico1);
        if (tecnico2 != null) System.out.println(tecnico2);
        if (tecnico1 == null && tecnico2 == null) System.out.println("No ha ningun tecnico creado");
    }
    public static void mostrarUsuarios(){
        if (usuario1 != null) System.out.println(usuario1);
        if (usuario2 != null) System.out.println(usuario2);
        if (usuario1 == null && usuario2 == null) System.out.println("No ha ningun usuario creado");
    }
    //Eliminar tecnico seleccionado
    public static boolean eliminarTecnico(String nombre){
        if (tecnico1 != null){
            if (nombre.equals(tecnico1.getApodo()))
                tecnico1 = null;
            return true;
        }
        if (tecnico2 != null){
            if (nombre.equals(tecnico2.getApodo()))
                tecnico2 = null;
            return true;
        }
        return false;
    }
    //Contar usuarios e incidencias
    public static int totalUsuarios(){
        int total = 0;
        if (usuario1 != null) total++;
        if (usuario2 != null) total++;
        return total;
    }
    public static int totalIncidencias(){
        int total = 0;
        if (inci1 != null) total++;
        if (inci2 != null) total++;
        if (inci3 != null) total++;
        if (inci4 != null) total++;
        if (inci5 != null) total++;
        if (inci6 != null) total++;
        return total;
    }

    public static void mostrar(){
        if (inci1 != null) System.out.println(inci1);
        if (inci2 != null) System.out.println(inci2);
        if (inci3 != null) System.out.println(inci3);
        if (inci4 != null) System.out.println(inci4);
        if (inci5 != null) System.out.println(inci5);
        if (inci6 != null) System.out.println(inci6);
    }
    //Para cerrar sesion
    public static void cerrarSesion() throws InterruptedException {
        System.out.println("Cerrando sesion");
        menu = 0;
        Utils.cargando();
    }
    // INCIDENCIAS
    public static int comprobarIncidenciasDisponibles(Usuario user){
        int total = 0;
        if (user.getIncidencia1() == null){
            total++;
        }
        if (user.getIncidencia2() == null){
            total++;
        }
        if (user.getIncidencia3() == null){
            total++;
        }
        return total;
    }
    public static int generarIdIncidencia(){
        int id = 0;
        id = (int) (Math.random()*100001)-1;
        return id;
    }
    public static boolean crearIncidencia(String creador, String comentario, String prioridad, LocalDate fechaCreacion){
        String id = "";
        id = String.valueOf(generarIdIncidencia());
        if (inci1 == null){
            inci1 = new Incidencia(id,creador,comentario,prioridad,fechaCreacion);
            asignarIncidenciaUsuario(tempUsuario,inci1);
            return true;
        }
        if (inci2 == null){
            inci2 = new Incidencia(id,creador,comentario,prioridad,fechaCreacion);
            asignarIncidenciaUsuario(tempUsuario,inci2);
            return true;
        }
        if (inci3 == null){
            inci3 = new Incidencia(id,creador,comentario,prioridad,fechaCreacion);
            asignarIncidenciaUsuario(tempUsuario,inci3);
            return true;
        }
        if (inci4 == null){
            inci4 = new Incidencia(id,creador,comentario,prioridad,fechaCreacion);
            asignarIncidenciaUsuario(tempUsuario,inci4);
            return true;
        }
        if (inci5 == null){
            inci5 = new Incidencia(id,creador,comentario,prioridad,fechaCreacion);
            asignarIncidenciaUsuario(tempUsuario,inci5);
            return true;
        }
        if (inci6 == null){
            inci6 = new Incidencia(id,creador,comentario,prioridad,fechaCreacion);
            asignarIncidenciaUsuario(tempUsuario,inci6);
            return true;
        }
        return false;
    }
    public static void asignarIncidenciaUsuario(Usuario tempUsuario,Incidencia inci){
        if (tempUsuario.getIncidencia1() == null) {
            tempUsuario.setIncidencia1(inci);
        }else if (tempUsuario.getIncidencia2() == null){
            tempUsuario.setIncidencia2(inci);
        }else if (tempUsuario.getIncidencia3() == null){
            tempUsuario.setIncidencia3(inci);
        }
    }

    //Seleccionar opcion del menu de los usuarios
    public static int opc(){
        int opc = 0;
        try {
            opc = Integer.parseInt(s.nextLine());
        }catch (NumberFormatException e){
            return 0;
        }
        return opc;
    }
    //Para ya dejar iniciado el usuario
    public static void inioSesion(){
        if (usuario1 != null) {
            if (tempUsuario.getApodo().equals(usuario1.getApodo()))
                usuario1.setInicioSesion(true);
        }
        if (usuario2 != null) {
            if (tempUsuario.getApodo().equals(usuario2.getApodo()))
                usuario2.setInicioSesion(true);
        }
    }
    //RESETEAR VARAIBLES
    public static void resetearTemp(){
        tempAdmin = null;
        tempTecnico = null;
        tempUsuario = null;
    }
    //INICIAR SESION USUARIO
    public static Object iniciarSesion(String nombre){
        if (usuario1 != null){
            if (nombre.equals(usuario1.getApodo())) return tempUsuario = usuario1;
        }
        if (usuario2 != null){
            if (nombre.equals(usuario2.getApodo()))return tempUsuario = usuario2;
        }
        if (tecnico1 != null){
            if (nombre.equals(tecnico1.getApodo()))return tempTecnico = tecnico1;
        }
        if (tecnico2 != null){
            if (nombre.equals(tecnico2.getApodo()))return tempTecnico = tecnico2;
        }
        if (admin != null){
            if (nombre.equals(admin.getApodo()))return tempAdmin = admin;
        }
        return null;
    }

    //USUARIO REGISTRO
    //Funcion que realiza el registro de un nuevo Usuario
    public static void nuevoUsuario() throws InterruptedException {
        if (registrarse()==3){
            System.out.println("No se pueden crear más usuarios");
            Utils.esperar();
            Utils.limpiarPantalla();
        }else{
            do {

                System.out.println("Dime el nombre para la cuenta");
                nombre = s.nextLine();
                if (!noSeRepiteNombre(nombre)) System.out.println("Este nombre ya esta usado por otra cuenta");

            }while (!noSeRepiteNombre(nombre));
            do {

                System.out.println("Dime el correo para la cuenta");
                correo = s.nextLine();
                if (!noSeRepiteCorreo(correo)) System.out.println("Esta correo ya esta usado por otra cuenta");

            }while(!noSeRepiteCorreo(correo));
            do {
                //if (!contra1.equals(contra2)) System.out.println("La contraseña no coincide");
                System.out.println("Escriba la contrasena para la cuenta");
                contra1 = s.nextLine();
                System.out.println("Repita la contrasena para la cuenta");
                contra2 = s.nextLine();
                if (!contra1.equals(contra2)) System.out.println("Las contraseñas no coinciden.");
            }while(!contra1.equals(contra2));
            if (usuario1 == null){
                usuario1 = new Usuario(nombre,correo,contra1,generaToken());
                System.out.println(enviaMail(correo,mensaje(usuario1),"Token de REGISTRO") ? "Correo enviado correctamente correctamente" : "No enviado");
                System.out.println(enviaMensajeTelegram(usuario1.getToken(),766869962)? "Telegram enviado correctamente" : "No enviado");
            }else if (usuario2 == null){
                usuario2 = new Usuario(nombre,correo,contra1,generaToken());
                System.out.println(enviaMail(correo,mensaje(usuario2),"Token de REGISTRO") ? "Correo enviado correctamente correctamente" : "No enviado");
                System.out.println(enviaMensajeTelegram(usuario2.getToken(),766869962)? "Telegram enviado correctamente" : "No enviado");
            }
        }
        Utils.cargando();

        System.out.println("Usuario creado correctamente");
    }
    //Comprobar que hay usuarios disponibles para registrarse
    public static int registrarse(){
        if (usuario1 == null && usuario2 == null)return 1;
        if (usuario1 == null || usuario2 == null)return 2;
        return 3;
    }
    //Comprobar que no se repite el nombre
    public static boolean noSeRepiteNombre(String nombre){
        if (registrarse()==1)return true;
        if (usuario1 != null){
            if (registrarse()==2 && usuario1.getApodo().equals(nombre))return false;
        }else return true;
        if (usuario2 != null){
            if (registrarse()==2 && usuario2.getApodo().equals(nombre))return false;
        }else return true;
        return true;
    }
    //Comprobar que no se repite el correo
    public static boolean noSeRepiteCorreo(String correo){
        if (registrarse()==1)return true;
        if (usuario1 != null){
            if (registrarse()==2 && usuario1.getEmail().equals(correo))return false;
        }else return true;
        if (usuario2 != null){
            if (registrarse()==2 && usuario2.getEmail().equals(correo))return false;
        }else return true;
        return true;
    }

    //GENERAR TOKEN
    public static int generaLongitud() {
        int num;
        do {
            num = (int) (Math.random() * 20);
        } while (num < 13 || num > 17);
        return num;
    }
    public static char generaLetra() {
        int num;
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUWXYZ";
        do {
            num = (int) (Math.random() * 30);
        } while (num > 25);
        return letras.charAt(num);
    }
    public static boolean numOLetra() {
        int num;
        num = (int) (Math.random() * 2);
        return num == 0;
    }
    public static String generaToken() {
        String token = "";
        for (int i = 1; i <= generaLongitud(); i++) {
            if (numOLetra()) token += generaLetra();
            else {
                int num = (int) (Math.random() * 11);
                token += num;
            }
        }
        return token;

    }

    //MAIL
    static boolean enviaMail(String destino, String mensaje, String asunto){

        boolean resultado = false;
        String emisor = "manuel.hermoso@fernando3martos.com";
        String username = "dam3@carlosprofe.es";
        String password = "Olivo.2022";
        String host = "ssl0.ovh.net";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port","587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emisor));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destino));
            message.setSubject(asunto);
            message.setContent(mensaje,"text/html; charset=utf-8");
            Transport.send(message);
            resultado = true;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return resultado;
    }
    //TELEGRAM
    static boolean enviaMensajeTelegram(String mensaje, int id){
        String direccion; // URL de la API de mi bot en mi conversacion
        String fijo = "https://api.telegram.org/bot5216262283:AAFqSfvcFeoy96SxZg2gflSPu2iOW8x4Nnw/sendMessage?chat_id=" + id + "&text=";
        direccion = fijo + mensaje;
        URL url;
        boolean dev = false;
        try {
            url = new URL(direccion);
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            dev = true;
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return dev;
    }

    //MENSAJE PARA CORREO

    public static String mensaje(Usuario usuario){
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
                "    <p style=\"font-size: 14px; line-height: 170%;\">Hola USER,</p>\n" +
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
                "      <span style=\"display:block;padding:16px 20px;line-height:120%;\"><strong><span style=\"font-size: 16px; line-height: 19.2px; font-family: Raleway, sans-serif;\">"+ usuario.getToken() +"</span></strong></span>\n" +
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
