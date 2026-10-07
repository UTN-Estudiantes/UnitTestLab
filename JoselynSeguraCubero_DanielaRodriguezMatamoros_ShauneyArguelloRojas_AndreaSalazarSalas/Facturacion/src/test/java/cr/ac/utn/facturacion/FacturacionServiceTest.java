package cr.ac.utn.facturacion;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FacturacionServiceTest {

    private static final double DELTA = 0.0001;
    private FacturacionService service;

    @BeforeEach
    void setUp() {
        service = new FacturacionService();
    }

    // ===================== RF-01 calcularSubtotal =====================

    @Test
    void calcularSubtotal_conListaNula_lanzaError() {
        // Arrange
        List<Item> items = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_conListaVacia_retornaCero() {
        // Arrange
        List<Item> items = new ArrayList<>();

        // Act
        double resultado = service.calcularSubtotal(items);

        // Assert
        assertEquals(0.0, resultado, DELTA);
    }

    @Test
    void calcularSubtotal_conPrecioNegativo_lanzaError() {
        // Arrange
        List<Item> items = List.of(new Item(-100.0, 2));

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_conCantidadCero_lanzaError() {
        // Arrange
        List<Item> items = List.of(new Item(100.0, 0));

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_conCantidadNegativa_lanzaError() {
        // Arrange
        List<Item> items = List.of(new Item(100.0, -3));

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_conVariosItems_sumaCorrectamente() {
        // Arrange
        List<Item> items = List.of(
                new Item(1000.0, 2),
                new Item(500.0, 3),
                new Item(250.0, 4));

        // Act
        double resultado = service.calcularSubtotal(items);

        // Assert
        assertEquals(4500.0, resultado, DELTA);
    }

    // ===================== RF-02 calcularImpuesto =====================

    @Test
    void calcularImpuesto_conSubtotalNegativo_lanzaError() {
        // Arrange
        double subtotal = -1000.0;
        double tasa = 0.13;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularImpuesto(subtotal, tasa));
    }

    @Test
    void calcularImpuesto_conTasaNegativa_lanzaError() {
        // Arrange
        double subtotal = 1000.0;
        double tasa = -0.13;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularImpuesto(subtotal, tasa));
    }

    @Test
    void calcularImpuesto_conTasaMayorAUno_lanzaError() {
        // Arrange
        double subtotal = 1000.0;
        double tasa = 1.01;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularImpuesto(subtotal, tasa));
    }

    @Test
    void calcularImpuesto_conSubtotalCero_retornaCero() {
        // Arrange
        double subtotal = 0.0;
        double tasa = 0.13;

        // Act
        double resultado = service.calcularImpuesto(subtotal, tasa);

        // Assert
        assertEquals(0.0, resultado, DELTA);
    }

    @Test
    void calcularImpuesto_conTasaIVA_retornaTrecePorciento() {
        // Arrange
        double subtotal = 10000.0;
        double tasa = 0.13;

        // Act
        double resultado = service.calcularImpuesto(subtotal, tasa);

        // Assert
        assertEquals(1300.0, resultado, DELTA);
    }

    // ================ RF-03 aplicarDescuentoPorVolumen ================

    @Test
    void aplicarDescuentoPorVolumen_conSubtotalNegativo_lanzaError() {
        // Arrange
        double subtotal = -1000.0;
        int cantidadArticulos = 15;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.aplicarDescuentoPorVolumen(subtotal, cantidadArticulos));
    }

    @Test
    void aplicarDescuentoPorVolumen_conCantidadNegativa_lanzaError() {
        // Arrange
        double subtotal = 1000.0;
        int cantidadArticulos = -5;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.aplicarDescuentoPorVolumen(subtotal, cantidadArticulos));
    }

    @Test
    void aplicarDescuentoPorVolumen_conNueveArticulos_retornaCero() {
        // Arrange
        double subtotal = 10000.0;
        int cantidadArticulos = 9;

        // Act
        double resultado = service.aplicarDescuentoPorVolumen(subtotal, cantidadArticulos);

        // Assert
        assertEquals(0.0, resultado, DELTA);
    }

    @Test
    void aplicarDescuentoPorVolumen_conDiezArticulos_retornaCincoPorciento() {
        // Arrange
        double subtotal = 10000.0;
        int cantidadArticulos = 10;

        // Act
        double resultado = service.aplicarDescuentoPorVolumen(subtotal, cantidadArticulos);

        // Assert
        assertEquals(500.0, resultado, DELTA);
    }

    @Test
    void aplicarDescuentoPorVolumen_conVeinteArticulos_retornaDiezPorciento() {
        // Arrange
        double subtotal = 10000.0;
        int cantidadArticulos = 20;

        // Act
        double resultado = service.aplicarDescuentoPorVolumen(subtotal, cantidadArticulos);

        // Assert
        assertEquals(1000.0, resultado, DELTA);
    }

    // ===================== RF-04 validarCedula =====================

    @Test
    void validarCedula_conNula_retornaFalse() {
        // Arrange
        String cedula = null;

        // Act
        boolean resultado = service.validarCedula(cedula);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void validarCedula_conVacia_retornaFalse() {
        // Arrange
        String cedula = "";

        // Act
        boolean resultado = service.validarCedula(cedula);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void validarCedula_conMenosDeNueveCaracteres_retornaFalse() {
        // Arrange
        String cedula = "12345678";

        // Act
        boolean resultado = service.validarCedula(cedula);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void validarCedula_conMasDeNueveCaracteres_retornaFalse() {
        // Arrange
        String cedula = "1234567890";

        // Act
        boolean resultado = service.validarCedula(cedula);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void validarCedula_conNueveCaracteresNoNumericos_retornaFalse() {
        // Arrange
        String conLetra = "12345678A";
        String conEspacio = "1234 5678";
        String conGuion = "1-2345678";

        // Act
        boolean resultadoLetra = service.validarCedula(conLetra);
        boolean resultadoEspacio = service.validarCedula(conEspacio);
        boolean resultadoGuion = service.validarCedula(conGuion);

        // Assert
        assertAll(
                () -> assertFalse(resultadoLetra),
                () -> assertFalse(resultadoEspacio),
                () -> assertFalse(resultadoGuion));
    }

    @Test
    void validarCedula_conNueveDigitos_retornaTrue() {
        // Arrange
        String cedula = "123456789";

        // Act
        boolean resultado = service.validarCedula(cedula);

        // Assert
        assertTrue(resultado);
    }

    // ===================== RF-05 calcularMontoFinal =====================

    @Test
    void calcularMontoFinal_conSubtotalNegativo_lanzaError() {
        // Arrange
        double subtotal = -1000.0;
        double impuesto = 130.0;
        double descuento = 0.0;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularMontoFinal(subtotal, impuesto, descuento));
    }

    @Test
    void calcularMontoFinal_conImpuestoNegativo_lanzaError() {
        // Arrange
        double subtotal = 1000.0;
        double impuesto = -130.0;
        double descuento = 0.0;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularMontoFinal(subtotal, impuesto, descuento));
    }

    @Test
    void calcularMontoFinal_conDescuentoNegativo_lanzaError() {
        // Arrange
        double subtotal = 1000.0;
        double impuesto = 130.0;
        double descuento = -50.0;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularMontoFinal(subtotal, impuesto, descuento));
    }
}
