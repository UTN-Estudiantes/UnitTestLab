using System;
using System.Collections.Generic;
using Facturacion;
using Xunit;

namespace Facturacion.Test
{
    public class CalcularSubtotalTests
    {
        private readonly FacturacionService _service = new FacturacionService();

        [Fact]
        public void CalcularSubtotal_ListaNula_LanzaArgumentNullException()
        {
            Assert.Throws<ArgumentNullException>(() => _service.CalcularSubtotal(null));
        }

        [Fact]
        public void CalcularSubtotal_ListaVacia_RetornaCero()
        {
            var resultado = _service.CalcularSubtotal(new List<ItemFactura>());

            Assert.Equal(0.0m, resultado);
        }

        [Fact]
        public void CalcularSubtotal_PrecioUnitarioNegativo_LanzaArgumentException()
        {
            var items = new List<ItemFactura>
            {
                new ItemFactura { PrecioUnitario = -10m, Cantidad = 1 }
            };

            Assert.Throws<ArgumentException>(() => _service.CalcularSubtotal(items));
        }

        [Theory]
        [InlineData(0)]
        [InlineData(-1)]
        public void CalcularSubtotal_CantidadCeroONegativa_LanzaArgumentException(int cantidad)
        {
            var items = new List<ItemFactura>
            {
                new ItemFactura { PrecioUnitario = 10m, Cantidad = cantidad }
            };

            Assert.Throws<ArgumentException>(() => _service.CalcularSubtotal(items));
        }

        [Fact]
        public void CalcularSubtotal_VariosItemsValidos_RetornaSumaDePrecioPorCantidad()
        {
            var items = new List<ItemFactura>
            {
                new ItemFactura { PrecioUnitario = 10m, Cantidad = 2 },
                new ItemFactura { PrecioUnitario = 5m, Cantidad = 4 },
                new ItemFactura { PrecioUnitario = 2.5m, Cantidad = 1 }
            };

            var resultado = _service.CalcularSubtotal(items);

            Assert.Equal(42.5m, resultado);
        }
    }
}
