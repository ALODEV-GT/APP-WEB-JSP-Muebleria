package dominio.clases;

public class Usuario {

    private String nombre; //Llave primaria
    private String password;
    private String area;

    public Usuario() {
    }

    public Usuario(String nombre, String password, String area) {
        this.nombre = nombre;
        this.password = password;
        this.area = area;
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

    public String getArea() {
        return area;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
