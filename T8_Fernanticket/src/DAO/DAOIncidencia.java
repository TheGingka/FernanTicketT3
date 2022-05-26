package DAO;

import models.Incidencia;
import models.IncidenciaDataClass;
import models.tipo.Tecnico;
import models.tipo.Usuario;

import java.util.ArrayList;
import java.util.Collection;

public interface DAOIncidencia {
    boolean insert(Incidencia incidencia);
    ArrayList<Incidencia> listaDeIncidenciasDeUsuario(int idUsuario);

    ArrayList<Incidencia> listaDeIncidenciasDeUsuarioCerradas(int id);

    ArrayList<Incidencia> recuperarIncidencias(int idUsuario);

    ArrayList<Incidencia> enProceso(Usuario user);

    ArrayList<Incidencia> imprimirIncidenciasAbiertas();

    ArrayList<Incidencia> imprimirIncidenciasCerradas();

    ArrayList<Incidencia> sinAsignar();



    boolean asignarIncidencia(IncidenciaDataClass i, Tecnico t);


    ArrayList<Incidencia> tecnicoAsignado(Tecnico tech);

    ArrayList<Incidencia> tecnicoCerrado(Tecnico tech);


}
