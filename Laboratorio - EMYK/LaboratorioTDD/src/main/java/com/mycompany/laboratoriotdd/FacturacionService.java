package com.mycompany.laboratoriotdd;

import java.util.List;

public class FacturacionService {

    // RF-01: suma el subtotal de los ítems (TODO: completar reglas)
    public double calcularSubtotal(List<ItemFactura> items) {
        throw new UnsupportedOperationException("No implementado");
    }

    // RF-02: calcula el impuesto del subtotal (TODO: completar reglas)
    public double calcularImpuesto(double subtotal, double tasa) {
        throw new UnsupportedOperationException("No implementado");
    }

    // RF-03: descuento según la cantidad de artículos (TODO: completar reglas)
    public double aplicarDescuentoPorVolumen(double subtotal, int cantidadArticulos) {
        throw new UnsupportedOperationException("No implementado");
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

