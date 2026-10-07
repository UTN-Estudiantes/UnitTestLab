# Calculadora de Facturación — Laboratorio TDD (Semana 4)

**Curso:** ISW-622 Pruebas de Software — III Cuatrimestre 2026  
**Institución:** Universidad Técnica Nacional (UTN)  
**Docente:** Jose David Carvajal Jiménez  

## Integrantes del Grupo
* Josue Vargas
* Cristohper León
* Stephanie Zamora
* Jeremy Mendoza

---

## Estructura del Proyecto

```
.
├── .gitignore
├── pytest.ini
├── README.md
├── src/
│   ├── __init__.py
│   └── facturacion_service.py
└── tests/
    ├── __init__.py
    └── test_facturacion_service.py
```

---

## Ejecución de Pruebas Unitarias

Para ejecutar la suite completa de 26 pruebas con `pytest`:

```bash
pytest tests/ -v
```

---

## Requerimientos Funcionales Cubiertos
* **RF-01:** `calcular_subtotal(items)` (6 pruebas unitarias) — *Josue Vargas*
* **RF-02:** `calcular_impuesto(subtotal, tasa)` (5 pruebas unitarias) — *Cristohper León*
* **RF-03:** `aplicar_descuento_por_volumen(subtotal, cantidad_articulos)` (5 pruebas unitarias) — *Stephanie Zamora*
* **RF-04:** `validar_cedula(cedula)` (6 pruebas unitarias) — *Jeremy Mendoza*
* **RF-05:** `calcular_monto_final(subtotal, impuesto, descuento)` (4 pruebas unitarias) — *Colaborativo*
