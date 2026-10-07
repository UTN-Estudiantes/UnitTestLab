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


@pytest.mark.parametrize("cantidad", [0, -1])
def test_calcular_subtotal_con_cantidad_invalida_lanza_error(servicio, cantidad):
    items = [Item(precio_unitario=10.0, cantidad=cantidad)]
    with pytest.raises(ValueError):
        servicio.calcular_subtotal(items)


def test_calcular_subtotal_con_lista_valida_retorna_suma(servicio):
    items = [
        Item(precio_unitario=10.0, cantidad=2),
        Item(precio_unitario=5.5, cantidad=3),
    ]
    assert servicio.calcular_subtotal(items) == 36.5


# =============================================================================
# RF-02 calcular_impuesto
# =============================================================================

def test_calcular_impuesto_con_subtotal_negativo_lanza_error(servicio):
    # Arrange
    subtotal = -100.0
    tasa = 0.13

    # Act
    with pytest.raises(ValueError) as error:
        servicio.calcular_impuesto(subtotal, tasa)

    # Assert
    assert "subtotal" in str(error.value).lower()


def test_calcular_impuesto_con_tasa_negativa_lanza_error(servicio):
    # Arrange
    subtotal = 1000.0
    tasa = -0.13

    # Act
    with pytest.raises(ValueError) as error:
        servicio.calcular_impuesto(subtotal, tasa)

    # Assert
    assert "tasa" in str(error.value).lower()


def test_calcular_impuesto_con_tasa_mayor_a_uno_lanza_error(servicio):
    # Arrange
    subtotal = 1000.0
    tasa = 1.01

    # Act
    with pytest.raises(ValueError) as error:
        servicio.calcular_impuesto(subtotal, tasa)

    # Assert
    assert "tasa" in str(error.value).lower()
