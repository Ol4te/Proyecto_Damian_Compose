package com.example.proyecto_damian_compose.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_damian_compose.Modelo.Pelicula

//ESta pantalla la hago con scaffol


@Preview
@Composable
fun PantallaPrincipal(){



    Scaffold(topBar = {
        Row(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.primary), horizontalArrangement = Arrangement.Center){
            Text("Mis peliculas", color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(20.dp), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }, floatingActionButton = { //TODO
        FloatingActionButton(onClick = { },
            containerColor = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(25.dp)
        ) { Icon(Icons.Default.Add, contentDescription = "Añadir película", Modifier.size(30.dp))}
    }
        ) {innerPadding ->

        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding), verticalArrangement = Arrangement.spacedBy(5.dp), contentPadding = PaddingValues(20.dp)) {
            //Para cada objeto de la lista se llama a la funcion
        }
    }
}


@Preview
@Composable
fun MostrarPelicula(){
    fun ContenedorPelicula(pelicula: Pelicula){
        Card(modifier = Modifier.fillMaxWidth(), onClick = {}){

        }
    }
}