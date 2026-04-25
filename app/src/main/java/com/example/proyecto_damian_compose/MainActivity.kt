package com.example.proyecto_damian_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.proyecto_damian_compose.Modelo.DatosDemo
import com.example.proyecto_damian_compose.Pantallas.PantallaGaleria
import com.example.proyecto_damian_compose.Pantallas.PantallaLogin
import com.example.proyecto_damian_compose.Pantallas.PantallaPrincipal
import com.example.proyecto_damian_compose.ui.theme.Proyecto_Damian_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_Damian_ComposeTheme {

                val backStack = rememberNavBackStack(Login)

                NavDisplay(backStack = backStack, entryProvider = entryProvider {
                    entry<Login>{
                    PantallaLogin(loginCorrecto = {email ->
                        backStack.removeLastOrNull()
                        backStack.add(Principal(email))})

                }

                    entry<Principal> {
                        val usuario = DatosDemo.usuarios.find { u -> u.email == it.email}
                        usuario?.let {u -> PantallaPrincipal(usuario = u,
                            abrirGaleria = {backStack.add(Galeria(u.email))}) }
                    }

                    entry<Galeria> {
                        PantallaGaleria(
                            usuario = DatosDemo.usuarios.find{ u -> u.email == it.email}!!,
                            volver = {backStack.removeLastOrNull()}
                        )
                    }

                    }

                )
            }
        }
    }
}

