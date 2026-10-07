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
}
