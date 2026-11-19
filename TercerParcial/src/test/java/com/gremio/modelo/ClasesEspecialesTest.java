// Pruebas de las ocho subclases: atributos especiales, dados, stats y mensajes oficiales.
package com.gremio.modelo;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Verifica el contrato polimórfico de todas las clases del gremio. */
class ClasesEspecialesTest {

    /** Stats fijas: FUE 16, DES 14, CON 13, INT 12, SAB 11, CAR 10. */
    private static final int[] STATS = {16, 14, 13, 12, 11, 10};

    private static void aplicarStats(Aventurero a) {
        a.setEstadisticas(STATS);
    }

    @Test
    void guerreroConArmaduraPesadaTieneCA16() {
        Guerrero g = new Guerrero("Conan", 5, 60, true);
        aplicarStats(g);
        assertTrue(g.isArmaduraPesada());
        assertEquals(16, g.calcularCA());
        assertEquals(6, g.getDadoDano());
        assertEquals("fue", g.getStatPrincipal());
        assertEquals("⚔️", g.getIcono());
        assertEquals("Guerrero", g.getDescripcionClase());
    }

    @Test
    void guerreroSinArmaduraUsaLaDestreza() {
        Guerrero g = new Guerrero("Conan", 1, 10, false);
        aplicarStats(g);
        assertEquals(12, g.calcularCA());
        assertEquals("Armadura Pesada: No", g.getDescripcionAtributo());
    }

    @Test
    void constructorDeterministaDeGuerreroPermiteCalcularCA() {
        Guerrero g = new Guerrero("Conan", 1, 20, true, STATS);
        assertEquals(16, g.calcularCA());
        assertEquals(5, g.getBonoAtaque());
    }

    @Test
    void magoUsaD4EInteligencia() {
        Mago m = new Mago("Merlín", 3, 24, 7);
        aplicarStats(m);
        assertEquals(4, m.getDadoDano());
        assertEquals("int", m.getStatPrincipal());
        assertEquals(7, m.getEspaciosConjuro());
        assertEquals(12, m.calcularCA());
        assertEquals("🔮", m.getIcono());
        assertThrows(IllegalArgumentException.class, () -> new Mago("X", 1, 10, -1));
    }

    @Test
    void clerigoUsaD4YSabiduria() {
        Clerigo c = new Clerigo("Claudio", 3, 24, "Pelor");
        aplicarStats(c);
        assertEquals(4, c.getDadoDano());
        assertEquals("sab", c.getStatPrincipal());
        assertEquals("Pelor", c.getDiosVenerado());
        assertEquals(12, c.calcularCA());
        assertEquals("✨", c.getIcono());
        assertThrows(IllegalArgumentException.class, () -> new Clerigo("X", 1, 10, "  "));
    }

    @Test
    void monjeSumaSabiduriaALaCA() {
        Monje m = new Monje("Bruce", 5, 40, 5);
        aplicarStats(m);
        assertEquals(6, m.getDadoDano());
        assertEquals("des", m.getStatPrincipal());
        assertEquals(12, m.calcularCA());
        assertEquals(5, m.getPuntosKi());
        assertEquals("🥋", m.getIcono());
        assertThrows(IllegalArgumentException.class, () -> new Monje("X", 1, 10, -1));
    }

    @Test
    void paladinConFuerzaAltaUsaArmaduraPesada() {
        Paladin p = new Paladin("Arturo", 5, 50, 16);
        aplicarStats(p);
        assertTrue(p.isArmaduraPesada());
        assertEquals(16, p.calcularCA());
        assertEquals(6, p.getDadoDano());
        assertEquals("fue", p.getStatPrincipal());
        assertEquals("🛡️", p.getIcono());
        assertEquals("Paladín", p.getDescripcionClase());
    }

    @Test
    void paladinConFuerzaBajaNoUsaArmaduraPesada() {
        Paladin p = new Paladin("Arturo", 1, 10, 12);
        aplicarStats(p);
        assertFalse(p.isArmaduraPesada());
        assertEquals(12, p.calcularCA());
        p.setPuntosFuerza(15);
        assertTrue(p.isArmaduraPesada());
        assertEquals(16, p.calcularCA());
    }

    @Test
    void exploradorUsaD6YDestreza() {
        Explorador e = new Explorador("Robin", 5, 40, "Bosque");
        aplicarStats(e);
        assertEquals(6, e.getDadoDano());
        assertEquals("des", e.getStatPrincipal());
        assertEquals("Bosque", e.getTerrenoFavorecido());
        assertEquals(12, e.calcularCA());
        assertEquals("🏹", e.getIcono());
        assertThrows(IllegalArgumentException.class, () -> new Explorador("X", 1, 10, ""));
    }

    @Test
    void picaroUsaD6YDestreza() {
        Picaro p = new Picaro("Loki", 5, 40, true);
        aplicarStats(p);
        assertEquals(6, p.getDadoDano());
        assertEquals("des", p.getStatPrincipal());
        assertTrue(p.isHerramientasLadron());
        assertEquals("🗡️", p.getIcono());
        assertEquals("Pícaro", p.getDescripcionClase());
        p.setHerramientasLadron(false);
        assertEquals("Herramientas de ladrón: No", p.getDescripcionAtributo());
    }

