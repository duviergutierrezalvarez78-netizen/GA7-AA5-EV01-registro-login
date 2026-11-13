package co.edu.sena.registrologin

import co.edu.sena.registrologin.data.JsonUtil
import co.edu.sena.registrologin.domain.AuthRepository
import co.edu.sena.registrologin.domain.AuthResult
import co.edu.sena.registrologin.domain.AuthUseCase
import co.edu.sena.registrologin.domain.Validador
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

private class RepositorioFalso : AuthRepository {
    var ultimoUsuario: String? = null
    override fun registrar(usuario: String, password: String): AuthResult {
        ultimoUsuario = usuario
        return AuthResult.Exito("Usuario registrado correctamente")
    }
    override fun iniciarSesion(usuario: String, password: String): AuthResult {
        ultimoUsuario = usuario
        return if (password == "1234") AuthResult.Exito("Autenticacion satisfactoria")
        else AuthResult.Error("Error en la autenticacion")
    }
}

class AuthModuloTest {
    private val repo = RepositorioFalso()
    private val casoDeUso = AuthUseCase(repo)

    @Test fun validador_rechazaCamposVacios() = assertNotNull(Validador.validar("", "1234"))
    @Test fun validador_rechazaPasswordCorta() = assertNotNull(Validador.validar("duvi", "12"))
    @Test fun validador_aceptaDatosValidos() = assertNull(Validador.validar("duvi", "1234"))

    @Test fun login_correcto() =
        assertTrue(casoDeUso.iniciarSesion("duvi", "1234") is AuthResult.Exito)

    @Test fun login_incorrecto() =
        assertEquals(AuthResult.Error("Error en la autenticacion"), casoDeUso.iniciarSesion("duvi", "9999"))

    @Test fun login_vacio_noLlegaAlRepositorio() {
        assertTrue(casoDeUso.iniciarSesion(" ", "1234") is AuthResult.Error)
        assertNull(repo.ultimoUsuario)
    }

    @Test fun registro_recortaUsuario() {
        assertTrue(casoDeUso.registrar("  duvi ", "1234") is AuthResult.Exito)
        assertEquals("duvi", repo.ultimoUsuario)
    }

    @Test fun json_cuerpoEscapaComillas() =
        assertEquals("""{"usuario":"a\"b","password":"x"}""", JsonUtil.cuerpo("a\"b", "x"))

    @Test fun json_extraeMensaje() =
        assertEquals("Hola", JsonUtil.extraerMensaje("""{"mensaje":"Hola"}"""))

    @Test fun json_sinMensaje() = assertNull(JsonUtil.extraerMensaje("{}"))
}
