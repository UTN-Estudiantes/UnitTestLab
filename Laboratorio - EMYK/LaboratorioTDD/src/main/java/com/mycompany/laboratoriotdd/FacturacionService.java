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

    // RF-04
    public boolean validarCedula(String cedula) {

        if (cedula == null) {
            return false;
        }

        if (cedula.length() != 9) {
            return false;
        }

        for (int i = 0; i < cedula.length(); i++) {
            char c = cedula.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    // RF-05
    public double calcularMontoFinal(
            double subtotal, double impuesto, double descuento) {

        if (subtotal < 0) {
            throw new IllegalArgumentException(
                    "El subtotal no puede ser negativo");
        }

        if (impuesto < 0) {
            throw new IllegalArgumentException(
                    "El impuesto no puede ser negativo");
        }

        if (descuento < 0) {
            throw new IllegalArgumentException(
                    "El descuento no puede ser negativo");
        }

        if (descuento > subtotal + impuesto) {
            throw new IllegalArgumentException(
                    "El descuento no puede superar el total");
        }

        return subtotal + impuesto - descuento;
    }
}
