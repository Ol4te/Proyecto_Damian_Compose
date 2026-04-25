package com.example.proyecto_damian_compose.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_damian_compose.Modelo.DatosDemo
import com.example.proyecto_damian_compose.Modelo.Pelicula
import com.example.proyecto_damian_compose.Modelo.Usuario
import com.example.proyecto_damian_compose.R


@Composable
fun AgregarPelicula(usuario : Usuario, cerrarMenu: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(shape = RoundedCornerShape(30.dp), color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)), verticalArrangement = Arrangement.spacedBy(12.dp)){

        var titulo_Pelicula by remember{ mutableStateOf("") }
        var director by remember{ mutableStateOf("") }
        var genero by remember{ mutableStateOf("") }
        var imagen by remember{ mutableStateOf("") }
        var score by remember{ mutableStateOf("") }

        var error_titulo by remember{ mutableStateOf(false)}
        var error_director by remember{ mutableStateOf(false)}
        var error_genero by remember{ mutableStateOf(false)}
        var error_score by remember{ mutableStateOf(false)}

        fun validarDatos(): Boolean{
            error_titulo = false
            error_director = false
            error_genero = false
            error_score = false

            var esValido = true //Bandera

            if(titulo_Pelicula.isBlank()){
                error_titulo = true
                esValido = false
            }

            if(director.isBlank()){
                error_director = true
                esValido = false
            }

            if(genero.isBlank()){
                error_genero = true
                esValido = false
            }

            if(score.toDoubleOrNull() == null){
                error_score = true
                esValido = false
            }
            return esValido
    }

       Row(Modifier.fillMaxWidth()){
           Column(Modifier.weight(2f).padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)){
               OutlinedTextField(value = titulo_Pelicula, onValueChange = {titulo_Pelicula = it}, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), placeholder = {Text("Titulo")}, isError = error_titulo )
               OutlinedTextField(value = director, onValueChange = {director = it}, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), placeholder = {Text("Director")}, isError = error_director)
               Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
                   OutlinedTextField(value = genero, onValueChange = {genero = it}, modifier = Modifier.weight(3f), shape = RoundedCornerShape(20.dp), placeholder = {Text("Categoría")}, isError = error_genero)
                   OutlinedTextField(value = score, onValueChange = {score = it}, modifier = Modifier.weight(1f), shape = RoundedCornerShape(10.dp), placeholder = {Text("0.0")}, isError = error_score)
               }

           }
           Column(Modifier.weight(1f).padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
               Card(modifier = Modifier.height(180.dp), shape = RoundedCornerShape(12.dp), onClick = {}) {
                   //TODO seleccionar una imagen del sistema
                   Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                       Image(painter = painterResource(R.drawable.imagen_renombrar), contentDescription = "Imagen Placeholder", Modifier.size(48.dp))
                   }
               }
           }

       }

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp).fillMaxWidth()){
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Flecha volver", Modifier.size(48.dp).clickable{cerrarMenu()})
            Spacer(Modifier.width(50.dp))
            Button(modifier = Modifier.width(150.dp), onClick = {
                if(validarDatos()){
                    val peliculaNueva = Pelicula(titulo_Pelicula, genero, director, score.toDouble(), R.drawable.imagen_renombrar) //Usamos imagen por defecto
                    DatosDemo.peliculas.add(peliculaNueva)
                    usuario.lista_Peliculas.add(peliculaNueva)
                    cerrarMenu()
                }
            }){
                Text("Agregar", color= MaterialTheme.colorScheme.onPrimary)
            }
        }


    }
}




/*@Preview
@Composable
fun prueba(){
    AgregarPelicula { DatosDemo.usuarios[0], }
}*/


fun cerrarMenu(){

}