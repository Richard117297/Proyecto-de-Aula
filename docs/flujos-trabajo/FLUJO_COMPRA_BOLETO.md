# Flujo de Compra de Boleto

## Propósito

Explicar detalladamente cómo un pasajero compra un boleto y qué ocurre después de confirmar el pago.

## Actores involucrados

- Pasajero
- Sistema StaffTrain
- Empleado

## Flujo paso a paso

| Paso | Acción | Responsable | Resultado |
|---|---|---|---|
| 1 | Seleccionar origen | Pasajero | El sistema recibe estación inicial. |
| 2 | Seleccionar destino | Pasajero | El sistema calcula ruta recomendada. |
| 3 | Mostrar mapa/grafo | Sistema | El pasajero entiende el recorrido. |
| 4 | Calcular distancia | Sistema | Se obtiene distancia total del grafo. |
| 5 | Seleccionar tren | Pasajero | El boleto queda asociado a un tren activo. |
| 6 | Seleccionar categoría | Pasajero | Se define Premium, Ejecutivo o Estándar. |
| 7 | Calcular valor | Sistema | Aplica distancia, tarifa base y multiplicador. |
| 8 | Registrar datos | Pasajero | Se completa información personal y contacto. |
| 9 | Registrar equipaje | Pasajero | Se validan máximo 2 maletas y 80 kg por maleta. |
| 10 | Revisar preboleto | Pasajero | Puede verificar información antes de pagar. |
| 11 | Confirmar pago | Pasajero | Se guarda el boleto. |
| 12 | Crear estado inicial | Sistema | El boleto queda `PENDIENTE_VALIDACION`. |

## Estados que cambian

- Boleto nuevo: `PENDIENTE_VALIDACION`.
- Equipaje nuevo: `REGISTRADO`.

## Archivos JSON relacionados

- `boletos.json`
- `equipajes.json`
- `rutas.json`
- `estaciones.json`
- `trenes.json`
- `tarifas.json`

## Servicios relacionados

- `BoletoService`
- `EquipajeService`
- `RutaService`
- `TrenService`

## Resultado final del flujo

El boleto no queda aprobado inmediatamente. Después de comprar, un empleado debe revisarlo y validarlo o rechazarlo.
