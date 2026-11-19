// Subclase Monje: puntos de Ki, dado d6 y CA con mods de DES + SAB.
package com.gremio.modelo;

/** Monje: suma Sabiduría a la CA y gasta Ki en su ataque especial. */
public class Monje extends Aventurero {

    private int puntosKi;

    public Monje(String nombre, int nivel, int puntosVida, int puntosKi) {
        super(nombre, nivel, puntosVida);
        if (puntosKi < 0) {
            throw new IllegalArgumentException("Los puntos de Ki no pueden ser negativos.");
        }
        this.puntosKi = puntosKi;
    }

    public Monje(String nombre, int nivel, int puntosVida) {
        this(nombre, nivel, puntosVida, 5);
    }

    public int getPuntosKi() {
        return puntosKi;
    }

    public void setPuntosKi(int puntosKi) {
        if (puntosKi < 0) {
            throw new IllegalArgumentException("Los puntos de Ki no pueden ser negativos.");
        }
        this.puntosKi = puntosKi;
    }

    /** Defensa sin armadura: 10 + mod(DES) + mod(SAB). */
    @Override
    protected int bonoCAAdicional() {
        return modificador(getStatValor("sab"));
    }

    @Override
    public int getDadoDano() {
        return 6;
    }

    @Override
    public String getStatPrincipal() {
        return "des";
    }

    @Override
    public String getIcono() {
        return "🥋";
    }

    @Override
    public String getDescripcionClase() {
        return "Monje";
    }

    @Override
    public String getDescripcionAtributo() {
        return "Puntos de Ki: " + puntosKi;
    }

    @Override
    public String ejecutarAccionCombate() {
        return "El Monje " + getNombre()
                + " gasta puntos de Ki para ejecutar una Ráfaga de Golpes y un Ataque Aturdidor.";
    }

    @Override
    public String toString() {
        return super.toString() + " [Ki: " + puntosKi + "]";
    }
}
