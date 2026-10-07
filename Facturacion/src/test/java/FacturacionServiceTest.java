
import com.utn.Facturacion.Facturacion;
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
