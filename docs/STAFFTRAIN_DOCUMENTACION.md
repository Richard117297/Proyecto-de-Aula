# StaffTrain - Sistema de Gestion de Trenes

## Descripcion general

StaffTrain es una aplicacion de escritorio para gestionar trenes de transporte de pasajeros. La solucion usa Java, JavaFX, FXML, Maven y persistencia en archivos JSON visibles en `data/json`.

## Pregunta problema

Como organizar la compra de boletos, control de equipaje, gestion de trenes, rutas y abordaje de una estacion ferroviaria manteniendo reglas de negocio claras y estructuras de datos academicas?

## Objetivo general

Desarrollar una aplicacion JavaFX funcional que permita a pasajeros, empleados y administradores gestionar las operaciones principales del sistema StaffTrain con persistencia JSON.

## Objetivos especificos

- Autenticar usuarios por rol desde JSON.
- Permitir compra, validacion y registro administrativo de boletos.
- Controlar equipaje por cantidad y peso.
- Gestionar trenes, estados y calculo de vagones.
- Recomendar rutas con un grafo de estaciones.
- Publicar orden de abordaje con cola de prioridad.

## Marco conceptual

Las listas enlazadas simples se usan para recorrer boletos, trenes, rutas y pasajeros sin depender exclusivamente de colecciones de Java. Las pilas modelan equipaje y vagones cuando el ultimo elemento agregado debe procesarse primero. La cola de prioridad ordena abordaje por categoria premium, ejecutivo y estandar, respetando el orden de atras hacia adelante. El grafo representa estaciones y conexiones ponderadas por kilometros. JSON permite persistencia legible sin base de datos relacional.

## Metodologia

Se usa cascada adaptada: levantamiento de RF-01 a RF-17, diseno de arquitectura por capas, implementacion incremental, validacion funcional y documentacion.

## Arquitectura del software

El flujo es `FXML -> Controller -> Service -> Repository -> JSON`.

- `app`: inicia JavaFX y ejecuta el seed inicial.
- `config`: rutas de vistas, CSS y almacenamiento JSON.
- `controllers`: coordinan eventos de la interfaz.
- `models`: entidades y estructuras de datos.
- `repositories`: lectura/escritura JSON con Jackson.
- `services`: reglas de negocio.
- `utils`: navegacion, sesion, alertas, fechas y validaciones.
- `exceptions`: errores de negocio, validacion y repositorio.
- `resources/views`: FXML compatibles con SceneBuilder.
- `data/json`: persistencia visible.
- `docs`: documentacion y legado C#.

## Modulos

- Autenticacion: bienvenida, login por rol y recuperacion local.
- Pasajero: compra boleto, consulta boleto, solicitud de cambio de ruta y perfil.
- Empleado: validacion/rechazo de boletos, control y entrega de equipaje, lista de pasajeros.
- Administrador: gestion de trenes, tarifas/boletos, rutas, pasajeros/vagones y orden de abordaje.
- Boletos: estados `PENDIENTE_VALIDACION`, `VALIDADO`, `RECHAZADO`, `REGISTRADO`, `USADO`.
- Equipaje: estados `REGISTRADO`, `EN_VAGON`, `ENTREGADO`.
- Trenes: estados `ACTIVO`, `EN_REVISION`, `BAJA`.
- Rutas: grafo A-K y Dijkstra manual.
- Abordaje: cola de prioridad.

## Cobertura RF-01 a RF-17

- RF-01: `AuthService`, `LoginController`, `usuarios.json`.
- RF-02: compra de boletos desde dashboard pasajero.
- RF-03: validacion/rechazo de boletos por empleado.
- RF-04: maximo 2 maletas y 80 kg por maleta en `EquipajeService`.
- RF-05: entrega por ID en `EntregarEquipajeController`.
- RF-06: agregar tren desde modulo administrador.
- RF-07: baja logica con estado `BAJA`.
- RF-08: almacenamiento de trenes y capacidad por tipo.
- RF-09: estructura para modificar rutas no iniciadas.
- RF-10: rutas publicadas y recomendacion por grafo.
- RF-11: estaciones, origen, destino y conexiones configuradas.
- RF-12: tarifas por distancia, base por km y multiplicador.
- RF-13: boleto con datos de pasajero, ruta, contacto, categoria y valor.
- RF-14: empleados consultan/modifican estado de boletos.
- RF-15: orden de abordaje con prioridad y vagones.
- RF-16: administrador consulta trenes y rutas.
- RF-17: calculo de vagones en `TrenService`.

## Logica del grafo

`GrafoEstaciones` almacena nodos y conexiones ponderadas. `obtenerRutaMasCorta` aplica Dijkstra manual y devuelve `ResultadoRuta` con estaciones y distancia total.

## Logica de tarifas

`valor = distanciaKm * valorBasePorKm * multiplicadorCategoria`. Los multiplicadores estan en `tarifas.json`.

## Logica de vagones

Cada vagon considera capacidad de 40 personas con tripulacion operativa. Se calcula un vagon de carga por cada dos vagones de pasajeros y se valida contra 28 vagones para Mercedes-Benz o 32 para Arnold.

## Logica de abordaje

`ColaPrioridadPasajeros` ordena primero premium, luego ejecutivo y despues estandar. Dentro de cada categoria prioriza los vagones posteriores.

## Persistencia JSON

`JsonDataInitializer` asegura archivos iniciales. Los repositorios concretos heredan de `AbstractJsonRepository` y usan `JsonFileManager` con Jackson.

## Credenciales de prueba

- Administrador: `admin` / `admin123`
- Empleado: `empleado` / `empleado123`
- Pasajero: `pasajero` / `pasajero123`

## Ejecucion

```bash
mvn clean compile
mvn javafx:run
```

## SceneBuilder

Abrir los FXML desde `src/main/resources/co/edu/upb/trenes/views`. Los controladores estan en `src/main/java/co/edu/upb/trenes/controllers`.

## Diseno visual

La interfaz usa paleta terracota, crema y blanco, con sidebar por rol, tarjetas limpias y botones consistentes desde `app.css`.

## Referencias

[1] Delgado, L. d., & Alonso, L. M. (2021). Software Development Models. Editorial Ediciones Futuro.
[2] Flores, M. A. (2022). Lenguaje de Programacion C Sharp.
[3] Videla, W. F. (2017). Estructura de Datos.
[4] A. Bosco, Estructuras de datos y algoritmos. Editorial Alpha, 2020.
[5] Guardati, O. C. (2006). Estructura de datos Tercera edicion. Mc Graw Hill.
[6] Universidad Don Bosco. (2020). Guia 8. Tablas Hash.
[7] Seymour, L. Grafos. Estructura de Datos.
[8] Microsoft. Visual Studio Code Documentation.
[9] Microsoft. C# in Visual Studio Code.
[10] Visual Studio: IDE y Editor de codigo. (2025).
[11] Bell, D., & Parr, M. (2010). C# Para Estudiantes. Pearson.
[12] Bosco, U. D. Programacion con estructuras de datos.
[13] Erickson, J. (2024). What is JSON?

## Conclusiones

StaffTrain queda como una base funcional y mantenible para evolucionar el sistema academico. La migracion conserva el legado como referencia, usa JSON visible y mantiene estructuras de datos manuales dentro de la logica.
