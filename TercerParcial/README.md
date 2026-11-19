<!-- Proyecto TercerParcial: simulador "Gremio de Aventureros" con arquitectura MVC. -->
# TercerParcial — Gremio de Aventureros

Aplicación de escritorio Java que registra aventureros y simula combates usando reglas de *Calabozos y Dragones 5ª Edición*. El proyecto está desarrollado con **Apache NetBeans 17+**, **Java 11+**, **Maven** y **JUnit Jupiter 5.10.2**, aplicando estrictamente el patrón **MVC**.

## Requisitos previos

| Herramienta | Versión |
|---|---|
| JDK | 11 o superior (verificado con JDK 26) |
| Apache NetBeans | 17 o superior |
| Maven | 3.9+ (o el incluido en NetBeans) |
| JUnit Jupiter | 5.10.2 (declarado en `pom.xml`) |

## Estructura del proyecto

```text
TercerParcial/
├── pom.xml
├── .gitignore
├── registro_gremio.txt              # se genera en ejecución (ignorado por Git)
├── docs/
│   ├── DiagramaUML.txt              # UML textual (PlantUML)
│   ├── DiagramaUML.svg              # diagrama vectorial (imprimir a PDF)
│   └── ejemplo_registro_gremio.txt  # 16 líneas de ejemplo de persistencia
├── PLAN_COMMITS.md                  # plan de 12 commits + comandos GitHub
└── src/
    ├── main/java/com/gremio/
    │   ├── modelo/
    │   │   ├── IAccionCombate.java
    │   │   ├── Aventurero.java
    │   │   ├── Guerrero.java
    │   │   ├── Mago.java
    │   │   ├── Clerigo.java
    │   │   ├── Monje.java
    │   │   ├── Paladin.java
    │   │   ├── Explorador.java
    │   │   ├── Picaro.java
    │   │   ├── Bardo.java
    │   │   ├── Monstruo.java
    │   │   └── RegistroGremio.java
    │   ├── controlador/
    │   │   ├── GremioControlador.java
    │   │   └── CombateControlador.java
    │   └── vista/
    │       ├── VentanaPrincipal.java
    │       └── VentanaPrincipal.form   # JFrame Form de NetBeans
    └── test/java/com/gremio/modelo/
        ├── AventureroTest.java
        ├── ClasesEspecialesTest.java
        └── RegistroGremioTest.java
```

## Cómo abrir y ejecutar

### Desde NetBeans

1. `File → Open Project…` y selecciona la carpeta `TercerParcial`.
2. NetBeans detecta el `pom.xml` como proyecto Maven.
3. Clic derecho en el proyecto → `Run` (o `Shift+F6`).

### Desde terminal

```powershell
cd TercerParcial
mvn test
mvn package
java -jar target\TercerParcial-1.0-SNAPSHOT.jar
```

> El proyecto compila con `javac -encoding UTF-8` incluso sin Maven; las pruebas se ejecutan con JUnit Platform Console 1.10.2. El resultado verificado es **42 pruebas, 42 correctas, 0 fallidas**.

## Arquitectura MVC

- **`modelo`**: contiene la jerarquía (`Aventurero` abstracta + 8 subclases), la interfaz `IAccionCombate`, `Monstruo` y la utilidad de persistencia `RegistroGremio`. Aquí están todas las reglas de negocio: 4d6-drop-lowest, `mod = floor((stat-10)/2)`, CA, competencia `floor((nivel-1)/4)+2`, bono de ataque y subida de nivel.
- **`controlador`**: `GremioControlador` administra una única `List<Aventurero>` polimórfica (sin `instanceof` ni casteos) y genera los dos reportes exigidos. `CombateControlador` gestiona iniciativa, turnos, críticos, fumbles y daño manual. Ninguno de los dos conoce Swing.
- **`vista`**: `VentanaPrincipal` es un **JFrame Form** de NetBeans (`VentanaPrincipal.form` + `initComponents()` con `GroupLayout`). La vista solo pinta componentes y delega cada evento al controlador; no hay `paintComponent` ni dibujo manual. La pestaña **Crear** usa un `JTextField` solo para el nombre, un `JSpinner` (1–20) para el nivel y combos con opciones predeterminadas para PV y el atributo especial (`Sí`/`No` cuando aplica). La decoración se hizo con **iconos vectoriales PNG** generados localmente en `src/main/resources/com/gremio/recursos/` (incluye `d4.png` y `d6.png` para los dados, e iconos de monstruos, clases y pestañas). En el `.form` se usan propiedades `org.netbeans.modules.form.editors2.IconEditor` con copias redimensionadas (`icono_*.png`), para que las imágenes también se vean en el editor de NetBeans, no solo al ejecutar. Los colores están serializados en el `.form` y reforzados en `main()` con `UIManager`, para que se vean igual en el diseñador y en la ejecución.

