package co.edu.sena.registrologin.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class HomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vista = UiKit.contenedor(this)
        vista.addView(UiKit.texto(this, "Autenticacion satisfactoria"))
        vista.addView(UiKit.boton(this, "Cerrar sesion") {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })
        setContentView(vista)
    }
}
