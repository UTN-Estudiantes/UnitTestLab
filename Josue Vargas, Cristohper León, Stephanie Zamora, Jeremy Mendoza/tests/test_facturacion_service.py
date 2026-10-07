"""
Pruebas Unitarias - Calculadora de Facturación
==============================================
ISW-622 Pruebas de Software - Semana 4
Universidad Técnica Nacional (UTN)
"""

import pytest

from src.facturacion_service import FacturacionService, ItemFactura


# =============================================================================
# RF-01: Cálculo del subtotal (Funcional)
# =============================================================================

def test_calcular_subtotal_con_lista_nula_lanza_value_error():
    """RF-01: Si la lista de items es None, debe lanzar ValueError."""
    # Arrange
    servicio = FacturacionService()

    # Act & Assert
    with pytest.raises(ValueError, match="La lista de items no puede ser nula"):
        servicio.calcular_subtotal(items=None)


def test_calcular_subtotal_con_lista_vacia_retorna_cero():
    """RF-01: Si la lista de items está vacía, el subtotal debe ser 0.0."""
    # Arrange
    servicio = FacturacionService()

    # Act
    subtotal = servicio.calcular_subtotal(items=[])

    # Assert
    assert subtotal == 0.0


def test_calcular_subtotal_con_precio_negativo_lanza_value_error():
    """RF-01: Si algún item tiene precioUnitario negativo, debe lanzar ValueError."""
    # Arrange
    servicio = FacturacionService()
    items = [ItemFactura(precio_unitario=-10.0, cantidad=2)]

    # Act & Assert
    with pytest.raises(ValueError, match="El precio unitario no puede ser negativo"):
        servicio.calcular_subtotal(items=items)


def test_calcular_subtotal_con_cantidad_cero_o_negativa_lanza_value_error():
    """RF-01: Si algún item tiene cantidad menor o igual a 0, debe lanzar ValueError."""
    # Arrange
    servicio = FacturacionService()
    items_cero = [ItemFactura(precio_unitario=10.0, cantidad=0)]
    items_negativo = [ItemFactura(precio_unitario=10.0, cantidad=-1)]

    # Act & Assert
    with pytest.raises(ValueError, match="La cantidad del item debe ser mayor a cero"):
        servicio.calcular_subtotal(items=items_cero)

    with pytest.raises(ValueError, match="La cantidad del item debe ser mayor a cero"):
        servicio.calcular_subtotal(items=items_negativo)


def test_calcular_subtotal_con_un_solo_item_retorna_subtotal():
    """RF-01: Lista con un único item calcula precio_unitario * cantidad."""
    # Arrange
    servicio = FacturacionService()
    items = [ItemFactura(precio_unitario=1500.0, cantidad=2)]

    # Act
    subtotal = servicio.calcular_subtotal(items=items)

    # Assert
    assert subtotal == 3000.0


def test_calcular_subtotal_con_varios_items_suma_correctamente():
    """RF-01: Lista con múltiples items suma correctamente el total."""
    # Arrange
    servicio = FacturacionService()
    items = [
        ItemFactura(precio_unitario=1500.0, cantidad=2),
        ItemFactura(precio_unitario=500.0, cantidad=3),
        ItemFactura(precio_unitario=200.0, cantidad=1),
    ]

    # Act
    subtotal = servicio.calcular_subtotal(items=items)

    # Assert
    assert subtotal == 4700.0






# =============================================================================
# RF-02: Cálculo de impuesto (Funcional)
# =============================================================================

def test_calcular_impuesto_con_subtotal_negativo_lanza_value_error():
    """RF-02: Si el subtotal es negativo, debe lanzar ValueError."""
    # Arrange
    servicio = FacturacionService()

    # Act & Assert
    with pytest.raises(ValueError, match="El subtotal no puede ser negativo"):
        servicio.calcular_impuesto(subtotal=-100.0, tasa=0.13)


def test_calcular_impuesto_con_tasa_negativa_lanza_value_error():
    """RF-02: Si la tasa es negativa, debe lanzar ValueError."""
    # Arrange
    servicio = FacturacionService()

    # Act & Assert
    with pytest.raises(ValueError, match="La tasa de impuesto no puede ser negativa"):
        servicio.calcular_impuesto(subtotal=1000.0, tasa=-0.05)


