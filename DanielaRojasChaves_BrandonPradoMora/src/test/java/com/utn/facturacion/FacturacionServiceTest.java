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
}