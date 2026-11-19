// Subclase Pícaro: herramientas de ladrón, dado d6 y stat primaria Destreza.
package com.gremio.modelo;

/** Pícaro: usa ventaja táctica para asestar un Ataque Furtivo. */
public class Picaro extends Aventurero {

    private boolean herramientasLadron;

    public Picaro(String nombre, int nivel, int puntosVida, boolean herramientasLadron) {
        super(nombre, nivel, puntosVida);
        this.herramientasLadron = herramientasLadron;
    }

    public Picaro(String nombre, int nivel, int puntosVida) {
        this(nombre, nivel, puntosVida, true);
    }

    public boolean isHerramientasLadron() {
        return herramientasLadron;
    }

    public void setHerramientasLadron(boolean herramientasLadron) {
        this.herramientasLadron = herramientasLadron;
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
        return "🗡️";
    }

    @Override
    public String getDescripcionClase() {
        return "Pícaro";
    }

    @Override
    public String getDescripcionAtributo() {
        return "Herramientas de ladrón: " + (herramientasLadron ? "Sí" : "No");
    }

    @Override
    public String ejecutarAccionCombate() {
        return "El Pícaro " + getNombre()
                + " aprovecha la ventaja táctica para asestar un Ataque Furtivo masivo y se reposiciona.";
    }

    @Override
    public String toString() {
        return super.toString() + " [Herramientas: " + (herramientasLadron ? "sí" : "no") + "]";
    }
}
