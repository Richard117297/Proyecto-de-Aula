# Contributing to StaffTrain

Gracias por contribuir a StaffTrain. Este repositorio sigue una arquitectura por capas y debe mantenerse claro, compilable y documentado.

## Cómo clonar

```bash
git clone <url-del-repositorio>
cd Proyecto-de-Aula
```

## Ramas

Usa ramas descriptivas:

- `feature/nombre-del-cambio`
- `fix/nombre-del-arreglo`
- `docs/nombre-documentacion`
- `refactor/nombre-refactor`

No trabajes directamente sobre `main` si el flujo del equipo usa ramas de integración.

## Compilar y ejecutar

Compilar:

```bash
mvn clean compile
```

Ejecutar:

```bash
mvn javafx:run
```

## Arquitectura obligatoria

Respeta el flujo:

```text
FXML -> Controller -> Service -> Repository -> JSON
```

Reglas:

- Los controladores coordinan la interfaz.
- Los servicios contienen reglas de negocio.
- Los repositorios leen y escriben JSON.
- No se debe agregar SQL ni base de datos relacional.
- No se deben consultar archivos JSON directamente desde FXML o controladores.

## Persistencia JSON

Los archivos en `data/json` son visibles para revisión académica. Modifícalos sólo cuando el cambio lo justifique y explica el motivo en el Pull Request.

## Documentación

Cuando agregues o cambies un flujo funcional, actualiza:

- `README.md`, si afecta la presentación general.
- `docs/STAFFTRAIN_DOCUMENTACION.md`, si afecta arquitectura o módulos.
- `docs/LOGICA_SISTEMA_STAFFTRAIN.md`, si afecta reglas de negocio.
- `docs/flujos-trabajo/`, si afecta procesos paso a paso.

## Pull Requests

Cada Pull Request debe incluir:

- Resumen del cambio.
- Módulos afectados.
- Validaciones ejecutadas.
- Evidencia visual si cambia JavaFX.
- Nota sobre JSON si aplica.

Antes de solicitar revisión, confirma que `mvn clean compile` pasa correctamente.
