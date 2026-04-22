package com.example.proyecto_damian_compose.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proyecto_damian_compose.R


// Aquí he jugado bastante porque quería añadir un icono gif al validar los datos el gif para -> Y volvemos a la pantalla inicial

@Preview
@Composable
fun PantallaRegistro(){
    var animarEmote by remember { mutableStateOf(true) } //Vamos a usar un componente llamado AsynImage (linea 62)

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(Modifier.height(40.dp))
        OutlinedTextField(value="", onValueChange = {}, placeholder = {Text("Introduce tu email")}, shape = RoundedCornerShape(20.dp), leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = "correo")
        })
        OutlinedTextField(value="", onValueChange = {}, placeholder = {Text("Contraseña")}, shape = RoundedCornerShape(20.dp), trailingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "key_icon")
        })
        OutlinedTextField(value="", onValueChange = {}, placeholder = {Text("Repite la contraseña")}, shape = RoundedCornerShape(20.dp), leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "key_icon", Modifier.size(24.dp))
        })

        Spacer(Modifier.height(10.dp))

        Row(Modifier.padding(horizontal = 30.dp), verticalAlignment = Alignment.CenterVertically) {
    //TODO Renderizador de GIF
            //No aparece en Preview solo en el Play
            AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(R.drawable.loading_gif).decoderFactory(coil.decode.GifDecoder.Factory()).build(), contentDescription = "Loading Icon", modifier = Modifier.size(100.dp))

        }
    }
}