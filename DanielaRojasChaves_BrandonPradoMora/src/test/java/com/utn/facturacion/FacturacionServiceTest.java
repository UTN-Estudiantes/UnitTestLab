package com.utn.facturacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias de FacturacionService, escritas con TDD.
 * Todas son funcionales (sin mocks): los 5 metodos son logica pura.
 */
class FacturacionServiceTest {

    private FacturacionService servicio;

    @BeforeEach
    void setUp() {
        // Se crea un servicio nuevo antes de CADA prueba:
        // asi las pruebas son independientes entre si.
        servicio = new FacturacionService();
    }
}