// Clase abstracta base del modelo: estado común, fórmulas D&D 5e y contrato de combate.
package com.gremio.modelo;

import java.util.Arrays;
import java.util.Random;

/**
 * Superclase abstracta de los ocho personajes del gremio.
 * Contiene toda la lógica de reglas (stats, CA, bono de ataque, subida de nivel)
 * y declara los métodos abstractos que cada subclase debe especializar.
 */
public abstract class Aventurero implements IAccionCombate {

    /** Claves de las seis puntuaciones de habilidad, en orden canónico. */
    private static final String[] CLAVES_STATS = {"fue", "des", "con", "int", "sab", "car"};
    private static final int NIVEL_MAXIMO = 20;
    private static final Random ALEATORIO = new Random();

    private String nombre;
    private int nivel;
    private int pvMaximos;
    private int hpActual;
    private int[] estadisticas;

    /** Constructor vacío (sobrecarga 1): valores por defecto válidos. */
    public Aventurero() {
        this("Aventurero", 1, 10);
    }

    /** Constructor nombre+nivel (sobrecarga 2): PV por defecto = 10. */
    public Aventurero(String nombre, int nivel) {
        this(nombre, nivel, 10);
    }

    /** Constructor completo (sobrecarga 3): genera 6 stats con 4d6-drop-lowest. */
    public Aventurero(String nombre, int nivel, int pvMaximos) {
        this(nombre, nivel, pvMaximos, generarEstadisticasAleatorias());
    }

    /**
     * Constructor de paquete (sobrecarga 4) usado por las pruebas JUnit para
     * inyectar puntuaciones deterministas y poder comprobar CA y bono de ataque.
     */
    Aventurero(String nombre, int nivel, int pvMaximos, int[] estadisticas) {
        validarDatos(nombre, nivel, pvMaximos);
        this.nombre = nombre;
        this.nivel = nivel;
        this.pvMaximos = pvMaximos;
        this.hpActual = pvMaximos;
        this.estadisticas = validarEstadisticas(estadisticas);
    }

