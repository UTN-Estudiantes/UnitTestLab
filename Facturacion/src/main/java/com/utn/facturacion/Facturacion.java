/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.utn.Facturacion;

import java.util.List;

/**
 *
 * @author ortiz
 */
public class Facturacion {

    // RF-01 — calcularSubtotal(items)
    public double calcularSubtotal(List<Item> items) {
        if (items == null) {
            throw new IllegalArgumentException("La lista no puede ser nula");
        }

        if (items.isEmpty()) {
            return 0.0;
        }

        double subtotal = 0.0;
        for (Item item : items) {
            if (item.getPrecio() < 0) {
                throw new IllegalArgumentException("El precio unitario no puede ser negativo");
            }
            if (item.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
            }
            subtotal += item.getPrecio() * item.getCantidad();
        }

        return subtotal;
    }

    // RF-02 — calcularImpuesto(subtotal, tasa)
    public double calcularImpuesto(double subtotal, double tasa) {

        if (subtotal < 0) {
            throw new IllegalArgumentException();
        }

        if (tasa < 0) {
            throw new IllegalArgumentException();
        }

        if (tasa > 1) {
            throw new IllegalArgumentException();
        }

        if (subtotal == 0) {
            return 0.0;
        }

        return subtotal * tasa;
    }

    //RF-03 — aplicarDescuentoPorVolumen(subtotal, cantidadArticulos)
    public double aplicarDescuentoPorVolumen(double subtotal, int cantidadArticulos) {

        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }

        if (cantidadArticulos < 0) {
            throw new IllegalArgumentException("La cantidad de articulos no puede ser negativa");
        }

        if (cantidadArticulos < 10) {
            return 0.0;
        }

        if (cantidadArticulos <= 19) {
            return subtotal * 0.05;
        }

        return subtotal * 0.10;
    }

    //RF-04 — validarCedula(cedula)
    public static boolean esCedulaValida(String cedula) {
        if (cedula == null || cedula.length() != 9) {
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

    //RF-05 — calcularMontoFinal(subtotal, impuesto, descuento)
    public double calcularMontoFinal(double subtotal, double impuesto, double descuento) {
        if (subtotal < 0 || impuesto < 0 || descuento < 0) {
            throw new IllegalArgumentException("Subtotal, impuesto y descuento no pueden ser negativos");
        }

        double total = subtotal + impuesto;
        if (descuento > total) {
            throw new IllegalArgumentException("El descuento no puede ser mayor que el total a pagar");
        }

        return total - descuento;
    }

}
