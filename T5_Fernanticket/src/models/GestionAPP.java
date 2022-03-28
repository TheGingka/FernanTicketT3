package models;

import models.Admin;
import models.Tecnico;
import models.Usuario;

import java.util.ArrayList;
import java.util.Objects;

public class GestionAPP {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Tecnico> tecnicos;
    private ArrayList<Admin> admins;

    // CONSTRUCTOR

    public GestionAPP() {
        this.usuarios = new ArrayList<>();
        this.tecnicos = new ArrayList<>();
        this.admins = new ArrayList<>();
        tecnico1();
        admin1();
        usuario1();
    }


    // GS

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(ArrayList<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }


    // Otros métodos - Necesarios

    public boolean insertaUsuario(String nombre, String apel, String clave, String email){ //No completado

        usuarios.add(new Usuario(generaIdUser(), nombre, apel, clave, email));

        return true;
    }

    public boolean insertaTecnico(String nombre, String apel, String clave, String email){ //No completado

        tecnicos.add(new Tecnico(generaIdTecnico(), nombre, apel, clave, email));

        return true;
    }

    public boolean insertaIncidencia(String descripcion, String email, int prioridad){

        Usuario usuario = buscaUsuario(email);
        Incidencia incidencia = new Incidencia(generaIdIncidencia(), descripcion, prioridad, buscaUsuario(email).getId());

        if (usuario == null){
            return false;
        }else{
            if (incidencia == null){
                return false;
            }else {
                usuario.insertaIncidencia(incidencia);
                return true;
            }
        }

    }

    public ArrayList<IncidenciaDataClass> buscaTodasIncidenciasAbiertas(){

        ArrayList<IncidenciaDataClass> incidenciasAbiertas = new ArrayList<IncidenciaDataClass>();

        for (Usuario u:
                usuarios) {
            for (Incidencia i:
                    u.getIncidencias()) {
                if (i != null && !i.isEstaResuelta())
                    incidenciasAbiertas.add(new IncidenciaDataClass(i,buscaUsuariobyID(i.getIdUsuario())));
            }
        }

        for (Tecnico t:
                tecnicos) {
            if (t.getIncidencias() != null)
                for (Incidencia i:
                        t.getIncidencias()) {
                    if (i != null && !i.isEstaResuelta())
                        incidenciasAbiertas.add(new IncidenciaDataClass(i, buscaUsuariobyID(i.getIdUsuario()), t));
                }
        }

        return incidenciasAbiertas;
    }

    public int incidenciasAbiertasByUsuario(int idUsuario){

        for (Usuario u:
             usuarios) {
            if (u.getId() == idUsuario)
                return u.incidenciasAbiertas();
        }
        return 0;
    }

    public int incidenciasAbiertas(){

        int contador = 0;

        for (Usuario u:
                usuarios) {
            contador += u.incidenciasAbiertas();
        }

        for (Tecnico t:
             tecnicos) {
            contador += t.incidenciasAbiertas();
        }

        return contador;
    } // Incidencias de usuario.

    public int incidenciasAsignadasByUsuario(int idUsuario){

        int contador = 0;

        for (Tecnico t:
             tecnicos) {
            for (Incidencia i:
                 t.getIncidencias()) {
                if (i.getIdUsuario() == idUsuario)
                    contador++;
            }
        }
        return contador;
    }

    public int incidenciasAbiertasAsignadas(){

        int contador = 0;

        for (Tecnico t:
                tecnicos) {
            contador += t.incidenciasAbiertas();
        }
        return contador;

    } // Incidencias de técnico.

    public int incidenciasAbiertasAsignadasByTecnico(int idTecnico){ // Contador de incidencias de tecnico.

        int contador = 0;

        for (Tecnico t:
                tecnicos) {
            if (t.getId() == idTecnico)
            contador += t.incidenciasAbiertas();
        }
        return contador;

    } // Incidencias de técnico.

    public int incidenciasCerradas(){

        int contador = 0;

        for (Tecnico t:
                tecnicos) {
            contador += t.incidenciasCerradas();
        }
        return contador;
    } // Incidencias de técnico.

    public int incidenciasCerradasByTecnico( int idTecnico){

        int contador = 0;

        for (Tecnico t:
                tecnicos) {
            if (t.getId() == idTecnico)
            contador += t.incidenciasCerradas();
        }
        return contador;
    } // Incidencias cerradas de técnico buscado.

