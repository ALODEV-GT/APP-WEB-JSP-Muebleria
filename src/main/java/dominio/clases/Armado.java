package dominio.clases;

public class Armado {

    private int idPiezaUsada;
    private int idEnsamble;

    public Armado(int idPiezaUsada, int idEnsamble) {
        this.idPiezaUsada = idPiezaUsada;
        this.idEnsamble = idEnsamble;
    }

    public Armado(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public int getIdPiezaUsada() {
        return idPiezaUsada;
    }

    public void setIdPiezaUsada(int idPiezaUsada) {
        this.idPiezaUsada = idPiezaUsada;
    }

    public int getIdEnsamble() {
        return idEnsamble;
    }

    public void setIdEnsamble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

}
