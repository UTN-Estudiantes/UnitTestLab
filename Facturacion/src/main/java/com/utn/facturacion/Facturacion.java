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
        throw new UnsupportedOperationException("No implementado");
    }
    
    // RF-02 — calcularImpuesto(subtotal, tasa)
    public double calcularImpuesto(double subtotal, double tasa) {
        throw new UnsupportedOperationException();
    }
    
    //RF-03 — aplicarDescuentoPorVolumen(subtotal, cantidadArticulos)
     public double aplicarDescuentoPorVolumen(double subtotal, int cantidadArticulos) {
        throw new UnsupportedOperationException("No implementado");
    }
     
    //RF-04 — validarCedula(cedula)
    public static boolean esCedulaValida(String cedula) {
        throw new UnsupportedOperationException("Pendiente");
    }
    
    //RF-05 — calcularMontoFinal(subtotal, impuesto, descuento)
    public double calcularMontoFinal(double subtotal, double impuesto, double descuento) {
    throw new UnsupportedOperationException("No implementado");
    }
    
}
