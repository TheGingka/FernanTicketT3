package modelo;

public class Administrador {
    private String apodo;
    private String email;
    private String contrasena;

    public Administrador(String apodo, String email, String contrasena) {
        this.apodo = apodo;
        this.email = email;
        this.contrasena = contrasena;
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
        return "Administrador" +
                "Apodo:'" + apodo + '\n' +
                ", email='" + email + '\n' +
                ", contrasena='" + contrasena ;
    }
}
