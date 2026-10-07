package com.utn.Facturacion;

import com.utn.Facturacion.Facturacion;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import static org.junit.jupiter.api.Assertions.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ortiz
 */
public class FacturacionServiceTest {
    
    // RF-01 — calcularSubtotal(items)
     @Test
    void calcularSubtotal_conListaNula_lanzaError() {
        Facturacion service = new Facturacion();
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularSubtotal(null);
        });
    }
    
    @Test
    void calcularSubtotal_conListaVacia_retornaCero() {
        Facturacion service = new Facturacion();
        List<Item> itemsVacios = List.of(); 
        
        double subtotal = service.calcularSubtotal(itemsVacios);
        
        assertEquals(0.0, subtotal, 0.001);
    }
    
    @Test
    void calcularSubtotal_conItemsValidos_retornaSumaCorrecta() {
        Facturacion service = new Facturacion();
            List<Item> items = List.of(
            new Item("Producto 1", 10.0, 2), 
            new Item("Producto 2", 15.0, 3)  
        );
        
        double subtotal = service.calcularSubtotal(items);
        assertEquals(65.0, subtotal, 0.001);
    }
    
    @Test
    void calcularSubtotal_conPrecioNegativo_lanzaError() {
        Facturacion service = new Facturacion();
        
        List<Item> items = List.of(
            new Item("Producto con error", -10.0, 2)
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularSubtotal(items);
        });
    }
    
    @Test
    void calcularSubtotal_conCantidadInvalida_lanzaError() {
        Facturacion service = new Facturacion();
        
        List<Item> items = List.of(
            new Item("Producto", 10.0, 0) // Cantidad 0 (también puedes probar con -1)
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularSubtotal(items);
        });
    }
    
    ///////-----------------------------------------------

    // RF-02 — calcularImpuesto(subtotal, tasa)
    
    // Caso 1: subtotal negativo
    @Test
    public void calcularImpuesto_conSubtotalNegativo_lanzaError() {

        Facturacion service = new Facturacion();

        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularImpuesto(-100.0, 0.13);
        });
    }
    
    // Caso 2: tasa negativa
    @Test
    public void calcularImpuesto_conTasaNegativa_lanzaError() {

        Facturacion service = new Facturacion();

        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularImpuesto(100.0, -0.13);
        });
    }
    
    // Caso 3: tasa mayor a 1
    @Test
    public void calcularImpuesto_conTasaMayorAUno_lanzaError() {

        Facturacion service = new Facturacion();

        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularImpuesto(100.0, 1.10);
        });
    }

    // Caso 4: subtotal igual a cero
    @Test
    public void calcularImpuesto_conSubtotalCero_retornaCero() {

        Facturacion service = new Facturacion();

        double resultado = service.calcularImpuesto(0.0, 0.13);

        assertEquals(0.0, resultado);
    }

    // Caso 5: valores válidos
    @Test
    public void calcularImpuesto_conValoresValidos_calculaCorrectamente() {

        Facturacion service = new Facturacion();

        double resultado = service.calcularImpuesto(100.0, 0.13);

        assertEquals(13.0, resultado);
    }
   
    
    ///////-----------------------------------------------


    // RF-03: Subtotal negativo
    @Test
    void aplicarDescuentoPorVolumen_subtotalNegativo_lanzaError() {

        // Arrange
        Facturacion facturacion = new Facturacion();

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> facturacion.aplicarDescuentoPorVolumen(-100.0, 5)
        );
    }

    // RF-03: Cantidad de artículos negativa
    @Test
    void aplicarDescuentoPorVolumen_cantidadNegativa_lanzaError() {

        // Arrange
        Facturacion facturacion = new Facturacion();

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> facturacion.aplicarDescuentoPorVolumen(1000.0, -1)
        );
    }

    // RF-03: Menos de 10 artículos
    @Test
    void aplicarDescuentoPorVolumen_menosDe10_retornaCero() {

        // Arrange
        Facturacion facturacion = new Facturacion();

        // Act
        double resultado
                = facturacion.aplicarDescuentoPorVolumen(1000.0, 9);

        // Assert
        assertEquals(0.0, resultado, 0.001);
    }

    // RF-03: Entre 10 y 19 artículos
    @Test
    void aplicarDescuentoPorVolumen_entre10y19_aplicaCincoPorciento() {

        // Arrange
        Facturacion facturacion = new Facturacion();

        // Act
        double resultado
                = facturacion.aplicarDescuentoPorVolumen(1000.0, 15);

        // Assert
        assertEquals(50.0, resultado, 0.001);
    }

    // RF-03: 20 artículos o más
    @Test
    void aplicarDescuentoPorVolumen_20OMas_aplicaDiezPorciento() {

        // Arrange
        Facturacion facturacion = new Facturacion();

        // Act
        double resultado
                = facturacion.aplicarDescuentoPorVolumen(1000.0, 20);

        // Assert
        assertEquals(100.0, resultado, 0.001);
    }

    ///////-----------------------------------------------
 
    //RF-04 — validarCedula(cedula)
    @Nested
    class EsCedulaValida {

        @Test
        void cedulaNula_devuelveFalse() {
            //Arrege
            String cedula = null;
            //Act
            boolean resultado = Facturacion.esCedulaValida(cedula);
            //Assert
            assertFalse(resultado);
        }

        @Test
        void cedulaVacia_devuelveFalse() {
            //Arrege
            String cedula = "";
            //Act
            boolean resultado = Facturacion.esCedulaValida(cedula);
            //Assert
            assertFalse(resultado);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "1234", "12345678"})
        void menosDe9Caracteres_devuelveFalse(String cedula) {

            //Act
            boolean resultado = Facturacion.esCedulaValida(cedula);
            //Assert
            assertFalse(resultado);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1234567890", "12345678901234"})
        void masDe9Caracteres_devuelveFalse(String cedula) {

            //Act
            boolean resultado = Facturacion.esCedulaValida(cedula);
            //Assert
            assertFalse(resultado);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "12345678A", "A12345678", "1234a5678", "1234 5678",
            "1-234-567", "         ", "12345678\n", "١٢٣٤٥٦٧٨٩"
        })
        void nueveCaracteresNoNumericos_devuelveFalse(String cedula) {

            //Act
            boolean resultado = Facturacion.esCedulaValida(cedula);
            //Assert
            assertFalse(resultado);
        }

        @ParameterizedTest
        @ValueSource(strings = {"123456789", "000000000", "101110111", "999999999"})
        void nueveDigitos_devuelveTrue(String cedula) {

            // Act
            boolean resultado = Facturacion.esCedulaValida(cedula);

            // Assert
            assertTrue(resultado);
        }

        @Test
        void cedulaNula_nuncaLanzaExcepcion() {
            // Arrange
            Executable accion = () -> Facturacion.esCedulaValida(null);

            // Act + Assert
            assertDoesNotThrow(accion);
        }
    }
    
    
}
