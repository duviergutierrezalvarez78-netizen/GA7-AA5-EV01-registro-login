package co.edu.sena.registrologin.ui

import android.app.Activity
import android.os.Bundle
import co.edu.sena.registrologin.domain.AuthResult

class RegisterActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vista = UiKit.contenedor(this)
        val usuario = UiKit.campo(this, "Usuario")
        val password = UiKit.campo(this, "Contrasena", clave = true)
        vista.addView(usuario)
        vista.addView(password)
        vista.addView(UiKit.boton(this, "Registrarse") {
            val u = usuario.text.toString()
            val p = password.text.toString()
            UiKit.ejecutar({ UiKit.casoDeUso.registrar(u, p) }) { r ->
                when (r) {
                    is AuthResult.Exito -> { UiKit.aviso(this, r.mensaje); finish() }
                    is AuthResult.Error -> UiKit.aviso(this, r.mensaje)
                }
            }
        })
        vista.addView(UiKit.boton(this, "Volver al inicio de sesion") { finish() })
        setContentView(vista)
    }
}
