# Flujo de Validación de Boleto

## Propósito

Describir cómo un empleado revisa boletos comprados y cómo esa decisión afecta al pasajero y al administrador.

## Actores involucrados

- Empleado
- Pasajero
- Administrador
- Sistema StaffTrain

## Flujo paso a paso

| Paso | Acción | Responsable | Resultado |
|---|---|---|---|
| 1 | Consultar pendientes | Empleado | Se listan boletos `PENDIENTE_VALIDACION`. |
| 2 | Seleccionar boleto | Empleado | Se muestra información completa. |
| 3 | Revisar datos | Empleado | Verifica pasajero, ruta, tren, pago y equipaje. |
| 4 | Validar boleto | Empleado | Estado cambia a `VALIDADO`. |
| 5 | Rechazar boleto | Empleado | Estado cambia a `RECHAZADO`. |
| 6 | Consultar estado | Pasajero | El pasajero ve si fue validado o rechazado. |
| 7 | Registrar final | Administrador | Si está validado, puede pasar a `REGISTRADO`. |

## Estados que cambian

- Boleto: `PENDIENTE_VALIDACION` a `VALIDADO`.
- Boleto: `PENDIENTE_VALIDACION` a `RECHAZADO`.
- Boleto validado: puede pasar a `REGISTRADO` por administrador.

## Archivos JSON relacionados

- `boletos.json`
- `equipajes.json`
- `pasajeros.json`

## Servicios relacionados

- `BoletoService`
- `EquipajeService`

## Resultado final del flujo

El boleto queda listo para registro administrativo si fue validado, o queda rechazado para que el pasajero conozca el estado.
