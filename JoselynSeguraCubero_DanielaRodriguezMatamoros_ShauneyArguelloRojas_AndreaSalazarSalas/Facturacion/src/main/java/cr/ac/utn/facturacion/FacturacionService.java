package cr.ac.utn.facturacion;

import java.util.List;

public class FacturacionService {

    public double calcularSubtotal(List<Item> items) {
        if (items == null) {
            throw new IllegalArgumentException("La lista de items no puede ser nula");
        }
        throw new UnsupportedOperationException("No implementado");
    }

    public double calcularImpuesto(double subtotal, double tasa) {
        throw new UnsupportedOperationException("No implementado");
    }

    public double aplicarDescuentoPorVolumen(double subtotal, int cantidadArticulos) {
        throw new UnsupportedOperationException("No implementado");
    }

    public boolean validarCedula(String cedula) {
        throw new UnsupportedOperationException("No implementado");
    }

    public double calcularMontoFinal(double subtotal, double impuesto, double descuento) {
        throw new UnsupportedOperationException("No implementado");
    }
}
