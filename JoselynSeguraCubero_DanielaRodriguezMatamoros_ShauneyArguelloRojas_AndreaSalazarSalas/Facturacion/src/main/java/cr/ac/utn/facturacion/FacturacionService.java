package cr.ac.utn.facturacion;

import java.util.List;

public class FacturacionService {

    public double calcularSubtotal(List<Item> items) {
        if (items == null) {
            throw new IllegalArgumentException("La lista de items no puede ser nula");
        }
        if (items.isEmpty()) {
            return 0.0;
        }
        double subtotal = 0.0;
        for (Item item : items) {
            if (item.getPrecioUnitario() < 0) {
                throw new IllegalArgumentException("El precio unitario no puede ser negativo");
            }
            if (item.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
            }
            subtotal += item.getPrecioUnitario() * item.getCantidad();
        }
        return subtotal;
    }

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
        if (subtotal == 0) {
            return 0.0;
        }
        return subtotal * tasa;
    }

    public double aplicarDescuentoPorVolumen(double subtotal, int cantidadArticulos) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        if (cantidadArticulos < 0) {
            throw new IllegalArgumentException("La cantidad de articulos no puede ser negativa");
        }
        if (cantidadArticulos < 10) {
            return 0.0;
        }
        if (cantidadArticulos <= 19) {
            return subtotal * 0.05;
        }
        return subtotal * 0.10;
    }

    public boolean validarCedula(String cedula) {
        if (cedula == null) {
            return false;
        }
        if (cedula.isEmpty()) {
            return false;
        }
        if (cedula.length() < 9) {
            return false;
        }
        if (cedula.length() > 9) {
            return false;
        }
        for (char c : cedula.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public double calcularMontoFinal(double subtotal, double impuesto, double descuento) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        if (impuesto < 0) {
            throw new IllegalArgumentException("El impuesto no puede ser negativo");
        }
        if (descuento < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo");
        }
        throw new UnsupportedOperationException("No implementado");
    }
}
