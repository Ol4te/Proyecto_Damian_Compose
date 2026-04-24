package com.example.proyecto_damian_compose.Pantallas

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proyecto_damian_compose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// Aquí he jugado bastante porque quería añadir un icono gif al validar los datos el gif para -> Y volvemos a la pantalla inicial

@Preview
@Composable //LE pasamos variables a nuestra funcion
fun PantallaRegistro( cerrarRegistro: () -> Unit = {},
                      Registrar: (String ,String) -> Unit = {_, _ -> }
) { //Lo conectamos con el icono de cerrar
    var animarEmote by remember { mutableStateOf(true) } //Vamos a usar un componente llamado AsynImage (linea 62)

    //Variables para los fields
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email_texto by remember { mutableStateOf("") }
    var contraseña_texto by remember { mutableStateOf("") }

    var confirmar_contraseña_texto by remember { mutableStateOf("") }

    var contraseña_visible by remember { mutableStateOf(false) }

    //variables para animar el boton de registro

    var boton_presionado by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) } //Para añadir un pequeño delay <- Asi simulamos el loading real y damos tiempo a la animacion a completarse

    var boton_visible by remember { mutableStateOf(true) }

    var scope = rememberCoroutineScope() // Es un timer de la aplicacion

    val animatedWidth by animateDpAsState(
        targetValue = if (boton_presionado) 80.dp else 200.dp, //Boton se encoje
        animationSpec = tween(durationMillis = 500)

    )

    //Variable de animacion


    //variables para errores
    var error_Nombre by remember { mutableStateOf("") }
    var error_Apellido by remember { mutableStateOf("") }
    var error_Email by remember { mutableStateOf("") }
    var error_Contraseña by remember { mutableStateOf("") }
    var error_Confirmar by remember { mutableStateOf("") }


    //Funcion para validaciones
    fun validar(): Boolean { //Validamos en el boton

        var esCorrecto = true

        //Se resetean los errores
        error_Nombre = ""
        error_Apellido = ""
        error_Email = ""
        error_Contraseña = ""
        error_Confirmar = ""

        if (nombre.isBlank()) {
            esCorrecto = false
            error_Nombre = "Nombre no válido"
        }

        if (apellido.isBlank()) {
            esCorrecto = false
            error_Apellido = "Apellido no válido"
        }

        if (email_texto.isBlank() || !email_texto.contains("@") || email_texto.isEmpty()) {
            error_Email = "Email inválido"
            esCorrecto = false
        }

        if (contraseña_texto.length < 6) {
            error_Contraseña = "Longitud mínima 6 caractéres"
            esCorrecto = false
        }

        if (confirmar_contraseña_texto != contraseña_texto) {
            error_Confirmar = "Las contraseñas no coinciden"
            esCorrecto = false
        }
        //Si todo funciona devuelve true
        return esCorrecto;
    }


    Column(
        modifier = Modifier.fillMaxSize().background(
            MaterialTheme.colorScheme.background.copy(alpha = 0.95f),
            shape = RoundedCornerShape(20.dp)
        ).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(10.dp))
        //Cerrar Registro
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Cerrar Registro",
            modifier = Modifier.size(48.dp).background(
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1f),
                shape = RoundedCornerShape(30.dp)
            )
                .clickable {
                    cerrarRegistro()
                })

        Spacer(Modifier.height(40.dp))

        Row(Modifier.width(275.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                placeholder = { Text("Nombre") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "icono nombre",
                        Modifier.size(24.dp)
                    )
                },
                isError = error_Nombre.isNotEmpty(),
                supportingText = { if (error_Nombre.isNotEmpty()) Text(error_Nombre) }
            ) //Si hay un error registrado por la tecla validar lo muestra,


            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                placeholder = { Text("Apellido") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp),
                isError = error_Apellido.isNotEmpty(),
                supportingText = { Text(error_Apellido) })
        }




        OutlinedTextField(
            value = email_texto,
            onValueChange = { email_texto = it },
            placeholder = { Text("Introduce tu email") },
            shape = RoundedCornerShape(20.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "correo")
            },
            singleLine = true,
            isError = error_Email.isNotEmpty(),
            supportingText = {
                Text(error_Email)
            })



        OutlinedTextField(
            value = contraseña_texto,
            onValueChange = { contraseña_texto = it },
            placeholder = { Text("Contraseña") },
            shape = RoundedCornerShape(20.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "key_icon",
                    modifier = Modifier.clickable { contraseña_visible = !contraseña_visible })
            },
            visualTransformation = if (contraseña_visible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = error_Contraseña.isNotEmpty(),
            supportingText = { Text(error_Contraseña) })


        OutlinedTextField(
            value = confirmar_contraseña_texto,
            onValueChange = { confirmar_contraseña_texto = it },
            placeholder = { Text("Repite la contraseña") },
            shape = RoundedCornerShape(20.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "key_icon",
                    Modifier.size(24.dp).clickable { contraseña_visible = !contraseña_visible })
            },
            visualTransformation = if (contraseña_visible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            isError = error_Confirmar.isNotEmpty(),
            supportingText = { Text(error_Confirmar) }
        )

        Spacer(Modifier.height(10.dp))

        Row(Modifier.padding(horizontal = 30.dp), verticalAlignment = Alignment.CenterVertically) {
            //TODO Renderizador de GIF
            //No aparece en Preview solo en el Play
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(R.drawable.loading_gif)
                    .decoderFactory(coil.decode.GifDecoder.Factory()).build(),
                contentDescription = "Loading Icon",
                modifier = Modifier.size(100.dp)
            )
        }
        Spacer(Modifier.height(20.dp))



        AnimatedVisibility(visible = boton_visible, exit = fadeOut()) {
            Button(onClick = {
                //Validamos
                // validar()
                if (validar()) {
                    Registrar(email_texto, contraseña_texto) //Devolvemos los datos
                    boton_presionado = true;
                    loading = true;
                    scope.launch {
                        delay(400)
                        boton_visible = false
                    }
                }


            }, modifier = Modifier.width(animatedWidth)) {
                AnimatedVisibility(visible = !loading, exit = fadeOut()) {
                    Text("Confirmar")
                }
            }
        }
    }
}