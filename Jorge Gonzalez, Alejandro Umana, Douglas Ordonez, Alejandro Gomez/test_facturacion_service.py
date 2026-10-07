import unittest
from facturacion_service import FacturacionService, Item

class TestFacturacionService(unittest.TestCase):
    def setUp(self):
        self.service = FacturacionService()



# PRUEBAS RF-03 — aplicarDescuentoPorVolumen(subtotal, cantidadArticulos)
    def test_aplicarDescuentoPorVolumen_conSubtotalOCantidadNegativa_lanzaError(self):
        with self.assertRaises(ValueError):
            self.service.aplicar_descuento_por_volumen(subtotal=-100.0, cantidad_articulos = 15)
        with self.assertRaises(ValueError):
            self.service.aplicar_descuento_por_volumen(subtotal=100.0, cantidad_articulos = -5 )

    def test_aplicarDescuentoPorVolumen_conMenosDe10Articulos_retornaCero(self):
        # arrange

        subtotal = 10000.0
        cantidad = 9
        
        # act
        descuento = self.service.aplicar_descuento_por_volumen(subtotal, cantidad)

        # assert
        self.assertEqual(descuento, 0.0)

    def test_aplicarDescuentoPorVolumen_con10a19Articulos_retornaCincoPorCiento(self):
        # arrange
        subtotal = 10000.0
        cantidad = 10

        # act
        descuento = self.service.aplicar_descuento_por_volumen(subtotal, cantidad)
        # assert
        self.assertEqual(descuento, 500.0)

    def test_aplicarDescuentoPorVolumen_con19Articulos_retornaCincoPorCiento(self):
        # arrange
        subtotal = 10000.0
        cantidad = 19

        # act
        descuento = self.service.aplicar_descuento_por_volumen(subtotal, cantidad)
        # assert
        self.assertEqual(descuento, 500.0)

    def test_aplicarDescuentoPorVolumen_con20oMasArticulos_retornaDiezPorCiento(self):
            # arrange
        subtotal = 10000.0
        cantidad = 20

        # act
        descuento = self.service.aplicar_descuento_por_volumen(subtotal, cantidad)
        # assert
        self.assertEqual(descuento, 1000.0)