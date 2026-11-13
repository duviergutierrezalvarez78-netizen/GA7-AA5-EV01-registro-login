package co.edu.sena.registrologin.domain

/** Casos de uso: validan y delegan en el repositorio. Sin dependencias de Android. */
class AuthUseCase(private val repositorio: AuthRepository) {

    fun registrar(usuario: String, password: String): AuthResult =
        Validador.validar(usuario, password)?.let { AuthResult.Error(it) }
            ?: repositorio.registrar(usuario.trim(), password)

    fun iniciarSesion(usuario: String, password: String): AuthResult =
        Validador.validar(usuario, password)?.let { AuthResult.Error(it) }
            ?: repositorio.iniciarSesion(usuario.trim(), password)
}
