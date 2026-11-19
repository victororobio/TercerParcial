<!-- Plan de control de versiones: 12 commits atómicos, rama feature/examen-final y PR a main. -->
# Plan de commits y GitHub

Este plan entrega **12 commits atómicos**, todos sobre la rama `feature/examen-final`, seguidos de un Pull Request a `main` y su fusión. **No se inicializó Git ni se creó el remoto automáticamente**: ejecuta estos comandos en tu máquina cuando quieras publicar el proyecto.

## 0. Preparación (una sola vez)

```powershell
cd "C:\Users\usuario\Documents\Proyecto predeterminado\TercerParcial"

git init
git config user.name "Tu Nombre"
git config user.email "tu.correo@ejemplo.com"
git checkout -b feature/examen-final

# Comprobación antes del primer commit (archivos que NO deben subir)
git status --short
```

> `.gitignore` excluye `/target/`, `**/*.class`, `/registro_gremio.txt` y carpetas del IDE. El archivo `docs/ejemplo_registro_gremio.txt` **sí** se versiona.

## 1. Estructura Maven y exclusiones

```powershell
git add pom.xml .gitignore
git commit -m "chore: configurar proyecto Maven Java 11 y .gitignore"
```

## 2. Contrato de combate y superclase

```powershell
git add src/main/java/com/gremio/modelo/IAccionCombate.java
git add src/main/java/com/gremio/modelo/Aventurero.java
git commit -m "feat(modelo): agregar interfaz IAccionCombate y clase abstracta Aventurero"
```

## 3. Primera tanda de subclases

```powershell
git add src/main/java/com/gremio/modelo/Guerrero.java
git add src/main/java/com/gremio/modelo/Mago.java
git add src/main/java/com/gremio/modelo/Clerigo.java
git commit -m "feat(modelo): implementar Guerrero, Mago y Clerigo con acciones oficiales"
```

## 4. Segunda tanda de subclases

```powershell
git add src/main/java/com/gremio/modelo/Monje.java
git add src/main/java/com/gremio/modelo/Paladin.java
git add src/main/java/com/gremio/modelo/Explorador.java
git commit -m "feat(modelo): implementar Monje, Paladin y Explorador con reglas de CA"
```

## 5. Última tanda de subclases

```powershell
git add src/main/java/com/gremio/modelo/Picaro.java
git add src/main/java/com/gremio/modelo/Bardo.java
git commit -m "feat(modelo): implementar Picaro y Bardo y completar las ocho clases"
```

## 6. Enemigos

```powershell
git add src/main/java/com/gremio/modelo/Monstruo.java
git commit -m "feat(modelo): agregar Monstruo con fabricas de Goblin, Ogro y Dragon"
```

## 7. Persistencia

```powershell
git add src/main/java/com/gremio/modelo/RegistroGremio.java
git commit -m "feat(persistencia): anexar eventos con try-with-resources y timestamp"
```

## 8. Controlador del gremio y reportes

```powershell
git add src/main/java/com/gremio/controlador/GremioControlador.java
git commit -m "feat(controlador): administrar lista polimorfica y generar dos reportes"
```

## 9. Controlador de combate

```powershell
git add src/main/java/com/gremio/controlador/CombateControlador.java
git commit -m "feat(controlador): simular iniciativa, turnos, criticos y dano manual"
```

## 10. Interfaz gráfica (JFrame Form)

```powershell
git add src/main/java/com/gremio/vista/VentanaPrincipal.form
git add src/main/java/com/gremio/vista/VentanaPrincipal.java
git add src/main/resources/com/gremio/recursos
git commit -m "feat(vista): agregar JFrame Form con cinco pestañas, campo, spinner, iconos vectoriales y colores"
```

## 11. Pruebas unitarias y muestra de registro

```powershell
git add src/test/java/com/gremio/modelo/AventureroTest.java
git add src/test/java/com/gremio/modelo/ClasesEspecialesTest.java
git add src/test/java/com/gremio/modelo/RegistroGremioTest.java
git add docs/ejemplo_registro_gremio.txt
git commit -m "test(modelo): agregar 42 pruebas JUnit 5 con TempDir para persistencia"
```

## 12. Documentación

```powershell
git add README.md PLAN_COMMITS.md docs/DiagramaUML.txt docs/DiagramaUML.svg
git commit -m "docs: entregar README, diagrama UML y plan de commits"

git log --oneline --decorate
```

## Publicación, Pull Request y fusión

```powershell
# 1) Crear el repositorio remoto vacío en GitHub desde el navegador
#    (nombre sugerido: TercerParcial), luego:
git remote add origin https://github.com/TU_USUARIO/TercerParcial.git
git branch -M main
git push -u origin main

git push -u origin feature/examen-final

# 2) Pull Request (requiere GitHub CLI autenticado: gh auth login)
gh pr create --base main --head feature/examen-final `
  --title "Examen final - Gremio de Aventureros" `
  --body "Implementa MVC, 8 clases, persistencia, JUnit y JFrame Form."

# 3) Fusionar el PR y actualizar la rama local
gh pr merge feature/examen-final --merge
git checkout main
git pull origin main

# 4) Evidencia final para la entrega
git log --oneline --graph --all --decorate
```

## Verificación recomendada antes de publicar

```powershell
mvn test
git status --short
```

El historial debe mostrar 12 commits en `feature/examen-final`, un PR fusionado en `main` y ningún archivo compilado o `registro_gremio.txt` versionado.
