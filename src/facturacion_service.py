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


    # --- RF-02 ---------------------------------------------------------
    def calcular_impuesto(self, subtotal: float, tasa: float) -> float:
        pass
    # --- RF-03 ---------------------------------------------------------
    def aplicar_descuento_por_volumen(self, subtotal: float, cantidad_articulos: int) -> float:
        pass

    # --- RF-04 ---------------------------------------------------------
    def validar_cedula(self, cedula: Optional[str]) -> bool:
        pass
    # --- RF-05 ---------------------------------------------------------
    def calcular_monto_final(self, subtotal: float, impuesto: float, descuento: float) -> float:
        pass