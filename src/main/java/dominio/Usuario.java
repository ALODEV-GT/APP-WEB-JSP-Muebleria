package dominio;

public class Usuario {

    private String nombre; //Llave primaria
    private String password;
    private int idArea;

    public Usuario() {
    }

    public Usuario(String nombre, String password, int idArea) {
        this.nombre = nombre;
        this.password = password;
        this.idArea = idArea;
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

}
