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


def test_calcular_impuesto_con_subtotal_cero_retorna_cero(servicio):
    # Arrange
    subtotal = 0.0
    tasa = 0.13

    # Act
    resultado = servicio.calcular_impuesto(subtotal, tasa)

    # Assert
    assert resultado == 0.0


def test_calcular_impuesto_con_valores_validos_retorna_subtotal_por_tasa(servicio):
    # Arrange
    subtotal = 1000.0
    tasa = 0.13

    # Act
    resultado = servicio.calcular_impuesto(subtotal, tasa)

    # Assert
    assert resultado == pytest.approx(130.0)

# =============================================================================
# RF-03 aplicar_descuento_por_volumen
# =============================================================================

def test_aplicar_descuento_por_volumen_con_subtotal_negativo_lanza_error(servicio):
    # Arrange
    subtotal = -100.0
    cantidad_articulos = 10

    # Act
    with pytest.raises(ValueError) as error:
        servicio.aplicar_descuento_por_volumen(subtotal, cantidad_articulos)

    # Assert
    assert "subtotal" in str(error.value).lower()


def test_aplicar_descuento_por_volumen_con_cantidad_negativa_lanza_error(servicio):
    # Arrange
    subtotal = 1000.0
    cantidad_articulos = -1

    # Act
    with pytest.raises(ValueError) as error:
        servicio.aplicar_descuento_por_volumen(subtotal, cantidad_articulos)

    # Assert
    assert "cantidad" in str(error.value).lower()


def test_aplicar_descuento_por_volumen_con_20_articulos_retorna_diez_por_ciento(servicio):
    # Arrange
    subtotal = 1000.0
    cantidad_articulos = 20

    # Act
    resultado = servicio.aplicar_descuento_por_volumen(subtotal, cantidad_articulos)

    # Assert
    assert resultado == pytest.approx(100.0)


def test_aplicar_descuento_por_volumen_con_10_articulos_retorna_cinco_por_ciento(servicio):
    # Arrange
    subtotal = 1000.0
    cantidad_articulos = 10

    # Act
    resultado = servicio.aplicar_descuento_por_volumen(subtotal, cantidad_articulos)

    # Assert
    assert resultado == pytest.approx(50.0)


def test_aplicar_descuento_por_volumen_con_9_articulos_retorna_cero(servicio):
    # Arrange
    subtotal = 1000.0
    cantidad_articulos = 9

    # Act
    resultado = servicio.aplicar_descuento_por_volumen(subtotal, cantidad_articulos)

    # Assert
    assert resultado == 0.0


def test_aplicar_descuento_por_volumen_con_19_articulos_retorna_cinco_por_ciento(servicio):
    # Arrange
    subtotal = 1000.0
    cantidad_articulos = 19

    # Act
    resultado = servicio.aplicar_descuento_por_volumen(subtotal, cantidad_articulos)

    # Assert
    assert resultado == pytest.approx(50.0)

# =============================================================================
# RF-04 validar_cedula
# =============================================================================


def test_validar_cedula_nula_retorna_false(servicio):
    # Arrange
    cedula = None

    # Act
    resultado = servicio.validar_cedula(cedula)

    # Assert
    assert resultado is False


def test_validar_cedula_vacia_retorna_false(servicio):
    # Arrange
    cedula = ""

    # Act
    resultado = servicio.validar_cedula(cedula)

    # Assert
    assert resultado is False

