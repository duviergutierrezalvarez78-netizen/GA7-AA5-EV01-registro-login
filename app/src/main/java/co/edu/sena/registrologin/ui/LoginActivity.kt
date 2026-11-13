package co.edu.sena.registrologin.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import co.edu.sena.registrologin.domain.AuthResult

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vista = UiKit.contenedor(this)
        val usuario = UiKit.campo(this, "Usuario")
        val password = UiKit.campo(this, "Contrasena", clave = true)
        vista.addView(usuario)
        vista.addView(password)
        vista.addView(UiKit.boton(this, "Iniciar sesion") {
            val u = usuario.text.toString()
            val p = password.text.toString()
            UiKit.ejecutar({ UiKit.casoDeUso.iniciarSesion(u, p) }) { r ->
                when (r) {
                    is AuthResult.Exito -> {
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                    is AuthResult.Error -> UiKit.aviso(this, r.mensaje)
                }
            }
        })
        vista.addView(UiKit.boton(this, "No tienes cuenta? Registrate") {
            startActivity(Intent(this, RegisterActivity::class.java))
        })
        setContentView(vista)
    }
}
