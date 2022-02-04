package model;

import java.time.LocalDate;

public class Incidencia {
    private String id;
    private String creador;
    private String comentarioUsuario;
    private String comentarioTecnico;
    private String prioridad;
    private LocalDate fechaCreacion;
    private LocalDate fechaResolucion;
    private boolean estado;

    public Incidencia(String id, String creador, String comentarioUsuario, String prioridad, LocalDate fechaCreacion) {
        this.id = id;
        this.creador = creador;
        this.comentarioUsuario = comentarioUsuario;
        this.comentarioTecnico = null;
        this.prioridad = prioridad;
        this.fechaCreacion = fechaCreacion;
        this.fechaResolucion = null;
        this.estado = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getComentarioUsuario() {
        return comentarioUsuario;
    }

    public void setComentarioUsuario(String comentarioUsuario) {
        this.comentarioUsuario = comentarioUsuario;
    }

    public String getComentarioTecnico() {
        return comentarioTecnico;
    }

    public void setComentarioTecnico(String comentarioTecnico) {
        this.comentarioTecnico = comentarioTecnico;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(LocalDate fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        if (estado){
            return "Incidencia con ID: " + id +"\n" +
                    "Abierta por: " + creador + '\n' +
                    "Comentario del usuario: " + comentarioUsuario + '\n' +
                    "Comentario del tecnico: " + comentarioTecnico + '\n' +
                    "Prioridad: " + prioridad + '\n' +
                    "Fecha de creacion: " + fechaCreacion + '\n' +
                    "Fecha de resolucion: " + fechaResolucion + '\n' +
                    "  RESUELTA";
        }else
            return  "Incidencia con ID: " + id +"\n" +
                    "Abierta por: " + creador + '\n' +
                    "Comentario: " + comentarioUsuario + '\n' +
                    "Prioridad: " + prioridad + '\n' +
                    "Fecha de creacion: " + fechaCreacion + '\n' +
                    "  NO RESUELTA";
    }
}
