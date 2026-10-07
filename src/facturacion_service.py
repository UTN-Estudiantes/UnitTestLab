from dataclasses import dataclass
from typing import List, Optional


# ---------------------------------------------------------------------------
# Modelos de datos
# ---------------------------------------------------------------------------

@dataclass
class Item:
    precio_unitario: float
    cantidad: int

# ---------------------------------------------------------------------------
# Servicio principal
# ---------------------------------------------------------------------------

class FacturacionService:

    # --- RF-01 ---------------------------------------------------------
    def calcular_subtotal(self, items: Optional[List[Item]]) -> float:
        if items is None:
            raise ValueError("La lista de items no puede ser None")
        if len(items) == 0:
            return 0.0
        subtotal = 0.0
        for item in items:
            if item.precio_unitario < 0:
                raise ValueError("El precio unitario no puede ser negativo")
            if item.cantidad <= 0:
                raise ValueError("La cantidad debe ser mayor que 0")
            subtotal += item.precio_unitario * item.cantidad
        return subtotal

    # --- RF-02 ---------------------------------------------------------
    def calcular_impuesto(self, subtotal: float, tasa: float) -> float:
        if subtotal < 0:
            raise ValueError("El subtotal no puede ser negativo")
        if tasa < 0:
            raise ValueError("La tasa no puede ser negativa")

    # --- RF-03 ---------------------------------------------------------
    def aplicar_descuento_por_volumen(self, subtotal: float, cantidad_articulos: int) -> float:
        pass

    # --- RF-04 ---------------------------------------------------------
    def validar_cedula(self, cedula: Optional[str]) -> bool:
        pass
    # --- RF-05 ---------------------------------------------------------
    def calcular_monto_final(self, subtotal: float, impuesto: float, descuento: float) -> float:
        pass