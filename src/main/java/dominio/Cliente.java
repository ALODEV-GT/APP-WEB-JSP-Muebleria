package dominio;

public class Cliente {

    private String NIT; //LLave primaria
    private String nombre;
    private String direccion;

    public Cliente() {
    }

    public Cliente(String NIT, String nombre, String direccion) {
        this.NIT = NIT;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Cliente(String NIT) {
        this.NIT = NIT;
    }

    public String getNIT() {
        return NIT;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
