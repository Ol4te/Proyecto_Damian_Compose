package com.example.proyecto_damian_compose.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_damian_compose.Modelo.DatosDemo
import com.example.proyecto_damian_compose.Modelo.Usuario

@Preview
@Composable
fun PruebaGaleria(){
    PantallaGaleria(DatosDemo.usuarios[1], volver = {})
}

@Composable
fun PantallaGaleria(usuario: Usuario, volver: () -> Unit = {}){

    var mostrarMenuAgregarPelicula by remember{ mutableStateOf(false) } //por defecto no se muestra
    //El codigo para mostrar menu va al final
Box(Modifier.fillMaxSize()) {
    Scaffold(topBar = {
        Row(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary)
                .padding(20.dp).height(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "VolverIcono",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.clickable {}.size(40.dp).clickable{volver()}
            )
            Text(
                "Añadir a tu colección",
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

        }
    }) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            item {
                //Boton de agregar una pelicula nueva
                Card(
                    modifier = Modifier.height(200.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurfaceVariant),
                    onClick = {mostrarMenuAgregarPelicula = true}) { //TODO Añadir funcion para pelicula
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Icono_Añadir_Pelicula",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(45.dp)
                        )

                    }
                }
            } //fin item

            items(DatosDemo.peliculas) { pelicula ->
                val guardada: Boolean = usuario.lista_Peliculas.contains(pelicula)
                Card(
                    modifier = Modifier.height(200.dp),
                    content = {
                        Image(
                            painter = painterResource(pelicula.imagen),
                            contentDescription = "Imagen Pelicula",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            alpha = if (guardada) 0.5f else 1f
                        )
                    },
                    onClick = {
                        if (!guardada) {
                            usuario.lista_Peliculas.add(pelicula)
                            volver() //Agregamos la pelicula y ademas volvemos a la pantalla principal
                        }
                    })


            } //fin de items
        }

    }
    if (mostrarMenuAgregarPelicula) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                AgregarPelicula(
                    usuario = usuario,
                    cerrarMenu = { mostrarMenuAgregarPelicula = false })
            }
        }
    }

}
    }



