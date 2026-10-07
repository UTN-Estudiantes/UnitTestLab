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
    
}