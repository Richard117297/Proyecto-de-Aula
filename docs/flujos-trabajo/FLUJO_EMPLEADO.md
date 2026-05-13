# Flujo del Empleado

## Propósito

Explicar cómo el empleado opera la validación de boletos, el control de equipaje y la consulta de pasajeros.

## Actores involucrados

- Empleado
- Pasajero
- Sistema StaffTrain
- Administrador

## Flujo paso a paso

| Paso | Acción | Responsable | Resultado |
|---|---|---|---|
| 1 | Iniciar sesión | Empleado | Accede al dashboard operativo. |
| 2 | Consultar boletos pendientes | Empleado | Visualiza boletos `PENDIENTE_VALIDACION`. |
| 3 | Seleccionar boleto | Empleado | Se muestra detalle del pasajero, ruta, valor y equipaje. |
| 4 | Validar boleto | Empleado | El boleto cambia a `VALIDADO`. |
| 5 | Rechazar boleto | Empleado | El boleto cambia a `RECHAZADO`. |
| 6 | Controlar equipaje | Empleado | Verifica máximo 2 maletas y 80 kg por maleta. |
| 7 | Asociar equipaje | Empleado | El equipaje queda relacionado al boleto. |
| 8 | Entregar equipaje | Empleado | El equipaje cambia a `ENTREGADO`. |
| 9 | Consultar lista de pasajeros | Empleado | Filtra pasajeros por ruta, tren, categoría o estado. |

## Estados que cambian

- Boleto: `PENDIENTE_VALIDACION` a `VALIDADO` o `RECHAZADO`.
- Equipaje: `REGISTRADO` o `EN_VAGON` a `ENTREGADO`.

## Archivos JSON relacionados

- `boletos.json`
- `equipajes.json`
- `pasajeros.json`
- `trenes.json`
- `rutas.json`

## Servicios relacionados

- `BoletoService`
- `EquipajeService`
- `RutaService`
- `TrenService`

## Resultado final del flujo

El empleado garantiza que sólo los boletos válidos avancen al flujo administrativo y que el equipaje cumpla las restricciones del sistema.
