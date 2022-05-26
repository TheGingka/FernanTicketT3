package models.tipo;

import DAO.DAOIncidenciaSql;
import models.Incidencia;

import java.util.ArrayList;

public class Usuario {
    public static DAOIncidenciaSql daoIncidenciaSql = new DAOIncidenciaSql();
    private int id;
    private String nombre;
    private String apel;
    private String clave;
    private String email;
    private ArrayList<Incidencia> incidencias;

    public Usuario(String nombre, String apel, String clave, String email) {
        this.nombre = nombre;
        this.apel = apel;
        this.clave = clave;
        this.email = email;
        this.incidencias = new ArrayList<>();
    }


    public Usuario(int id,String nombre, String apel, String clave, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apel = apel;
        this.clave = clave;
        this.email = email;
        this.incidencias = daoIncidenciaSql.recuperarIncidencias(id);
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

    public void setIncidencias(Incidencia incidencia) {
        incidencias.add(incidencia);
    }

    @Override
    public String toString() {
        return  "___________ "+ nombre + " ___________" + "\n" +
                "Nombre: " + nombre + "\n" +
                "Apellido: " + apel + "\n" +
                "Clave: " + clave + "\n" +
                "Email: " + email + "\n" +
                "_______________________________"+ "\n";
    }
}