    public ArrayList<IncidenciaDataClass> buscaIncidenciasAsignadas(int idIncidencia){

        ArrayList<IncidenciaDataClass> incidenciasAsignadas = new ArrayList<>();

        for (Tecnico t:
                tecnicos) {
            for (Incidencia i:
                    t.getIncidencias()) {
                if (i.getId() == idIncidencia)
                    incidenciasAsignadas.add(new IncidenciaDataClass(i, buscaUsuariobyID(i.getIdUsuario()), t));
            }
        }

        return incidenciasAsignadas;

    }

    private int generaIdIncidencia(){
        int id;
        boolean hayRepetido = true;

        do {

            id = (int) (Math.random() * 1000);

            if (usuarios == null){
                return id;
            }else{
                for (Usuario u:
                        usuarios) {
                    if (u.getIncidencias() == null){
                        return id;

                    }else{
                        for (Incidencia i:
                                u.getIncidencias()) {
                            if (i != null && i.getId() == id){

                                hayRepetido = false;

                            }
                        }
                    }

                }
            }
        }while (!hayRepetido);

        return id;
    }

    private int generaIdUser() {

        int id;
        boolean hayRepetido = true;

        do {

            id = (int) (Math.random() * 1000);

            if (usuarios == null){
                return id;
            }else{
                for (Usuario u:
                        usuarios) {
                    if (u != null && u.getId() == id) {
                        hayRepetido = false;
                    }
                }
            }
        }while (!hayRepetido);

        return id;
    }

    private int generaIdTecnico(){

        int id;
        boolean hayRepetido = true;

        do {

            id = (int) (Math.random() * 101);

            if (tecnicos == null){
                return id;
            }else{
                for (Tecnico t:
                        tecnicos) {
                    if (t != null && t.getId() == id) {
                        hayRepetido = false;
                    }
                }
            }
        }while (!hayRepetido);

        return id;

    }

    public Usuario buscaUsuario(String email){

        for (Usuario u:
                usuarios) {
            if (u.getEmail().equals(email))
                return u;
        }

        return null;
    }

    public Usuario buscaUsuariobyID(int idUsuario){

        for (Usuario u:
                usuarios) {
            if (u.getId() == idUsuario)
                return u;
        }

        return null;
    }

    public Object login(String email, String clave) {

        for (Usuario u:
             usuarios) {
            if (u != null && u.login(email,clave)) return u;
        }

        for (Tecnico t:
                tecnicos) {
            if (t != null && t.login(email,clave)) return t;
        }

        for (Admin a:
                admins) {
            if (a != null && a.login(email,clave)) return a;
        }

        return null;
    }

    public Object buscaUser(String email){

        for (Usuario u:
                usuarios) {
            if (u != null && u.getEmail().equals(email)) return u;
        }

        for (Tecnico t:
                tecnicos) {
            if (t != null && t.getEmail().equals(email)) return t;
        }

        for (Admin a:
                admins) {
            if (a != null && a.getEmail().equals(email)) return a;
        }

        return null;
    }

    public float prioridadMediaApp(){

        float suma = 0, total = 0;

        for (Tecnico t:
                tecnicos) {
            for (Incidencia i:
                    t.getIncidencias()) {
                if (i.getId() != 0) {
                    total++;
                    suma += i.getPrioridad();
                }
            }
        }

        return suma / total;

    }

    public float prioridadMediaAppByTecnico(int idTecnico){

        float suma = 0, total = 0;

        for (Tecnico t:
                tecnicos) {
            if (t.getId() == idTecnico)
            for (Incidencia i:
                    t.getIncidencias()) {
                if (i.getId() != 0) {
                    total++;
                    suma += i.getPrioridad();
                }
            }
        }

        return suma / total;

    }

    public Incidencia buscaIncidencia(int idIncidencia){

        for (Usuario u:
                usuarios) {
            for (Incidencia i:
                    u.getIncidencias()) {
                if (i.getId() == idIncidencia) return i;
            }
        }
        return null;
    }

    public Tecnico buscaTecnico(int idTecnico){

        for (Tecnico t:
                tecnicos) {
            if (t.getId() == idTecnico) return t;
        }

        return null;
    }