    @Test
    void bardoUsaD4YCarisma() {
        Bardo b = new Bardo("Orfeo", 5, 30, "Laúd");
        aplicarStats(b);
        assertEquals(4, b.getDadoDano());
        assertEquals("car", b.getStatPrincipal());
        assertEquals("Laúd", b.getInstrumentoMusical());
        assertEquals(12, b.calcularCA());
        assertEquals("🎵", b.getIcono());
        assertThrows(IllegalArgumentException.class, () -> new Bardo("X", 1, 10, null));
    }

    @Test
    void losOchoMensajesDeAccionSonLosDelExamen() {
        List<Aventurero> gremio = Arrays.asList(
                new Guerrero("G", 1, 10, true),
                new Mago("M", 1, 10, 1),
                new Clerigo("C", 1, 10, "Dios"),
                new Monje("N", 1, 10, 1),
                new Paladin("P", 1, 10, 16),
                new Explorador("E", 1, 10, "Bosque"),
                new Picaro("I", 1, 10, true),
                new Bardo("B", 1, 10, "Laúd"));

        String[] esperados = {
            "El Guerrero G usa Oleada de Acción para realizar múltiples ataques físicos continuos.",
            "El Mago M gasta un espacio de conjuro para lanzar una poderosa Bola de Fuego.",
            "El Clérigo C usa Canalizar Divinidad para curar a sus aliados y expulsar muertos vivientes.",
            "El Monje N gasta puntos de Ki para ejecutar una Ráfaga de Golpes y un Ataque Aturdidor.",
            "El Paladín P consume magia para imbuir su arma con un Castigo Divino de daño radiante.",
            "El Explorador E aplica Marca del Cazador sobre el objetivo para maximizar su daño sostenido.",
            "El Pícaro I aprovecha la ventaja táctica para asestar un Ataque Furtivo masivo y se reposiciona.",
            "El Bardo B toca su instrumento para otorgar Inspiración Bárdica a un aliado."
        };

        assertEquals(8, gremio.size());
        for (int i = 0; i < gremio.size(); i++) {
            assertEquals(esperados[i], gremio.get(i).ejecutarAccionCombate());
        }
    }

    @Test
    void losOchoDadosYStatPrimariosCoincidenConLaTabla() {
        List<Aventurero> gremio = Arrays.asList(
                new Guerrero("G", 1, 10), new Mago("M", 1, 10),
                new Clerigo("C", 1, 10), new Monje("N", 1, 10),
                new Paladin("P", 1, 10), new Explorador("E", 1, 10),
                new Picaro("I", 1, 10), new Bardo("B", 1, 10));
        int[] dados = {6, 4, 4, 6, 6, 6, 6, 4};
        String[] stats = {"fue", "int", "sab", "des", "fue", "des", "des", "car"};

        for (int i = 0; i < gremio.size(); i++) {
            assertEquals(dados[i], gremio.get(i).getDadoDano(), gremio.get(i).getDescripcionClase());
            assertEquals(stats[i], gremio.get(i).getStatPrincipal(), gremio.get(i).getDescripcionClase());
        }
    }

    @Test
    void sobrecargasDeSubirNivelFuncionanEnSubclases() {
        Guerrero g = new Guerrero("Conan", 3, 40, true);
        g.subirNivel();
        assertEquals(4, g.getNivel());
        assertEquals(50, g.getPvMaximos());
        g.subirNivel(2);
        assertEquals(6, g.getNivel());
        assertEquals(70, g.getPvMaximos());
        assertEquals(3, g.getCompetencia());
    }

    @Test
    void lineaDeRegistroIncluyeElNombreDeClaseConTilde() {
        Clerigo clerigo = new Clerigo("Lucía", 1, 10, "Pelor");
        Picaro picaro = new Picaro("Loki", 1, 10, true);
        aplicarStats(clerigo);
        aplicarStats(picaro);
        assertEquals("Clérigo[Lucía, Nv.1, 10PV, CA 12]", clerigo.getLineaRegistro());
        assertEquals("Paladín[Arturo, Nv.1, 10PV, CA 16]",
                new Paladin("Arturo", 1, 10, 16).getLineaRegistro());
        assertEquals("Pícaro[Loki, Nv.1, 10PV, CA 12]", picaro.getLineaRegistro());
    }

    @Test
    void fabricasDeMonstruosDevuelvenLosValoresDelSimulador() {
        Monstruo goblin = Monstruo.crearGoblin();
        Monstruo ogro = Monstruo.crearOgro();
        Monstruo dragon = Monstruo.crearDragon();

        assertEquals("Goblin", goblin.getNombre());
        assertEquals(20, goblin.getHpMaximos());
        assertEquals(12, goblin.getCa());
        assertEquals("Fácil", goblin.getDificultad());
        assertEquals("👺", goblin.getIcono());

        assertEquals(50, ogro.getHpMaximos());
        assertEquals(14, ogro.getCa());
        assertEquals("Medio", ogro.getDificultad());
        assertEquals("👹", ogro.getIcono());

        assertEquals(100, dragon.getHpMaximos());
        assertEquals(16, dragon.getCa());
        assertEquals("Difícil", dragon.getDificultad());
        assertEquals("🐉", dragon.getIcono());
    }

    @Test
    void elHpDelMonstruoNuncaSaleDeRango() {
        Monstruo goblin = Monstruo.crearGoblin();
        goblin.setHp(999);
        assertEquals(20, goblin.getHp());
        goblin.setHp(-4);
        assertEquals(0, goblin.getHp());
        assertFalse(goblin.estaVivo());
    }
}
