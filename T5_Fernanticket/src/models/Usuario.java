package models;

import java.util.ArrayList;

public class Usuario {

    private int id;
    private String nombre;
    private String apel;
    private String clave;
    private String email;
    private ArrayList<Incidencia> incidencias;

    public Usuario(int id, String nombre, String apel, String clave, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apel = apel;
        this.clave = clave;
        this.email = email;
        this.incidencias = new ArrayList<>();
    }

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

    //OM

    public void insertaIncidencia(Incidencia incidencia){
       incidencias.add(incidencia);
    }

    public boolean deleteIncidencia (int idIncidencia){

        for (Incidencia i:
             incidencias) {
            if (i.getId() == idIncidencia)
                incidencias.remove(i);
                return true;
        }
        return false;
    }

    public Incidencia buscaIncidenciabyId(int id){

        if (incidencias != null){
            for (Incidencia i:
                    incidencias) {
                if (i.getId() == id) return i;
            }
        }
        return null;
    }

    public ArrayList<Incidencia> buscaIncidenciabyTerm(String termino){

        ArrayList<Incidencia> incidenciasTerm = new ArrayList<>();

        if (incidencias != null){
            for (Incidencia i:
                    incidencias) {
                if (i.getDescripcion().contains(termino))
                    incidenciasTerm.add(i);
            }
        }
        return incidenciasTerm;
    }


    public int incidenciasAbiertas(){

        int contador = 0;

            for (Incidencia i:
                    incidencias) {
                if (i != null && !i.isEstaResuelta()) contador++;
            }

        return contador;
    }

    public float prioridadMediaUsuario(){

        float total = 0, suma = 0;

        for (Incidencia i:
                incidencias) {

                total++;
                suma += i.getPrioridad();

        }

        return suma / total;

    }

    public boolean login(String email, String clave) {

        return getEmail().contains(email) && getClave().contains(clave);

    }

    public boolean compruebaClave(String claveantigua) {

        return claveantigua.equals(getClave());
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
}
