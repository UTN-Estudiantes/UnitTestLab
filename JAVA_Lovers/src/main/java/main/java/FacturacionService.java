package main.java;
import java.util.List;
public class FacturacionService {
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
}