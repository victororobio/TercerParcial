// Subclase Mago: espacios de conjuro, dado d4 y stat primaria Inteligencia.
package com.gremio.modelo;

/** Mago: lanza hechizos gastando espacios de conjuro. */
public class Mago extends Aventurero {

    private int espaciosConjuro;

    public Mago(String nombre, int nivel, int puntosVida, int espaciosConjuro) {
        super(nombre, nivel, puntosVida);
        if (espaciosConjuro < 0) {
            throw new IllegalArgumentException("Los espacios de conjuro no pueden ser negativos.");
        }
        this.espaciosConjuro = espaciosConjuro;
    }

    public Mago(String nombre, int nivel, int puntosVida) {
        this(nombre, nivel, puntosVida, 3);
    }

    public int getEspaciosConjuro() {
        return espaciosConjuro;
    }

    public void setEspaciosConjuro(int espaciosConjuro) {
        if (espaciosConjuro < 0) {
            throw new IllegalArgumentException("Los espacios de conjuro no pueden ser negativos.");
        }
        this.espaciosConjuro = espaciosConjuro;
    }

    @Override
    public int getDadoDano() {
        return 4;
    }

    @Override
    public String getStatPrincipal() {
        return "int";
    }

    @Override
    public String getIcono() {
        return "🔮";
    }

    @Override
    public String getDescripcionClase() {
        return "Mago";
    }

    @Override
    public String getDescripcionAtributo() {
        return "Espacios de conjuro: " + espaciosConjuro;
    }

    @Override
    public String ejecutarAccionCombate() {
        return "El Mago " + getNombre()
                + " gasta un espacio de conjuro para lanzar una poderosa Bola de Fuego.";
    }

    @Override
    public String toString() {
        return super.toString() + " [Espacios de conjuro: " + espaciosConjuro + "]";
    }
}
