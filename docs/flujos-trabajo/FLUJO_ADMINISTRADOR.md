# Flujo del Administrador

## Propósito

Documentar las operaciones administrativas de StaffTrain sobre trenes, rutas, tarifas, boletos validados, solicitudes y abordaje.

## Actores involucrados

- Administrador
- Sistema StaffTrain
- Empleado
- Pasajero

## Flujo paso a paso

| Paso | Acción | Responsable | Resultado |
|---|---|---|---|
| 1 | Iniciar sesión | Administrador | Accede al dashboard administrativo. |
| 2 | Agregar tren | Administrador | Se registra un tren activo en `trenes.json`. |
| 3 | Verificar tren | Administrador | Se actualiza kilometraje o estado operativo. |
| 4 | Dar de baja tren | Administrador | El tren cambia a estado `BAJA`. |
| 5 | Consultar trenes | Administrador | Revisa información, ruta asignada y capacidad. |
| 6 | Gestionar tarifas | Administrador | Ajusta valor por kilómetro y categoría. |
| 7 | Registrar boleto validado | Administrador | El boleto pasa de `VALIDADO` a `REGISTRADO`. |
| 8 | Gestionar rutas | Administrador | Crea, modifica o publica rutas disponibles. |
| 9 | Recomendar rutas | Sistema | Usa el grafo de estaciones para calcular mejor camino. |
| 10 | Revisar cambios de ruta | Administrador | Aprueba o rechaza solicitudes del pasajero. |
| 11 | Calcular vagones | Administrador | Determina vagones de pasajeros y carga. |
| 12 | Publicar abordaje | Administrador | Genera orden por prioridad y posición. |

## Estados que cambian

- Tren: `ACTIVO`, `EN_REVISION`, `BAJA`.
- Boleto: `VALIDADO` a `REGISTRADO`.
- Solicitud de ruta: `PENDIENTE` a `APROBADA` o `RECHAZADA`.
- Abordaje: se publica en el archivo correspondiente.

## Archivos JSON relacionados

- `trenes.json`
- `vagones.json`
- `rutas.json`
- `estaciones.json`
- `tarifas.json`
- `boletos.json`
- `abordajes.json`
- `solicitudes-cambio-ruta.json`

## Servicios relacionados

- `TrenService`
- `RutaService`
- `BoletoService`
- `AbordajeService`
- `EstacionService`

## Resultado final del flujo

El administrador mantiene la configuración operativa del sistema y formaliza los procesos validados por empleados y solicitados por pasajeros.
