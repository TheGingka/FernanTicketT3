package model;

public class Tecnico {
    private String apodo;
    private String email;
    private String contrasena;
    private Incidencia incidencia1;
    private Incidencia incidencia2;

    //Constructor

    public Tecnico(String apodo, String email, String contrasena) {
        this.apodo = apodo;
        this.email = email;
        this.contrasena = contrasena;
        this.incidencia1 = null;
        this.incidencia2 = null;
    }

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

    public boolean comprobarContraseña(String contraseña){
        if (contraseña.equals(getContrasena()))return true;
        return false;
    }
    @Override
    public String toString() {
        return "Apodo: " + getApodo() + '\n' +
                "Email:" + getEmail() + '\n' +
                "Contraseña: " + getContrasena();
    }
}
