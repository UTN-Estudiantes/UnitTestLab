using System;
using Facturacion;
using Xunit;

namespace Facturacion.Test
{
    public class CalcularMontoFinalTests
    {
        private readonly FacturacionService _service = new FacturacionService();

        [Theory]
        [InlineData(-100, 13, 10)]
        [InlineData(100, -13, 10)]
        [InlineData(100, 13, -10)]
        public void CalcularMontoFinal_AlgunValorNegativo_LanzaArgumentException(double subtotal, double impuesto, double descuento)
        {
            Assert.Throws<ArgumentException>(() => _service.CalcularMontoFinal((decimal)subtotal, (decimal)impuesto, (decimal)descuento));
        }

        [Fact]
        public void CalcularMontoFinal_DescuentoMayorQueSubtotalMasImpuesto_LanzaArgumentException()
        {
            Assert.Throws<ArgumentException>(() => _service.CalcularMontoFinal(100m, 13m, 114m));
        }

        [Fact]
        public void CalcularMontoFinal_ValoresValidos_RetornaSubtotalMasImpuestoMenosDescuento()
        {
            var resultado = _service.CalcularMontoFinal(100m, 13m, 10m);

            Assert.Equal(103.0m, resultado);
        }

        [Fact]
        public void CalcularMontoFinal_DescuentoIgualASubtotalMasImpuesto_RetornaCero()
        {
            var resultado = _service.CalcularMontoFinal(100m, 13m, 113m);

            Assert.Equal(0.0m, resultado);
        }
    }
}
