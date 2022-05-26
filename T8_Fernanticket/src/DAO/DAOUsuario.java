package DAO;

import models.tipo.Usuario;

import java.util.ArrayList;

public interface DAOUsuario {



    public boolean insert(Usuario usuario);

    public Usuario readUsuarioByEmailYClave(String email, String clave);

    boolean actualizar(String clave, int id);

    Usuario readUsuarioPorId(int idUsuario);

    ArrayList<Usuario> usuarioTotales();
}
