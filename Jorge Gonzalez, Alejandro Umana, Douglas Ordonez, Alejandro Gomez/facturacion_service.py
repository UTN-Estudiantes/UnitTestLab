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
            """RF-02: Calcula el impuesto aplicando la tasa al subtotal."""
            if subtotal < 0:
                raise ValueError("El subtotal no puede ser negativo.")
            if tasa < 0 or tasa > 1.0:
                raise ValueError("La tasa de impuesto debe estar entre 0.0 y 1.0.")

            return subtotal * tasa
            
    def aplicar_descuento_por_volumen(self, subtotal: float, cantidad_articulos: int) -> float:
            """RF-03: Calcula el descuento según la cantidad total de artículos."""
            if subtotal < 0 or cantidad_articulos < 0:
                raise ValueError("El subtotal y la cantidad de artículos no pueden ser negativos.")

            if cantidad_articulos >= 20:
                return subtotal * 0.10
            elif cantidad_articulos >= 10:
                return subtotal * 0.05

            return 0.0
        
    def validar_cedula(self, cedula: Optional[str]) -> bool:
            """RF-04: Valida si una cédula costarricense consta de exactamente 9 dígitos numéricos."""
            if not cedula or len(cedula) != 9:
                return False

            return cedula.isascii() and cedula.isdigit()
    
    def calcular_monto_final(self, subtotal: float, impuesto: float, descuento: float) -> float:
        raise NotImplementedError()