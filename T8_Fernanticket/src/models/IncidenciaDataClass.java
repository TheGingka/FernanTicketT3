package models;

import models.tipo.Tecnico;
import models.tipo.Usuario;

import java.time.LocalDate;
import java.util.Date;

public class IncidenciaDataClass {

    private int id;
    private String descripcion;
    private String solucion;
    private int prioridad;
    private int estaResuelta;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
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

    public int isEstaResuelta() {
        return estaResuelta;
    }

    public void setEstaResuelta(int estaResuelta) {
        this.estaResuelta = estaResuelta;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
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
        return  "\n" + "________________ INCIDENCIA: "+ id +" _________________" + "\n" +
                "Incidencia con ID: " + id + "\n" +
                "Tu comentario: " + descripcion + "\n" +
                (estaResuelta == 0 ? "" : "Solución: " + solucion + "\n") +
                "Prioridad: " + prioridad + "\n" +
                (nombreTecnico == null ? "" : "Tecnico asignado: " + idTecnico + ", con nombre: " + nombreTecnico + "\n") +
                (estaResuelta == 1 ? "Esta resuelta.\n" : "No está resuelta.\n") +
                "Fecha de inicio: " + fechaInicio + "\n" +
                (fechaFin == null ? "" : "Fecha de resolución: " + fechaFin + "\n") +
                "Días desde su creación: " + dias + "\n" +
                "________________________________________________"+ "\n";
    }

    public String toStringTech() {
        return  "\n" + "________________ INCIDENCIA: "+ id +" _________________" + "\n" +
                "Usuario: " + nombreUsuario + "\n" +
                "Comentario del usuario: " + descripcion + "\n" +
                (estaResuelta == 0 ? "" : "Solución: " + solucion + "\n") +
                "Prioridad: " + prioridad + "\n" +
                (estaResuelta == 1 ? "Está resuelta.\n" : "No está resuelta.\n") +
                "Fecha de inicio: " + fechaInicio + "\n" +
                (fechaFin == null ? "" : "Fecha de resolución: " + fechaFin + "\n") +
                "ID del USUARIO: " + idUsuario + "\n" +
                "Días desde su creación: " + dias + "\n" +
                "________________________________________________"+ "\n";
    }

    public String toStringAdmin() {
        return   "\n" + "________________ INCIDENCIA: "+ id +" _________________" + "\n" +
                "Usuario: " + nombreUsuario + "\n" +
                "Comentario del usuario: " + descripcion + "\n" +
                (estaResuelta == 0 ? "" : "Solución: " + solucion + "\n") +
                "Prioridad: " + prioridad + "\n" +
                (estaResuelta == 1 ? "Esta resuelta.\n" : "No está resuelta.\n") +
                "Fecha de inicio: " + fechaInicio + "\n" +
                (fechaFin == null ? "" : "Fecha de resolución: " + fechaFin + "\n") +
                "ID del Usuario: " + idUsuario + "\n" +
                (nombreTecnico == null ? "" : "Tecnico asignado: " + idTecnico + ", con nombre: " + nombreTecnico + "\n") +
                "Días desde su creación: " + dias + "\n" +
                "________________________________________________"+ "\n";
    }


}
