package models;

import java.time.LocalDate;
import java.util.Date;

public class Incidencia {
    private int id;
    private String descripcion;
    private String solucion;
    private int prioridad;
    private int estaResuelta;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int idUsuario;
    private int idTecnico;

    public Incidencia(String descripcion, int prioridad, LocalDate fechaInicio, int idUsuario) {
        this.descripcion = descripcion;
        this.solucion = null;
        this.prioridad = prioridad;
        this.estaResuelta = 0;
        this.fechaInicio = fechaInicio;
        this.fechaFin = null;
        this.idUsuario = idUsuario;
        this.idTecnico = -1;
    }

    public Incidencia(int id, String descripcion, String solucion, int prioridad, int estaresuelta, LocalDate fechaModificada, int idUsuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.solucion = solucion;
        this.prioridad = prioridad;
        this.estaResuelta = estaresuelta;
        this.fechaInicio = fechaModificada;
        this.fechaFin = null;
        this.idUsuario = idUsuario;
        this.idTecnico = -1;
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

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }


}
