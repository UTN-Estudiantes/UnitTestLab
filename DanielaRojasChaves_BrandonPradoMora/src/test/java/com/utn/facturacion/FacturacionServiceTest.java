package com.utn.facturacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FacturacionServiceTest {

    private FacturacionService servicio;

    @BeforeEach
    void setUp() {
        // Se crea un servicio nuevo antes de CADA prueba: asi las pruebas son independientes entre si.
        servicio = new FacturacionService();
    }

    @Test
    void calcularSubtotal_conListaNula_lanzaError() {
        // Arrange: lista nula (no hay nada mas que preparar)
        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularSubtotal(null));
    }

    @Test
    void calcularSubtotal_conListaVacia_retornaCero() {
        // Arrange
        List<ItemFactura> items = Collections.emptyList();

        // Act
        double subtotal = servicio.calcularSubtotal(items);

        // Assert
        assertEquals(0.0, subtotal, 0.001);
    }

    @Test
    void calcularSubtotal_conPrecioNegativo_lanzaError() {
        // Arrange
        List<ItemFactura> items = List.of(new ItemFactura(-500.0, 1));

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_conCantidadCero_lanzaError() {
        // Arrange: 0 es el valor limite (el primero invalido)
        List<ItemFactura> items = List.of(new ItemFactura(500.0, 0));

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_conVariosItems_sumaCorrectamente() {
        // Arrange
        List<ItemFactura> items = List.of(
                new ItemFactura(1500.0, 2),  // 3000
                new ItemFactura(250.0, 4)    // 1000
        );

        // Act
        double subtotal = servicio.calcularSubtotal(items);

        // Assert
        assertEquals(4000.0, subtotal, 0.001);
    }

    @Test
    void calcularImpuesto_conSubtotalNegativo_lanzaError() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularImpuesto(-100.0, 0.13));
    }
}