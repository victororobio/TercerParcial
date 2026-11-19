// Subclase Clérigo: dios venerado, dado d4 y stat primaria Sabiduría.
package com.gremio.modelo;

/** Clérigo: canaliza divinidad para curar y expulsar muertos vivientes. */
public class Clerigo extends Aventurero {

    private String diosVenerado;

    public Clerigo(String nombre, int nivel, int puntosVida, String diosVenerado) {
        super(nombre, nivel, puntosVida);
        if (diosVenerado == null || diosVenerado.trim().isEmpty()) {
            throw new IllegalArgumentException("El dios venerado es obligatorio.");
        }
        this.diosVenerado = diosVenerado;
    }

    public Clerigo(String nombre, int nivel, int puntosVida) {
        this(nombre, nivel, puntosVida, "Pelor");
    }

    public String getDiosVenerado() {
        return diosVenerado;
    }

    public void setDiosVenerado(String diosVenerado) {
        if (diosVenerado == null || diosVenerado.trim().isEmpty()) {
            throw new IllegalArgumentException("El dios venerado es obligatorio.");
        }
        this.diosVenerado = diosVenerado;
    }

    @Override
    public int getDadoDano() {
        return 4;
    }

    @Override
    public String getStatPrincipal() {
        return "sab";
    }

    @Override
    public String getIcono() {
        return "✨";
    }

    @Override
    public String getDescripcionClase() {
        return "Clérigo";
    }

    @Override
    public String getDescripcionAtributo() {
        return "Dios venerado: " + diosVenerado;
    }

    @Override
    public String ejecutarAccionCombate() {
        return "El Clérigo " + getNombre()
                + " usa Canalizar Divinidad para curar a sus aliados y expulsar muertos vivientes.";
    }

    @Override
    public String toString() {
        return super.toString() + " [Dios: " + diosVenerado + "]";
    }
}
