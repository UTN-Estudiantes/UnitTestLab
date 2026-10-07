using Facturacion;
using Xunit;

namespace Facturacion.Test
{
    public class ValidarCedulaTests
    {
        private readonly FacturacionService _service = new FacturacionService();

        [Fact]
        public void ValidarCedula_Nula_RetornaFalse()
        {
            var resultado = _service.ValidarCedula(null);

            Assert.False(resultado);
        }

        [Fact]
        public void ValidarCedula_Vacia_RetornaFalse()
        {
            var resultado = _service.ValidarCedula(string.Empty);

            Assert.False(resultado);
        }

        [Theory]
        [InlineData("1234567")]
        public void ValidarCedula_MenosDeNueveCaracteres_RetornaFalse(string cedula)
        {
            var resultado = _service.ValidarCedula(cedula);

            Assert.False(resultado);
        }

        [Theory]
        [InlineData("1234567890")]
        public void ValidarCedula_MasDeNueveCaracteres_RetornaFalse(string cedula)
        {
            var resultado = _service.ValidarCedula(cedula);

            Assert.False(resultado);
        }

        [Theory]
        [InlineData("12345678A")]
        [InlineData("12345 678")]
        [InlineData("123-45678")]
        public void ValidarCedula_NueveCaracteresConCaracterNoNumerico_RetornaFalse(string cedula)
        {
            var resultado = _service.ValidarCedula(cedula);

            Assert.False(resultado);
        }

        [Theory]
        [InlineData("123456789")]
        public void ValidarCedula_NueveDigitos_RetornaTrue(string cedula)
        {
            var resultado = _service.ValidarCedula(cedula);

            Assert.True(resultado);
        }
    }
}
