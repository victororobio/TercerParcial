// Controlador del gremio: orquesta el modelo y la persistencia, sin conocer Swing.
package com.gremio.controlador;

import com.gremio.modelo.Aventurero;
import com.gremio.modelo.Bardo;
import com.gremio.modelo.Clerigo;
import com.gremio.modelo.Explorador;
import com.gremio.modelo.Guerrero;
import com.gremio.modelo.Mago;
import com.gremio.modelo.Monje;
import com.gremio.modelo.Paladin;
import com.gremio.modelo.Picaro;
import com.gremio.modelo.RegistroGremio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Controlador de alto nivel: administra la lista polimórfica de aventureros. */
public class GremioControlador {

    /** Colección polimórfica: nunca se usa instanceof ni se castean subclases. */
    private final List<Aventurero> aventureros = new ArrayList<>();

    /** Nombres de las ocho clases, en el orden del formulario. */
    public static final String[] CLASES = {
        "Guerrero", "Mago", "Clérigo", "Monje", "Paladín", "Explorador", "Pícaro", "Bardo"
    };

    /** Etiqueta del atributo especial de cada clase (para la vista). */
    public static String etiquetaAtributo(String clase) {
        switch (clase) {
            case "Guerrero": return "Armadura Pesada (sí / no)";
            case "Mago": return "Espacios de Conjuro (entero ≥ 0)";
            case "Clérigo": return "Dios Venerado (elige)";
            case "Monje": return "Puntos de Ki (entero ≥ 0)";
            case "Paladín": return "Puntos de Fuerza (entero ≥ 0)";
            case "Explorador": return "Terreno Favorecido (elige)";
            case "Pícaro": return "Herramientas de Ladrón (sí / no)";
            case "Bardo": return "Instrumento Musical (elige)";
            default: return "Atributo especial";
        }
    }

    /** Pista del atributo especial y dado de cada clase (para la vista). */
    public static String pistaClase(String clase) {
        switch (clase) {
            case "Guerrero": return "Daño d6 (dado de 6 caras) · Stat FUE (Fuerza) · Elige sí o no";
            case "Mago": return "Daño d4 (dado de 4 caras) · Stat INT (Inteligencia) · Hechizos por día";
            case "Clérigo": return "Daño d4 (dado de 4 caras) · Stat SAB (Sabiduría) · Elige a su dios";
            case "Monje": return "Daño d6 (dado de 6 caras) · Stat DES (Destreza) · Energía interior";
            case "Paladín": return "Daño d6 (dado de 6 caras) · Stat FUE (Fuerza) · Fuerza ≥ 15 da CA 16";
            case "Explorador": return "Daño d6 (dado de 6 caras) · Stat DES (Destreza) · Elige el terreno";
            case "Pícaro": return "Daño d6 (dado de 6 caras) · Stat DES (Destreza) · Elige sí o no";
            case "Bardo": return "Daño d4 (dado de 4 caras) · Stat CAR (Carisma) · Elige el instrumento";
            default: return "";
        }
    }

    /**
     * Fábrica del modelo: convierte el texto del formulario en la subclase
     * correcta. La vista solo envía cadenas y números.
     */
    public Aventurero crearAventurero(String clase, String nombre, int nivel,
                                      int puntosVida, String atributo) {
        if (clase == null) {
            throw new IllegalArgumentException("Debe elegir una clase.");
        }
        switch (clase) {
            case "Guerrero":
                return new Guerrero(nombre, nivel, puntosVida, leerBooleano(atributo, "Armadura Pesada"));
            case "Mago":
                return new Mago(nombre, nivel, puntosVida, leerEntero(atributo, "Espacios de Conjuro"));
            case "Clérigo":
                return new Clerigo(nombre, nivel, puntosVida, leerTexto(atributo, "Dios Venerado"));
            case "Monje":
                return new Monje(nombre, nivel, puntosVida, leerEntero(atributo, "Puntos de Ki"));
            case "Paladín":
                return new Paladin(nombre, nivel, puntosVida, leerEntero(atributo, "Puntos de Fuerza"));
            case "Explorador":
                return new Explorador(nombre, nivel, puntosVida, leerTexto(atributo, "Terreno Favorecido"));
            case "Pícaro":
                return new Picaro(nombre, nivel, puntosVida, leerBooleano(atributo, "Herramientas de Ladrón"));
            case "Bardo":
                return new Bardo(nombre, nivel, puntosVida, leerTexto(atributo, "Instrumento Musical"));
            default:
                throw new IllegalArgumentException("Clase desconocida: " + clase);
        }
    }

