// Persistencia: anexa líneas con timestamp a registro_gremio.txt usando try-with-resources.
package com.gremio.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/** Utilidad final de persistencia de eventos del gremio. */
public final class RegistroGremio {

    /** Ruta relativa (nunca absoluta) del archivo de registro. */
    private static final String ARCHIVO = "registro_gremio.txt";
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private RegistroGremio() {
        // Clase de utilidad: sin instancias.
    }

    /** Anexa un evento al archivo por defecto del proyecto. */
    public static void registrar(String descripcion) throws IOException {
        registrar(Paths.get(ARCHIVO), descripcion);
    }

    /**
     * Anexa una línea con formato "yyyy-MM-dd HH:mm:ss | descripción".
     * La sobrecarga con Path permite a JUnit usar @TempDir y no tocar el archivo real.
     */
    public static void registrar(Path archivo, String descripcion) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("La ruta del archivo es obligatoria.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción del evento es obligatoria.");
        }
        String linea = LocalDateTime.now().format(FORMATO) + " | " + descripcion;
        // Java 11: FileWriter(archivo, charset, append=true) + try-with-resources.
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(archivo.toFile(), StandardCharsets.UTF_8, true))) {
            writer.println(linea);
        }
    }

    /** Devuelve el nombre del archivo configurado (para la vista de resumen). */
    public static String getArchivo() {
        return ARCHIVO;
    }

    /** Lee todas las líneas; si el archivo aún no existe devuelve una lista vacía. */
    public static List<String> leer(Path archivo) throws IOException {
        if (!Files.exists(archivo)) {
            return Collections.emptyList();
        }
        return Files.readAllLines(archivo, StandardCharsets.UTF_8);
    }

    /** Lectura del archivo por defecto, usada por la pestaña Resumen. */
    public static List<String> leerRegistro() throws IOException {
        return leer(Paths.get(ARCHIVO));
    }
}
