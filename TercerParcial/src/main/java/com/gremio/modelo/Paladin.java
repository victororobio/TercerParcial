// Subclase Paladín: puntos de fuerza, dado d6 y armadura pesada derivada.
package com.gremio.modelo;

/** Paladín: guerrero sagrado; con fuerza alta porta armadura pesada (CA 16). */
public class Paladin extends Aventurero {

    private int puntosFuerza;
    private boolean armaduraPesada;

    public Paladin(String nombre, int nivel, int puntosVida, int puntosFuerza) {
        super(nombre, nivel, puntosVida);
        if (puntosFuerza < 0) {
            throw new IllegalArgumentException("Los puntos de fuerza no pueden ser negativos.");
        }
        this.puntosFuerza = puntosFuerza;
        // Regla del simulador: fuerza >= 15 permite portar armadura pesada.
        this.armaduraPesada = puntosFuerza >= 15;
    }

    public Paladin(String nombre, int nivel, int puntosVida) {
        this(nombre, nivel, puntosVida, 16);
    }

    public int getPuntosFuerza() {
        return puntosFuerza;
    }

    public void setPuntosFuerza(int puntosFuerza) {
        if (puntosFuerza < 0) {
            throw new IllegalArgumentException("Los puntos de fuerza no pueden ser negativos.");
        }
        this.puntosFuerza = puntosFuerza;
        this.armaduraPesada = puntosFuerza >= 15;
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
        return "🛡️";
    }

    @Override
    public String getDescripcionClase() {
        return "Paladín";
    }

    @Override
    public String getDescripcionAtributo() {
        return "Puntos de fuerza: " + puntosFuerza;
    }

    @Override
    public String ejecutarAccionCombate() {
        return "El Paladín " + getNombre()
                + " consume magia para imbuir su arma con un Castigo Divino de daño radiante.";
    }

    @Override
    public String toString() {
        return super.toString() + " [Fuerza: " + puntosFuerza + "]";
    }
}
