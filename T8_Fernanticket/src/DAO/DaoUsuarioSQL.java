package DAO;

import models.tipo.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DaoUsuarioSQL implements DAOUsuario{

    @Override
    public boolean insert(Usuario usuario) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        open();
        String sentencia = "insert into usuario (nombre,apel,clave,email) values('" +
                usuario.getNombre() + "','" +
                usuario.getApel() + "','" +
                usuario.getClave() + "','" +
                usuario.getEmail() + "');";
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
    public Usuario readUsuarioByEmailYClave(String email, String clave) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        Usuario usuario = null;
        String sentencia = "select * from usuario where email = ? and clave = ?";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            ps.setString(1,email);
            ps.setString(2,clave);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    usuario = new Usuario(
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
        return usuario;
    }

    @Override
    public boolean actualizar(String clave, int id) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "UPDATE usuario SET clave = '" + clave+ "'" + " where id = " + id + ";";
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

    public int readUsuarioCantidad() {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        int contador = 0;
        String sentencia = "select * from usuario";
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
    public Usuario readUsuarioPorId(int idUsuario) {
    DAOManager daoManager = DAOManager.getSingletonInstance();
    Usuario usuario = null;
    String sentencia = "select * from usuario where id = ?";
    open();
    try {
        PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
        ps.setString(1, String.valueOf(idUsuario));
        try (ResultSet rs = ps.executeQuery()){
            if (rs.next()){
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apel"),
                        rs.getString("clave"),
                        rs.getString("email")
                );
            }
        }
        close();
    } catch (Exception e){
        e.printStackTrace();
        close();
    }
    return usuario;
    }

    @Override
    public ArrayList<Usuario> usuarioTotales() {
        ArrayList<Usuario> usuariosTotales = new ArrayList<>();
        DAOManager daoManager = DAOManager.getSingletonInstance();
        String sentencia = "select * from usuario";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    usuariosTotales.add(new Usuario(
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
        return usuariosTotales;
    }
}
