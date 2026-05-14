# Flujo General del Sistema

## Propósito

Explicar cómo se conectan los procesos principales de StaffTrain desde el ingreso del usuario hasta la persistencia de las operaciones en JSON.

## Actores involucrados

- Pasajero
- Empleado
- Administrador
- Sistema StaffTrain

## Flujo paso a paso

| Paso | Acción | Responsable | Resultado |
|---|---|---|---|
| 1 | Abrir StaffTrain | Usuario | Se muestra la bienvenida. |
| 2 | Iniciar sesión | Usuario | El sistema valida credenciales desde `usuarios.json`. |
| 3 | Cargar dashboard | Sistema | Se abre el panel según el rol autenticado. |
| 4 | Comprar boleto | Pasajero | Se crea un boleto en estado `PENDIENTE_VALIDACION`. |
| 5 | Validar boleto | Empleado | El boleto pasa a `VALIDADO` o `RECHAZADO`. |
| 6 | Registrar boleto validado | Administrador | El boleto pasa a `REGISTRADO`. |
| 7 | Controlar equipaje | Empleado | Se valida cantidad y peso de maletas. |
| 8 | Gestionar trenes y rutas | Administrador | Se actualizan rutas, trenes, tarifas y abordajes. |
| 9 | Consultar estado | Pasajero | El pasajero revisa su boleto o solicita cambio de ruta. |
| 10 | Persistir cambios | Sistema | Los repositorios guardan datos en `data/json`. |

## Estados que cambian

- Boleto: `PENDIENTE_VALIDACION`, `VALIDADO`, `RECHAZADO`, `REGISTRADO`, `USADO`.
- Equipaje: `REGISTRADO`, `EN_VAGON`, `ENTREGADO`.
- Tren: `ACTIVO`, `EN_REVISION`, `BAJA`.
- Solicitud de cambio de ruta: `PENDIENTE`, `APROBADA`, `RECHAZADA`.

## Archivos JSON relacionados

- `usuarios.json`
- `boletos.json`
- `equipajes.json`
- `trenes.json`
- `rutas.json`
- `estaciones.json`
- `abordajes.json`
- `solicitudes-cambio-ruta.json`

## Servicios relacionados

- `AuthService`
- `BoletoService`
- `EquipajeService`
- `TrenService`
- `RutaService`
- `AbordajeService`

## Resultado final del flujo

El sistema mantiene trazabilidad completa por rol: el pasajero compra y consulta, el empleado valida y opera, el administrador registra y configura, y toda la información queda persistida en JSON.