    public boolean estaIncidenciaAsignada(int idIncidencia){

        for (Usuario u:
                usuarios) {
            for (Incidencia i:
                    u.getIncidencias()) {
                if (i.getId() == idIncidencia)
                    return false;
            }
        }

        for (Tecnico t:
                tecnicos) {
            for (Incidencia i:
                    t.getIncidencias()) {
                if (i.getId() == idIncidencia)
                    return true;
            }
        }

        return false;
    }

    public Usuario buscaUsuariobyIncidencia(int idIncidencia){

        for (Usuario u:
                usuarios) {
            for (Incidencia i:
                    u.getIncidencias()) {
                if (i.getId() == idIncidencia) return u;
            }
        }

        return null;
    }

    public boolean asignaIncidencia(int idIncidencia, int idTecnico){

        for (Usuario u:
                usuarios) {
            for (Incidencia i:
                    u.getIncidencias()) {
                if (i.getId() == idIncidencia){
                    for (Tecnico t:
                            tecnicos) {
                        if (t.getId() == idTecnico){
                            t.asignaIncidencia(i);
                        }
                    }
                    u.deleteIncidencia(i.getId());
                    return true;
                }
            }
        }
        return false;

    }

    public boolean cierraIncidencia(int idIncidencia, int idTecnico, String solucion){

        for (Tecnico t:
             tecnicos) {
            if (t.getId() == idTecnico)
            return t.cierraIncidencia(idIncidencia, solucion);
        }
        return false;
    }

    public ArrayList<IncidenciaDataClass> buscaIncidenciasbyTerm(String termino){

        ArrayList<IncidenciaDataClass> incidenciasTermino = new ArrayList<>();

        for (Usuario u:
             usuarios) {
            for (Incidencia i:
                 u.buscaIncidenciabyTerm(termino)) {
                incidenciasTermino.add(new IncidenciaDataClass(i, buscaUsuariobyID(i.getIdUsuario())));
            }
        }

        for (Tecnico t:
                tecnicos) {
            for (Incidencia i:
                    t.buscaIncidenciasbyTerm(termino)) {
                incidenciasTermino.add(new IncidenciaDataClass(i, buscaUsuariobyID(i.getIdUsuario()), t));
            }
        }

        return incidenciasTermino;

    }

    public int incidenciasAsignadas(int idTecnico){

        int contador = 0;

        for (Tecnico t:
             tecnicos) {
            if (t.getId() == idTecnico)
                contador += incidenciasAbiertas();
                contador += incidenciasCerradas();

        }

        return contador;
    }

    public ArrayList<IncidenciaDataClass> buscaIncidenciasAbiertasbyTecnico(int idTecnico){

        ArrayList<IncidenciaDataClass> incidenciasAbiertas = new ArrayList<>();


        for (Tecnico t:
             tecnicos) {
            if (t.getId() == idTecnico)
                if (t.getIncidencias() != null)
                for (Incidencia i:
                     t.getIncidencias()) {
                    if (!i.isEstaResuelta())
                        incidenciasAbiertas.add(new IncidenciaDataClass(i, buscaUsuariobyID(i.getIdUsuario()), t));
                }
        }

        return incidenciasAbiertas;
    }

    public ArrayList<IncidenciaDataClass> buscaIncidenciasCerradassbyTecnico(int idTecnico){

        ArrayList<IncidenciaDataClass> incidenciasAbiertas = new ArrayList<>();


        for (Tecnico t:
                tecnicos) {
            if (t.getId() == idTecnico)
                for (Incidencia i:
                        t.getIncidencias()) {
                    if (i.isEstaResuelta())
                        incidenciasAbiertas.add(new IncidenciaDataClass(i, buscaUsuariobyID(i.getIdUsuario())));
                }
        }

        return incidenciasAbiertas;
    }

    public ArrayList<IncidenciaDataClass> buscaIncidenciasCerradas(){

        ArrayList<IncidenciaDataClass> incidenciasCerradas = new ArrayList<>();

        for (Tecnico t:
             tecnicos) {
            for (Incidencia i:
                 t.getIncidencias()) {
                if (i.isEstaResuelta()){
                    incidenciasCerradas.add(new IncidenciaDataClass(i, buscaUsuariobyID(i.getIdUsuario()), t));
                }
            }
        }
        return incidenciasCerradas;
    }

