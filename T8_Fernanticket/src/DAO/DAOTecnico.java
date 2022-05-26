package DAO;

import models.tipo.Tecnico;

import java.util.ArrayList;

public interface DAOTecnico {

    boolean insert(Tecnico tecnico);

    Object readUsuarioByEmailYClave(String email, String clave);

    ArrayList<Tecnico> tecnicosTotales();

    boolean update(String clavenueva, Tecnico tech);
}
