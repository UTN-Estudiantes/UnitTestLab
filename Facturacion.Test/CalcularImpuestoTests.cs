using System;
using Facturacion;
using Xunit;

namespace Facturacion.Test
{
    public class CalcularImpuestoTests
    {
        private readonly FacturacionService _service = new FacturacionService();

        [Fact]
        public void CalcularImpuesto_SubtotalNegativo_LanzaArgumentException()
        {
            Assert.Throws<ArgumentException>(() => _service.CalcularImpuesto(-10m, 0.13m));
        }

        [Fact]
        public void CalcularImpuesto_TasaNegativa_LanzaArgumentException()
        {
            Assert.Throws<ArgumentException>(() => _service.CalcularImpuesto(100m, -0.01m));
        }

        [Fact]
        public void CalcularImpuesto_TasaMayorAUno_LanzaArgumentException()
        {
            Assert.Throws<ArgumentException>(() => _service.CalcularImpuesto(100m, 1.01m));
        }

        [Theory]
        [InlineData(0.13)]
        [InlineData(1.0)]
        public void CalcularImpuesto_SubtotalCero_RetornaCeroSinImportarTasa(double tasa)
        {
            var resultado = _service.CalcularImpuesto(0m, (decimal)tasa);

            Assert.Equal(0.0m, resultado);
        }

        [Theory]
        [InlineData(100, 0.13, 13)]
        [InlineData(200, 0.10, 20)]
        public void CalcularImpuesto_SubtotalYTasaValidos_RetornaSubtotalPorTasa(double subtotal, double tasa, double esperado)
        {
            var resultado = _service.CalcularImpuesto((decimal)subtotal, (decimal)tasa);

            Assert.Equal((decimal)esperado, resultado);
        }
    }
}
