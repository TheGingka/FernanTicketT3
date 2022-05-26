package DAO;

import models.tipo.Tecnico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoTecnicoSQL implements DAOTecnico{

    @Override
    public boolean insert(Tecnico tecnico) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        open();
        String sentencia = "insert into tecnico (nombre,apel,clave,email) values('" +
                tecnico.getNombre() + "','" +
                tecnico.getApel() + "','" +
                tecnico.getClave() + "','" +
                tecnico.getEmail() + "');";
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
    public Object readUsuarioByEmailYClave(String email, String clave) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        Tecnico tecni = null;
        String sentencia = "select * from tecnico where email = ? and clave = ?";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            ps.setString(1,email);
            ps.setString(2,clave);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    tecni = new Tecnico(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apel"),
                            clave,
                            email
                    );
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return tecni;
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
    public ArrayList<Tecnico> tecnicosTotales() {
        ArrayList<Tecnico> tecnicosTotales = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from tecnico";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    tecnicosTotales.add(new Tecnico(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apel"),
                            rs.getString("clave"),
                            rs.getString("email")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return tecnicosTotales;
    }

    public boolean comprobarTecnicoPorEmail(String email) {
        return true;
    }

    @Override
    public boolean update(String clavenueva, Tecnico tech) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "UPDATE tecnico SET clave = '" + clavenueva+ "'" + " where id = " + tech.getId() + ";";
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

    public int readTecnicoCantidad() {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        int contador = 0;
        String sentencia = "select * from tecnico";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    contador++;
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return contador;
    }

    public ArrayList<Tecnico> readTecnicoIncidenciasAbiertas() {
        ArrayList<Tecnico> tecnicosTotales = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select t.* from tecnico t, incidencia i where i.id ";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    tecnicosTotales.add(new Tecnico(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apel"),
                            rs.getString("clave"),
                            rs.getString("email")
                    ));
                }
            }
            close();
        } catch (Exception e){
            e.printStackTrace();
            close();
        }
        return tecnicosTotales;
    }
}
