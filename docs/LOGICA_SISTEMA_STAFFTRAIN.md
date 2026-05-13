# Logica del Sistema StaffTrain

## Flujo pasajero

El pasajero inicia sesion, entra a su dashboard, compra un boleto, registra equipaje opcional, consulta su boleto, solicita cambio de ruta y edita datos basicos del perfil.

## Flujo empleado

El empleado consulta boletos pendientes, valida o rechaza cada boleto, registra equipaje con limites, entrega equipaje por ID y consulta pasajeros asociados a los boletos.

## Flujo administrador

El administrador agrega, verifica y da de baja trenes; consulta informacion; registra boletos validados; recomienda rutas; calcula vagones; revisa solicitudes; y publica orden de abordaje.

## Compra de boleto

La pantalla pasajero pide origen, destino, categoria, pago, datos personales, contacto y equipaje. `RutaService` recomienda ruta por grafo, `BoletoService` calcula tarifa y persiste en `boletos.json`. `EquipajeService` persiste equipaje en `equipajes.json`.

## Validacion

El empleado cambia el estado de `PENDIENTE_VALIDACION` a `VALIDADO` o `RECHAZADO`. El administrador registra despues los boletos validados.

## Equipaje

El control valida maximo 2 maletas por pasajero y maximo 80 kg por maleta. La entrega cambia el estado a `ENTREGADO`.

## Cambio de ruta

El pasajero crea una solicitud en `solicitudes-cambio-ruta.json` con estado `PENDIENTE`. El flujo administrativo puede ampliarse para aprobar o rechazar.

## Creacion y recomendacion de ruta

El grafo base se carga desde estaciones y conexiones. La recomendacion usa Dijkstra manual y devuelve camino y distancia.

## Orden de abordaje

El administrador publica un orden desde los boletos. `AbordajeService` usa `ColaPrioridadPasajeros` y guarda el resultado en `abordajes.json`.
