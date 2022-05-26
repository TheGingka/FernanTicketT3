package DAO;

import models.Incidencia;
import models.IncidenciaDataClass;
import models.tipo.Tecnico;
import models.tipo.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class DAOIncidenciaSql implements DAOIncidencia{
    @Override
    public boolean insert(Incidencia incidencia) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        open();
        String sentencia = "insert into incidencia (descripcion,solucion,prioridad,estaresuelta,fechainicio,idusuario,idtecnico) values('" +
                incidencia.getDescripcion() + "','" +
                incidencia.getSolucion() + "','" +
                incidencia.getPrioridad() + "','" +
                incidencia.isEstaResuelta() + "','" +
                incidencia.getFechaInicio() + "','" +
                incidencia.getIdUsuario() + "','" +
                incidencia.getIdTecnico() + "');";
        try (Statement stmt = daoManager.getConn().createStatement()) {
            stmt.executeUpdate(sentencia);
            close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            close();
            return false;
        }
    }




    @Override
    public ArrayList<Incidencia> listaDeIncidenciasDeUsuario(int idUsuario) {
        ArrayList<Incidencia> listaIncidenciasPendientes = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from incidencia where idUsuario = ? and estaResuelta = 0";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            ps.setInt(1,idUsuario);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Date fechaOriginal = rs.getDate("fechainicio");
                    LocalDate fechaModificada = new java.sql.Date(fechaOriginal.getTime()).toLocalDate();
                    listaIncidenciasPendientes.add(new Incidencia(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getString("solucion"),
                            rs.getInt("prioridad"),
                            rs.getInt("estaresuelta"),
                            fechaModificada,
                            rs.getInt("idusuario")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return listaIncidenciasPendientes;
    }
    private void open() {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        try {
            daoManager.open();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void close(){
        DAOManager daoManager = DAOManager.getSingletonInstance();
        try {
            daoManager.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Incidencia> listaDeIncidenciasDeUsuarioCerradas(int id) {
        ArrayList<Incidencia> listaIncidenciasPendientes = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from incidencia where idUsuario = ? and estaResuelta = 1";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Date fechaOriginal = rs.getDate("fechainicio");
                    LocalDate fechaModificada = new java.sql.Date(fechaOriginal.getTime()).toLocalDate();
                    listaIncidenciasPendientes.add(new Incidencia(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getString("solucion"),
                            rs.getInt("prioridad"),
                            rs.getInt("estaresuelta"),
                            fechaModificada,
                            rs.getInt("idusuario")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return listaIncidenciasPendientes;
    }

    @Override
    public ArrayList<Incidencia> recuperarIncidencias(int idUsuario) {
        ArrayList<Incidencia> listaIncidenciasPendientes = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from incidencia where idUsuario = ? and idtecnico = -1";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            ps.setInt(1,idUsuario);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Date fechaOriginal = rs.getDate("fechainicio");
                    LocalDate fechaModificada = new java.sql.Date(fechaOriginal.getTime()).toLocalDate();
                    listaIncidenciasPendientes.add(new Incidencia(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getString("solucion"),
                            rs.getInt("prioridad"),
                            rs.getInt("estaresuelta"),
                            fechaModificada,
                            rs.getInt("idusuario")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return listaIncidenciasPendientes;
    }

    @Override
    public ArrayList<Incidencia> enProceso(Usuario user) {
        ArrayList<Incidencia> listaIncidenciasPendientes = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from incidencia where idUsuario = ? and idtecnico != -1";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            ps.setInt(1,user.getId());
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Date fechaOriginal = rs.getDate("fechainicio");
                    LocalDate fechaModificada = new java.sql.Date(fechaOriginal.getTime()).toLocalDate();
                    listaIncidenciasPendientes.add(new Incidencia(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getString("solucion"),
                            rs.getInt("prioridad"),
                            rs.getInt("estaresuelta"),
                            fechaModificada,
                            rs.getInt("idusuario")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return listaIncidenciasPendientes;
    }
    @Override
    public ArrayList<Incidencia> imprimirIncidenciasAbiertas() {
        ArrayList<Incidencia> imprimirIncidenciasAbiertas = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from incidencia where estaresuelta = 0 ";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Date fechaOriginal = rs.getDate("fechainicio");
                    LocalDate fechaModificada = new java.sql.Date(fechaOriginal.getTime()).toLocalDate();
                    imprimirIncidenciasAbiertas.add(new Incidencia(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getString("solucion"),
                            rs.getInt("prioridad"),
                            rs.getInt("estaresuelta"),
                            fechaModificada,
                            rs.getInt("idusuario")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return imprimirIncidenciasAbiertas;
    }
    @Override
    public ArrayList<Incidencia> imprimirIncidenciasCerradas() {
        ArrayList<Incidencia> imprimirIncidenciasAbiertas = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from incidencia where estaresuelta = 1 and idtecnico != -1";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Date fechaOriginal = rs.getDate("fechainicio");
                    LocalDate fechaModificada = new java.sql.Date(fechaOriginal.getTime()).toLocalDate();
                    imprimirIncidenciasAbiertas.add(new Incidencia(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getString("solucion"),
                            rs.getInt("prioridad"),
                            rs.getInt("estaresuelta"),
                            fechaModificada,
                            rs.getInt("idusuario")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return imprimirIncidenciasAbiertas;
    }

    public ArrayList<Incidencia> imprimirIncidenciasTermino(String termino) {
        ArrayList<Incidencia> imprimirIncidenciasAbiertas = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from incidencia where descripcion LIKE('%"+ termino+"%');";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Date fechaOriginal = rs.getDate("fechainicio");
                    LocalDate fechaModificada = new java.sql.Date(fechaOriginal.getTime()).toLocalDate();
                    imprimirIncidenciasAbiertas.add(new Incidencia(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getString("solucion"),
                            rs.getInt("prioridad"),
                            rs.getInt("estaresuelta"),
                            fechaModificada,
                            rs.getInt("idusuario")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return imprimirIncidenciasAbiertas;
    }

    @Override
    public ArrayList<Incidencia> sinAsignar() {
        ArrayList<Incidencia> imprimirIncidenciasAbiertas = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from incidencia where estaresuelta = 0 and idtecnico = -1";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Date fechaOriginal = rs.getDate("fechainicio");
                    LocalDate fechaModificada = new java.sql.Date(fechaOriginal.getTime()).toLocalDate();
                    imprimirIncidenciasAbiertas.add(new Incidencia(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getString("solucion"),
                            rs.getInt("prioridad"),
                            rs.getInt("estaresuelta"),
                            fechaModificada,
                            rs.getInt("idusuario")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return imprimirIncidenciasAbiertas;
    }

    @Override
    public boolean asignarIncidencia(IncidenciaDataClass i, Tecnico t) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "UPDATE incidencia SET idtecnico = '" + t.getId()+ "'" + " where id = " + i.getId() + ";";
        open();
        try (Statement stmt = daoManager.getConn().createStatement()){
            stmt.executeUpdate(sentencia);
            close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            close();
            return false;
        }

    }

    @Override
    public ArrayList<Incidencia> tecnicoAsignado(Tecnico tech) {
        ArrayList<Incidencia> listaIncidenciasPendientes = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from incidencia where estaresuelta = 0 and idtecnico = ?";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            ps.setInt(1,tech.getId());
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Date fechaOriginal = rs.getDate("fechainicio");
                    LocalDate fechaModificada = new java.sql.Date(fechaOriginal.getTime()).toLocalDate();
                    listaIncidenciasPendientes.add(new Incidencia(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getString("solucion"),
                            rs.getInt("prioridad"),
                            rs.getInt("estaresuelta"),
                            fechaModificada,
                            rs.getInt("idusuario")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return listaIncidenciasPendientes;
    }

    @Override
    public ArrayList<Incidencia> tecnicoCerrado(Tecnico tech) {
        ArrayList<Incidencia> listaIncidenciasPendientes = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from incidencia where estaresuelta = 1 and idtecnico = ?";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            ps.setInt(1,tech.getId());
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Date fechaOriginal = rs.getDate("fechainicio");
                    LocalDate fechaModificada = new java.sql.Date(fechaOriginal.getTime()).toLocalDate();
                    listaIncidenciasPendientes.add(new Incidencia(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getString("solucion"),
                            rs.getInt("prioridad"),
                            rs.getInt("estaresuelta"),
                            fechaModificada,
                            rs.getInt("idusuario")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return listaIncidenciasPendientes;
    }


    public boolean resolver(String solucion, int num, LocalDate fecha) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "UPDATE incidencia SET estaresuelta = '" + 1 + "', fechafin = '" + fecha  + "', solucion = '" + solucion  + "' WHERE id = " + num + ";";
        open();
        try (Statement stmt = daoManager.getConn().createStatement()){
            stmt.executeUpdate(sentencia);
            close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            close();
            return false;
        }
    }

    public int readIncidenciaMedia() {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        int contador = 0;
        String sentencia = "select prioridad from incidencia";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    contador = contador + rs.getInt("prioridad");
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return contador;
    }

    public int readIncidenciaAbiertaAsignadaNumero(Tecnico tech) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        int contador = 0;
        String sentencia = "select * from incidencia where idTecnico = " + tech.getId() + " AND estaresuelta = false;";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    contador ++;
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return contador;
    }

    public int readIncidenciaCerradaAsignadaNumero(Tecnico tech) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        int contador = 0;
        String sentencia = "select * from incidencia where idTecnico = " + tech.getId() + " AND estaresuelta = true;";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    contador ++;
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return contador;
    }

    public int readIncidenciaSinResolverseNumero() {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        int contador = 0;
        String sentencia = "select * from incidencia where estaresuelta = false AND idTecnico != -1;";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    contador ++;
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return contador;
    }


    public double readIncidenciaMediaTech(Tecnico tech) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        double contador = 0;
        double divisor = 0;
        String sentencia = "select prioridad from incidencia where idTecnico = " + tech.getId() + ";";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    contador = contador + rs.getInt("prioridad");
                    divisor++;
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return contador/divisor;
    }
}
