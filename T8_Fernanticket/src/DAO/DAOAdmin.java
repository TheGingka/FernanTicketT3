package DAO;

import models.tipo.Admin;

public interface DAOAdmin {
    public boolean insert(Admin admin);
    public Admin readAdminByEmailyClave(String email, String clave);

    Object readAdminByEmailYClave(String email, String clave);
}
