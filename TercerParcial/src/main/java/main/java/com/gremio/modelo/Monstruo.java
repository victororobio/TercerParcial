// Enemigo del simulador: tres enemigos predefinidos mediante factories estáticas.
package com.gremio.modelo;

/** Monstruo con HP mutable para la simulación de combate manual. */
public class Monstruo {

    private final String nombre;
    private final int hpMaximos;
    private final int ca;
    private final String dificultad;
    private final String icono;
    private int hp;

    private Monstruo(String nombre, int hpMaximos, int ca, String dificultad, String icono) {
        this.nombre = nombre;
        this.hpMaximos = hpMaximos;
        this.ca = ca;
        this.dificultad = dificultad;
        this.icono = icono;
        this.hp = hpMaximos;
    }

    /** Fábrica estática del Goblin: 20 PV, CA 12. */
    public static Monstruo crearGoblin() {
        return new Monstruo("Goblin", 20, 12, "Fácil", "👺");
    }

    /** Fábrica estática del Ogro: 50 PV, CA 14. */
    public static Monstruo crearOgro() {
        return new Monstruo("Ogro", 50, 14, "Medio", "👹");
    }

    /** Fábrica estática del Dragón: 100 PV, CA 16. */
    public static Monstruo crearDragon() {
        return new Monstruo("Dragón", 100, 16, "Difícil", "🐉");
    }

    public String getNombre() {
        return nombre;
    }

    public int getHpMaximos() {
        return hpMaximos;
    }

    public int getCa() {
        return ca;
    }

    public String getDificultad() {
        return dificultad;
    }

    public String getIcono() {
        return icono;
    }

    public int getHp() {
        return hp;
    }

    /** El HP nunca baja de 0 ni supera el máximo. */
    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(hpMaximos, hp));
    }

    public boolean estaVivo() {
        return hp > 0;
    }

    @Override
    public String toString() {
        return icono + " " + nombre + " [" + hp + "/" + hpMaximos + " PV, CA " + ca
                + ", " + dificultad + "]";
    }
}
