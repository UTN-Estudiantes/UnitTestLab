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

