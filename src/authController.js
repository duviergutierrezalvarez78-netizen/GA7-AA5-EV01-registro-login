// authController.js
// Aqui esta la logica de negocio para el registro y el inicio de sesion.
// Las rutas (routes) solo reciben la peticion y llaman a estas funciones.

const bcrypt = require("bcryptjs");
const { buscarPorUsuario, agregarUsuario } = require("./usuarios");

// POST /api/registro
// Recibe { usuario, password } en el body de la peticion.
function registrar(req, res) {
  const { usuario, password } = req.body;

  // Validacion basica: los dos campos son obligatorios.
  if (!usuario || !password) {
    return res.status(400).json({
      mensaje: "El usuario y la contrasena son obligatorios",
    });
  }

  // Verificamos que el usuario no exista ya registrado.
  const existente = buscarPorUsuario(usuario);
  if (existente) {
    return res.status(409).json({
      mensaje: "El usuario ya se encuentra registrado",
    });
  }

  // La contrasena nunca se guarda en texto plano: se genera un hash con bcrypt.
  const passwordHash = bcrypt.hashSync(password, 10);
  agregarUsuario(usuario, passwordHash);

  return res.status(201).json({
    mensaje: "Usuario registrado correctamente",
  });
}

// POST /api/login
// Recibe { usuario, password } en el body de la peticion.
function iniciarSesion(req, res) {
  const { usuario, password } = req.body;

  if (!usuario || !password) {
    return res.status(400).json({
      mensaje: "El usuario y la contrasena son obligatorios",
    });
  }

  // Buscamos el usuario en la "base de datos" en memoria.
  const encontrado = buscarPorUsuario(usuario);

  // Si no existe el usuario, la autenticacion falla.
  if (!encontrado) {
    return res.status(401).json({
      mensaje: "Error en la autenticacion",
    });
  }

  // Comparamos la contrasena enviada contra el hash guardado.
  const passwordCorrecta = bcrypt.compareSync(password, encontrado.passwordHash);

  if (!passwordCorrecta) {
    return res.status(401).json({
      mensaje: "Error en la autenticacion",
    });
  }

  // Usuario y contrasena correctos.
  return res.status(200).json({
    mensaje: "Autenticacion satisfactoria",
  });
}

module.exports = { registrar, iniciarSesion };
