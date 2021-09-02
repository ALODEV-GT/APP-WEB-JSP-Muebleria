package dominio.clases;

public class Usuario {

    private int idArea;
    private String nombre; //Llave primaria
    private String password;
    private String area;

    
    
    
    public Usuario(int idArea, String nombre) {
        this.idArea = idArea;
        this.nombre = nombre;
    }
    
    public Usuario() {
    }

    public Usuario(String nombre, String password, int idArea) {
        this.nombre = nombre;
        this.password = password;
        this.idArea = idArea;
    }

    public Usuario(String nombre, String password, String area) {
        this.nombre = nombre;
        this.password = password;
        this.area = area;
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public int getIdArea() {
        return this.idArea;
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

    public void setidArea(int idArea) {
        this.idArea = idArea;
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
