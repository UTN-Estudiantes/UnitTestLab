/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cr.ac.utn.facturacion;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FacturacionServiceTest {

    private FacturacionService service;

    @BeforeEach
    void setUp() {
        service = new FacturacionService();
    }

    // ===================== RF-01 calcularSubtotal =====================

    @Test
    public double calcularSubtotal(List<Item> items) {
    if (items == null) {
        throw new IllegalArgumentException("La lista de items no puede ser nula");
    }
    throw new UnsupportedOperationException("No implementado");
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
}

