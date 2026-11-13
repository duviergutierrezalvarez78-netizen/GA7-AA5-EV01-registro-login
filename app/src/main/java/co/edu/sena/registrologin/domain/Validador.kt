package co.edu.sena.registrologin.domain

/** Validaciones de entrada, espejo de las reglas del backend (campos obligatorios). */
object Validador {
    const val MIN_PASSWORD = 4

    fun validar(usuario: String, password: String): String? = when {
        usuario.isBlank() || password.isBlank() -> "El usuario y la contrasena son obligatorios"
        password.length < MIN_PASSWORD -> "La contrasena debe tener al menos $MIN_PASSWORD caracteres"
        else -> null
    }
}
