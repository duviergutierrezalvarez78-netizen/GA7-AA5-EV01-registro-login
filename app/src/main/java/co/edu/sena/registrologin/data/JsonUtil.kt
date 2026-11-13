package co.edu.sena.registrologin.data

/** Serializacion JSON minima (sin librerias externas). */
object JsonUtil {
    fun cuerpo(usuario: String, password: String) =
        """{"usuario":"${escapar(usuario)}","password":"${escapar(password)}"}"""

    fun extraerMensaje(json: String): String? =
        Regex("\"mensaje\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"").find(json)
            ?.groupValues?.get(1)
            ?.replace("\\\"", "\"")?.replace("\\\\", "\\")

    private fun escapar(s: String) = s.replace("\\", "\\\\").replace("\"", "\\\"")
}
