package test.java;
import java.util.ArrayList;
import java.util.List;
import main.java.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class FacturacionServiceTest {

    private static final double DELTA = 0.001;

    // RF-01 calcularSubtotal 

    @Test
    void calcularSubtotal_ConListaNula() {
        // Arrange
        FacturacionService servicio = new FacturacionService();
        List<ItemFactura> items = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_ConListaVacia_RetornaCero() {
        // Arrange
        FacturacionService servicio = new FacturacionService();
        List<ItemFactura> items = new ArrayList<>();

        // Act
        double subtotal = servicio.calcularSubtotal(items);

        // Assert
        assertEquals(0.0, subtotal, DELTA);
    }

    @Test
    void calcularSubtotal_ConPrecioNegativo() {
        // Arrange
        FacturacionService servicio = new FacturacionService();
        List<ItemFactura> items = List.of(new ItemFactura(-100.0, 2));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_ConCantidadCero() {
        // Arrange
        FacturacionService servicio = new FacturacionService();
        List<ItemFactura> items = List.of(new ItemFactura(100.0, 0));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_ConCantidadNegativa() {
        // Arrange
        FacturacionService servicio = new FacturacionService();
        List<ItemFactura> items = List.of(new ItemFactura(100.0, -3));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularSubtotal(items));
    }

    @Test
    void calcularSubtotal_ConUnItem_RetornaPrecioPorCantidad() {
        // Arrange
        FacturacionService servicio = new FacturacionService();
        List<ItemFactura> items = List.of(new ItemFactura(1500.0, 4));

        // Act
        double subtotal = servicio.calcularSubtotal(items);

        // Assert
        assertEquals(6000.0, subtotal, DELTA);
    }

    @Test
    void calcularSubtotal_ConVariosItems_SumaCorrectamente() {
        // Arrange
        FacturacionService servicio = new FacturacionService();
        List<ItemFactura> items = List.of(
                new ItemFactura(1000.0, 2),
                new ItemFactura(500.0, 3),
                new ItemFactura(250.5, 2));

        // Act
        double subtotal = servicio.calcularSubtotal(items);

        // Assert
        assertEquals(4001.0, subtotal, DELTA);
    }

    
    // RF-02 calcularImpuesto 

    @Test
    void calcularImpuesto_ConSubtotalNegativo() {
        // Arrange
        FacturacionService servicio = new FacturacionService();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularImpuesto(-100.0, 0.13));
    }

    @Test
    void calcularImpuesto_ConTasaNegativa() {
        // Arrange
        FacturacionService servicio = new FacturacionService();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularImpuesto(100.0, -0.13));
    }

    @Test
    void calcularImpuesto_ConTasaMayorAUno() {
        // Arrange
        FacturacionService servicio = new FacturacionService();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularImpuesto(100.0, 1.01));
    }

    @Test
    void calcularImpuesto_ConSubtotalCero_RetornaCero() {
        // Arrange
        FacturacionService servicio = new FacturacionService();

        // Act
        double impuesto = servicio.calcularImpuesto(0.0, 0.13);

        // Assert
        assertEquals(0.0, impuesto, DELTA);
    }

    @Test
    void calcularImpuesto_ConDatosValidos_RetornaSubtotalPorTasa() {
        // Arrange
        FacturacionService servicio = new FacturacionService();

        // Act
        double impuesto = servicio.calcularImpuesto(10000.0, 0.13);

        // Assert
        assertEquals(1300.0, impuesto, DELTA);
    }
}
