// Subclase Explorador: terreno favorecido, dado d6 y stat primaria Destreza.
package com.gremio.modelo;

/** Explorador: cazador que marca al objetivo para maximizar su daño. */
public class Explorador extends Aventurero {

    private String terrenoFavorecido;

    public Explorador(String nombre, int nivel, int puntosVida, String terrenoFavorecido) {
        super(nombre, nivel, puntosVida);
        if (terrenoFavorecido == null || terrenoFavorecido.trim().isEmpty()) {
            throw new IllegalArgumentException("El terreno favorecido es obligatorio.");
        }
        this.terrenoFavorecido = terrenoFavorecido;
    }

    public Explorador(String nombre, int nivel, int puntosVida) {
        this(nombre, nivel, puntosVida, "Bosque");
    }

    public String getTerrenoFavorecido() {
        return terrenoFavorecido;
    }

    public void setTerrenoFavorecido(String terrenoFavorecido) {
        if (terrenoFavorecido == null || terrenoFavorecido.trim().isEmpty()) {
            throw new IllegalArgumentException("El terreno favorecido es obligatorio.");
        }
        this.terrenoFavorecido = terrenoFavorecido;
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
        return "🏹";
    }

    @Override
    public String getDescripcionClase() {
        return "Explorador";
    }

    @Override
    public String getDescripcionAtributo() {
        return "Terreno favorecido: " + terrenoFavorecido;
    }

    @Override
    public String ejecutarAccionCombate() {
        return "El Explorador " + getNombre()
                + " aplica Marca del Cazador sobre el objetivo para maximizar su daño sostenido.";
    }

    @Override
    public String toString() {
        return super.toString() + " [Terreno: " + terrenoFavorecido + "]";
    }
}
