package models;

import DAO.DAOIncidenciaSql;
import DAO.DaoAdminSQL;
import DAO.DaoTecnicoSQL;
import DAO.DaoUsuarioSQL;
import models.tipo.Tecnico;
import models.tipo.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class GestionAPP {
    public static DaoUsuarioSQL daoUsuarioSQL = new DaoUsuarioSQL();
    public  static DAOIncidenciaSql daoIncidenciaSql = new DAOIncidenciaSql();
    public  static DaoTecnicoSQL daoTecnicoSQL = new DaoTecnicoSQL();
    public  static DaoAdminSQL daoAdminSQL = new DaoAdminSQL();

    public boolean comprobarEmailUsuario(String email) {
        return true;
    }

    public boolean introducirUsuario(String nombre, String apellido, String clave, String email) {
        Usuario temp = new Usuario(nombre,apellido,clave,email);
        return daoUsuarioSQL.insert(temp);
    }



    public Object login(String email, String clave) {
        Object temp = null;
        if (daoUsuarioSQL.readUsuarioByEmailYClave(email,clave) != null)return daoUsuarioSQL.readUsuarioByEmailYClave(email,clave);
        if (daoTecnicoSQL.readUsuarioByEmailYClave(email,clave) != null)return daoTecnicoSQL.readUsuarioByEmailYClave(email,clave);
        if (daoAdminSQL.readAdminByEmailYClave(email,clave) != null) return daoAdminSQL.readAdminByEmailyClave(email,clave);
        return null;
    }

    public boolean insertaIncidencia(String descripcion, Usuario user, int prioridad) {
        LocalDate fecha = LocalDate.now();
        Incidencia temp = new Incidencia(descripcion,prioridad,fecha, user.getId());
        user.setIncidencias(temp);
        return daoIncidenciaSql.insert(temp);
    }

    public ArrayList<IncidenciaDataClass> incidenciasAbiertasDeUsuario(Usuario user) {
        ArrayList<IncidenciaDataClass> incidenciaDataClasses = new ArrayList<>();
        ArrayList<Incidencia> incidencias = new ArrayList<>();
        incidencias = daoIncidenciaSql.listaDeIncidenciasDeUsuario(user.getId());
        if (incidencias.size() != 0){
            for (Incidencia i:
                 incidencias) {
                IncidenciaDataClass temp = new IncidenciaDataClass(i,user);
                incidenciaDataClasses.add(temp);
            }
            return incidenciaDataClasses;
        }
        return incidenciaDataClasses;
    }

    public ArrayList<IncidenciaDataClass> incidenciasCerradasDeUsuario(Usuario user) {
        ArrayList<IncidenciaDataClass> incidenciaDataClasses = new ArrayList<>();
        ArrayList<Incidencia> incidencias = new ArrayList<>();
        incidencias = daoIncidenciaSql.listaDeIncidenciasDeUsuarioCerradas(user.getId());
        if (incidencias.size() != 0){
            for (Incidencia i:
                    incidencias) {
                IncidenciaDataClass temp = new IncidenciaDataClass(i,user);
                incidenciaDataClasses.add(temp);
            }
            return incidenciaDataClasses;
        }
        return incidenciaDataClasses;
    }

    public boolean cambiaClaveUsuario(String clavenueva, String clavenueva2, Usuario user) {
        return daoUsuarioSQL.actualizar(clavenueva, user.getId());
    }

    public int incidenciasEnProceso(Usuario user) {
        return daoIncidenciaSql.enProceso(user).size();
    }

    public ArrayList<IncidenciaDataClass> imprimirIncidenciasAbiertas() {
        ArrayList<IncidenciaDataClass> incidenciaDataClasses = new ArrayList<>();
        ArrayList<Incidencia> incidencias = new ArrayList<>();
        incidencias = daoIncidenciaSql.imprimirIncidenciasAbiertas();
        if (incidencias.size() != 0) {
            for (Incidencia i :
                    incidencias) {
                IncidenciaDataClass temp = new IncidenciaDataClass(i, daoUsuarioSQL.readUsuarioPorId(i.getIdUsuario()));
                incidenciaDataClasses.add(temp);
            }
            return incidenciaDataClasses;
        }
        return incidenciaDataClasses;

    }

    public ArrayList<IncidenciaDataClass> imprimirIncidenciasCerradas() {
        ArrayList<IncidenciaDataClass> incidenciaDataClasses = new ArrayList<>();
        ArrayList<Incidencia> incidencias = new ArrayList<>();
        incidencias = daoIncidenciaSql.imprimirIncidenciasCerradas();
        if (incidencias.size() != 0) {
            for (Incidencia i :
                    incidencias) {
                IncidenciaDataClass temp = new IncidenciaDataClass(i, daoUsuarioSQL.readUsuarioPorId(i.getIdUsuario()));
                incidenciaDataClasses.add(temp);
            }
            return incidenciaDataClasses;
        }
        return incidenciaDataClasses;
    }

    public ArrayList<IncidenciaDataClass> imprimirIncidenciasTermino(String termino) {
        ArrayList<IncidenciaDataClass> incidenciaDataClasses = new ArrayList<>();
        ArrayList<Incidencia> incidencias = new ArrayList<>();
        incidencias = daoIncidenciaSql.imprimirIncidenciasTermino(termino);
        if (incidencias.size() != 0) {
            for (Incidencia i :
                    incidencias) {
                IncidenciaDataClass temp = new IncidenciaDataClass(i, daoUsuarioSQL.readUsuarioPorId(i.getIdUsuario()));
                incidenciaDataClasses.add(temp);
            }
            return incidenciaDataClasses;
        }
        return incidenciaDataClasses;
    }

    public ArrayList<Tecnico> tecnicos() {
        return daoTecnicoSQL.tecnicosTotales();
    }

    public ArrayList<Usuario> usuarios() {
        return daoUsuarioSQL.usuarioTotales();
    }

    public boolean comprobarTecnicoPorEmail(String email) {
        return true;
    }

    public boolean introducirTecnico(String nombre, String apellido, String clave, String email) {
        Tecnico temp = new Tecnico(nombre,apellido,clave,email);
        return daoTecnicoSQL.insert(temp);
    }

    public ArrayList<IncidenciaDataClass> incidenciasAbiertasSinAsignar() {
        ArrayList<IncidenciaDataClass> incidenciaDataClasses = new ArrayList<>();
        ArrayList<Incidencia> incidencias = new ArrayList<>();
        incidencias = daoIncidenciaSql.sinAsignar();
        if (incidencias.size() != 0) {
            for (Incidencia i :
                    incidencias) {
                IncidenciaDataClass temp = new IncidenciaDataClass(i, daoUsuarioSQL.readUsuarioPorId(i.getIdUsuario()));
                incidenciaDataClasses.add(temp);
            }
            return incidenciaDataClasses;
        }
        return incidenciaDataClasses;
    }

    public boolean asignarIncidencia(IncidenciaDataClass i, Tecnico t) {
        return daoIncidenciaSql.asignarIncidencia(i,t);
    }

    public ArrayList<IncidenciaDataClass> comprobarIncidenciasAbiertas(Tecnico tech) {
        ArrayList<IncidenciaDataClass> incidenciaDataClasses = new ArrayList<>();
        ArrayList<Incidencia> incidencias = new ArrayList<>();
        incidencias = daoIncidenciaSql.tecnicoAsignado(tech);
        if (incidencias.size() != 0) {
            for (Incidencia i :
                    incidencias) {
                IncidenciaDataClass temp = new IncidenciaDataClass(i, daoUsuarioSQL.readUsuarioPorId(i.getIdUsuario()));
                incidenciaDataClasses.add(temp);
            }
            return incidenciaDataClasses;
        }
        return incidenciaDataClasses;
    }

    public ArrayList<IncidenciaDataClass> comprobarIncidenciasCerradas(Tecnico tech) {
        ArrayList<IncidenciaDataClass> incidenciaDataClasses = new ArrayList<>();
        ArrayList<Incidencia> incidencias = new ArrayList<>();
        incidencias = daoIncidenciaSql.tecnicoCerrado(tech);
        if (incidencias.size() != 0) {
            for (Incidencia i :
                    incidencias) {
                IncidenciaDataClass temp = new IncidenciaDataClass(i, daoUsuarioSQL.readUsuarioPorId(i.getIdUsuario()));
                incidenciaDataClasses.add(temp);
            }
            return incidenciaDataClasses;
        }
        return incidenciaDataClasses;
    }

    public boolean cambiarClaveTecnico(String clavenueva, String clavenueva2, Tecnico tech) {
        return daoTecnicoSQL.update(clavenueva,tech);
    }

    public boolean resolverIncidencia(String solucion, int num) {
        LocalDate fecha = LocalDate.now();
        return daoIncidenciaSql.resolver(solucion,num,fecha);
    }

    public int numeroDeUsuarios() {
        return daoUsuarioSQL.readUsuarioCantidad();
    }

    public int prioridadMediaApp() {
        return daoIncidenciaSql.readIncidenciaMedia();
    }

    public int numeroDeTecnicos() {
        return daoTecnicoSQL.readTecnicoCantidad();
    }

    public int incidenciasAbiertasNumero(Tecnico tech) {
        return daoIncidenciaSql.readIncidenciaAbiertaAsignadaNumero(tech);
    }

    public int incidenciasCerradasNumero(Tecnico tech) {
        return daoIncidenciaSql.readIncidenciaCerradaAsignadaNumero(tech);
    }

    public int incidenciasSinResolverse() {
        return daoIncidenciaSql.readIncidenciaSinResolverseNumero();
    }

    public ArrayList<Tecnico> tecnicosConIncidenciasAbiertas() {

        return daoTecnicoSQL.readTecnicoIncidenciasAbiertas();
    }

    public double incidenciasMedia(Tecnico tech) {

        return daoIncidenciaSql.readIncidenciaMediaTech(tech);

    }
}
