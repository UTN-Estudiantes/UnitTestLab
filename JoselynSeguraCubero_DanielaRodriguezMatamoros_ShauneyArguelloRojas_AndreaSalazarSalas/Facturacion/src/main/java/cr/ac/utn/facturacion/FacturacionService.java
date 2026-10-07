package cr.ac.utn.facturacion;

import java.util.List;

public class FacturacionService {

    public double calcularSubtotal(List<Item> items) {
        if (items == null) {
            throw new IllegalArgumentException("La lista de items no puede ser nula");
        }
        double subtotal = 0.0;
        for (Item item : items) {
            validarNoNegativo(item.getPrecioUnitario(), "precio unitario");
            if (item.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
            }
            subtotal += item.getPrecioUnitario() * item.getCantidad();
        }
        return subtotal;
    }

    public double calcularImpuesto(double subtotal, double tasa) {
        validarNoNegativo(subtotal, "subtotal");
        validarNoNegativo(tasa, "tasa");
        if (tasa > 1) {
            throw new IllegalArgumentException("La tasa no puede ser mayor a 1");
        }
        return subtotal * tasa;
    }

    public double aplicarDescuentoPorVolumen(double subtotal, int cantidadArticulos) {
        validarNoNegativo(subtotal, "subtotal");
        validarNoNegativo(cantidadArticulos, "cantidad de articulos");
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
        if (cedula.length() != 9) {
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
        validarNoNegativo(subtotal, "subtotal");
        validarNoNegativo(impuesto, "impuesto");
        validarNoNegativo(descuento, "descuento");
        if (descuento > subtotal + impuesto) {
            throw new IllegalArgumentException("El descuento no puede ser mayor que subtotal + impuesto");
        }
        return subtotal + impuesto - descuento;
    }

    private void validarNoNegativo(double valor, String nombre) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor de " + nombre + " no puede ser negativo");
        }
    }
}
