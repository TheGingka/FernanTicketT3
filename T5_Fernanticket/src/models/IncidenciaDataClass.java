package models;

import java.text.ParseException;
import java.util.Date;

public class IncidenciaDataClass {

    private int id;
    private String descripcion;
    private String solucion;
    private int prioridad;
    private boolean estaResuelta;
    private Date fechaInicio;
    private Date fechaFin;
    private int idUsuario;
    private int dias;
    private String nombreUsuario;
    private String emailUsuario;
    private int idTecnico;
    private String nombreTecnico;

    public IncidenciaDataClass(Incidencia i, Usuario u, Tecnico t) {
        this.id = i.getId();
        this.descripcion = i.getDescripcion();
        this.solucion = i.getSolucion();
        this.prioridad = i.getPrioridad();
        this.estaResuelta = i.isEstaResuelta();
        this.fechaInicio = i.getFechaInicio();
        this.fechaFin = i.getFechaFin();
        this.idUsuario = u.getId();
        this.dias = dias;
        this.nombreUsuario = u.getNombre();
        this.emailUsuario = u.getEmail();
        this.idTecnico = t.getId();
        this.nombreTecnico = t.getNombre();
    }

    public IncidenciaDataClass(Incidencia i, Usuario u) {
        this.id = i.getId();
        this.descripcion = i.getDescripcion();
        this.solucion = i.getSolucion();
        this.prioridad = i.getPrioridad();
        this.estaResuelta = i.isEstaResuelta();
        this.fechaInicio = i.getFechaInicio();
        this.fechaFin = i.getFechaFin();
        this.idUsuario = u.getId();
        this.dias = dias;
        this.nombreUsuario = u.getNombre();
        this.emailUsuario = u.getEmail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isEstaResuelta() {
        return estaResuelta;
    }

    public void setEstaResuelta(boolean estaResuelta) {
        this.estaResuelta = estaResuelta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }


    @Override
    public String toString() {
        return  "_________________________________" + "\n" +
                "Incidencia con ID: " + id + "\n" +
                "Comentario del usuario: " + descripcion + "\n" +
                (!estaResuelta ? "" : "Solución: \n" + solucion) +
                "Prioridad: " + prioridad + "\n" +
                        (estaResuelta ? "Esta resuelta.\n" : "No está resuelta.\n") +
                "Fecha de inicio: " + fechaInicio + "\n" +
                        (fechaFin == null ? "" : "Fecha de resolución: \n" + fechaFin) +
                "ID del USUARIO: " + idUsuario + "\n" +
                "Días desde su creación: " + dias + "\n" +
                "Nombre del usuario: " + nombreUsuario + "\n" +
                "_________________________________"+ "\n";
    }


}
