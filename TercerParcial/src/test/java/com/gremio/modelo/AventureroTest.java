// Pruebas unitarias de la clase abstracta: constructores, sobrecarga, reglas y validaciones.
package com.gremio.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Suite de pruebas del modelo base Aventurero (sin GUI). */
class AventureroTest {

    /** Stats deterministas: FUE 16, DES 14, CON 13, INT 12, SAB 11, CAR 10. */
    private static final int[] STATS = {16, 14, 13, 12, 11, 10};

    /** Subclase concreta mínima para poder instanciar la abstracta en las pruebas. */
    private static class AventureroDePrueba extends Aventurero {

        AventureroDePrueba() {
            super();
        }

        AventureroDePrueba(String nombre, int nivel) {
            super(nombre, nivel);
        }

        AventureroDePrueba(String nombre, int nivel, int pv) {
            super(nombre, nivel, pv);
        }

        AventureroDePrueba(String nombre, int nivel, int pv, int[] estadisticas) {
            super(nombre, nivel, pv, estadisticas);
        }

        @Override
        public int getDadoDano() {
            return 6;
        }

        @Override
        public String getStatPrincipal() {
            return "fue";
        }

        @Override
        public String getIcono() {
            return "🧪";
        }

        @Override
        public String getDescripcionAtributo() {
            return "Atributo de prueba";
        }

        @Override
        public String ejecutarAccionCombate() {
            return "Acción de prueba";
        }
    }

    @Test
    void constructorVacioCreaValoresPorDefecto() {
        Aventurero a = new AventureroDePrueba();
        assertNotNull(a);
        assertEquals("Aventurero", a.getNombre());
        assertEquals(1, a.getNivel());
        assertEquals(10, a.getPvMaximos());
        assertEquals(10, a.getHpActual());
    }

    @Test
    void constructorNombreNivelUsaPvPorDefecto() {
        Aventurero a = new AventureroDePrueba("Thor", 5);
        assertEquals("Thor", a.getNombre());
        assertEquals(5, a.getNivel());
        assertEquals(10, a.getPvMaximos());
    }

    @Test
    void constructorCompletoInicializaEstado() {
        Aventurero a = new AventureroDePrueba("Merlín", 7, 88);
        assertEquals("Merlín", a.getNombre());
        assertEquals(7, a.getNivel());
        assertEquals(88, a.getPvMaximos());
        assertEquals(88, a.getHpActual());
        assertTrue(a.estaVivo());
    }

    @Test
    void constructorGeneraSeisEstadisticas() {
        Aventurero a = new AventureroDePrueba("Thor", 1, 10);
        assertEquals(6, a.getEstadisticas().length);
        assertTrue(a.getStatValor("fue") >= 3 && a.getStatValor("fue") <= 18);
    }

    @Test
    void estadisticasInyectadasSeLeenPorClave() {
        Aventurero a = new AventureroDePrueba("Thor", 1, 10, STATS);
        assertEquals(16, a.getStatValor("fue"));
        assertEquals(14, a.getStatValor("DES"));
        assertEquals(10, a.getStatValor("car"));
    }

    @Test
    void modificadorSigueLaFormulaDnd() {
        assertEquals(0, Aventurero.modificador(10));
        assertEquals(0, Aventurero.modificador(11));
        assertEquals(1, Aventurero.modificador(12));
        assertEquals(-1, Aventurero.modificador(9));
        assertEquals(5, Aventurero.modificador(20));
    }

    @Test
    void bonoAtaqueUsaCompetenciaMasStatPrimaria() {
        Aventurero a = new AventureroDePrueba("Thor", 5, 100, STATS);
        assertEquals(3, a.getCompetencia());
        assertEquals(6, a.getBonoAtaque());
    }

    @Test
    void subirNivelSinArgumentoSumaUno() {
        Aventurero a = new AventureroDePrueba("Thor", 4, 100, STATS);
        a.subirNivel();
        assertEquals(5, a.getNivel());
        assertEquals(110, a.getPvMaximos());
        assertEquals(110, a.getHpActual());
    }

    @Test
    void subirNivelConCantidadSumaCinco() {
        Aventurero a = new AventureroDePrueba("Thor", 5, 100, STATS);
        a.subirNivel(5);
        assertEquals(10, a.getNivel());
        assertEquals(150, a.getPvMaximos());
    }

    @Test
    void subirNivelNuncaSuperaVeinte() {
        Aventurero a = new AventureroDePrueba("Thor", 19, 100, STATS);
        a.subirNivel(5);
        assertEquals(20, a.getNivel());
        assertEquals(110, a.getPvMaximos());
    }

    @Test
    void subirNivelConCeroLanzaExcepcion() {
        Aventurero a = new AventureroDePrueba("Thor", 1, 10, STATS);
        assertThrows(IllegalArgumentException.class, () -> a.subirNivel(0));
    }

    @Test
    void constructorRechazaNombreVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> new AventureroDePrueba("   ", 1, 10));
    }

    @Test
    void constructorRechazaNivelFueraDeRango() {
        assertThrows(IllegalArgumentException.class,
                () -> new AventureroDePrueba("Thor", 0, 10));
        assertThrows(IllegalArgumentException.class,
                () -> new AventureroDePrueba("Thor", 21, 10));
    }

    @Test
    void constructorRechazaPvNoPositivos() {
        assertThrows(IllegalArgumentException.class,
                () -> new AventureroDePrueba("Thor", 1, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new AventureroDePrueba("Thor", 1, -5));
    }

    @Test
    void recibirDanoYCurarActualizanHp() {
        Aventurero a = new AventureroDePrueba("Thor", 1, 30, STATS);
        a.recibirDano(12);
        assertEquals(18, a.getHpActual());
        a.curar();
        assertEquals(30, a.getHpActual());
        a.recibirDano(999);
        assertEquals(0, a.getHpActual());
        assertTrue(!a.estaVivo());
    }

    @Test
    void toStringIncluyeDatosPrincipales() {
        Aventurero a = new AventureroDePrueba("Thor", 5, 100, STATS);
        String texto = a.toString();
        assertTrue(texto.contains("Thor"));
        assertTrue(texto.contains("Nv.5"));
        assertTrue(texto.contains("CA 12"));
        assertTrue(texto.contains("d6"));
    }

    @Test
    void lineaRegistroSigueElFormatoDelExamen() {
        Aventurero a = new AventureroDePrueba("Thor", 5, 100, STATS);
        assertEquals("AventureroDePrueba[Thor, Nv.5, 100PV, CA 12]", a.getLineaRegistro());
    }

    @Test
    void tirada4d6DropLowestSiempreEntre3Y18() {
        for (int i = 0; i < 200; i++) {
            int resultado = Aventurero.tirar4d6DropLowest();
            assertTrue(resultado >= 3 && resultado <= 18,
                    "Valor fuera de rango: " + resultado);
        }
    }
}
