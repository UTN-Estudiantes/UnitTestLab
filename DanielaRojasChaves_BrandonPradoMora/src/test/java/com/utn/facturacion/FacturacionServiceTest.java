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

        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_conCantidadCero_lanzaError() {
        // Arrange: 0 es el valor limite (el primero invalido)
        List<ItemFactura> items = List.of(new ItemFactura(500.0, 0));

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
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularImpuesto(-100.0, 0.13));
    }

    @Test
    void calcularImpuesto_conTasaNegativa_lanzaError() {
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularImpuesto(1000.0, -0.13));
    }

    @Test
    void calcularImpuesto_conTasaMayorAUno_lanzaError() {
        // Act + Assert: 1.5 equivale a 150%
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularImpuesto(1000.0, 1.5));
    }

    @Test
    void calcularImpuesto_conSubtotalCero_retornaCero() {
        // Act
        double impuesto = servicio.calcularImpuesto(0.0, 0.13);

        // Assert
        assertEquals(0.0, impuesto, 0.001);
    }

    @Test
    void calcularImpuesto_conValoresValidos_retornaSubtotalPorTasa() {
        // Arrange: IVA de Costa Rica
        double subtotal = 10000.0;
        double tasa = 0.13;

        // Act
        double impuesto = servicio.calcularImpuesto(subtotal, tasa);

        // Assert
        assertEquals(1300.0, impuesto, 0.001);
    }

    @Test
    void aplicarDescuentoPorVolumen_conSubtotalNegativo_lanzaError() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> servicio.aplicarDescuentoPorVolumen(-100.0, 15));
    }

    @Test
    void aplicarDescuentoPorVolumen_conCantidadNegativa_lanzaError() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> servicio.aplicarDescuentoPorVolumen(1000.0, -1));
    }

    @Test
    void aplicarDescuentoPorVolumen_conMenosDeDiezArticulos_retornaCero() {
        // Act
        double descuento = servicio.aplicarDescuentoPorVolumen(1000.0, 5);

        // Assert
        assertEquals(0.0, descuento, 0.001);
    }
}