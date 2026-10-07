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

    @Test
    void aplicarDescuentoPorVolumen_conDiezArticulos_retornaCincoPorCiento() {
        // Arrange: 10 es el limite inferior del tramo de 5%
        double subtotal = 1000.0;

        // Act
        double descuento = servicio.aplicarDescuentoPorVolumen(subtotal, 10);

        // Assert: se devuelve el MONTO del descuento, no el total
        assertEquals(50.0, descuento, 0.001);
    }
    @Test
    void aplicarDescuentoPorVolumen_conVeinteArticulos_retornaDiezPorCiento() {
        // Arrange: 20 es el limite inferior del tramo de 10%
        double subtotal = 1000.0;

        // Act
        double descuento = servicio.aplicarDescuentoPorVolumen(subtotal, 20);

        // Assert
        assertEquals(100.0, descuento, 0.001);
    }
    
    @Test
    void validarCedula_conCedulaNula_retornaFalse() {
        // Act
        boolean resultado = servicio.validarCedula(null);

        // Assert: si el metodo lanzara un error, la prueba fallaria
        assertFalse(resultado);
    }

    @Test
    void validarCedula_conCedulaVacia_retornaFalse() {
        // Act
        boolean resultado = servicio.validarCedula("");

        // Assert
        assertFalse(resultado);
    }

    @Test
    void validarCedula_conMenosDeNueveCaracteres_retornaFalse() {
        // Act: 8 digitos
        boolean resultado = servicio.validarCedula("12345678");

        // Assert
        assertFalse(resultado);
    }

    @Test
    void validarCedula_conMasDeNueveCaracteres_retornaFalse() {
        // Act: 10 digitos
        boolean resultado = servicio.validarCedula("1234567890");

        // Assert
        assertFalse(resultado);
    }
    
    @Test
    void validarCedula_conLetra_retornaFalse() {
        // Act: 9 caracteres, pero el ultimo es una letra
        boolean resultado = servicio.validarCedula("12345678A");

        // Assert
        assertFalse(resultado);
    }

    @Test
    void validarCedula_conNueveDigitos_retornaTrue() {
        // Act
        boolean resultado = servicio.validarCedula("208880123");

        // Assert
        assertTrue(resultado);
    }

    @Test
    void calcularMontoFinal_conSubtotalNegativo_lanzaError() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularMontoFinal(-1.0, 130.0, 0.0));
    }

    @Test
    void calcularMontoFinal_conImpuestoNegativo_lanzaError() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularMontoFinal(1000.0, -1.0, 0.0));
    }
}