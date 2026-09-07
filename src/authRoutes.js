// authRoutes.js
// Aqui se definen las rutas (endpoints) del servicio web relacionadas con
// autenticacion: registro e inicio de sesion.

const express = require("express");
const router = express.Router();
const { registrar, iniciarSesion } = require("./authController");

// Ruta para registrar un nuevo usuario.
router.post("/registro", registrar);

// Ruta para iniciar sesion con un usuario ya registrado.
router.post("/login", iniciarSesion);

module.exports = router;
