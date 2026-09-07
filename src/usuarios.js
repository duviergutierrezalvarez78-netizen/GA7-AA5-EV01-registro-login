// usuarios.js
// Este archivo funciona como una "base de datos" simple en memoria.
// Guarda un arreglo con los usuarios registrados mientras el servidor esta encendido.
// En un proyecto real esto se reemplazaria por una base de datos como MySQL o MongoDB.

const usuarios = [];

// Busca un usuario por su nombre de usuario.
// Devuelve el usuario encontrado o undefined si no existe.
function buscarPorUsuario(usuario) {
  return usuarios.find((u) => u.usuario === usuario);
}

// Agrega un nuevo usuario al arreglo.
function agregarUsuario(usuario, passwordHash) {
  const nuevoUsuario = { usuario, passwordHash };
  usuarios.push(nuevoUsuario);
  return nuevoUsuario;
}

module.exports = { usuarios, buscarPorUsuario, agregarUsuario };
