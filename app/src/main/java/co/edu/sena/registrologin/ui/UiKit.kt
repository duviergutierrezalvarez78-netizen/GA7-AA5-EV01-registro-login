package co.edu.sena.registrologin.ui

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import co.edu.sena.registrologin.BuildConfig
import co.edu.sena.registrologin.data.AuthRepositoryHttp
import co.edu.sena.registrologin.domain.AuthResult
import co.edu.sena.registrologin.domain.AuthUseCase
import java.util.concurrent.Executors

/** Componentes de UI reutilizables y fabrica del caso de uso (capa de presentacion). */
object UiKit {
    private val hilo = Executors.newSingleThreadExecutor()
    private val principal = Handler(Looper.getMainLooper())

    val casoDeUso by lazy { AuthUseCase(AuthRepositoryHttp(BuildConfig.API_BASE_URL)) }

    fun contenedor(ctx: Context) = LinearLayout(ctx).apply {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        setPadding(48, 48, 48, 48)
        layoutParams = ViewGroup.LayoutParams(-1, -1)
    }

    fun campo(ctx: Context, pista: String, clave: Boolean = false) = EditText(ctx).apply {
        hint = pista
        inputType = if (clave) 0x81 else 0x01 // textPassword / text
    }

    fun boton(ctx: Context, texto: String, accion: () -> Unit) =
        Button(ctx).apply { text = texto; setOnClickListener { accion() } }

    fun texto(ctx: Context, t: String) = TextView(ctx).apply { text = t; textSize = 20f }

    /** Ejecuta la operacion de red fuera del hilo principal y entrega el resultado en el principal. */
    fun ejecutar(operacion: () -> AuthResult, alResultado: (AuthResult) -> Unit) {
        hilo.execute { val r = operacion(); principal.post { alResultado(r) } }
    }

    fun aviso(ctx: Activity, mensaje: String) = Toast.makeText(ctx, mensaje, Toast.LENGTH_LONG).show()
}