    private static boolean leerBooleano(String valor, String etiqueta) {
        if (valor == null) {
            throw new IllegalArgumentException(etiqueta + ": escribe sí o no.");
        }
        String normalizado = valor.trim().toLowerCase();
        if (normalizado.equals("sí") || normalizado.equals("si")) {
            return true;
        }
        if (normalizado.equals("no")) {
            return false;
        }
        throw new IllegalArgumentException(etiqueta + ": escribe sí o no.");
    }

    private static int leerEntero(String valor, String etiqueta) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(etiqueta + " es obligatorio.");
        }
        try {
            int numero = Integer.parseInt(valor.trim());
            if (numero < 0) {
                throw new IllegalArgumentException(etiqueta + " debe ser ≥ 0.");
            }
            return numero;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(etiqueta + " debe ser un número entero.");
        }
    }

    private static String leerTexto(String valor, String etiqueta) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(etiqueta + " es obligatorio.");
        }
        return valor.trim();
    }

    /** Registra un aventurero y anexa el evento REGISTRO. */
    public void agregarAventurero(Aventurero aventurero) throws IOException {
        if (aventurero == null) {
            throw new IllegalArgumentException("El aventurero no puede ser nulo.");
        }
        aventureros.add(aventurero);
        RegistroGremio.registrar("REGISTRO | " + aventurero.getLineaRegistro());
    }

    /** Copia defensiva: la vista no puede modificar la lista interna. */
    public List<Aventurero> listarAventureros() {
        return new ArrayList<>(aventureros);
    }

    public int getCantidadAventureros() {
        return aventureros.size();
    }

    /** Sube de nivel y persiste el evento NIVEL. */
    public void subirNivel(Aventurero aventurero, int cantidad) throws IOException {
        int nivelAnterior = aventurero.getNivel();
        aventurero.subirNivel(cantidad);
        RegistroGremio.registrar("NIVEL | " + aventurero.getNombre()
                + " Nv." + nivelAnterior + " → Nv." + aventurero.getNivel());
    }

    /** Aplica daño y persiste el evento DAÑO. */
    public void recibirDano(Aventurero aventurero, int dano) throws IOException {
        aventurero.recibirDano(dano);
        RegistroGremio.registrar("DAÑO | " + aventurero.getNombre()
                + " recibió " + dano + " → " + aventurero.getHpActual() + "/"
                + aventurero.getPvMaximos());
    }

    /** Cura por completo y persiste el evento CURAR. */
    public void curar(Aventurero aventurero) throws IOException {
        int hpAnterior = aventurero.getHpActual();
        aventurero.curar();
        RegistroGremio.registrar("CURAR | " + aventurero.getNombre()
                + " " + hpAnterior + " → " + aventurero.getHpActual());
    }

    /** Elimina por referencia de objeto (sin casteos) y persiste el evento. */
    public void eliminar(Aventurero aventurero) throws IOException {
        aventureros.remove(aventurero);
        RegistroGremio.registrar("ELIMINAR | " + aventurero.getNombre());
    }

    /** Reporte 1: cantidad de aventureros agrupados por clase. */
    public Map<String, Integer> generarReportePorClase() {
        Map<String, Integer> conteo = new TreeMap<>();
        for (Aventurero a : aventureros) {
            String clase = a.getDescripcionClase();
            conteo.put(clase, conteo.getOrDefault(clase, 0) + 1);
        }
        return conteo;
    }

    /** Reporte 2: aventureros con nivel estrictamente mayor al umbral. */
    public List<Aventurero> generarReporteNivelMayor(int nivel) {
        List<Aventurero> filtrados = new ArrayList<>();
        for (Aventurero a : aventureros) {
            if (a.getNivel() > nivel) {
                filtrados.add(a);
            }
        }
        return filtrados;
    }

    /** Permite a la vista registrar eventos sin acceder a RegistroGremio. */
    public void registrarEvento(String tipo, String descripcion) throws IOException {
        RegistroGremio.registrar(tipo + " | " + descripcion);
    }

    /** Contenido actual del archivo para la pestaña Resumen. */
    public List<String> leerRegistro() throws IOException {
        return RegistroGremio.leerRegistro();
    }
}
