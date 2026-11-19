// Controlador de combate: iniciativa, turnos, ataque 3d6 y daño manual; sin Swing.
package com.gremio.controlador;

import com.gremio.modelo.Aventurero;
import com.gremio.modelo.Monstruo;
import com.gremio.modelo.RegistroGremio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/** Orquesta un combate por rondas con daño tirado manualmente por el usuario. */
public class CombateControlador {

    private static final int RONDAS_MAXIMAS = 5;

    private final List<Aventurero> aventureros;
    private final List<String> bitacora = new ArrayList<>();
    private final Random aleatorio = new Random();

    private Monstruo monstruo;
    private List<Aventurero> orden;
    private int turnoActual;
    private int ronda;
    private boolean activo;
    private boolean esperandoDano;
    private int claseDano;
    private int factorDano = 1;
    private Aventurero aventureroActual;
    private String estado = "Elige un monstruo y pulsa «Iniciar combate»";
    private String resultado;

    public CombateControlador(List<Aventurero> aventureros) {
        this.aventureros = new ArrayList<>(aventureros);
    }

    /** Resultado de una tirada de 3d6 con detección de crítico y pifia. */
    public static final class Tirada {

        private final int[] dados;
        private final int total;
        private final boolean critico;
        private final boolean pifia;

        private Tirada(int[] dados) {
            this.dados = dados.clone();
            this.total = dados[0] + dados[1] + dados[2];
            this.critico = dados[0] == 6 && dados[1] == 6 && dados[2] == 6;
            this.pifia = dados[0] == 1 && dados[1] == 1 && dados[2] == 1;
        }

        public int[] getDados() {
            return dados.clone();
        }

        public int getTotal() {
            return total;
        }

        public boolean isCritico() {
            return critico;
        }

        public boolean isPifia() {
            return pifia;
        }
    }

    /** Entrada de la tabla de iniciativa (jugador + total ordenado). */
    private static final class Iniciativa {

        private final Aventurero aventurero;
        private final int total;

        private Iniciativa(Aventurero aventurero, int total) {
            this.aventurero = aventurero;
            this.total = total;
        }
    }

    /** Nombres de los tres enemigos disponibles. */
    public static final String[] MONSTRUOS = {"Goblin", "Ogro", "Dragón"};

