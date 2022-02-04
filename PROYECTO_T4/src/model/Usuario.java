package model;

public class Usuario {
    //Atributos
    private String apodo;
    private String email;
    private String contrasena;
    private Incidencia incidencia1;
    private Incidencia incidencia2;
    private Incidencia incidencia3;
    private String token;
    private boolean inicioSesion;

    //Constructor
    public Usuario(String apodo, String email, String contrasena, String token) {
        this.apodo = apodo;
        this.email = email;
        this.contrasena = contrasena;
        this.incidencia1 = null;
        this.incidencia2 = null;
        this.incidencia3 = null;
        this.token = token;
        this.inicioSesion = false;

    }

    //Getter y Setter
    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Incidencia getIncidencia1() {
        return incidencia1;
    }

    public void setIncidencia1(Incidencia incidencia1) {
        this.incidencia1 = incidencia1;
    }

    public Incidencia getIncidencia2() {
        return incidencia2;
    }

    public void setIncidencia2(Incidencia incidencia2) {
        this.incidencia2 = incidencia2;
    }

    public Incidencia getIncidencia3() {
        return incidencia3;
    }

    public void setIncidencia3(Incidencia incidencia3) {
        this.incidencia3 = incidencia3;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getInicioSesion() {
        return inicioSesion;
    }

    public void setInicioSesion(boolean inicioSesion) {
        this.inicioSesion = inicioSesion;
    }



        //Otros metodos
    //Para comprobar la contraseña
    public boolean comprobarContraseña(String contraseña){
        if (contraseña.equals(getContrasena()))return true;
        return false;
    }
    //Consultar incidencias
    public String comprobarIncidencias (){
        return "1: " + ((incidencia1 == null)? "no hay todavia ninguna incidencia" : incidencia1) + "\n" +
                "\n" +
                "2: " + ((incidencia2 == null)? "no hay todavia ninguna incidencia" : incidencia2) + "\n" +
                "\n" +
                "3: " + ((incidencia3 == null)? "no hay todavia ninguna incidencia" : incidencia3) + "\n";
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "apodo='" + apodo + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", incidencia1=" + incidencia1 +
                ", incidencia2=" + incidencia2 +
                ", incidencia3=" + incidencia3 +
                ", toke=" + token +
                '}';
    }
    public String usuario() {
        return "Usuario" + "\n" +
                "Apodo: " + apodo + "\n" +
                "Email: " + email + "\n" +
                "Contrasena: " + contrasena;
    }
}
