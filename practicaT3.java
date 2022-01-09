import java.util.Scanner;

import modelo.Administrador;
import modelo.Incidencia;
import modelo.Tecnico;
import modelo.Usuario;
import utils.Utils;
import utils.Menu;
public class practicaT3 {
    /*
    FECHA DE ENTREGA 10 DE ENERO

     IMPORTANTE: Cuando consulto datos de incidencias, me tienen que aparecer todos los datos de las
     mismas y muy importante, si no está cerrada, deben aparecer los días que han pasado desde que se abrió.
     Ejemplo de vista de incidencia:

      Cosas que podemos usar como extra para más visual
      System.out.print("Apagando programa");
          for (int x = 0; x <4; x++){               ----> Podemos usarlo para apagar o al loguearse
              System.out.print(". ");
              Thread.sleep(500);
           }

      letra de asci art Larry 3d
     */

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
        Usuario usuario1 = new Usuario("pepe","pepe","pepe");
        Usuario usuario2 = null;
        Tecnico tecnico1 = null;
        Tecnico tecnico2 = new Tecnico("Carlos","nene","pepe");
        Administrador admin = new Administrador("Manuel","pepe","pepe");

        //Definimos las incidencias
        Incidencia inci1 = null;
        Incidencia inci2 = null;
        Incidencia inci3 = null;
        Incidencia inci4 = null;
        Incidencia inci5 = null;
        Incidencia inci6 = null;

