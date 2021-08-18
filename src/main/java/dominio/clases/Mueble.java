package dominio.clases;

public class Mueble {

    private String nombre; //Llave primaria
    private double precio;

    public Mueble() {
    }

    public Mueble(String nombre) {
        this.nombre = nombre;
    }

    public Mueble(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

}
