package modelo;

public class Tecnico {
    //Atributos
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

    public boolean comprobarContraseña(String contraseña){
        if (contraseña.equals(getContrasena()))return true;
        return false;
    }

    public int totalIncidencias (){
        if (incidencia1 == null && incidencia2 == null)return 2;
        if (incidencia1 != null && incidencia2 == null)return 1;
        if (incidencia1 == null && incidencia2 != null)return 1;
        if (incidencia1 != null && incidencia2 != null)return 0;
        else return 0;
    }

    public String insertarIncidencia (Incidencia inc){
        if (totalIncidencias() != 0){
            if (incidencia1 == null){
                setIncidencia1(inc);
                return "Se ha cerrado correctamente la incidencia";
            }else if (incidencia2 == null){
                setIncidencia2(inc);
                return "Se ha cerrado correctamente la incidencia";
            }
        }else return "Ya no tiene mas sitio";
        return "";
    }

    public boolean comprobarNombre (String apodo){
        if (apodo .equals(getApodo()))return true;
        else return false;
    }

    public String incidenciasResueltas (){
        return "Incidencia 1:  " + ((incidencia1 == null)? "no hay todavia ninguna incidencia" : incidencia1) + "\n" +
                "Incidencia 2:  " + ((incidencia2 == null)? "no hay todavia ninguna incidencia" : incidencia1);
    }
    @Override
    public String toString() {
        return "Tecnico" + "\n" +
                "Apodo:  " + apodo + '\n' +
                "Email:  " + email + '\n' +
                "Contrasena:  " + contrasena;
    }
}
