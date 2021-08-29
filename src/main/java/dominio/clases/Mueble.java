package dominio.clases;

import java.util.List;

public class Mueble {

    private String nombre; //Llave primaria
    private double precio;
    private List<EnsamblePieza> requerimientos;

    public Mueble() {
    }

    public Mueble(String nombre) {
        this.nombre = nombre;
    }

    public Mueble(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<EnsamblePieza> getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(List<EnsamblePieza> requerimientos) {
        this.requerimientos = requerimientos;
    }
    
    

}
