package cr.ac.utn.facturacion;

public class Item {

    private final double precioUnitario;
    private final int cantidad;

    public Item(double precioUnitario, int cantidad) {
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }
}
