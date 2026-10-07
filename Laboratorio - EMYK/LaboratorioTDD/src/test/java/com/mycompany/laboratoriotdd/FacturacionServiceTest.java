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
    
    // =====================================================================
    // RF-02 — calcularImpuesto(subtotal, tasa)
    // =====================================================================

    @Test
    void calcularImpuesto_conSubtotalNegativo_lanzaError() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularImpuesto(-1000, 0.13));
    }

    @Test
    void calcularImpuesto_conTasaNegativa_lanzaError() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularImpuesto(1000, -0.13));
    }

    @Test
    void calcularImpuesto_conTasaMayorAUno_lanzaError() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularImpuesto(1000, 1.5));
    }

    @Test
    void calcularImpuesto_conSubtotalCero_retornaCero() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act
        double resultado = service.calcularImpuesto(0, 0.13);

        // Assert
        assertEquals(0.0, resultado, DELTA);
    }

    @Test
    void calcularImpuesto_conValoresValidos_retornaSubtotalPorTasa() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act
        double resultado = service.calcularImpuesto(10000, 0.13);

        // Assert
        assertEquals(1300.0, resultado, DELTA);
    }

    // =====================================================================
    // RF-03 — aplicarDescuentoPorVolumen(subtotal, cantidadArticulos)
    // =====================================================================

    @Test
    void aplicarDescuentoPorVolumen_conSubtotalNegativo_lanzaError() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.aplicarDescuentoPorVolumen(-500, 10));
    }

    @Test
    void aplicarDescuentoPorVolumen_conCantidadNegativa_lanzaError() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.aplicarDescuentoPorVolumen(10000, -1));
    }

    @Test
    void aplicarDescuentoPorVolumen_conMenosDeDiezArticulos_retornaCero() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act
        double resultado = service.aplicarDescuentoPorVolumen(10000, 9);

        // Assert
        assertEquals(0.0, resultado, DELTA);
    }

    @Test
    void aplicarDescuentoPorVolumen_conDiezArticulos_retornaCincoPorCiento() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act
        double resultado = service.aplicarDescuentoPorVolumen(10000, 10);

        // Assert
        assertEquals(500.0, resultado, DELTA);
    }

    @Test
    void aplicarDescuentoPorVolumen_conVeinteArticulos_retornaDiezPorCiento() {
        // Arrange
        FacturacionService service = new FacturacionService();

        // Act
        double resultado = service.aplicarDescuentoPorVolumen(10000, 20);

        // Assert
        assertEquals(1000.0, resultado, DELTA);
    }

}


