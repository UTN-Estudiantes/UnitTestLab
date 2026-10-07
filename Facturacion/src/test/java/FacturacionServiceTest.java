import com.utn.Facturacion.Facturacion;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    
    //RF-04 — validarCedula(cedula)
    @Nested
    class EsCedulaValida {
        
        @Test
        void cedulaNula_devuelveFalse(){
            //Arrege
            String cedula = null;
            //Act
            boolean resultado = Facturacion.esCedulaValida(cedula);
            //Assert
            assertFalse(resultado);
        }
        
        @Test
        void cedulaVacia_devuelveFalse(){
            //Arrege
            String cedula = "";
            //Act
            boolean resultado = Facturacion.esCedulaValida(cedula);
            //Assert
            assertFalse(resultado);
        }
        
        @ParameterizedTest
        @ValueSource(strings = {"1", "1234", "12345678"})
        void menosDe9Caracteres_devuelveFalse(String cedula){
            
            //Act
            boolean resultado = Facturacion.esCedulaValida(cedula);
            //Assert
            assertFalse(resultado);
        }
        
        @ParameterizedTest
        @ValueSource(strings = {"1234567890", "12345678901234"})
        void masDe9Caracteres_devuelveFalse(String cedula){
            
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
        void nueveCaracteresNoNumericos_devuelveFalse(String cedula){
            
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
