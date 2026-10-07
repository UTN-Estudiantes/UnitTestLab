package main.java;
import java.util.List;

public class FacturacionService {
    
    private static final int UMBRAL_DESCUENTO_BAJO = 10;
    private static final int UMBRAL_DESCUENTO_ALTO = 20;
    private static final double DESCUENTO_BAJO = 0.05;
    private static final double DESCUENTO_ALTO = 0.10;
    private static final int LONGITUD_CEDULA = 9;
    
    // RF-01
    public double calcularSubtotal(List<ItemFactura> items) {
        if (items == null) {
            throw new IllegalArgumentException("La lista de items no puede ser nula");
        }
        double subtotal = 0.0;
        for (ItemFactura item : items) {
            validarItem(item);
            subtotal += item.getPrecioUnitario() * item.getCantidad();
        }
        return subtotal;
    }
    
    private void validarItem(ItemFactura item) {
        if (item.getPrecioUnitario() < 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");
        }
        if (item.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
    }


    // RF-02
    public double calcularImpuesto(double subtotal, double tasa) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        if (tasa < 0) {
            throw new IllegalArgumentException("La tasa no puede ser negativa");
        }
        if (tasa > 1) {
            throw new IllegalArgumentException("La tasa no puede ser mayor a 1");
        }
        return subtotal * tasa;
    }    
    
 
    // RF-03
    public double aplicarDescuentoPorVolumen(double subtotal, int cantidadArticulos) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        if (cantidadArticulos < 0) {
            throw new IllegalArgumentException("La cantidad de articulos no puede ser negativa");
        }
        if (cantidadArticulos >= UMBRAL_DESCUENTO_ALTO) {
            return subtotal * DESCUENTO_ALTO;
        }
        if (cantidadArticulos >= UMBRAL_DESCUENTO_BAJO) {
            return subtotal * DESCUENTO_BAJO;
        }
        return 0.0;
    }
    

    // RF-04
    public boolean validarCedula(String cedula) {
        if (cedula == null || cedula.length() != LONGITUD_CEDULA) {
            return false;
        }
        for (char c : cedula.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    
  
    // RF-05
    public double calcularMontoFinal(double subtotal, double impuesto, double descuento) {
        throw new UnsupportedOperationException();
    }
}