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
