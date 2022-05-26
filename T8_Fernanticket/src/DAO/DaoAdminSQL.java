package DAO;

import models.tipo.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DaoAdminSQL implements DAOAdmin{
    @Override
    public boolean insert(Admin admin) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        open();
        String sentencia = "insert into usuario (nombre,apel,clave,email) values('" +
                admin.getNombre() + "','" +
                admin.getApel() + "','" +
                admin.getClave() + "','" +
                admin.getEmail() + "');";
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
    public Admin readAdminByEmailyClave(String email, String clave) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        Admin admin = null;
        String sentencia = "select * from admin where email = ? and clave = ?";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            ps.setString(1,email);
            ps.setString(2,clave);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    admin = new Admin(
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
        return admin;
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
    public Object readAdminByEmailYClave(String email, String clave) {
        DAOManager daoManager = DAOManager.getSingletonInstance();
        Admin admin = null;
        String sentencia = "select * from admin where email = ? and clave = ?";
        open();
        try {
            PreparedStatement ps = daoManager.getConn().prepareStatement(sentencia);
            ps.setString(1,email);
            ps.setString(2,clave);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    admin = new Admin(
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
        return admin;
    }
}
