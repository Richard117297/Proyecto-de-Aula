# Flujo de Equipaje

## Propósito

Explicar cómo se registra, controla y entrega el equipaje asociado a boletos de pasajeros.

## Actores involucrados

- Pasajero
- Empleado
- Sistema StaffTrain

## Flujo paso a paso

| Paso | Acción | Responsable | Resultado |
|---|---|---|---|
| 1 | Registrar cantidad | Pasajero o empleado | Se indica número de maletas. |
| 2 | Validar cantidad | Sistema | No permite más de 2 maletas. |
| 3 | Registrar peso | Pasajero o empleado | Se indica peso por maleta. |
| 4 | Validar peso | Sistema | No permite más de 80 kg por maleta. |
| 5 | Asociar a boleto | Sistema | El equipaje queda ligado al boleto. |
| 6 | Control operativo | Empleado | Revisa estado y datos del equipaje. |
| 7 | Asignar vagón de carga | Empleado o sistema | El equipaje puede quedar `EN_VAGON`. |
| 8 | Entregar equipaje | Empleado | Cambia a estado `ENTREGADO`. |

## Estados que cambian

- Equipaje: `REGISTRADO`.
- Equipaje: `EN_VAGON`.
- Equipaje: `ENTREGADO`.

## Archivos JSON relacionados

- `equipajes.json`
- `boletos.json`
- `vagones.json`

## Servicios relacionados

- `EquipajeService`
- `BoletoService`

## Resultado final del flujo

El equipaje queda controlado por ID y asociado al boleto, respetando las restricciones académicas del sistema.
