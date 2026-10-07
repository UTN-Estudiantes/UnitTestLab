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



