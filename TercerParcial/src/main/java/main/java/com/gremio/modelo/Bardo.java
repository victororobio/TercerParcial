// Subclase Bardo: instrumento musical, dado d4 y stat primaria Carisma.
package com.gremio.modelo;

/** Bardo: otorga inspiración bárdica tocando su instrumento. */
public class Bardo extends Aventurero {

    private String instrumentoMusical;

    public Bardo(String nombre, int nivel, int puntosVida, String instrumentoMusical) {
        super(nombre, nivel, puntosVida);
        if (instrumentoMusical == null || instrumentoMusical.trim().isEmpty()) {
            throw new IllegalArgumentException("El instrumento musical es obligatorio.");
        }
        this.instrumentoMusical = instrumentoMusical;
    }

    public Bardo(String nombre, int nivel, int puntosVida) {
        this(nombre, nivel, puntosVida, "Laúd");
    }

    public String getInstrumentoMusical() {
        return instrumentoMusical;
    }

    public void setInstrumentoMusical(String instrumentoMusical) {
        if (instrumentoMusical == null || instrumentoMusical.trim().isEmpty()) {
            throw new IllegalArgumentException("El instrumento musical es obligatorio.");
        }
        this.instrumentoMusical = instrumentoMusical;
    }

    @Override
    public int getDadoDano() {
        return 4;
    }

    @Override
    public String getStatPrincipal() {
        return "car";
    }

    @Override
    public String getIcono() {
        return "🎵";
    }

    @Override
    public String getDescripcionClase() {
        return "Bardo";
    }

    @Override
    public String getDescripcionAtributo() {
        return "Instrumento musical: " + instrumentoMusical;
    }

    @Override
    public String ejecutarAccionCombate() {
        return "El Bardo " + getNombre()
                + " toca su instrumento para otorgar Inspiración Bárdica a un aliado.";
    }

    @Override
    public String toString() {
        return super.toString() + " [Instrumento: " + instrumentoMusical + "]";
    }
}
