// Pruebas de persistencia con @TempDir: nunca se escribe el archivo real del proyecto.
package com.gremio.modelo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Verifica el formato de línea, el anexado y la lectura de registro_gremio.txt. */
class RegistroGremioTest {

    /** Formato obligatorio: yyyy-MM-dd HH:mm:ss | TIPO | Descripción. */
    private static final Pattern LINEA = Pattern.compile(
            "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} \\| [A-ZÁÉÍÓÚÑ]+ \\| .+$");

    @TempDir
    Path carpetaTemporal;

    @Test
    void registrarEscribeUnaLineaConTimestampYTipo() throws IOException {
        Path archivo = carpetaTemporal.resolve("registro_gremio.txt");
        RegistroGremio.registrar(archivo, "REGISTRO | Guerrero[Conan, Nv.1, 10PV, CA 16]");

        List<String> lineas = RegistroGremio.leer(archivo);
        assertEquals(1, lineas.size());
        assertTrue(LINEA.matcher(lineas.get(0)).matches(), lineas.get(0));
        assertTrue(lineas.get(0).endsWith("| REGISTRO | Guerrero[Conan, Nv.1, 10PV, CA 16]"));
    }

    @Test
    void registrarVariasVecesAnexaSinBorrarElContenido() throws IOException {
        Path archivo = carpetaTemporal.resolve("registro_gremio.txt");
        RegistroGremio.registrar(archivo, "REGISTRO | Mago[Merlín, Nv.3]");
        RegistroGremio.registrar(archivo, "NIVEL | Mago Merlín Nv.3 → Nv.4");
        RegistroGremio.registrar(archivo, "COMBATE | Goblin: VICTORIA");

        assertEquals(3, RegistroGremio.leer(archivo).size());
    }

    @Test
    void leerArchivoInexistenteDevuelveListaVacia() throws IOException {
        Path archivo = carpetaTemporal.resolve("aun_no_existe.txt");
        assertTrue(RegistroGremio.leer(archivo).isEmpty());
    }

    @Test
    void registrarRechazaDescripcionNulaOVacia() {
        Path archivo = carpetaTemporal.resolve("registro_gremio.txt");
        assertThrows(IllegalArgumentException.class, () -> RegistroGremio.registrar(archivo, null));
        assertThrows(IllegalArgumentException.class, () -> RegistroGremio.registrar(archivo, "   "));
    }

    @Test
    void registrarRechazaRutaNula() {
        assertThrows(IllegalArgumentException.class, () -> RegistroGremio.registrar(null, "REGISTRO | X"));
    }

    @Test
    void elArchivoPorDefectoEsRelativoYEstaDefinido() {
        assertEquals("registro_gremio.txt", RegistroGremio.getArchivo());
        assertTrue(!RegistroGremio.getArchivo().contains("/"));
        assertTrue(!RegistroGremio.getArchivo().contains("\\"));
    }

    @Test
    void consecutiveEventosMantienenElOrdenDeEscritura() throws IOException {
        Path archivo = carpetaTemporal.resolve("registro_gremio.txt");
        for (int i = 1; i <= 12; i++) {
            RegistroGremio.registrar(archivo, "DADO | Tirada " + i);
        }
        List<String> lineas = RegistroGremio.leer(archivo);
        assertEquals(12, lineas.size());
        assertTrue(lineas.get(0).endsWith("DADO | Tirada 1"));
        assertTrue(lineas.get(11).endsWith("DADO | Tirada 12"));
    }
}
