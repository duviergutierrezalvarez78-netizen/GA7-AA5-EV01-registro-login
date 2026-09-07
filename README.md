# API Registro y Login - GA7-220501096-AA5-EV01

Servicio web REST hecho con Node.js y Express para registro de usuarios e
inicio de sesion.

## Estructura del proyecto

```
api-registro-login/
├── src/
│   ├── server.js         -> punto de entrada, levanta el servidor
│   ├── authRoutes.js      -> define los endpoints /api/registro y /api/login
│   ├── authController.js  -> logica de registro e inicio de sesion
│   └── usuarios.js        -> "base de datos" de usuarios en memoria
├── package.json
└── README.md
```

## Como ejecutarlo

1. Instalar Node.js (version 18 o superior).
2. Instalar las dependencias:
   ```
   npm install
   ```
3. Levantar el servidor:
   ```
   npm start
   ```
4. El servidor queda escuchando en `http://localhost:3000`

## Endpoints

### Registrar usuario
`POST /api/registro`

Body (JSON):
```json
{ "usuario": "duvi", "password": "1234" }
```

Respuestas:
- 201: usuario registrado correctamente
- 400: falta usuario o contrasena
- 409: el usuario ya existe

### Iniciar sesion
`POST /api/login`

Body (JSON):
```json
{ "usuario": "duvi", "password": "1234" }
```

Respuestas:
- 200: `{ "mensaje": "Autenticacion satisfactoria" }`
- 401: `{ "mensaje": "Error en la autenticacion" }`
- 400: falta usuario o contrasena

## Notas

- La contrasena se guarda con hash (bcrypt), nunca en texto plano.
- Los usuarios se guardan en memoria (arreglo), se reinician cada vez que se
  apaga el servidor. Esto es suficiente para el alcance de la evidencia.
