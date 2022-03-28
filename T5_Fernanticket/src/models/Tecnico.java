package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Tecnico {

    private int id;
    private String nombre;
    private String apel;
    private String clave;
    private String email;
    private ArrayList<Incidencia> incidencias;

    // CONSTRUCTOR


    public Tecnico(int id, String nombre, String apel, String clave, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apel = apel;
        this.clave = clave;
        this.email = email;
        this.incidencias = new ArrayList<>();
    }

    // GS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApel() {
        return apel;
    }

    public void setApel(String apel) {
        this.apel = apel;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(ArrayList<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }


    // Otros Metodos

    public Incidencia buscaIncidenciabyId(int idIncidencia){
        return null;
    }

    public ArrayList<Incidencia> buscaIncidenciasbyTerm(String termino){

        ArrayList<Incidencia> incidenciasTerm = new ArrayList<>();

        for (Incidencia i:
             incidencias) {
            if (i.getDescripcion().contains(termino))
                incidenciasTerm.add(i);
        }

        return incidenciasTerm;
    }

    public int incidenciasCerradas(){

        int contador = 0;

            for (Incidencia i:
                    incidencias) {
                if (i != null && i.isEstaResuelta()) contador++;
            }

        return contador;
    }

    public int incidenciasAbiertas(){

        int contador = 0;

            for (Incidencia i:
                    incidencias) {
                if (i != null && !i.isEstaResuelta()) contador++;
            }

        return contador;
    }

    public boolean login(String email, String clave) {

        return getEmail().contains(email) && getClave().contains(clave);

    }

    public void asignaIncidencia(Incidencia incidencia){

        incidencias.add(incidencia);

    }

    public boolean cierraIncidencia(int idIncidencia, String solucion){

        Calendar calendar = Calendar.getInstance();

        for (Incidencia i:
             incidencias) {
            if (i.getId() == idIncidencia)
                i.setSolucion(solucion);
                i.setEstaResuelta(true);
                i.setFechaFin(calendar.getTime());
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return  "__________________________" + "\n" +
                "Nombre: " + nombre + "\n" +
                "Apellido: " + apel + "\n" +
                "Clave: " + clave + "\n" +
                "Email: " + email + "\n" +
                "__________________________"+ "\n";
    }

    public boolean compruebaClave(String claveantigua) {

        return claveantigua.equals(getClave());

    }
}
