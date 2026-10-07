/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.utn.Facturacion;

import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author ortiz
 */
public class Facturacion {

    private static final double TASA_MAXIMA = 1.0;

    private static final int UMBRAL_DESCUENTO_BAJO = 10;
    private static final int UMBRAL_DESCUENTO_ALTO = 20;
    private static final double PORCENTAJE_DESCUENTO_BAJO = 0.05;
    private static final double PORCENTAJE_DESCUENTO_ALTO = 0.10;

    private static final Pattern PATRON_CEDULA = Pattern.compile("[0-9]{9}");

    // RF-01 — calcularSubtotal(items)
    public double calcularSubtotal(List<Item> items) {
        if (items == null) {
            throw new IllegalArgumentException("La lista no puede ser nula");
        }

        double subtotal = 0.0;
        for (Item item : items) {
            validarItem(item);
            subtotal += item.getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    // RF-02 — calcularImpuesto(subtotal, tasa)
    public double calcularImpuesto(double subtotal, double tasa) {
        validarNoNegativo(subtotal, "El subtotal no puede ser negativo");
        if (tasa < 0 || tasa > TASA_MAXIMA) {
            throw new IllegalArgumentException("La tasa debe estar entre 0 y 1");
        }
        return subtotal * tasa;
    }

    // RF-03 — aplicarDescuentoPorVolumen(subtotal, cantidadArticulos)
    public double aplicarDescuentoPorVolumen(double subtotal, int cantidadArticulos) {
        validarNoNegativo(subtotal, "El subtotal no puede ser negativo");
        validarNoNegativo(cantidadArticulos, "La cantidad de artículos no puede ser negativa");
        return subtotal * porcentajeDescuento(cantidadArticulos);
    }

    // RF-04 — esCedulaValida(cedula)
    public static boolean esCedulaValida(String cedula) {
        return cedula != null && PATRON_CEDULA.matcher(cedula).matches();
    }

    // RF-05 — calcularMontoFinal(subtotal, impuesto, descuento)
    public double calcularMontoFinal(double subtotal, double impuesto, double descuento) {
        validarNoNegativo(subtotal, "El subtotal no puede ser negativo");
        validarNoNegativo(impuesto, "El impuesto no puede ser negativo");
        validarNoNegativo(descuento, "El descuento no puede ser negativo");

        double total = subtotal + impuesto;
        if (descuento > total) {
            throw new IllegalArgumentException("El descuento no puede ser mayor que el total a pagar");
        }
        return total - descuento;
    }

    // Métodos privados de apoyo 

    private static void validarNoNegativo(double valor, String mensaje) {
        if (valor < 0) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    private static void validarItem(Item item) {
        validarNoNegativo(item.getPrecio(), "El precio unitario no puede ser negativo");
        if (item.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
    }

    private static double porcentajeDescuento(int cantidadArticulos) {
        if (cantidadArticulos >= UMBRAL_DESCUENTO_ALTO) {
            return PORCENTAJE_DESCUENTO_ALTO;
        }
        if (cantidadArticulos >= UMBRAL_DESCUENTO_BAJO) {
            return PORCENTAJE_DESCUENTO_BAJO;
        }
        return 0.0;
    }
}