## Clases disponibles

| Clase | Atributo especial | Daño | Stat principal | Icono |
|---|---|---|---|---|
| Guerrero | Armadura pesada (sí/no) | d6 | FUE | ⚔️ |
| Mago | Espacios de conjuro | d4 | INT | 🔮 |
| Clérigo | Dios venerado | d4 | SAB | ✨ |
| Monje | Puntos de Ki | d6 | DES | 🥋 |
| Paladín | Puntos de fuerza | d6 | FUE | 🛡️ |
| Explorador | Terreno favorecido | d6 | DES | 🏹 |
| Pícaro | Herramientas de ladrón | d6 | DES | 🗡️ |
| Bardo | Instrumento musical | d4 | CAR | 🎵 |

> Nota de diseño: el Paladín deriva el uso de armadura pesada de sus puntos de fuerza (`≥ 15`), conciliando el atributo especial del examen con la regla de CA 16.

## Combate

1. Elegir enemigo: **Goblin** (20 PV, CA 12, Fácil), **Ogro** (50 PV, CA 14, Medio) o **Dragón** (100 PV, CA 16, Difícil).
2. Iniciativa: `3d6 + mod(DES)` para cada aventurero; se ordena de mayor a menor.
3. Cada turno: mensaje polimórfico de la clase + ataque `3d6 + bono` contra la CA.
   - `3×6` = crítico: acierto automático y daño ×2.
   - `3×1` = pifia: fallo automático.
4. Si acierta, el **usuario** pulsa el botón `d4` o `d6` (el correcto queda resaltado). Daño: `max(1, dado + mod(stat principal)) × factor`.
5. Máximo 5 rondas. Resultado: **VICTORIA** o **DERROTA**, con barra de HP y bitácora en pantalla.

## Persistencia

`RegistroGremio` anexa cada evento a `registro_gremio.txt` con `try-with-resources`, `PrintWriter`/`FileWriter` en modo *append* y codificación UTF-8:

```text
yyyy-MM-dd HH:mm:ss | TIPO | Descripción
```

Ejemplo real (16 líneas) en `docs/ejemplo_registro_gremio.txt`. El archivo de la raíz se genera al ejecutar y está excluido por `.gitignore`.

## Reportes

En la pestaña **Resumen**:

1. **Por clase** → `GremioControlador.generarReportePorClase()` devuelve `Map<String,Integer>`.
2. **Nivel > 3** → `GremioControlador.generarReporteNivelMayor(3)` devuelve `List<Aventurero>`.

## Pruebas unitarias

Solo en `src/test/java/com/gremio/modelo/`:

- `AventureroTest` (18 pruebas): constructores, sobrecargas, stats, modificadores, CA, competencia, subida de nivel y validaciones.
- `ClasesEspecialesTest` (17 pruebas): 8 subclases, atributos especiales, dados, mensajes oficiales y fábricas de monstruos.
- `RegistroGremioTest` (7 pruebas): formato de línea, anexado, lectura y validaciones con `@TempDir`.

## Control de versiones

La rama de trabajo prevista es `feature/examen-final`, con 12 commits atómicos y Pull Request a `main`. Los comandos exactos están en [`PLAN_COMMITS.md`](PLAN_COMMITS.md). No se inicializó Git automáticamente; el plan se entrega como instrucciones para ejecutar en tu máquina.
