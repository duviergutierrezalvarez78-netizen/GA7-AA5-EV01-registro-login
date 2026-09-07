// server.js
// Punto de entrada de la aplicacion. Aqui se configura y se enciende el
// servidor web con Express.

const express = require("express");
const authRoutes = require("./authRoutes");

const app = express();
const PUERTO = 3000;

// Middleware que permite a Express leer JSON en el body de las peticiones.
app.use(express.json());

// Todas las rutas de autenticacion quedan bajo el prefijo /api
app.use("/api", authRoutes);

// Ruta de prueba para confirmar que el servicio esta activo.
app.get("/", (req, res) => {
  res.send("Servicio web de registro y login funcionando correctamente");
});

// Se levanta el servidor en el puerto definido.
app.listen(PUERTO, () => {
  console.log(`Servidor corriendo en http://localhost:${PUERTO}`);
});
