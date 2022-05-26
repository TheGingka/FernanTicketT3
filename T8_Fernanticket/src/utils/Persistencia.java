package utils;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

public class Persistencia {
    // LOG

    public static void inicioSesion(String nombre){     //Guardar Inicio de sesion

        String mensajeLog = "Inicio de sesion;";
        mensajeLog += nombre + ";";
        mensajeLog += String.valueOf(LocalDate.now()) + ";";
        mensajeLog += String.valueOf(LocalTime.now()) + "";
        mensajeLog += "";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./src/data/Log/log" , true));
            bw.write("\n" + mensajeLog);
            bw.close();

        } catch (IOException ioe) {
            System.out.println("No se ha podido escribir en el fichero.");
        }

    }

    public static void cerrarSesion (String nombre){     //Guardar Inicio de sesion

        String mensajeLog = "Cierre de sesion;";
        mensajeLog += nombre + ";";
        mensajeLog += String.valueOf(LocalDate.now()) + ";";
        mensajeLog += String.valueOf(LocalTime.now()) + " ";
        mensajeLog += "";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./src/data/Log/log.txt" , true));
            System.out.println("hola");
            bw.write("\n" + mensajeLog);
            bw.close();

        } catch (IOException ioe) {
            System.out.println("No se ha podido escribir en el fichero.");
        }

    }

    public static void nuevaIncidencia(String nombre ) {
        String mensajeLog = "Nueva incidencia; ";
        mensajeLog += nombre + ";  ";
        mensajeLog += String.valueOf(LocalDate.now()) + ";";
        mensajeLog += String.valueOf(LocalTime.now()) + " ";
        mensajeLog += "";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./src/data/Log/log" , true));
            bw.write("\n" + mensajeLog);
            bw.close();

        } catch (IOException ioe) {
            System.out.println("No se ha podido escribir en el fichero.");
        }
    }

    public static void cerrarIncidencia(String nombre){     //Guardar Inicio de sesion

        String mensajeLog = "Incidencia cerrada;  ";
        mensajeLog += nombre + ";  ";
        mensajeLog += String.valueOf(LocalDate.now()) + "; ";
        mensajeLog += String.valueOf(LocalTime.now()) + "  ";
        mensajeLog += "";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./src/data/Log/log" , true));
            bw.write("\n" + mensajeLog);
            bw.close();

        } catch (IOException ioe) {
            System.out.println("No se ha podido escribir en el fichero.");
        }

    }

    public static void asignacionIncidencia(String nombre){     //Guardar Inicio de sesion

        String mensajeLog = "Asignacion de incidencia; ";
        mensajeLog += nombre + ";  ";
        mensajeLog += String.valueOf(LocalDate.now()) + "; ";
        mensajeLog += String.valueOf(LocalTime.now()) + "  ";
        mensajeLog += "";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./src/data/Log/log" , true));
            bw.write("\n" + mensajeLog);
            bw.close();

        } catch (IOException ioe) {
            System.out.println("No se ha podido escribir en el fichero.");
        }

    }

    public static void ultimoInicio(String nombre){
        String mensajeLog = "";
        mensajeLog += String.valueOf(LocalDate.now()) + ";";
        String hora =  String.valueOf(LocalTime.now());
        mensajeLog += hora.substring(0,5)  + "  ";

        try{
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream("./src/data/Properties/properties.properties");
            properties.load(file);
            if (properties.containsKey(nombre)){
                properties.setProperty(nombre , mensajeLog);
            }else properties.put(nombre , mensajeLog);
            properties.store(new FileWriter("./src/data/Properties/properties.properties") , null);
        }catch (FileNotFoundException e){
            System.out.println("No se encontro el archivo");

        }catch (IOException e){
            System.out.println("No se puede leer el fichero");
        }
    }

    public static String ultimaVez(String nombre) {
        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(new File("./src/data/Properties/properties.properties"));
            properties.load(file);
            if (properties.containsKey(nombre)){
                return properties.getProperty(nombre);
            }else return null;
        }catch (FileNotFoundException e){
            System.out.println("No se encontro el archivo");

        }catch (IOException e){
            System.out.println("No se puede leer el fichero");
        }
        return null;
    }

    public static boolean esInvitado() {
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(new File("./src/data/Properties/propertiesConfig.txt"));
            properties.load(fis);
            String p = properties.getProperty("invitado");
            fis.close();
            if (p.equals("true")) return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
