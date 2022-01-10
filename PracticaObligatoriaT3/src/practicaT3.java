import modelo.Administrador;
import modelo.Incidencia;
import modelo.Tecnico;
import modelo.Usuario;
import utils.Menu;
import utils.Utils;

import java.time.LocalDate;
import java.util.Scanner;

public class practicaT3 {


    public static void main(String[] args) throws InterruptedException {
        // creamos la virable que recopila la información sobre si deseamos iniciar sesión o registrarnos
        int opcElegida = 0, tipoUsuario = 0, opcTipoUsuario = 0;

        // creamos la variable que recopila el tipo de usuario que iniciamos
        boolean usuarioNormal = false, usuarioTecnico = false, usuarioAdmin = false, finApliacion = false,
                cerrarTecnico = false, cerrarUsuario = false, cerrarAdmin = false;

        // creamos la variable que recopila el tipo de usuario que iniciamos
        boolean usuarioNormal1 = false; //si es true nos referimos al primer usuario si no al segundo

        // creamos la variable que recopila el tipo de usuario que iniciamos
        boolean usuarioTecnico1 = false; //si es true nos referimos al primer tecnico si no al segundo

        //creamos el scanner
        var s = new Scanner(System.in);

        //variables para el logueo o el registro
        String contrasena = "", apodo = "", correo = "", contra = "";

        //Definimos los tipos de usarios
        Usuario usuario1 = null;
        Usuario usuario2 = null;
        Tecnico tecnico1 = null;
        Tecnico tecnico2 = new Tecnico("tech","tech@tech.es","tech");
        Administrador admin = new Administrador("admin","admin@admin.es","admin");

        //Definimos las incidencias
        Incidencia inci1 = null;
        Incidencia inci2 = null;
        Incidencia inci3 = null;
        Incidencia inci4 = null;
        Incidencia inci5 = null;
        Incidencia inci6 = null;

        //Declaro la variable de fecha actual
        LocalDate hoy = LocalDate.now();

        //Para crear Incidencias
        String comentario = "", prioridad = "", nombre = "";
        int id = 0;
        do {
            //reseteamos variables
            usuarioNormal = false;
            usuarioAdmin = false;
            usuarioTecnico = false;
            cerrarAdmin = false;
            cerrarTecnico = false;
            cerrarUsuario = false;
            opcElegida = 0;

            do {
               Menu.menuPrincipal();
                opcElegida = Integer.parseInt(s.nextLine());
            }while (opcElegida != 1 && opcElegida != 2);
            switch (opcElegida){ 
                case 1: //Opción de iniciar sesión
                    do {
                        Menu.menuInciarSesion();
                        opcElegida = Integer.parseInt(s.nextLine());
                        switch (opcElegida){//tipo de usario
                            case 1:
                                System.out.print("Dime el apodo del usuario: ");
                                nombre = s.nextLine();
                                //Caso de iniciar sesión de usuario1
                                if (usuario1 != null){ //Comprobar que el usuario existe
                                    if (nombre.equals(usuario1.getApodo())){
                                        System.out.print("Dime la contraseña del usuario: ");
                                        if (s.nextLine().equals(usuario1.getContrasena())){
                                            usuarioNormal = true; //Para que entre en el menu de usuario
                                            usuarioNormal1 = true; //Para que sepamos que nos referimos al primer usuario
                                        }
                                    }
                                    //Caso de iniciar sesión de usuario2
                                }else{
                                    System.out.println();
                                    System.out.println("No hay usuario 1 registrado.");
                                    System.out.println();
                                }
                                if (usuario2 != null){  //Comprobar que el usuario existe
                                        if (nombre.equals(usuario2.getApodo())){
                                            System.out.print("Dime la contraseña del usuario: ");
                                            if (s.nextLine().equals(usuario2.getContrasena())){
                                                usuarioNormal = true; //Para que entre en el menu de usuario
                                                usuarioNormal1 = false; //Para que sepamos que nos referimos al segundo usuario
                                            }
                                        }
                                    }else{
                                    System.out.println();
                                    System.out.println("No hay usuario 2 registrado.");
                                    System.out.println();
                                }


                                break;
                            case 2:
                                //Caso de iniciar sesión de tecnico1
                                if (tecnico1 != null || tecnico2 != null)System.out.print("Dime el apodo del tecnico: ");
                                if (tecnico1 != null){  //Comprobar que el usuario existe
                                    if (s.nextLine().equals(tecnico1.getApodo())){
                                        System.out.print("Dime la contraseña del tecnico: ");
                                        if (s.nextLine().equals(tecnico1.getContrasena())){
                                            usuarioTecnico = true; //Para que entre en el menu de tecnico
                                            usuarioTecnico1 = true; //Para que sepamos que nos referimos al primer tecnico
                                        }
                                    }
                                }
                                //Caso de iniciar sesión de tecnico2
                                if (tecnico2 != null){  //Comprobar que el usuario existe
                                        if (s.nextLine().equals(tecnico2.getApodo())){
                                            System.out.print("Dime la contraseña del tecnico: ");
                                            if (s.nextLine().equals(tecnico2.getContrasena())){
                                                usuarioTecnico = true; //Para que entre en el menu de tecnico
                                                usuarioTecnico1 = false; //Para que sepamos que nos referimos al segundo tecnico
                                            }
                                        }
                                    }

                                break;

                            case 3:
                                //Caso de iniciar sesión de admin
                                System.out.print("Dime el apodo del admin: ");
                                if (admin != null){  //Comprobar que el usuario existe
                                    if (s.nextLine().equals(admin.getApodo())){
                                        System.out.println("Dime la contraseña del admin: ");
                                        if (s.nextLine().equals(admin.getContrasena())){
                                            usuarioAdmin = true; //Para que entre en el menu de admin
                                        }
                                    }
                                }
                                break;
                            default:
                                System.out.println("No ha seleccionado una de las opciones disponibles en la pantalla");
                                break;
                        }


                    }while(!usuarioNormal & !usuarioAdmin & !usuarioTecnico);
                    break;


                case 2:  //Los que se registran solo solo usuarios
                    if (usuario1 != null && usuario2 != null){
                        System.out.println("Ya estan todos los usuarios creados.");
                    }else {
                        System.out.print("Dime el apodo del usuario: ");
                        apodo = s.nextLine();
                        System.out.print("Dime el correo del usuario: ");
                        correo = s.nextLine();
                        do {
                            System.out.print("Dime la contraseña del usuario: ");
                            contra = s.nextLine();
                            System.out.print("Repite la contraseña: ");
                            contrasena = s.nextLine();
                        } while (contra == contrasena);
                        if (usuario1 == null){
                            usuario1 = new Usuario(apodo,correo,contrasena);
                        }else usuario2 = new Usuario(apodo,correo,contrasena);
                        usuarioNormal = true;
                        //if (usuario1 != null)usuario1.
                    }
                    break;



            }
            //Para asignar que tipo de usuario es
            tipoUsuario = (usuarioNormal)? 1 : (usuarioTecnico ? 2 : (usuarioAdmin) ? 3 : 0);
            System.out.println(tipoUsuario);
            switch (tipoUsuario){
                case 1: //Perfil usuario normal:
                    do {
                        //usuarioNormal lo usamos como una bandera para saber si estamos con el primer usuario o con el segundo
                        if (usuario2 != null){
                            System.out.println("Bienvenido " + ((usuarioNormal1)? usuario1.getApodo() : usuario2.getApodo()) + ", tiene usted perfil de usuario normal");
                        }else System.out.println("Bienvenido " + usuario1.getApodo() + ", tiene usted perfil de usuario normal");

                        Menu.menuUsuario();
                        opcTipoUsuario = Integer.parseInt(s.nextLine());

                        switch (opcTipoUsuario){
                            case 1:
                                if (!usuarioNormal1) {
                                    if (inci1 != null && inci2 != null && inci3 != null){
                                        System.out.println("Ahora mismo estan todas creadas. ");
                                    }else{
                                        //Para ir aumentando la id y nincuno tenga ninguna igual
                                        id++;
                                        System.out.print("¿Cual es el motivo por el que abre la incidencia?: ");
                                        comentario = s.nextLine();
                                        System.out.print("¿Que prioridad tiene?: ");
                                        prioridad = s.nextLine();

                                        if (inci1 == null){
                                            inci1 = new Incidencia(String.valueOf(id),usuario1.getApodo(),comentario,prioridad,hoy);
                                            usuario1.setIncidencia1(inci1);
                                        }else if (inci2 == null){
                                            inci2 = new Incidencia(String.valueOf(id),usuario1.getApodo(),comentario,prioridad,hoy);
                                            usuario1.setIncidencia2(inci2);
                                        }else if (inci3 == null){
                                            inci3 = new Incidencia(String.valueOf(id),usuario1.getApodo(),comentario,prioridad,hoy);
                                            usuario1.setIncidencia3(inci3);
                                        }
                                    }
                                }else if (inci4 != null && inci5 != null && inci6 != null){
                                    System.out.println("Ahora mismo estan todas creadas. ");
                                }else {
                                    id++;
                                    System.out.println("¿Cual es el motivo por el que abre la incidencia?: ");
                                    comentario = s.nextLine();
                                    System.out.println("¿Que prioridad tiene?: ");
                                    prioridad = s.nextLine();
                                    if (usuario2 != null) {
                                        if (inci4 == null) {
                                            inci4 = new Incidencia(String.valueOf(id), usuario2.getApodo(), comentario, prioridad, hoy);
                                            usuario2.setIncidencia1(inci4);
                                        } else if (inci5 == null) {
                                            inci5 = new Incidencia(String.valueOf(id), usuario2.getApodo(), comentario, prioridad, hoy);
                                            usuario2.setIncidencia2(inci5);
                                        } else if (inci6 == null) {
                                            inci6 = new Incidencia(String.valueOf(id), usuario2.getApodo(), comentario, prioridad, hoy);
                                            usuario2.setIncidencia3(inci6);
                                        }
                                    }
                                }

                                break;
                            case 2:

                                if (!usuarioNormal1) {
                                System.out.println(usuario1.comprobarIncidencias());
                            }else {
                                    if(usuario2 != null) {
                                        System.out.println(usuario2.comprobarIncidencias());
                                    }
                                }
                                break;
                            case 3:
                                if (usuarioNormal1) {
                                    if (tecnico1 != null) {
                                        if (tecnico1.getIncidencia1().getCreador().equals(usuario1.getApodo())) {
                                            System.out.println(tecnico1.getIncidencia1());
                                        }
                                        if (tecnico1.getIncidencia2().getCreador().equals(usuario1.getApodo())) {
                                            System.out.println(tecnico1.getIncidencia2());
                                        }
                                    }
                                        if (tecnico2 != null) {
                                            if (tecnico2.getIncidencia1() != null) {
                                                if (tecnico2.getIncidencia1().getCreador().equals(usuario1.getApodo())) {
                                                    System.out.println(tecnico2.getIncidencia1());

                                                }
                                            }
                                            if (tecnico2.getIncidencia2()!=null) {
                                                if (tecnico2.getIncidencia2().getCreador().equals(usuario1.getApodo())) {
                                                    System.out.println(tecnico2.getIncidencia2());
                                                }
                                            }
                                        }
                                }else if (tecnico1 != null) {
                                    if (tecnico1.getIncidencia1().getCreador().equals(usuario2.getApodo())) {
                                        System.out.println(tecnico1.getIncidencia1());
                                    }
                                    if (tecnico1.getIncidencia2().getCreador().equals(usuario2.getApodo())) {
                                        System.out.println(tecnico1.getIncidencia2());
                                    }
                                }
                                    if (tecnico2 != null){
                                        if (tecnico2.getIncidencia1() != null) {
                                            if (usuario2 != null){
                                                if (tecnico2.getIncidencia1().getCreador().equals(usuario2.getApodo())) {
                                                    System.out.println(tecnico2.getIncidencia1());
                                                }
                                            }
                                        }
                                        if (tecnico2.getIncidencia2() != null) {
                                            if (usuario2 != null) {
                                                if (tecnico2.getIncidencia2().getCreador().equals(usuario2.getApodo())) {
                                                    System.out.println(tecnico2.getIncidencia2());
                                                }
                                            }
                                        }
                                }

                                break;
                            case 4:
                                if (usuarioNormal1) {
                                    System.out.println(usuario1);
                                }else System.out.println(usuario2);
                                break;
                            case 5:
                                if (usuarioNormal1){
                                    do {
                                        System.out.print("Dime la contraseña del usuario: ");
                                        usuario1.setContrasena(s.nextLine());
                                        System.out.print("Repite la contraseña: ");
                                        contrasena = s.nextLine();
                                    }while(!usuario1.comprobarContraseña(contrasena));
                                }else {
                                    do {
                                        System.out.print("Dime la contraseña del usuario: ");
                                        usuario2.setContrasena(s.nextLine());
                                        System.out.print("Repite la contraseña: ");
                                        contrasena = s.nextLine();
                                    } while (!usuario2.comprobarContraseña(contrasena));
                                }
                                break;
                            case 6:
                                cerrarUsuario = true;
                                break;
                        }
                    }while (cerrarUsuario == false);

                    break;
                case 2: //Perfil técnico:
                    do {
                        //usuarioTecnico1 lo usamos como una bandera para saber si estamos con el primer tecnico o con el segundo
                        System.out.println("Bienvenido " + ((usuarioTecnico1)? tecnico1.getApodo() : tecnico2.getApodo()) + ", tiene usted perfil de tecnico");
                        Menu.menuTecnico();
                        opcTipoUsuario = Integer.parseInt(s.nextLine());

                        switch (opcTipoUsuario){
                            case 1:
                                if (usuarioTecnico1){
                                    if (inci1 != null){
                                        if (inci1.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci1);
                                    }
                                    if (inci2 != null){
                                        if (inci2.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci2);
                                    }
                                    if (inci3 != null){
                                        if (inci3.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci3);
                                    }
                                    if (inci4 != null){
                                        if (inci4.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci4);
                                    }
                                    if (inci5 != null){
                                        if (inci5.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci5);
                                    }
                                    if (inci6 != null){
                                        if (inci6.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci6);
                                    }

                                }else{
                                    if (inci1 != null){
                                        if (inci1.getAsignado().equals(tecnico2.getApodo())) System.out.println(inci1);
                                    }
                                    if (inci2 != null){
                                        if (inci2.getAsignado().equals(tecnico2.getApodo())) System.out.println(inci2);
                                    }
                                    if (inci3 != null){
                                        if (inci3.getAsignado().equals(tecnico2.getApodo())) System.out.println(inci3);
                                    }
                                    if (inci4 != null){
                                        if (inci4.getAsignado().equals(tecnico2.getApodo())) System.out.println(inci4);
                                    }
                                    if (inci5 != null){
                                        if (inci5.getAsignado().equals(tecnico2.getApodo())) System.out.println(inci5);
                                    }
                                    if (inci6 != null){
                                        if (inci6.getAsignado().equals(tecnico2.getApodo())) System.out.println(inci6);
                                    }

                                }
                                break;
                            case 2:
                                String op, comentar;
                                if (usuarioTecnico1){
                                    if (inci1 != null){
                                        if (inci1.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci1);
                                    }
                                    if (inci2 != null){
                                        if (inci2.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci2);
                                    }
                                    if (inci3 != null){
                                        if (inci3.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci3);
                                    }
                                    if (inci4 != null){
                                        if (inci4.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci4);
                                    }
                                    if (inci5 != null){
                                        if (inci5.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci5);
                                    }
                                    if (inci6 != null){
                                        if (inci6.getAsignado().equals(tecnico1.getApodo())) System.out.println(inci6);
                                    }
                                    System.out.print("Seleccione que id quiere cerrar: ");
                                    op = s.nextLine();
                                    if (inci1 != null){
                                    if (inci1.getId().equals(op) && inci1.getAsignado().equals(tecnico1.getApodo())){
                                        System.out.print("¿Que comentario desea poner?: ");
                                        comentar = s.nextLine();
                                        inci1.setComentarioTecnico(comentar);
                                        inci2.setFechaResolucion(hoy);
                                        inci1.setEstado(true);
                                        tecnico1.insertarIncidencia(inci1);
                                        inci1 = null;
                                        usuario1.setIncidencia1(null);
                                    }
                                    }
                                    if (inci2 != null) {
                                        if (inci2.getId().equals(op) && inci2.getAsignado().equals(tecnico1.getApodo())) {
                                            System.out.print("¿Que comentario desea poner?: ");
                                            comentar = s.nextLine();
                                            inci2.setComentarioTecnico(comentar);
                                            inci2.setFechaResolucion(hoy);
                                            inci2.setEstado(true);
                                            tecnico1.insertarIncidencia(inci2);
                                            inci2 = null;
                                            usuario1.setIncidencia2(null);
                                        }
                                    }
                                    if (inci3 != null) {
                                        if (inci3.getId().equals(op) && inci3.getAsignado().equals(tecnico1.getApodo())) {
                                            System.out.print("¿Que comentario desea poner?: ");
                                            comentar = s.nextLine();
                                            inci3.setComentarioTecnico(comentar);
                                            inci3.setFechaResolucion(hoy);
                                            inci3.setEstado(true);
                                            tecnico1.insertarIncidencia(inci3);
                                            inci3 = null;
                                            usuario1.setIncidencia3(null);
                                        }
                                    }
                                        if (inci4 != null) {
                                            if (inci4.getId().equals(op) && inci4.getAsignado().equals(tecnico1.getApodo())) {
                                                System.out.print("¿Que comentario desea poner?: ");
                                                comentar = s.nextLine();
                                                inci4.setComentarioTecnico(comentar);
                                                inci4.setFechaResolucion(hoy);
                                                inci4.setEstado(true);
                                                tecnico1.insertarIncidencia(inci4);
                                                inci4 = null;
                                                usuario1.setIncidencia1(null);
                                            }
                                        }
                                            if (inci5 != null) {
                                                if (inci5.getId().equals(op) && inci5.getAsignado().equals(tecnico1.getApodo())) {
                                                    System.out.print("¿Que comentario desea poner?: ");
                                                    comentar = s.nextLine();
                                                    inci5.setComentarioTecnico(comentar);
                                                    inci5.setFechaResolucion(hoy);
                                                    inci5.setEstado(true);
                                                    tecnico1.insertarIncidencia(inci5);
                                                    inci5 = null;
                                                    usuario2.setIncidencia2(null);
                                                }
                                            }
                                                if (inci6 != null) {
                                                    if (inci6.getId().equals(op) && inci6.getAsignado().equals(tecnico1.getApodo())) {
                                                        System.out.print("¿Que comentario desea poner?: ");
                                                        comentar = s.nextLine();
                                                        inci6.setComentarioTecnico(comentar);
                                                        inci6.setFechaResolucion(hoy);
                                                        inci6.setEstado(true);
                                                        tecnico1.insertarIncidencia(inci6);
                                                        inci6 = null;
                                                        usuario2.setIncidencia3(null);
                                                    }
                                                }


                                }else{
                                    if (tecnico2 != null) {
                                        if (inci1 != null) {
                                            if (inci1.getAsignado().equals(tecnico2.getApodo()))
                                                System.out.println(inci1);
                                        }
                                        if (inci2 != null) {
                                            if (inci2.getAsignado().equals(tecnico2.getApodo()))
                                                System.out.println(inci2);
                                        }
                                        if (inci3 != null) {
                                            if (inci3.getAsignado().equals(tecnico2.getApodo()))
                                                System.out.println(inci3);
                                        }
                                        if (inci4 != null) {
                                            if (inci4.getAsignado().equals(tecnico2.getApodo()))
                                                System.out.println(inci4);
                                        }
                                        if (inci5 != null) {
                                            if (inci5.getAsignado().equals(tecnico2.getApodo()))
                                                System.out.println(inci5);
                                        }
                                        if (inci6 != null) {
                                            if (inci6.getAsignado().equals(tecnico2.getApodo()))
                                                System.out.println(inci6);
                                        }
                                    }
                                    System.out.print("Seleccione que id quiere cerrar: ");
                                    op = s.nextLine();
                                    if (inci1 != null){
                                        if (inci1.getId().equals(op) && inci1.getAsignado().equals(tecnico2.getApodo())){
                                            System.out.print("¿Que comentario desea poner?: ");
                                            comentar = s.nextLine();
                                            inci1.setComentarioTecnico(comentar);
                                            inci1.setFechaResolucion(hoy);
                                            inci1.setEstado(true);
                                            System.out.println(tecnico2.insertarIncidencia(inci1));
                                            inci1 = null;
                                            usuario1.setIncidencia1(null);
                                        }
                                    }
                                    if (inci2 != null) {
                                        if (inci2.getId().equals(op) && inci2.getAsignado().equals(tecnico2.getApodo())) {
                                            System.out.println("¿Que comentario desea poner?: ");
                                            comentar = s.nextLine();
                                            inci2.setComentarioTecnico(comentar);
                                            inci2.setFechaResolucion(hoy);
                                            inci2.setEstado(true);
                                            System.out.println(tecnico2.insertarIncidencia(inci2));
                                            inci2 = null;
                                            usuario1.setIncidencia2(null);
                                        }
                                    }
                                    if (inci3 != null) {
                                        if (inci3.getId().equals(op) && inci3.getAsignado().equals(tecnico2.getApodo())) {
                                            System.out.println("¿Que comentario desea poner?: ");
                                            comentar = s.nextLine();
                                            inci3.setComentarioTecnico(comentar);
                                            inci3.setFechaResolucion(hoy);
                                            inci3.setEstado(true);
                                            System.out.println(tecnico2.insertarIncidencia(inci3));
                                            inci3 = null;
                                            usuario1.setIncidencia3(null);
                                        }
                                    }
                                    if (inci4 != null) {
                                        if (inci4.getId().equals(op) && inci4.getAsignado().equals(tecnico2.getApodo())) {
                                            System.out.println("¿Que comentario desea poner?: ");
                                            comentar = s.nextLine();
                                            inci4.setComentarioTecnico(comentar);
                                            inci4.setFechaResolucion(hoy);
                                            inci4.setEstado(true);
                                            System.out.println(tecnico2.insertarIncidencia(inci4));
                                            inci4 = null;
                                            usuario2.setIncidencia1(null);
                                        }
                                    }
                                    if (inci5 != null) {
                                        if (inci5.getId().equals(op) && inci5.getAsignado().equals(tecnico2.getApodo())) {
                                            System.out.println("¿Que comentario desea poner?: ");
                                            comentar = s.nextLine();
                                            inci5.setComentarioTecnico(comentar);
                                            inci5.setFechaResolucion(hoy);
                                            inci5.setEstado(true);
                                            System.out.println(tecnico2.insertarIncidencia(inci5));
                                            inci5 = null;
                                            usuario1.setIncidencia2(null);
                                        }
                                    }
                                    if (inci6 != null) {
                                        if (inci6.getId().equals(op) && inci6.getAsignado().equals(tecnico2.getApodo())) {
                                            System.out.println("¿Que comentario desea poner?: ");
                                            comentar = s.nextLine();
                                            inci6.setComentarioTecnico(comentar);
                                            inci6.setFechaResolucion(hoy);
                                            inci6.setEstado(true);
                                            System.out.println(tecnico2.insertarIncidencia(inci6));
                                            inci6 = null;
                                            usuario2.setIncidencia3(null);
                                        }
                                    }
                                }
                                break;
                            case 3:
                                if (usuarioTecnico1){
                                    System.out.println(tecnico1.incidenciasResueltas());
                                }else System.out.println(tecnico2.incidenciasResueltas());
                                break;
                            case 4:
                                if (usuarioTecnico1){
                                    System.out.println(tecnico1);
                                }else System.out.println(tecnico2);
                                break;
                            case 5:
                                if (usuarioTecnico1){
                                    do {
                                        System.out.print("Dime la contraseña del usuario: ");
                                        tecnico1.setContrasena(s.nextLine());
                                        System.out.println("Repite la contraseña: ");
                                        contrasena = s.nextLine();
                                    }while(!tecnico1.comprobarContraseña(contrasena));
                                }else {
                                    do {
                                        System.out.println("Dime la contraseña del usuario: ");
                                        usuario2.setContrasena(s.nextLine());
                                        System.out.println("Repite la contraseña: ");
                                        contrasena = s.nextLine();
                                    } while (!usuario2.comprobarContraseña(contrasena));
                                }
                                break;
                            case 6:
                                cerrarTecnico = true;
                                break;
                        }
                    }while (cerrarTecnico == false);

                    break;
                case 3: //Perfil administrador:
                    do {
                        System.out.println("Bienvenido " + admin.getApodo() + ", tiene usted perfil de administracion");
                        Menu.menuAdmin();
                        opcTipoUsuario = Integer.parseInt(s.nextLine());

                        switch (opcTipoUsuario) {
                            case 1:
                                if (usuario1 != null) System.out.println(usuario1.comprobarIncidencias());

                                if (usuario2 != null) System.out.println(usuario2.comprobarIncidencias());
                                break;
                            case 2:
                                String idSeleccionada, tecnicoSeleccionado;
                                System.out.println((usuario1 != null) ? usuario1 : "No hay ningun primer usuario");
                                Utils.espacio();
                                System.out.println((usuario2 != null) ? usuario2 : "No hay ningun segundo usuario");
                                break;
                            case 3:
                                System.out.println((tecnico1 != null) ? tecnico1 : "No hay ningun primer tecnico");
                                Utils.espacio();
                                System.out.println((tecnico2 != null) ? tecnico2 : "No hay ningun segundo tecnico");
                                break;
                            case 4:
                                if (inci1 == null && inci2 == null && inci3 == null && inci4 == null && inci5 == null && inci6 == null) {
                                    System.out.println("No hay ninguna incidencia para asignar");
                                } else{
                                    if (usuario1 != null) System.out.println(usuario1.comprobarIncidencias());

                                if (usuario2 != null) System.out.println(usuario2.comprobarIncidencias());
                                System.out.print("Seleccione la id que desea asignar: ");
                                idSeleccionada = s.nextLine();
                                System.out.println((tecnico1 != null) ? tecnico1 : "No hay ningun primer tecnico");
                                System.out.println((tecnico2 != null) ? tecnico2 : "No hay ningun segundo tecnico");
                                System.out.print("Introduce el apodo del tecnico al  que desea asignar la incidencia: ");
                                tecnicoSeleccionado = s.nextLine();
                                if (tecnico1 != null) {
                                    if (inci1 != null) {
                                        if (inci1.comprobarNombre(idSeleccionada) && tecnico1.comprobarNombre(tecnicoSeleccionado))
                                            inci1.setAsignado(tecnicoSeleccionado);
                                    }
                                    if (inci2 != null) {
                                        if (inci2.comprobarNombre(idSeleccionada) && tecnico1.comprobarNombre(tecnicoSeleccionado))
                                            inci2.setAsignado(tecnicoSeleccionado);
                                    }
                                    if (inci3 != null) {
                                        if (inci3.comprobarNombre(idSeleccionada) && tecnico1.comprobarNombre(tecnicoSeleccionado))
                                            inci3.setAsignado(tecnicoSeleccionado);
                                    }
                                    if (inci4 != null) {
                                        if (inci4.comprobarNombre(idSeleccionada) && tecnico1.comprobarNombre(tecnicoSeleccionado))
                                            inci4.setAsignado(tecnicoSeleccionado);
                                    }
                                    if (inci5 != null) {
                                        if (inci5.comprobarNombre(idSeleccionada) && tecnico1.comprobarNombre(tecnicoSeleccionado))
                                            inci5.setAsignado(tecnicoSeleccionado);
                                    }
                                    if (inci6 != null) {
                                        if (inci6.comprobarNombre(idSeleccionada) && tecnico1.comprobarNombre(tecnicoSeleccionado))
                                            inci6.setAsignado(tecnicoSeleccionado);
                                    }
                                }
                                if (tecnico2 != null) {
                                    if (inci1 != null) {
                                        if (inci1.comprobarNombre(idSeleccionada) && tecnico2.comprobarNombre(tecnicoSeleccionado))
                                            inci1.setAsignado(tecnicoSeleccionado);
                                    }
                                    if (inci2 != null) {
                                        if (inci2.comprobarNombre(idSeleccionada) && tecnico2.comprobarNombre(tecnicoSeleccionado))
                                            inci2.setAsignado(tecnicoSeleccionado);
                                    }
                                    if (inci3 != null) {
                                        if (inci3.comprobarNombre(idSeleccionada) && tecnico2.comprobarNombre(tecnicoSeleccionado))
                                            inci3.setAsignado(tecnicoSeleccionado);
                                    }
                                    if (inci4 != null) {
                                        if (inci4.comprobarNombre(idSeleccionada) && tecnico2.comprobarNombre(tecnicoSeleccionado))
                                            inci4.setAsignado(tecnicoSeleccionado);
                                    }
                                    if (inci5 != null) {
                                        if (inci5.comprobarNombre(idSeleccionada) && tecnico2.comprobarNombre(tecnicoSeleccionado))
                                            inci5.setAsignado(tecnicoSeleccionado);
                                    }
                                    if (inci6 != null) {
                                        if (inci6.comprobarNombre(idSeleccionada) && tecnico2.comprobarNombre(tecnicoSeleccionado))
                                            inci6.setAsignado(tecnicoSeleccionado);
                                    }
                                } else System.out.println("No se ha encontrado la incidencia o el tecnico.");
                        }
                                break;
                            case 5:
                                if (tecnico1 != null && tecnico2 != null){
                                    System.out.println("Ya estan todos los tecnicos creados.");
                                }else {
                                    System.out.print("Dime el apodo del tecnico: ");
                                    apodo = s.nextLine();
                                    System.out.print("Dime el correo del tecnico: ");
                                    correo = s.nextLine();
                                    do {
                                        System.out.print("Dime la contraseña del tecnico: ");
                                        contra = s.nextLine();
                                        System.out.print("Repite la contraseña: ");
                                        contrasena = s.nextLine();
                                    } while (contra == contrasena);
                                    if (tecnico1 != null){
                                        tecnico2 = new Tecnico(apodo,correo,contrasena);
                                    }else tecnico1 = new Tecnico(apodo,correo,contrasena);
                                }
                                break;
                            case 6:
                                if (tecnico1 == null && tecnico2 == null){
                                    System.out.println("No existe ningun tecnico ahora mismo.");
                                }else{
                                    System.out.println("¿Qué tecnico desea eliminar?");
                                    System.out.println("1." + ((tecnico1 != null)? tecnico1.getApodo() : "No hay ningun primer tecnico"));
                                    Utils.espacio();
                                    System.out.println("2." +((tecnico2 != null)? tecnico2.getApodo() : "No hay ningun segundo tecnico"));
                                    System.out.print("Elija el apodo del tecnico que desea eliminar: ");
                                    apodo = s.nextLine();
                                    if (tecnico1 != null && apodo .equals(tecnico1.getApodo())){
                                        tecnico1 = null;
                                        System.out.println("Se ha eliminado correctamente.");
                                    }else if (tecnico2 != null && apodo .equals(tecnico2.getApodo())){
                                        tecnico2 = null;
                                        System.out.println("Se ha eliminado correctamente.");
                                    }else System.out.println("No se ha encontrado ningun tecnico con ese apodo.");
                                }
                                break;
                            case 7:
                                cerrarAdmin = true;
                                break;
                            case 8:
                                finApliacion = true;
                                break;
                        }
                    }while (cerrarAdmin == false & finApliacion == false);
                    break;

            }
        }while (finApliacion == false);
        System.out.println("Fin del programa.");
        Utils.cargando();
    }
}
