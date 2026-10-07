from typing import List, Optional

class Item:
    def __init__(self, precio_unitario: float, cantidad: int):
        self.precio_unitario = precio_unitario
        self.cantidad = cantidad
class FacturacionService:

    def calcular_subtotal(self, items: Optional[List[Item]]) -> float:
            """RF-01: Calcula el subtotal multiplicando precio x cantidad por cada ítem."""
            if items is None:
                raise ValueError("La lista de ítems no puede ser nula.")

            subtotal = 0.0
            for item in items:
                if item.precio_unitario < 0:
                    raise ValueError("El precio unitario no puede ser negativo.")
                if item.cantidad <= 0:
                    raise ValueError("La cantidad debe ser mayor a cero.")
                subtotal += item.precio_unitario * item.cantidad

            return subtotal
    def calcular_impuesto(self, subtotal: float, tasa: float) -> float:
        raise NotImplementedError()
    
    def aplicar_descuento_por_volumen(self, subtotal: float, cantidad_articulos: int) -> float:
        raise NotImplementedError()
    
    def validar_cedula(self, cedula: Optional[str]) -> bool:
        raise NotImplementedError()
    
    def calcular_monto_final(self, subtotal: float, impuesto: float, descuento: float) -> float:
        raise NotImplementedError()