    /** Genera las seis puntuaciones: cuatro dados, se descarta el menor. */
    private static int[] generarEstadisticasAleatorias() {
        int[] resultado = new int[CLAVES_STATS.length];
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = tirar4d6DropLowest();
        }
        return resultado;
    }

    /** Tirada 4d6 ordenada descendente: suma de los tres mayores (3..18). */
    public static int tirar4d6DropLowest() {
        int[] dados = new int[4];
        for (int i = 0; i < dados.length; i++) {
            dados[i] = ALEATORIO.nextInt(6) + 1;
        }
        Arrays.sort(dados);
        return dados[3] + dados[2] + dados[1];
    }

    /** Modificador D&D: floor((stat - 10) / 2), también para valores negativos. */
    public static int modificador(int stat) {
        return Math.floorDiv(stat - 10, 2);
    }

    private static void validarDatos(String nombre, int nivel, int pvMaximos) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (nivel < 1 || nivel > NIVEL_MAXIMO) {
            throw new IllegalArgumentException("El nivel debe estar entre 1 y 20.");
        }
        if (pvMaximos <= 0) {
            throw new IllegalArgumentException("Los puntos de vida deben ser mayores que 0.");
        }
    }

    private static int[] validarEstadisticas(int[] stats) {
        if (stats == null || stats.length != CLAVES_STATS.length) {
            throw new IllegalArgumentException("Se requieren exactamente 6 puntuaciones de habilidad.");
        }
        int[] copia = stats.clone();
        for (int valor : copia) {
            if (valor < 3 || valor > 18) {
                throw new IllegalArgumentException("Cada habilidad debe estar entre 3 y 18.");
            }
        }
        return copia;
    }

    // ---------- Getters y setters encapsulados ----------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    /** Preferir siempre subirNivel(int) para mantener coherentes los PV. */
    public void setNivel(int nivel) {
        if (nivel < 1 || nivel > NIVEL_MAXIMO) {
            throw new IllegalArgumentException("El nivel debe estar entre 1 y 20.");
        }
        this.nivel = nivel;
    }

    public int getPvMaximos() {
        return pvMaximos;
    }

    public int getHpActual() {
        return hpActual;
    }

    public void setHpActual(int hpActual) {
        this.hpActual = Math.max(0, Math.min(pvMaximos, hpActual));
    }

    /** Copia defensiva: nadie puede alterar las stats desde fuera. */
    public int[] getEstadisticas() {
        return estadisticas.clone();
    }

    public void setEstadisticas(int[] estadisticas) {
        this.estadisticas = validarEstadisticas(estadisticas);
    }

    // ---------- Reglas D&D 5e ----------

    /** Devuelve fue/des/con/int/sab/car. */
    public int getStatValor(String clave) {
        for (int i = 0; i < CLAVES_STATS.length; i++) {
            if (CLAVES_STATS[i].equalsIgnoreCase(clave)) {
                return estadisticas[i];
            }
        }
        throw new IllegalArgumentException("Habilidad desconocida: " + clave);
    }

    /** Bonificación de competencia: floor((nivel-1)/4) + 2. */
    public int getCompetencia() {
        return Math.floorDiv(nivel - 1, 4) + 2;
    }

    /** Bono de ataque = competencia + modificador de la stat primaria. */
    public int getBonoAtaque() {
        return getCompetencia() + modificador(getStatValor(getStatPrincipal()));
    }

    /**
     * CA = 10 + mod(DES) + bono extra; 16 fija si la clase usa armadura pesada.
     * El Monje sobrescribe bonoCAAdicional() para sumar mod(SAB).
     */
    public int calcularCA() {
        if (usaArmaduraPesada()) {
            return 16;
        }
        return 10 + modificador(getStatValor("des")) + bonoCAAdicional();
    }

    /** Gancho polimórfico: Guerrero y Paladín lo sobrescriben. */
    protected boolean usaArmaduraPesada() {
        return false;
    }

    /** Gancho polimórfico: el Monje suma su modificador de Sabiduría. */
    protected int bonoCAAdicional() {
        return 0;
    }

    /** Sobrecarga sin argumento: sube un nivel. */
    public void subirNivel() {
        subirNivel(1);
    }

    /** Cada nivel otorga +10 PV, siempre con tope en nivel 20. */
    public void subirNivel(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad de niveles debe ser positiva.");
        }
        int nuevoNivel = Math.min(NIVEL_MAXIMO, nivel + cantidad);
        int gananciaPv = (nuevoNivel - nivel) * 10;
        nivel = nuevoNivel;
        pvMaximos += gananciaPv;
        hpActual = Math.min(pvMaximos, hpActual + gananciaPv);
    }

    public void recibirDano(int dano) {
        if (dano < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo.");
        }
        hpActual = Math.max(0, hpActual - dano);
    }

    public void curar() {
        hpActual = pvMaximos;
    }

    public boolean estaVivo() {
        return hpActual > 0;
    }

    // ---------- Información polimórfica de cada clase ----------

    /** Nombre visible de la clase (las subclases con tilde lo sobrescriben). */
    public String getDescripcionClase() {
        return getClass().getSimpleName();
    }

    /** Línea exacta exigida en registro_gremio.txt para el evento REGISTRO. */
    public String getLineaRegistro() {
        return String.format("%s[%s, Nv.%d, %dPV, CA %d]",
                getDescripcionClase(), nombre, nivel, pvMaximos, calcularCA());
    }

    /** Cadena resumida para la tarjeta del gremio: "FUE 14 DES 12 ...". */
    public String getResumenStats() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CLAVES_STATS.length; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(CLAVES_STATS[i].toUpperCase()).append(' ').append(estadisticas[i]);
        }
        return sb.toString();
    }

    /** Las seis stats como texto libre, para el JOptionPane de creación. */
    public String getDetalleStats() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CLAVES_STATS.length; i++) {
            sb.append(CLAVES_STATS[i].toUpperCase())
                    .append(": ").append(estadisticas[i])
                    .append(" (mod ")
                    .append(modificador(estadisticas[i]) >= 0 ? "+" : "")
                    .append(modificador(estadisticas[i])).append(")\n");
        }
        return sb.toString();
    }

    /** Caras del dado de daño: d4 o d6. */
    public abstract int getDadoDano();

    /** Clave de la habilidad usada para atacar y calcular daño. */
    public abstract String getStatPrincipal();

    /** Icono Unicode de la clase para las tarjetas. */
    public abstract String getIcono();

    /** Descripción del atributo especial, lista para mostrar en la vista. */
    public abstract String getDescripcionAtributo();

    /** La acción de combate queda abstracta por contrato de IAccionCombate. */
    @Override
    public abstract String ejecutarAccionCombate();

    @Override
    public String toString() {
        return String.format("%s[%s, Nv.%d, %d/%dPV, CA %d, Atq %+d, d%d]",
                getDescripcionClase(), nombre, nivel, hpActual, pvMaximos,
                calcularCA(), getBonoAtaque(), getDadoDano());
    }
}
