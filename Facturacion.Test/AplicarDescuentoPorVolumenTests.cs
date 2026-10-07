using System;
using Facturacion;
using Xunit;

namespace Facturacion.Test
{
    public class AplicarDescuentoPorVolumenTests
    {
        private readonly FacturacionService _service = new FacturacionService();

        [Fact]
        public void AplicarDescuentoPorVolumen_SubtotalNegativo_LanzaArgumentException()
        {
            Assert.Throws<ArgumentException>(() => _service.AplicarDescuentoPorVolumen(-100m, 15));
        }

        [Fact]
        public void AplicarDescuentoPorVolumen_CantidadArticulosNegativa_LanzaArgumentException()
        {
            Assert.Throws<ArgumentException>(() => _service.AplicarDescuentoPorVolumen(100m, -1));
        }

        [Theory]
        [InlineData(0)]
        [InlineData(9)]
        public void AplicarDescuentoPorVolumen_CantidadMenorA10_RetornaCero(int cantidadArticulos)
        {
            var resultado = _service.AplicarDescuentoPorVolumen(100m, cantidadArticulos);

            Assert.Equal(0.0m, resultado);
        }

        [Theory]
        [InlineData(10)]
        [InlineData(19)]
        public void AplicarDescuentoPorVolumen_CantidadEntre10y19_RetornaCincoPorciento(int cantidadArticulos)
        {
            var resultado = _service.AplicarDescuentoPorVolumen(100m, cantidadArticulos);

            Assert.Equal(5.0m, resultado);
        }

        [Theory]
        [InlineData(20)]
        [InlineData(50)]
        public void AplicarDescuentoPorVolumen_Cantidad20OMas_RetornaDiezPorciento(int cantidadArticulos)
        {
            var resultado = _service.AplicarDescuentoPorVolumen(100m, cantidadArticulos);

            Assert.Equal(10.0m, resultado);
        }
    }
}
