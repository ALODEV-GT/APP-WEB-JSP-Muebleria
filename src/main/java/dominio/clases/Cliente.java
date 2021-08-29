package dominio.clases;

public class Cliente {

    private String nit=""; //LLave primaria
    private String nombre="";
    private String direccion="";
    private String municipo="";
    private String departamento="";

    public Cliente() {
    }

    public Cliente(String nit) {
        this.nit = nit;
    }

    public Cliente(String nombre, String nit, String direccion, String municipo, String departamento) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.municipo = municipo;
        this.departamento = departamento;
    }

    public Cliente(String nombre, String nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipo() {
        return municipo;
    }

    public void setMunicipo(String municipo) {
        this.municipo = municipo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
