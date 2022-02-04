package model;

public class Administrador {
    private String apodo;
    private String email;
    private String contrasena;
    private int tipoUsuario;

    public Administrador(String apodo, String email, String contrasena) {
        this.apodo = apodo;
        this.email = email;
        this.contrasena = contrasena;
        tipoUsuario = 3;
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

    @Override
    public String toString() {
        return "Administrador{" +
                "apodo='" + apodo + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