def test_calcular_impuesto_con_tasa_mayor_a_uno_lanza_value_error():
    """RF-02: Si la tasa es mayor a 1 (>100%), debe lanzar ValueError."""
    # Arrange
    servicio = FacturacionService()

    # Act & Assert
    with pytest.raises(ValueError, match="La tasa de impuesto no puede ser mayor a 1"):
        servicio.calcular_impuesto(subtotal=1000.0, tasa=1.5)


def test_calcular_impuesto_con_subtotal_cero_retorna_cero():
    """RF-02: Con subtotal = 0, el impuesto debe ser 0.0 sin importar la tasa."""
    # Arrange
    servicio = FacturacionService()

    # Act
    impuesto = servicio.calcular_impuesto(subtotal=0.0, tasa=0.13)

    # Assert
    assert impuesto == 0.0


def test_calcular_impuesto_con_valores_validos_calcula_correctamente():
    """RF-02: Con subtotal y tasa válidos, calcula subtotal * tasa."""
    # Arrange
    servicio = FacturacionService()

    # Act
    impuesto = servicio.calcular_impuesto(subtotal=10000.0, tasa=0.13)

    # Assert
    assert impuesto == 1300.0


# =============================================================================
# RF-03: Descuento por volumen de compra (Funcional)
# =============================================================================

def test_aplicar_descuento_con_subtotal_negativo_lanza_value_error():
    """RF-03: Si el subtotal es negativo, debe lanzar ValueError."""
    # Arrange
    servicio = FacturacionService()

    # Act & Assert
    with pytest.raises(ValueError, match="El subtotal no puede ser negativo"):
        servicio.aplicar_descuento_por_volumen(subtotal=-500.0, cantidad_articulos=15)


def test_aplicar_descuento_con_cantidad_negativa_lanza_value_error():
    """RF-03: Si la cantidad de artículos es negativa, debe lanzar ValueError."""
    # Arrange
    servicio = FacturacionService()

    # Act & Assert
    with pytest.raises(ValueError, match="La cantidad de articulos no puede ser negativa"):
        servicio.aplicar_descuento_por_volumen(subtotal=1000.0, cantidad_articulos=-5)


def test_aplicar_descuento_con_menos_de_diez_articulos_retorna_cero():
    """RF-03: Si cantidadArticulos < 10, el descuento es 0.0."""
    # Arrange
    servicio = FacturacionService()

    # Act
    descuento = servicio.aplicar_descuento_por_volumen(subtotal=10000.0, cantidad_articulos=9)

    # Assert
    assert descuento == 0.0


def test_aplicar_descuento_entre_diez_y_diecinueve_articulos_aplica_cinco_por_ciento():
    """RF-03: Si cantidadArticulos está entre 10 y 19, aplica 5% del subtotal."""
    # Arrange
    servicio = FacturacionService()

    # Act & Assert
    assert servicio.aplicar_descuento_por_volumen(subtotal=10000.0, cantidad_articulos=10) == 500.0
    assert servicio.aplicar_descuento_por_volumen(subtotal=10000.0, cantidad_articulos=19) == 500.0


def test_aplicar_descuento_con_veinte_o_mas_articulos_aplica_diez_por_ciento():
    """RF-03: Si cantidadArticulos >= 20, aplica 10% del subtotal."""
    # Arrange
    servicio = FacturacionService()

    # Act & Assert
    assert servicio.aplicar_descuento_por_volumen(subtotal=10000.0, cantidad_articulos=20) == 1000.0
    assert servicio.aplicar_descuento_por_volumen(subtotal=10000.0, cantidad_articulos=50) == 1000.0


# =============================================================================
# RF-04: Validación de cédula física costarricense (Funcional)
# =============================================================================

def test_validar_cedula_con_none_retorna_false():
    """RF-04: Si cedula es None, debe devolver False sin lanzar error."""
    # Arrange
    servicio = FacturacionService()

    # Act
    resultado = servicio.validar_cedula(cedula=None)

    # Assert
    assert resultado is False


def test_validar_cedula_con_cadena_vacia_retorna_false():
    """RF-04: Si cedula es cadena vacía, debe devolver False."""
    # Arrange
    servicio = FacturacionService()

    # Act
    resultado = servicio.validar_cedula(cedula="")

    # Assert
    assert resultado is False