    public ArrayList<IncidenciaDataClass> buscaIncidenciasSinAsignar(){

        ArrayList<IncidenciaDataClass> incidenciasSA = new ArrayList<>();

        for (Usuario u:
             usuarios) {
            for (Incidencia i:
                 u.getIncidencias()) {
                incidenciasSA.add(new IncidenciaDataClass(i, buscaUsuariobyID(i.getIdUsuario())));
            }
        }
        return incidenciasSA;
    }

    public ArrayList<IncidenciaDataClass> buscaIncidenciasCerradasbyUsuario(int idUsuario){

        ArrayList<IncidenciaDataClass> incidenciasCerradas = new ArrayList<>();

        for (Tecnico t:
                tecnicos) {
            for (Incidencia i:
                    t.getIncidencias()) {
                if (i.isEstaResuelta()){
                    if (i.getIdUsuario() == idUsuario)
                    incidenciasCerradas.add(new IncidenciaDataClass(i, buscaUsuariobyID(i.getIdUsuario()), t));
                }
            }
        }
        return incidenciasCerradas;
    }

    // Otros métodos - Personales

    private void usuario1(){
        usuarios.add(new Usuario(generaIdUser(), "user", "user", "user", "user"));
    }

    private void tecnico1(){
        tecnicos.add(new Tecnico(generaIdTecnico(), "tech", "tech", "tech", "tech"));
    }

    private void admin1(){
        admins.add(new Admin(1, "admin", "admin", "admin", "admin"));
    }

    public boolean introducirUsuario(String nombre, String apel, String clave, String email){

        return usuarios.add(new Usuario(generaIdUser(), nombre, apel, clave, email));

    }

    public boolean introducirTecnico(String email, String clave, String nombre, String apel){

        return tecnicos.add(new Tecnico(generaIdTecnico(), nombre, apel, clave, email));

    }

    public boolean comprobarClave(String clave, String clave2) {

        return clave.equals(clave2);

    }

    public boolean comprobarEmailUsuario(String email){

        for (Usuario u:
             usuarios) {
            if (u.getEmail().equals(email))
                return false;
        }
        return true;
    }

    public boolean comprobarEmailTecnico(String email){

        for (Tecnico t:
             tecnicos) {
            if (t.getEmail().equals(email))
                return false;
        }
        return true;
    }

    public boolean cambiaClaveUsuario(String claveantigua, String clavenueva, String clavenueva2){

        for (Usuario u:
                usuarios) {
            if (u != null && u.compruebaClave(claveantigua))
                if (clavenueva.equals(clavenueva2)){
                    u.setClave(clavenueva);
                    return true;
                }
        }
        return false;
    }

    public boolean comprobarIncidenciasTecnico(Tecnico tecnico) {

        for (Tecnico t:
             tecnicos) {
            if (t.getId() == tecnico.getId())
                if (t.getIncidencias().size() == 0){
                    return true;
                }else{
                    for (Incidencia i:
                            t.getIncidencias()) {
                        if (i.isEstaResuelta()) return false;
                    }
                }
        }
        return false;
    }

    public int incidenciasSinAsignar() { // Numero de incidencias sin asignar admin.

        int contador = 0;
        
        for (Usuario u:
             usuarios) {
            for (Incidencia i:
                 u.getIncidencias()) {
                contador++;
            }
        }
        return contador;
    }

    public boolean cambiaClaveTecnico(String claveantigua, String clavenueva, String clavenueva2) {

        for (Tecnico t:
                tecnicos) {
            if (t != null && t.compruebaClave(claveantigua))
                if (clavenueva.equals(clavenueva2)){
                    t.setClave(clavenueva);
                    return true;
                }
        }
        return false;

    }

    public boolean sePuedeBorrarTecnico(int idTecnico){

        for (Tecnico t:
             tecnicos) {
            if (t.getId() == idTecnico)
                if (t.getIncidencias().size() == 0)
                    return true;
                for (Incidencia i:
                     t.getIncidencias()) {
                    if (i.isEstaResuelta())
                        return false;
                }
                return true;
        }
        return false; // Esta variable nunca se accede, ya que el tecnico siempre entra.
    }

    public void borrarTecnico(int idTecnico){

        for (int i = 0; i < tecnicos.size(); i++) {
            if (tecnicos.get(i).getId() == idTecnico){
                tecnicos.remove(i);
                break;
            }
        }

    }

}
