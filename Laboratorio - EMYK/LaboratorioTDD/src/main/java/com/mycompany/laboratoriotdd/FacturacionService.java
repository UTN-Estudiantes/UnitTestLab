package com.mycompany.laboratoriotdd;

import java.util.List;

public class FacturacionService {

    // RF-01
    public double calcularSubtotal(List<ItemFactura> items) {

        if (items == null) {
            throw new IllegalArgumentException(
                    "La lista de items no puede ser nula");
        }

        double subtotal = 0.0;

        for (ItemFactura item : items) {

            if (item.getPrecioUnitario() < 0) {
                throw new IllegalArgumentException(
                        "El precio unitario no puede ser negativo");
            }

            if (item.getCantidad() <= 0) {
                throw new IllegalArgumentException(
                        "La cantidad debe ser mayor que cero");
            }

            subtotal += item.getPrecioUnitario() * item.getCantidad();
        }

        return subtotal;
    }

        // RF-02
    public double calcularImpuesto(double subtotal, double tasa) {

        if (subtotal < 0) {
            throw new IllegalArgumentException(
                    "El subtotal no puede ser negativo");
        }

        if (tasa < 0 || tasa > 1) {
            throw new IllegalArgumentException(
                    "La tasa debe estar entre 0 y 1");
        }

        return subtotal * tasa;
    }

    // RF-03
    public double aplicarDescuentoPorVolumen(
            double subtotal, int cantidadArticulos) {

        if (subtotal < 0) {
            throw new IllegalArgumentException(
                    "El subtotal no puede ser negativo");
        }

        if (cantidadArticulos < 0) {
            throw new IllegalArgumentException(
                    "La cantidad de artículos no puede ser negativa");
        }

        if (cantidadArticulos < 10) {
            return 0.0;
        }

        if (cantidadArticulos < 20) {
            return subtotal * 0.05;
        }

        return subtotal * 0.10;
    }


    // RF-04: true si la cédula tiene exactamente 9 dígitos.
    // Nunca lanza error: null, vacía o con letras/espacios/guiones da false.
    public boolean validarCedula(String cedula) {
        throw new UnsupportedOperationException("No implementado");
    }

    // RF-05: devuelve subtotal + impuesto - descuento.
    // Lanza error si algún valor es negativo o si el descuento es mayor
    // que subtotal + impuesto. Si es igual, devuelve 0.0 (es válido).
    public double calcularMontoFinal(double subtotal, double impuesto, double descuento) {
        throw new UnsupportedOperationException("No implementado");
    }
}

