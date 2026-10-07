import pytest
from facturacion_service import FacturacionService, Item

@pytest.fixture
def servicio():
    return FacturacionService()


# =============================================================================
# RF-01 calcular_subtotal
# =============================================================================

def test_calcular_subtotal_con_lista_nula_lanza_error(servicio):
    items = None
    with pytest.raises(ValueError):
        servicio.calcular_subtotal(items)


def test_calcular_subtotal_con_lista_vacia_retorna_cero(servicio):
    items = []
    assert servicio.calcular_subtotal(items) == 0.0


def test_calcular_subtotal_con_precio_negativo_lanza_error(servicio):
    items = [Item(precio_unitario=-1.0, cantidad=1)]
    with pytest.raises(ValueError):
        servicio.calcular_subtotal(items)
