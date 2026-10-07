import unittest
import pytest
from facturacion_service import FacturacionService, Item

class TestFacturacionService(unittest.TestCase):
    def setUp(self):
        self.service = FacturacionService()


# PRUEBAS RF-01 — calcularSubtotal(items)

    def test_lista_nula_lanza_value_error(self):
        with self.assertRaises(ValueError):
            self.service.calcular_subtotal(None)

    def test_lista_vacia_retorna_cero(self):
        self.assertEqual(self.service.calcular_subtotal([]), 0.0)

    def test_precio_unitario_negativo_lanza_error(self):
        items = [Item(-10.0, 2)]
        with self.assertRaises(ValueError):
            self.service.calcular_subtotal(items)

    def test_precio_negativo_en_algun_item_lanza_error(self):
        items = [Item(10.0, 1), Item(-5.0, 3), Item(20.0, 2)]
        with self.assertRaises(ValueError):
            self.service.calcular_subtotal(items)

    def test_cantidad_cero_lanza_error(self):
        items = [Item(10.0, 0)]
        with self.assertRaises(ValueError):
            self.service.calcular_subtotal(items)

    def test_cantidad_negativa_lanza_error(self):
        items = [Item(10.0, -1)]
        with self.assertRaises(ValueError):
            self.service.calcular_subtotal(items)

    def test_cantidad_invalida_en_algun_item_lanza_error(self):
        items = [Item(10.0, 1), Item(5.0, 0)]
        with self.assertRaises(ValueError):
            self.service.calcular_subtotal(items)

    def test_un_solo_item_valido(self):
        items = [Item(10.0, 3)]
        self.assertAlmostEqual(self.service.calcular_subtotal(items), 30.0)

    def test_varios_items_validos_suma_precio_por_cantidad(self):
        items = [Item(10.0, 2), Item(5.5, 4), Item(100.0, 1)]
        # 20.0 + 22.0 + 100.0
        self.assertAlmostEqual(self.service.calcular_subtotal(items), 142.0)

    def test_precio_cero_es_valido(self):
        items = [Item(0.0, 5), Item(8.0, 2)]
        self.assertAlmostEqual(self.service.calcular_subtotal(items), 16.0)


#PRUEBAS RF-02 — calcularImpuesto(subtotal, tasa)
#[RED]
    def test_calcular_impuesto_con_subtotal_negativo_debe_lanzar_error(servicio):
        #Arange
        subtotal = -1.0
        tasa = 0.13

        #Act y Assert
        with pytest.raises(ValueError):
            servicio.calcular_impuesto(subtotal, tasa)

    def test_calcular_impuesto_con_tasa_negativa_debe_lanzar_error(servicio):
        # Arrange
        subtotal = 100.0
        tasa = 1.01

        #Act y Assert
        with pytest.raises(ValueError):
            servicio.calcular_impuesto(subtotal, tasa)

    def test_calcular_impuesto_con_tasa_mayor_a_uno_lanza_error(servicio):
        #Arrange
        subtotal = 100.0
        tasa = 1.01
        with pytest.raises(ValueError):
            servicio.calcular_impuesto(subtotal, tasa)

    def test_calcular_impuesto_con_subtotal_cero_retorna_cero(servicio):
        #Arrange
        subtotal = 0.0
        tasa = 0.13

        #Act
        resultado = servicio.calcular_impuesto(subtotal, tasa)

        #Assert
        resultado == 0.0

    def test_calcular_impuesto_con_valores_validos_retorna_subtotal_por_tasa(servicio):
        #Arrange
        subtotal = 1000.0
        tasa = 0.13

        #Act
        resultado = servicio.calcular_impuesto(subtotal, tasa)

        #Assert
        assert resultado == pytest.approx(130.0)


    def test_calcular_impuesto_con_tasa_exactamente_uno_es_valida(servicio):
        #Arrange
        subtotal = 200.0
        tasa = 1.0

        #Act
        resultado = servicio.calcular_impuesto(subtotal, tasa)

        #Assert
        assert resultado == pytest.approx(200.0)

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

# PRUEBAS RF-04 — validarCedula(cedula)

    def test_validar_cedula_con_nueve_digitos(self):
        cedula = "208710996"
        resultado= self.service.validar_cedula(cedula)
        self.assertTrue(resultado)

 
    def test_validar_cedula_con_cedula_nula(self):
        cedula = None
        resultado = self.service.validar_cedula(cedula)
        self.assertFalse(resultado)


    def validar_cedula_con_cadena_vacia(self):
        cedula = ""
        resultado = self.service.validar_cedula(cedula)
        self.assertFalse(resultado)

 
    def test_validar_cedula_con_menos_de_nueve_caracteres(self):
        cedula = "208710996"
        resultado = self.service.validar_cedula(cedula)
        self.assertFalse(resultado)

 
    def test_validar_cedula_con_mas_de_nueve_caracteres(self):
        cedula = "2087109965"
        resultado = self.service.validar_cedula(cedula)
        self.assertFalse(resultado)


    def test_validar_cedula_con_letras(self):
        cedula = "208710996ALEJANDRO"
        resultado = self.service.validar_cedula(cedula)
        self.assertFalse(resultado)

    def test_validar_cedula_con_espacio(self):
        cedula = "20871 0996"
        resultado= self.service.validar_cedula(cedula)
        self.assertFalse(resultado)

    def test_validar_cedula_con_digitos_unicode(self):
        cedula = "\u0661\u0662\u0663\u0664\u0665\u0666\u0667\u0668\u0669"
        resultado = self.service.validar_cedula(cedula)
        self.assertFalse(resultado)

        

