"""
Calculadora de Facturación - Lógica de Negocio
===============================================
ISW-622 Pruebas de Software - Laboratorio TDD (Semana 4)
Universidad Técnica Nacional (UTN)
"""

from dataclasses import dataclass
from typing import List, Optional, Union


# ---------------------------------------------------------------------------
# Modelos de datos
# ---------------------------------------------------------------------------

@dataclass
class ItemFactura:
    """Representa un item o línea de factura."""
    precio_unitario: float
    cantidad: int


# ---------------------------------------------------------------------------
# Servicio de Facturación
# ---------------------------------------------------------------------------

class FacturacionService:
    """Servicio que encapsula la lógica de facturación."""

    def calcular_subtotal(self, items: Optional[List[Union[ItemFactura, dict]]]) -> float:
        """RF-01: Calcula el subtotal de una factura sumando (precioUnitario x cantidad) de cada item."""
        if items is None:
            raise ValueError("La lista de items no puede ser nula")
        if len(items) == 0:
            return 0.0

        subtotal = 0.0
        for item in items:
            precio, cantidad = self._validar_item(item)
            subtotal += precio * cantidad

        return round(subtotal, 2)

    def _validar_item(self, item: Union[ItemFactura, dict]) -> tuple[float, int]:
        """Valida que un item tenga precio unitario y cantidad válidos."""
        precio = item.precio_unitario if hasattr(item, "precio_unitario") else item["precio_unitario"]
        cantidad = item.cantidad if hasattr(item, "cantidad") else item["cantidad"]
        if precio < 0:
            raise ValueError("El precio unitario no puede ser negativo")
        if cantidad <= 0:
            raise ValueError("La cantidad del item debe ser mayor a cero")
        return float(precio), int(cantidad)

    def calcular_impuesto(self, subtotal: float, tasa: float) -> float:
        """RF-02: Calcula el monto de impuesto (IVA) sobre un subtotal dada una tasa."""
        if subtotal < 0:
            raise ValueError("El subtotal no puede ser negativo")
        if tasa < 0:
            raise ValueError("La tasa de impuesto no puede ser negativa")
        if tasa > 1.0:
            raise ValueError("La tasa de impuesto no puede ser mayor a 1")
        if subtotal == 0.0:
            return 0.0
        return round(float(subtotal) * float(tasa), 2)

    def aplicar_descuento_por_volumen(self, subtotal: float, cantidad_articulos: int) -> float:
        """RF-03: Calcula el monto del descuento por volumen de compra."""
        if subtotal < 0:
            raise ValueError("El subtotal no puede ser negativo")
        if cantidad_articulos < 0:
            raise ValueError("La cantidad de articulos no puede ser negativa")

    def validar_cedula(self, cedula: Optional[str]) -> bool:
        """RF-04: Valida el formato de cédula física costarricense (9 dígitos numéricos)."""
        pass

    def calcular_monto_final(self, subtotal: float, impuesto: float, descuento: float) -> float:
        """RF-05: Calcula el monto final a pagar combinando subtotal, impuesto y descuento."""
        pass

    # Alias compatibles con camelCase según enunciado
    calcularSubtotal = calcular_subtotal
    calcularImpuesto = calcular_impuesto
    aplicarDescuentoPorVolumen = aplicar_descuento_por_volumen
    validarCedula = validar_cedula
    calcularMontoFinal = calcular_monto_final