        do {
            //reseteamos variables
            usuarioNormal = false;
            usuarioAdmin = false;
            usuarioTecnico = false;
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
                                System.out.println("Dime el apodo del usuario");

                                //Caso de iniciar sesión de usuario1
                                if (usuario1 != null){ //Comprobar que el usuario existe
                                    if (s.nextLine().equals(usuario1.getApodo())){
                                        System.out.println("Dime la contraseña del usuario");
                                        if (s.nextLine().equals(usuario1.getContrasena())){
                                            usuarioNormal = true; //Para que entre en el menu de usuario
                                            usuarioNormal1 = true; //Para que sepamos que nos referimos al primer usuario
                                        }
                                    }
                                }else{

                                    //Caso de iniciar sesión de usuario2
                                    if (usuario2 != null){  //Comprobar que el usuario existe
                                        if (s.nextLine().equals(usuario2.getApodo())){
                                            System.out.println("Dime la contraseña del usuario");
                                            if (s.nextLine().equals(usuario2.getContrasena())){
                                                usuarioNormal = true; //Para que entre en el menu de usuario
                                                usuarioNormal1 = false; //Para que sepamos que nos referimos al segundo usuario
                                            }
                                        } 
                                    }
                                }
                                break;
                            case 2:
                                //Caso de iniciar sesión de tecnico1
                                if (tecnico1 != null || tecnico2 != null)System.out.println("Dime el apodo del tecnico");
                                if (tecnico1 != null){  //Comprobar que el usuario existe
                                    if (s.nextLine().equals(tecnico1.getApodo())){
                                        System.out.println("Dime la contraseña del usuario");
                                        if (s.nextLine().equals(tecnico1.getContrasena())){
                                            usuarioTecnico = true; //Para que entre en el menu de tecnico
                                            usuarioTecnico1 = true; //Para que sepamos que nos referimos al primer tecnico
                                        }
                                    }
                                }else{
                                    //Caso de iniciar sesión de tecnico2
                                    if (tecnico2 != null){  //Comprobar que el usuario existe
                                        if (s.nextLine().equals(tecnico2.getApodo())){
                                            System.out.println("Dime la contraseña del usuario");
                                            if (s.nextLine().equals(tecnico2.getContrasena())){
                                                usuarioTecnico = true; //Para que entre en el menu de tecnico
                                                usuarioTecnico1 = false; //Para que sepamos que nos referimos al segundo tecnico
                                            }
                                        }
                                    }
                                }
                                break;

                            case 3:
                                //Caso de iniciar sesión de admin
                                System.out.println("Dime el apodo del admin");
                                if (admin != null){  //Comprobar que el usuario existe
                                    if (s.nextLine().equals(admin.getApodo())){
                                        System.out.println("Dime la contraseña del usuario");
                                        if (s.nextLine().equals(admin.getContrasena())){
                                            usuarioAdmin = true; //Para que entre en el menu de admin
                                        }
                                    }
                                }
                                break;
                        }


                    }while(!usuarioNormal & !usuarioAdmin & !usuarioTecnico);
                    break;


                case 2:  //Los que se registran solo solo usuarios
                    if (usuario1 != null && usuario2 != null){
                        System.out.println("Ya estan todos los usuarios creados");
                    }else {
                        System.out.println("Dime el apodo del usuario");
                        apodo = s.nextLine();
                        System.out.println("Dime el correo del usuario");
                        correo = s.nextLine();
                        do {
                            System.out.println("Dime la contraseña del usuario");
                            contra = s.nextLine();
                            System.out.println("Repite la contraseña");
                            contrasena = s.nextLine();
                        } while (contra == contrasena);
                        if (usuario1 != null){
                            usuario2 = new Usuario(apodo,correo,contrasena);
                        }else usuario1 = new Usuario(apodo,correo,contrasena);
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
                        System.out.println("Bienvenido " + ((usuarioNormal1)? usuario1.getApodo() : usuario2.getApodo()) + ", tiene usted perfil de usuario normal");
                        Menu.menuUsuario();
                        opcTipoUsuario = Integer.parseInt(s.nextLine());

                        switch (opcTipoUsuario){
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                if (usuarioNormal1) {
                                    System.out.println(usuario1);
                                }else System.out.println(usuario2);
                                break;
                            case 5:
                                if (usuarioNormal1){
                                    do {
                                        System.out.println("Dime la contraseña del usuario");
                                        usuario1.setContrasena(s.nextLine());
                                        System.out.println("Repite la contraseña");
                                        contrasena = s.nextLine();
                                    }while(!usuario1.comprobarContraseña(contrasena));
                                }else {
                                    do {
                                        System.out.println("Dime la contraseña del usuario");
                                        usuario2.setContrasena(s.nextLine());
                                        System.out.println("Repite la contraseña");
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
                                    System.out.println((tecnico1.getIncidencia1() == null)? tecnico1.getIncidencia1() : "No tiene ninguna primera incidencia");
                                    System.out.println((tecnico1.getIncidencia2() == null)? tecnico1.getIncidencia2() : "No tiene ninguna segunda incidencia");
                                }else{
                                    System.out.println((tecnico2.getIncidencia1() == null)? tecnico2.getIncidencia1() : "No tiene ninguna primera incidencia");
                                    System.out.println((tecnico2.getIncidencia2() == null)? tecnico2.getIncidencia2() : "No tiene ninguna segunda incidencia");
                                }
                                break;
                            case 2:

                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                if (usuarioTecnico1){
                                    do {
                                        System.out.println("Dime la contraseña del usuario");
                                        tecnico1.setContrasena(s.nextLine());
                                        System.out.println("Repite la contraseña");
                                        contrasena = s.nextLine();
                                    }while(!tecnico1.comprobarContraseña(contrasena));
                                }else {
                                    do {
                                        System.out.println("Dime la contraseña del usuario");
                                        usuario2.setContrasena(s.nextLine());
                                        System.out.println("Repite la contraseña");
                                        contrasena = s.nextLine();
                                    } while (!usuario2.comprobarContraseña(contrasena));
                                }
                                break;
                            case 6:
                                break;
                        }
                    }while (cerrarTecnico == false);

                    break;
                case 3: //Perfil administrador:
                    do {
                        System.out.println("Bienvenido " + admin.getApodo() + ", tiene usted perfil de administracion");
                        Menu.menuAdmin();
                        opcTipoUsuario = Integer.parseInt(s.nextLine());

                        switch (opcTipoUsuario){
                            case 1:
                                System.out.println("1." +((inci1 != null)? inci1 : "No hay ninguna incidencia"));
                                Utils.espacio();
                                System.out.println("2." +((inci2 != null)? inci2 : "No hay ninguna incidencia"));
                                Utils.espacio();
                                System.out.println("3." +((inci3 != null)? inci3 : "No hay ninguna incidencia"));
                                Utils.espacio();
                                System.out.println("4." +((inci4 != null)? inci4 : "No hay ninguna incidencia"));
                                Utils.espacio();
                                System.out.println("5." +((inci5 != null)? inci5 : "No hay ninguna incidencia"));
                                Utils.espacio();
                                System.out.println("6." +((inci6 != null)? inci6 : "No hay ninguna incidencia"));
                                break;
                            case 2:
                                System.out.println((usuario1 != null)? usuario1 : "No hay ningun primer usuario");
                                Utils.espacio();
                                System.out.println((usuario2 != null)? usuario2 : "No hay ningun segundo usuario");
                                break;
                            case 3:
                                System.out.println((tecnico1 != null)? tecnico1 : "No hay ningun primer tecnico");
                                Utils.espacio();
                                System.out.println((tecnico2 != null)? tecnico2 : "No hay ningun segundo tecnico");
                                break;
                            case 4:
                                break;
                            case 5:
                                if (tecnico1 != null && tecnico2 != null){
                                    System.out.println("Ya estan todos los tecnicos creados");
                                }else {
                                    System.out.println("Dime el apodo del tecnico");
                                    apodo = s.nextLine();
                                    System.out.println("Dime el correo del tecnico");
                                    correo = s.nextLine();
                                    do {
                                        System.out.println("Dime la contraseña del tecnico");
                                        contra = s.nextLine();
                                        System.out.println("Repite la contraseña");
                                        contrasena = s.nextLine();
                                    } while (contra == contrasena);
                                    if (tecnico1 != null){
                                        tecnico2 = new Tecnico(apodo,correo,contrasena);
                                    }else tecnico1 = new Tecnico(apodo,correo,contrasena);
                                }
                                break;
                            case 6:
                                if (tecnico1 == null && tecnico2 == null){
                                    System.out.println("No existe ningun tecnico ahora mismo");
                                }else{
                                    System.out.println("¿Qué tecnico desea eliminar?");
                                    System.out.println("1." + ((tecnico1 != null)? tecnico1.getApodo() : "No hay ningun primer tecnico"));
                                    Utils.espacio();
                                    System.out.println("2." +((tecnico2 != null)? tecnico2.getApodo() : "No hay ningun segundo tecnico"));
                                    System.out.println("Eliga el numero que desea eliminar");
                                    opcElegida = Integer.parseInt(s.nextLine());
                                    switch (opcElegida){
                                        case 1:
                                            tecnico1 = null;
                                            System.out.println("Tecnico eliminado correctamente");
                                            break;
                                        case 2:
                                            tecnico2 = null;
                                            System.out.println("Tecnico eliminado correctamente");
                                            break;
                                        default:
                                            System.out.println("Solo puede seleccionar entre el uno y el dos");
                                            break;
                                    }
                                }
                                break;
                            case 7:
                                cerrarAdmin = true;
                                break;
                        }
                    }while (cerrarAdmin == false);
                    break;
            }
        }while (finApliacion == false);

    }
}
