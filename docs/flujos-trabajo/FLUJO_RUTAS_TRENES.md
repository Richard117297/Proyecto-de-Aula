# Flujo de Rutas y Trenes

## Propósito

Explicar cómo StaffTrain usa el grafo de estaciones, las rutas publicadas y la gestión de trenes para operar los viajes.

## Actores involucrados

- Administrador
- Pasajero
- Sistema StaffTrain

## Flujo paso a paso

| Paso | Acción | Responsable | Resultado |
|---|---|---|---|
| 1 | Cargar estaciones | Sistema | Se inicializa el grafo desde JSON. |
| 2 | Seleccionar origen/destino | Pasajero o administrador | Se solicita recomendación de ruta. |
| 3 | Calcular mejor camino | Sistema | El grafo obtiene ruta y distancia. |
| 4 | Publicar ruta | Administrador | La ruta queda disponible para compra. |
| 5 | Asignar tren | Administrador | Una ruta puede quedar asociada a tren activo. |
| 6 | Verificar tren | Administrador | Revisa kilometraje, capacidad y estado. |
| 7 | Dar de baja | Administrador | El tren cambia a `BAJA` sin borrar historial. |
| 8 | Calcular vagones | Sistema | Determina vagones de pasajeros y carga. |
| 9 | Publicar abordaje | Administrador | Se genera orden por prioridad. |

## Estados que cambian

- Tren: `ACTIVO`, `EN_REVISION`, `BAJA`.
- Ruta: disponible o modificada según operación administrativa.
- Abordaje: publicado en `abordajes.json`.

## Archivos JSON relacionados

- `estaciones.json`
- `conexiones-estaciones.json`
- `rutas.json`
- `trenes.json`
- `vagones.json`
- `abordajes.json`

## Servicios relacionados

- `RutaService`
- `TrenService`
- `AbordajeService`
- `EstacionService`

## Resultado final del flujo

El sistema permite recomendar rutas con grafo, asignar trenes, calcular capacidad y publicar el orden de abordaje de forma trazable.
