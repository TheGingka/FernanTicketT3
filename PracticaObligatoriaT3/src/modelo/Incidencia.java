package modelo;

import java.time.LocalDate;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class Incidencia {
    //Atributos
    private String id;
    private String creador;
    private String asignado;
    private String comentarioUsuario;
    private String comentarioTecnico;
    private String prioridad;
    private LocalDate fechaCreacion;
    private LocalDate fechaResolucion;
    private boolean estado;
    LocalDate hoy = LocalDate.now();
    //Constructor

    public Incidencia(String id,String creador, String comentarioUsuario, String prioridad, LocalDate fechaCreacion) {
        this.id = id;
        this.creador = creador;
        this.asignado = null;
        this.comentarioUsuario = comentarioUsuario;
        this.prioridad = prioridad;
        this.fechaCreacion = fechaCreacion;
        comentarioTecnico = null;
        fechaResolucion = null;
        estado = false;
    }

    //Getter y Setter

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

    public String getAsignado() {
        return asignado;
    }

    public void setAsignado(String asignado) {
        this.asignado = asignado;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean comprobarNombre (String id){
        if (id .equals(getId()))return true;
        else return false;
    }

    public boolean comprobarAsignado (String asignado){
        if (asignado .equals(getAsignado()))return true;
        else return false;
    }

    @Override
    public String toString() {
        if (estado){
            return "Incidencia con ID: " + id +"\n" +
                    "Abierta por: " + creador + '\n' +
                    "Solucionado por el tecnico: " + asignado + '\n' +
                    "Comentario del usuario: " + comentarioUsuario + '\n' +
                    "Comentario del tecnico: " + comentarioTecnico + '\n' +
                    "Prioridad: " + prioridad + '\n' +
                    "Fecha de creacion: " + fechaCreacion + '\n' +
                    "Fecha de resolucion: " + fechaResolucion + '\n' +
                    "  RESUELTA";
        }else
        return  "Incidencia con ID: " + id +"\n" +
                "Abierta por: " + creador + '\n' +
                "Han pasado "+ String.valueOf(DAYS.between(fechaCreacion, hoy))+ "\n" +
                "Asignado al tecnico: " + asignado + '\n' +
                "Comentario: " + comentarioUsuario + '\n' +
                "Prioridad: " + prioridad + '\n' +
                "Fecha de creacion: " + fechaCreacion + '\n' +
                "  NO RESUELTA";
    }
}
