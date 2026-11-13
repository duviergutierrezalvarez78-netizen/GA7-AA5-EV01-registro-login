package co.edu.sena.registrologin.data

import co.edu.sena.registrologin.domain.AuthRepository
import co.edu.sena.registrologin.domain.AuthResult
import java.net.HttpURLConnection
import java.net.URL

/** Implementacion del repositorio que consume la API REST Node/Express (POST /registro y /login). */
class AuthRepositoryHttp(private val baseUrl: String) : AuthRepository {

    override fun registrar(usuario: String, password: String) =
        post("$baseUrl/registro", usuario, password, codigoExito = 201)

    override fun iniciarSesion(usuario: String, password: String) =
        post("$baseUrl/login", usuario, password, codigoExito = 200)

    private fun post(url: String, usuario: String, password: String, codigoExito: Int): AuthResult {
        val conexion = URL(url).openConnection() as HttpURLConnection
        return try {
            conexion.requestMethod = "POST"
            conexion.connectTimeout = 8000
            conexion.readTimeout = 8000
            conexion.doOutput = true
            conexion.setRequestProperty("Content-Type", "application/json")
            conexion.outputStream.use { it.write(JsonUtil.cuerpo(usuario, password).toByteArray()) }

            val codigo = conexion.responseCode
            val flujo = if (codigo < 400) conexion.inputStream else conexion.errorStream
            val texto = flujo?.bufferedReader()?.use { it.readText() }.orEmpty()
            val mensaje = JsonUtil.extraerMensaje(texto) ?: "Respuesta inesperada ($codigo)"
            if (codigo == codigoExito) AuthResult.Exito(mensaje) else AuthResult.Error(mensaje)
        } catch (e: Exception) {
            AuthResult.Error("No se pudo conectar con el servidor")
        } finally {
            conexion.disconnect()
        }
    }
}
