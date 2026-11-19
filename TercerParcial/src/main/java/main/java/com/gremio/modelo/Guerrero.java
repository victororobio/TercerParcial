// Subclase Guerrero: armadura pesada, dado d6 y stat primaria Fuerza.
package com.gremio.modelo;

/** Guerrero: CA 16 fija cuando lleva armadura pesada. */
public class Guerrero extends Aventurero {

    private boolean armaduraPesada;

    public Guerrero(String nombre, int nivel, int puntosVida, boolean armaduraPesada) {
        super(nombre, nivel, puntosVida);
        this.armaduraPesada = armaduraPesada;
    }

    public Guerrero(String nombre, int nivel, int puntosVida) {
        this(nombre, nivel, puntosVida, true);
    }

    /** Constructor de paquete para pruebas deterministas de CA. */
    Guerrero(String nombre, int nivel, int puntosVida, boolean armaduraPesada, int[] estadisticas) {
        super(nombre, nivel, puntosVida, estadisticas);
        this.armaduraPesada = armaduraPesada;
    }

    public boolean isArmaduraPesada() {
        return armaduraPesada;
    }

    public void setArmaduraPesada(boolean armaduraPesada) {
        this.armaduraPesada = armaduraPesada;
    }

    @Override
    protected boolean usaArmaduraPesada() {
        return armaduraPesada;
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
        return "⚔️";
    }

    @Override
    public String getDescripcionClase() {
        return "Guerrero";
    }

    @Override
    public String getDescripcionAtributo() {
        return "Armadura Pesada: " + (armaduraPesada ? "Sí" : "No");
    }

    @Override
    public String ejecutarAccionCombate() {
        return "El Guerrero " + getNombre()
                + " usa Oleada de Acción para realizar múltiples ataques físicos continuos.";
    }

    @Override
    public String toString() {
        return super.toString() + " [Armadura pesada: " + (armaduraPesada ? "sí" : "no") + "]";
    }
}