    /** Fábrica estática: la vista elige un nombre, el controlador crea el modelo. */
    public static Monstruo crearMonstruo(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("Debe elegir un monstruo.");
        }
        switch (nombre) {
            case "Goblin":
                return Monstruo.crearGoblin();
            case "Ogro":
                return Monstruo.crearOgro();
            case "Dragón":
                return Monstruo.crearDragon();
            default:
                throw new IllegalArgumentException("Monstruo desconocido: " + nombre);
        }
    }

    /** Tirada de tres dados de seis caras (pública para pruebas y vista). */
    public Tirada roll3d6() {
        int[] dados = new int[3];
        for (int i = 0; i < dados.length; i++) {
            dados[i] = aleatorio.nextInt(6) + 1;
        }
        return new Tirada(dados);
    }

    /**
     * Prepara el combate: HP del monstruo, iniciativa de todos los aventureros
     * (3d6 + mod DES) y orden de turnos. Comienza la ronda 1.
     */
    public void iniciarCombate(Monstruo enemigo) throws IOException {
        if (enemigo == null) {
            throw new IllegalArgumentException("Debe seleccionar un monstruo.");
        }
        if (aventureros.isEmpty()) {
            throw new IllegalStateException("Necesitas al menos un aventurero para combatir.");
        }
        if (activo) {
            throw new IllegalStateException("Ya hay un combate en curso.");
        }

        this.monstruo = enemigo;
        monstruo.setHp(monstruo.getHpMaximos());
        this.bitacora.clear();
        this.resultado = null;
        this.turnoActual = 0;
        this.ronda = 1;
        this.esperandoDano = false;
        this.orden = new ArrayList<>();
        this.activo = true;

        registrarLog("═══ COMBATE CONTRA " + monstruo.getNombre().toUpperCase() + " ═══");
        registrarLog(monstruo.getIcono() + " " + monstruo.getNombre() + ": "
                + monstruo.getHpMaximos() + " PV · CA " + monstruo.getCa());
        registrarLog("═══ INICIATIVA ═══");

        List<Iniciativa> tabla = new ArrayList<>();
        for (Aventurero a : aventureros) {
            Tirada t = roll3d6();
            int mod = Aventurero.modificador(a.getStatValor("des"));
            int total = t.getTotal() + mod;
            String detalle = a.getNombre() + " " + formatoDados(t.getDados())
                    + "=" + t.getTotal() + conSigno(mod) + "=" + total;
            registrarLog(detalle);
            RegistroGremio.registrar("INICIATIVA | " + detalle);
            tabla.add(new Iniciativa(a, total));
        }

        tabla.sort(Comparator.comparingInt((Iniciativa i) -> i.total).reversed());
        registrarLog("═══ ORDEN DE TURNOS ═══");
        for (int i = 0; i < tabla.size(); i++) {
            registrarLog("Turno " + (i + 1) + ": " + tabla.get(i).aventurero.getNombre());
            orden.add(tabla.get(i).aventurero);
        }

        registrarLog("═══ RONDA " + ronda + " ═══");
        estado = "Ronda 1 — turno de " + orden.get(0).getNombre();
        procesarTurno();
    }

    /**
     * Procesa turnos hasta que un ataque acierta (y queda esperando daño) o
     * hasta que termina el combate. Los fallos avanzan automáticamente.
     */
    public void procesarTurno() throws IOException {
        if (!activo || esperandoDano) {
            return;
        }
        while (activo && !esperandoDano) {
            if (turnoActual >= orden.size()) {
                ronda++;
                turnoActual = 0;
                if (ronda > RONDAS_MAXIMAS) {
                    finalizar(false);
                    return;
                }
                registrarLog("═══ RONDA " + ronda + " ═══");
            }

            Aventurero a = orden.get(turnoActual);
            aventureroActual = a;
            registrarLog("🎯 Turno de " + a.getNombre());
            // Polimorfismo puro: la subclase elige su mensaje oficial.
            registrarLog(a.ejecutarAccionCombate());

            Tirada ataque = roll3d6();
            int bono = a.getBonoAtaque();
            int total = ataque.getTotal() + bono;
            boolean acierta = ataque.isCritico()
                    || (!ataque.isPifia() && total >= monstruo.getCa());

            registrarLog("🎲 Ataque: " + formatoDados(ataque.getDados())
                    + conSigno(bono) + " = " + total + " vs CA " + monstruo.getCa());

            if (acierta) {
                esperandoDano = true;
                claseDano = a.getDadoDano();
                factorDano = ataque.isCritico() ? 2 : 1;
                registrarLog("💥 ACIERTA" + (ataque.isCritico() ? " ⭐ ¡CRÍTICO! ×2" : ""));
                registrarLog("🎲 ¡Pulsa el d" + claseDano + " para tirar el daño!");
                estado = "💥 " + a.getNombre() + " acierta"
                        + (ataque.isCritico() ? " ¡CRÍTICO! (×2)" : "")
                        + " → ¡Pulsa el d" + claseDano + " para tirar el daño!";
            } else {
                registrarLog("✗ FALLA");
                estado = "✗ " + a.getNombre() + " falla — siguiente turno";
                turnoActual++;
            }
        }
    }

    /**
     * Aplica el dado que el usuario tiró manualmente.
     * daño = max(1, dado + mod(stat principal)) × factor de crítico.
     */
    public void aplicarDano(int valorDado) throws IOException {
        if (!activo || !esperandoDano) {
            return;
        }
        if (valorDado < 1 || valorDado > claseDano) {
            throw new IllegalArgumentException("Debes tirar un d" + claseDano + " (1.."
                    + claseDano + ").");
        }

        int mod = Aventurero.modificador(
                aventureroActual.getStatValor(aventureroActual.getStatPrincipal()));
        int dano = Math.max(1, valorDado + mod) * factorDano;
        int hpAntes = monstruo.getHp();
        monstruo.setHp(Math.max(0, hpAntes - dano));

        registrarLog("🎲 Daño: d" + claseDano + "=" + valorDado + conSigno(mod)
                + (factorDano > 1 ? " ×2" : "") + " = " + dano);
        registrarLog(monstruo.getNombre() + ": " + hpAntes + " → " + monstruo.getHp() + " PV");
        RegistroGremio.registrar("TURNO " + ronda + " | " + aventureroActual.getNombre()
                + " acierta → " + dano + " daño");
        RegistroGremio.registrar("DAÑO | " + aventureroActual.getNombre() + " → "
                + monstruo.getNombre() + ": " + dano + " daño (" + monstruo.getHp() + "/"
                + monstruo.getHpMaximos() + ")");

        esperandoDano = false;
        claseDano = 0;
        factorDano = 1;
        turnoActual++;

        if (monstruo.getHp() <= 0) {
            finalizar(true);
            return;
        }
        procesarTurno();
    }

    /** Cancela el combate en curso y lo deja registrado. */
    public void cancelar() throws IOException {
        if (!activo) {
            return;
        }
        registrarLog("═══ COMBATE CANCELADO ═══");
        activo = false;
        esperandoDano = false;
        estado = "Combate cancelado";
        RegistroGremio.registrar("COMBATE | Cancelado");
    }

    private void finalizar(boolean victoria) throws IOException {
        activo = false;
        esperandoDano = false;
        if (victoria) {
            resultado = "VICTORIA";
            registrarLog("═══ 🏆 ¡VICTORIA! ═══");
            registrarLog("¡El " + monstruo.getNombre() + " ha caído en " + ronda + " ronda(s)!");
            estado = "🏆 ¡Victoria! El " + monstruo.getNombre() + " ha sido derrotado";
        } else {
            resultado = "DERROTA";
            registrarLog("═══ FIN DEL COMBATE ═══");
            registrarLog("El " + monstruo.getNombre() + " sobrevive con "
                    + monstruo.getHp() + "/" + monstruo.getHpMaximos() + " PV");
            estado = "El " + monstruo.getNombre() + " sobrevive con "
                    + monstruo.getHp() + "/" + monstruo.getHpMaximos() + " PV";
        }
        RegistroGremio.registrar("COMBATE | " + monstruo.getNombre() + ": "
                + (victoria ? "VICTORIA" : "sobrevive con " + monstruo.getHp() + " PV"));
    }

    private void registrarLog(String texto) {
        bitacora.add(texto);
    }

    private static String formatoDados(int[] dados) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < dados.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(dados[i]);
        }
        return sb.append(']').toString();
    }

    private static String conSigno(int valor) {
        return valor >= 0 ? "+" + valor : String.valueOf(valor);
    }

    // ---------- Estado expuesto a la vista (solo lectura) ----------

    public boolean isActivo() {
        return activo;
    }

    public boolean isEsperandoDano() {
        return esperandoDano;
    }

    public int getClaseDano() {
        return claseDano;
    }

    public int getRonda() {
        return ronda;
    }

    public String getResultado() {
        return resultado;
    }

    public String getEstado() {
        return estado;
    }

    public Monstruo getMonstruo() {
        return monstruo;
    }

    /** Copia defensiva de la bitácora para que la vista la pinte. */
    public List<String> getBitacora() {
        return new ArrayList<>(bitacora);
    }

    /** Utilidad estática para convertir la bitácora en texto de JTextArea. */
    public static String bitacoraComoTexto(List<String> lineas) {
        return String.join(System.lineSeparator(), lineas)
                + (lineas.isEmpty() ? "" : System.lineSeparator());
    }

    /** Devuelve los dados como texto legible (uso de la vista y pruebas). */
    public static String describirTirada(Tirada tirada) {
        return Arrays.toString(tirada.getDados()) + " total " + tirada.getTotal();
    }
}
