<div align="center">

  <img src="./docs/assets/stafftrain-logo.svg" alt="StaffTrain Logo" width="520" />

  # StaffTrain — Sistema de Gestión de Trenes

  **Aplicación de escritorio para la gestión de trenes de transporte de pasajeros, desarrollada con Java, JavaFX, Maven y persistencia JSON.**

</div>

Proyecto academico migrado desde una base inicial en C# hacia Java 17, JavaFX, FXML, Maven y persistencia en archivos JSON.

## Tecnologias

- Java 17+
- JavaFX Controls y FXML
- Maven
- Jackson Databind para JSON
- FXML compatible con SceneBuilder
- Persistencia visible en `data/json`

## Arquitectura

El paquete base es `co.edu.upb.trenes`.

- `app`: arranque JavaFX con `MainApp`.
- `config`: configuracion general y ruta relativa de JSON.
- `controllers`: controladores JavaFX por modulo.
- `models`: entidades y estructuras de datos academicas.
- `repositories`: patron Repository con implementaciones JSON.
- `services`: reglas de negocio y coordinacion del dominio.
- `utils`: navegacion, alertas, validaciones y fechas.
- `exceptions`: excepciones de negocio, validacion y repositorio.
- `src/main/resources/.../views`: vistas FXML para SceneBuilder.
- `data/json`: archivos editables de persistencia.

El codigo C# anterior se conserva como referencia en `docs/legacy-csharp`.

## Ejecucion

Compilar:

```bash
mvn clean compile
```

Ejecutar:

```bash
mvn javafx:run
```

Credenciales iniciales:

- `admin` / `admin123`
- `empleado` / `empleado123`
- `pasajero` / `pasajero123`

## SceneBuilder

Los FXML estan en:

```text
src/main/resources/co/edu/upb/trenes/views
```

Cada archivo declara su `fx:controller` ubicado en `src/main/java/co/edu/upb/trenes/controllers`.

## Persistencia JSON

La persistencia no usa SQL. `JsonStorageConfig` define la ruta relativa `data/json`, `JsonDataInitializer` asegura que los archivos existan y `JsonFileManager` centraliza lectura y escritura con Jackson.

Los servicios no leen archivos directamente. Los controladores llaman servicios, los servicios llaman repositorios y los repositorios usan JSON.

## Modulos y RF

- RF-01: autenticacion con `AuthService`, `UsuarioJsonRepository` y `login.fxml`.
- RF-02, RF-03, RF-12, RF-13, RF-14: boletos con lista enlazada simple y repositorio JSON.
- RF-04, RF-05: equipaje con pila, maximo 2 maletas y maximo 80 kg por maleta.
- RF-06, RF-07, RF-08, RF-17: trenes y vagones con lista enlazada, pila y calculo de vagones.
- RF-09, RF-10, RF-11: rutas y estaciones con lista enlazada y grafo.
- RF-15, RF-16: informacion en estacion y abordaje con cola de prioridad.

## Estado actual

Aplicacion base StaffTrain funcional: bienvenida, login por rol, dashboards, compra de boletos, validacion de boletos, equipaje, trenes, rutas por grafo, calculo de vagones, abordaje y documentacion.

## Documentacion

- `docs/STAFFTRAIN_DOCUMENTACION.md`
- `docs/LOGICA_SISTEMA_STAFFTRAIN.md`

## Documentacion de flujos de trabajo

La carpeta `docs/flujos-trabajo/` contiene la explicacion paso a paso de los procesos principales del sistema:

- Flujo general del sistema.
- Flujo del pasajero.
- Flujo del empleado.
- Flujo del administrador.
- Flujo de compra de boleto.
- Flujo de validacion de boleto.
- Flujo de equipaje.
- Flujo de rutas y trenes.

## Proximos pasos

- Completar formularios finales de cada modulo.
- Agregar pruebas unitarias para servicios.
- Refinar diseno visual de pantallas.
- Conectar tablas y CRUD completos por modulo.
