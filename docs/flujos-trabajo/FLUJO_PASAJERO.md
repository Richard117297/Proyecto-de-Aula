# Flujo del Pasajero

## Propósito

Describir las acciones disponibles para el pasajero y el resultado de cada operación dentro de StaffTrain.

## Actores involucrados

- Pasajero
- Sistema StaffTrain
- Empleado, como validador posterior del boleto
- Administrador, cuando revisa solicitudes de cambio de ruta

## Flujo paso a paso

| Paso | Acción | Responsable | Resultado |
|---|---|---|---|
| 1 | Iniciar sesión | Pasajero | Accede al dashboard de pasajero. |
| 2 | Elegir viajar | Pasajero | Se abre la compra de boleto. |
| 3 | Seleccionar origen y destino | Pasajero | El sistema calcula ruta recomendada con el grafo. |
| 4 | Revisar mapa y ruta | Pasajero | Visualiza estaciones, distancia e intermedias. |
| 5 | Seleccionar tren y categoría | Pasajero | Se calcula el valor según distancia y tarifa. |
| 6 | Registrar datos personales | Pasajero | Se completa la información del boleto. |
| 7 | Registrar contacto de emergencia | Pasajero | El boleto queda con contacto asociado. |
| 8 | Registrar equipaje | Pasajero | El sistema valida máximo 2 maletas y 80 kg por maleta. |
| 9 | Revisar preboleto | Pasajero | Confirma datos antes de pagar. |
| 10 | Confirmar pago | Pasajero | Se crea boleto en `PENDIENTE_VALIDACION`. |
| 11 | Consultar mi boleto | Pasajero | Revisa estado y datos registrados. |
| 12 | Solicitar cambio de ruta | Pasajero | Se crea solicitud en estado `PENDIENTE`. |
| 13 | Editar perfil básico | Pasajero | Actualiza nombre, documento o teléfono según permisos. |

## Estados que cambian

- Boleto: inicia en `PENDIENTE_VALIDACION`.
- Equipaje: inicia en `REGISTRADO`.
- Solicitud de cambio de ruta: inicia en `PENDIENTE`.

## Archivos JSON relacionados

- `boletos.json`
- `equipajes.json`
- `pasajeros.json`
- `rutas.json`
- `trenes.json`
- `solicitudes-cambio-ruta.json`

## Servicios relacionados

- `AuthService`
- `BoletoService`
- `EquipajeService`
- `RutaService`
- `TrenService`
- `UsuarioService`

## Resultado final del flujo

El pasajero obtiene un boleto pendiente de validación, puede revisar su información y conserva trazabilidad sobre equipaje, ruta, tren y estado del proceso.
