package models;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Incidencia implements Comparable<Incidencia> {

    private int id;
    private String descripcion;
    private String solucion;
    private int prioridad;
    private boolean estaResuelta;
    private Date fechaInicio;
    private Date fechaFin;
    private int idUsuario;

    // CONSTRUCTOR

    public Incidencia(int id, String descripcion, int prioridad, int idUsuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estaResuelta = false;
        this.fechaInicio = new Date();
        this.idUsuario = idUsuario;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    // GS

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

    //OM

    public int diasAbierta() throws ParseException {

        Calendar calendar = Calendar.getInstance();
        Date fechaActual = calendar.getTime();
        Date fechaInicial = dateFormat.parse(String.valueOf(fechaInicio));
        return (int) ((fechaActual.getTime() - fechaInicial.getTime()) / 86400000);

    }

    public int diasEnResolverse() throws ParseException {

        Calendar calendar = Calendar.getInstance();
        Date fechaActual = calendar.getTime();
        Date fechaInicial = dateFormat.parse(String.valueOf(fechaInicio));
        Date fechaFinal = dateFormat.parse(String.valueOf(fechaFin));
        return (int) ((fechaInicial.getTime() - fechaFinal.getTime()) / 86400000);

    }

    @Override
    public String toString() {
        return "Incidencia{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", solucion='" + solucion + '\'' +
                ", prioridad=" + prioridad +
                ", estaResuelta=" + estaResuelta +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", idUsuario=" + idUsuario +
                ", dateFormat=" + dateFormat +
                '}';
    }

    @Override
    public int compareTo(Incidencia o) {
        return fechaFin.compareTo(o.fechaFin);
    }
}
