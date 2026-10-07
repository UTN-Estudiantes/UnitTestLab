from typing import List, Optional

class Item:
    def __init__(self, precio_unitario: float, cantidad: int):
        self.precio_unitario = precio_unitario
        self.cantidad = cantidad
class FacturacionService:

    def calcular_subtotal(self, items: Optional[List[Item]]) -> float:
        raise NotImplementedError()
    
    def calcular_impuesto(self, subtotal: float, tasa: float) -> float:
        raise NotImplementedError()
    
    def aplicar_descuento_por_volumen(self, subtotal: float, cantidad_articulos: int) -> float:
        raise NotImplementedError()
    
    def validar_cedula(self, cedula: Optional[str]) -> bool:
        raise NotImplementedError()
    
    def calcular_monto_final(self, subtotal: float, impuesto: float, descuento: float) -> float:
        raise NotImplementedError()