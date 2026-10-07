package com.mycompany.laboratoriotdd;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FacturacionServiceTest {

    private static final double DELTA = 0.001;

    // =====================================================================
    // RF-01 — calcularSubtotal(items)
    // =====================================================================

    @Test
    void calcularSubtotal_conListaNula_lanzaError() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularSubtotal(null));
    }

    @Test
    void calcularSubtotal_conListaVacia_retornaCero() {
        // Arrange
        FacturacionService service = new FacturacionService();
        List<ItemFactura> items = new ArrayList<>();

        // Act
        double resultado = service.calcularSubtotal(items);

        // Assert
        assertEquals(0.0, resultado, DELTA);
    }

    @Test
    void calcularSubtotal_conPrecioNegativo_lanzaError() {
        // Arrange
        FacturacionService service = new FacturacionService();
        List<ItemFactura> items = List.of(
                new ItemFactura(-100, 2)
        );

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_conCantidadCero_lanzaError() {
        // Arrange
        FacturacionService service = new FacturacionService();
        List<ItemFactura> items = List.of(
                new ItemFactura(5000, 0)
        );

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_conCantidadNegativa_lanzaError() {
        // Arrange
        FacturacionService service = new FacturacionService();
        List<ItemFactura> items = List.of(
                new ItemFactura(5000, -1)
        );

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_conVariosItems_sumaCorrectamente() {
        // Arrange
        FacturacionService service = new FacturacionService();

        List<ItemFactura> items = List.of(
                new ItemFactura(5000, 2),
                new ItemFactura(12000, 1)
        );

        // Act
        double resultado = service.calcularSubtotal(items);

        // Assert
        assertEquals(22000.0, resultado, DELTA);
    }
}


