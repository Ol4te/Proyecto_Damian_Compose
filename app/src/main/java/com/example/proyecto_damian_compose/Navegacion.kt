package com.example.proyecto_damian_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.proyecto_damian_compose.controller.RetrofitConfig
import com.example.proyecto_damian_compose.modelo.DatosDemo
import com.example.proyecto_damian_compose.pantallas.PantallaAgregarPelicula
import com.example.proyecto_damian_compose.pantallas.PantallaGaleria
import com.example.proyecto_damian_compose.pantallas.PantallaLogin
import com.example.proyecto_damian_compose.pantallas.PantallaPrincipal

@Composable
fun Navegacion() {
    var comprobando by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        RetrofitConfig.comprobarServidor()
        comprobando = false
    }

    if(comprobando){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(Modifier.height(16.dp))
            Text("Conectando con el servidor...")
            Text("(la primera vez puede tardar hasta un minuto)")
        }
    } else{
        NavegacionApp()
    }
}

@Composable
fun NavegacionApp(){
    val backStack = rememberNavBackStack(Login)

    NavDisplay(backStack = backStack, entryProvider = entryProvider {
        entry<Login>{
            PantallaLogin(loginCorrecto = {email ->
                backStack.removeLastOrNull()
                backStack.add(Principal(email))})

        }


        entry<Principal> {
            PantallaPrincipal(
                abrirAgregarPelicula = {
                    backStack.add(AgregarPelicula)
                }
            )
        }


        entry<Galeria> {
            PantallaGaleria(
                usuario = DatosDemo.usuarios.find{ u -> u.email == it.email}!!,
                volver = {backStack.removeLastOrNull()}
            )
        }

        entry<AgregarPelicula> {
            PantallaAgregarPelicula(
                volver = {
                    backStack.removeLastOrNull()
                }
            )
        }

    }

    )

}