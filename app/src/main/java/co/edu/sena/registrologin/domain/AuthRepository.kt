package co.edu.sena.registrologin.domain

/** Resultado de una operacion de autenticacion contra el servicio web. */
sealed class AuthResult {
    data class Exito(val mensaje: String) : AuthResult()
    data class Error(val mensaje: String) : AuthResult()
}

/** Contrato de la capa de dominio; la capa de datos lo implementa (inversion de dependencias). */
interface AuthRepository {
    fun registrar(usuario: String, password: String): AuthResult
    fun iniciarSesion(usuario: String, password: String): AuthResult
}
