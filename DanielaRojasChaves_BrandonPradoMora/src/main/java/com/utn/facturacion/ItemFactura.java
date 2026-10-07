package com.utn.facturacion;

/* Un item de la factura: precio unitario y cantidad.*/
public class ItemFactura {
    
    private final double precioUnitario;
    private final int cantidad;

    public ItemFactura(double precioUnitario, int cantidad) {
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