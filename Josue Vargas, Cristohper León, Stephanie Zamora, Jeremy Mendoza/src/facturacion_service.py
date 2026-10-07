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
        pass

    def calcular_impuesto(self, subtotal: float, tasa: float) -> float:
        """RF-02: Calcula el monto de impuesto (IVA) sobre un subtotal dada una tasa."""
        pass

    def aplicar_descuento_por_volumen(self, subtotal: float, cantidad_articulos: int) -> float:
        """RF-03: Calcula el monto del descuento por volumen de compra."""
        pass

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
