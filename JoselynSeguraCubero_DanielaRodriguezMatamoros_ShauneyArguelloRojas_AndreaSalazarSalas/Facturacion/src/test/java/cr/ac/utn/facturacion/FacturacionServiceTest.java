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
    void calcularSubtotal_conListaNula_lanzaError() {
        // Arrange
        List<Item> items = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularSubtotal(items));
    }
}

