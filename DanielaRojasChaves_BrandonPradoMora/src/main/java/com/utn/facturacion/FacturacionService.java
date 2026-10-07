package com.utn.facturacion;

import java.util.List;

/*Logica de facturacion*/
public class FacturacionService {

    public double calcularSubtotal(List<ItemFactura> items) {
        if (items == null) {
            throw new IllegalArgumentException("La lista de items no puede ser nula");
        }
        double subtotal = 0.0;
        for (ItemFactura item : items) {
            validarItem(item);
            subtotal += item.getPrecioUnitario() * item.getCantidad();
        }
        return subtotal;
    }

    public double calcularImpuesto(double subtotal, double tasa) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        if (tasa < 0) {
            throw new IllegalArgumentException("La tasa no puede ser negativa");
        }
        throw new UnsupportedOperationException("No implementado");
    }

    public double aplicarDescuentoPorVolumen(double subtotal, int cantidadArticulos) {
        throw new UnsupportedOperationException("No implementado");
    }

    public boolean validarCedula(String cedula) {
        throw new UnsupportedOperationException("No implementado");
    }

    public double calcularMontoFinal(double subtotal, double impuesto, double descuento) {
        throw new UnsupportedOperationException("No implementado");
    }

    private void validarItem(ItemFactura item) {
        if (item.getPrecioUnitario() < 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");
        }
        if (item.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
    }
}