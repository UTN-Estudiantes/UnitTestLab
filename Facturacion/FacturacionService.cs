using System;
using System.Collections.Generic;

namespace Facturacion
{
    public class FacturacionService
    {
        public decimal CalcularSubtotal(List<ItemFactura> items)
        {
            if (items == null)
            {
                throw new ArgumentNullException(nameof(items));
            }

            decimal subtotal = 0.0m;

            foreach (var item in items)
            {
                if (item.PrecioUnitario < 0)
                {
                    throw new ArgumentException("El precio unitario no puede ser negativo.");
                }

                if (item.Cantidad <= 0)
                {
                    throw new ArgumentException("La cantidad debe ser mayor a 0.");
                }

                subtotal += item.PrecioUnitario * item.Cantidad;
            }

            return subtotal;
        }

        public decimal CalcularImpuesto(decimal subtotal, decimal tasa)
        {
            if (subtotal < 0)
            {
                throw new ArgumentException("El subtotal no puede ser negativo.");
            }

            if (tasa < 0)
            {
                throw new ArgumentException("La tasa no puede ser negativa.");
            }

            if (tasa > 1)
            {
                throw new ArgumentException("La tasa no puede ser mayor a 1.");
            }

            if (subtotal == 0)
            {
                return 0.0m;
            }

            return subtotal * tasa;
        }

        public decimal AplicarDescuentoPorVolumen(decimal subtotal, int cantidadArticulos)
        {
            if (subtotal < 0)
            {
                throw new ArgumentException("El subtotal no puede ser negativo.");
            }

            if (cantidadArticulos < 0)
            {
                throw new ArgumentException("La cantidad de artículos no puede ser negativa.");
            }

            if (cantidadArticulos < 10)
            {
                return 0.0m;
            }

            if (cantidadArticulos <= 19)
            {
                return subtotal * 0.05m;
            }

            return subtotal * 0.10m;
        }

        public bool ValidarCedula(string cedula)
        {
            if (cedula == null || cedula.Length != 9)
            {
                return false;
            }

            foreach (var caracter in cedula)
            {
                if (!char.IsDigit(caracter))
                {
                    return false;
                }
            }

            return true;
        }

        public decimal CalcularMontoFinal(decimal subtotal, decimal impuesto, decimal descuento)
        {
            if (subtotal < 0 || impuesto < 0 || descuento < 0)
            {
                throw new ArgumentException("Subtotal, impuesto y descuento no pueden ser negativos.");
            }

            if (descuento > subtotal + impuesto)
            {
                throw new ArgumentException("El descuento no puede ser mayor que lo que se debe pagar.");
            }

            return subtotal + impuesto - descuento;
        }
    }
}
