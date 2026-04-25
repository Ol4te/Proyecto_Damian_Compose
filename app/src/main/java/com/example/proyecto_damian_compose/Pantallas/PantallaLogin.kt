package com.example.proyecto_damian_compose.Pantallas

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button


import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_damian_compose.Modelo.DatosDemo
import com.example.proyecto_damian_compose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



//TODO añadir boton de recordar contraseña

@Preview
@Composable
fun PantallaLogin(loginCorrecto: (String) -> Unit ={}) {

    //Variables para los TextField
    var email_texto by remember { mutableStateOf("") }
    var contraseña_texto by remember { mutableStateOf("") }
    var contraseña_visible by remember { mutableStateOf(false) }

    var error_login by remember{mutableStateOf(false)}

    //Variables para el boton animado
    var presionado by remember {mutableStateOf(false)}
    var loading by remember{mutableStateOf(false)}
    var visible by remember{mutableStateOf(true)}
    val scope = rememberCoroutineScope() //Es un timer, tambien necesita ser guardado

    //Para cerrar aplicacion
    val contexto = LocalContext.current


    //Guardamos las propiedades del width aqui arriba y las aplicamos en el buton
    val animatedWidth by animateDpAsState(
        targetValue = if(presionado) 80.dp else 200.dp, //al presionarse cambia
        animationSpec = tween(durationMillis = 500)
    ) // PERO con esto el Texto no desaparece asi que usamos un componente en el buton para el Texto


    //Variables para el boton de registro
    var mostrarRegistro by remember{mutableStateOf(false)}
    //La estrategia aqui es: 1. Pasamos de una Column a una Box - Column (pantalla login)
    // Ahora podemos poner Box - Registro - Columna principal (pantalla de login)

    Box(modifier= Modifier.fillMaxSize()) {

    //Primero se carga la pantalla de Login y luego, como es un box, si le damos a registrarse carga por encima otra pantalla
        //Lo hice asi por probar
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            //Titulo
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)) {
                Image(
                    painterResource(id = R.drawable.login_superior),
                    contentDescription = "Imagen Login Superior",
                    modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f)))
                Column() {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            " REGISTRARSE",
                            modifier = Modifier
                                .background(
                                    Color.Gray.copy(alpha = 0.4f),
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .padding(7.dp)
                                .clickable { mostrarRegistro = true
                                },
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 12.sp
                        ) //TODO CLICKEABLE
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Exit Application",
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    color = Color.White.copy(alpha = 0.4f),
                                    shape = RoundedCornerShape(15.dp)
                                ).clickable{
                                    (contexto as? Activity)?.finish()
                                }
                        )
                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            "LOGIN",
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp,
                            color = Color.White
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(
                                modifier = Modifier
                                    .width(75.dp)
                                    .height(4.dp)
                                    .background(color = Color.White)
                            )
                            Text(
                                "  TO  ",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(75.dp)
                                    .height(4.dp)
                                    .background(color = Color.White)
                            )
                        }
                        Text(
                            " YOUR ACCOUNT",
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                    //LINEAS DE TITULO LOGIN
                }
            }


            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                //TODO Agregar verificacion de uno o dos usuarios falsos que crearemos como una lista arriba
                //TODO Agregar recordar datos
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp))
                OutlinedTextField(modifier = Modifier.width(300.dp),
                    placeholder = {Text("Email", color = Color.Gray)},
                    value = email_texto,
                    onValueChange = { email_texto = it },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "icono_email"
                        )
                    }, singleLine = true, shape = RoundedCornerShape(20.dp),
                    isError = error_login,
                    supportingText = {if(error_login)Text("Datos Incorrectos")}
                    )


                Spacer(modifier = Modifier.height(10.dp))
                //Contraseña
                OutlinedTextField(modifier = Modifier.width(300.dp),
                    value = contraseña_texto,
                    placeholder = {Text("Contraseña",color= Color.Gray)},
                    onValueChange = { contraseña_texto = it },
                    trailingIcon = { //Iconos al final del TextField // ICONO VARIABLE
                        Icon(
                            painter = painterResource(
                                id = if (contraseña_visible) R.drawable.visibility else R.drawable.no_visibility
                            ), contentDescription = "Mostrar Contraseña/No Mostrar", modifier = Modifier
                                .size(25.dp)
                                .clickable { contraseña_visible = !contraseña_visible },
                        )

                    },
                    visualTransformation = if(contraseña_visible) VisualTransformation.None else PasswordVisualTransformation(),// Si visible es cierto no hay visual transformation
                    singleLine = true, shape = RoundedCornerShape(20.dp),
                    isError = error_login,
                    supportingText = {if(error_login)Text("Datos Incorrectos")}
                )

                Spacer(Modifier.height(5.dp))
                AnimatedVisibility(visible = visible, exit = fadeOut()) {
                    Button(onClick = {

                       if(DatosDemo.comprobarLogin(email_texto, contraseña_texto)){
                           presionado = true
                           loading = true
                           //Primero se encoge boton y desaparece el texto y luego ->
                           scope.launch { delay(300)
                               visible = false} // <- Desaparece el boton completamente
                           loginCorrecto(email_texto)
                       } else {
                           error_login = true //Si falla la comprobacion ponemos el error a true
                       }
                    }, modifier = Modifier.width(animatedWidth) ) {
                        AnimatedVisibility(visible = !loading, exit = fadeOut()) {
                            Text("LOGIN")
                        }
                    }

                }

                Spacer(Modifier.height(150.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                    Spacer(modifier = Modifier.width(100.dp).background(Color.Gray).height(2.dp))

                    Spacer(Modifier.width(30.dp))

                    Spacer(modifier = Modifier.width(100.dp).background(Color.Gray).height(2.dp))
                }

                Spacer(Modifier.height(10.dp))
                Row(Modifier.padding(horizontal = 50.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)){
                    Image(painter = painterResource(id = R.drawable.google), contentDescription = "Login Google", modifier = Modifier.size(32.dp).clickable{
                        email_texto = "damian@gmail.com"
                            contraseña_texto = "123456"
                    })
                    Image(painter = painterResource(id = R.drawable.facebook), contentDescription = "Login Google", modifier = Modifier.size(32.dp).clickable{
                        email_texto = "miguel@gmail.com"
                        contraseña_texto = "123456"
                    })
                    Image(painter = painterResource(id = R.drawable.apple), contentDescription = "Login Google", modifier = Modifier.size(32.dp).clickable{
                        email_texto = "laura@gmail.com"
                        contraseña_texto = "123456"
                    })
                }


            }
        }

        //Registro
        // AnimatedVisibility(visible = mostrarRegistro, modifier = Modifier.fillMaxSize().padding(30.dp).background(
          //  MaterialTheme.colorScheme.background.copy(alpha = 0.5f), shape = RoundedCornerShape(20.dp)
        // )) { PantallaRegistro()}
        AnimatedVisibility(visible = mostrarRegistro) {
            Box(modifier = Modifier.fillMaxWidth().padding(15.dp).background(Color.Black.copy(alpha = 0.4f)), contentAlignment = Alignment.Center){
                PantallaRegistro(cerrarRegistro = {mostrarRegistro = false},
                    EnviarDatosLogin = { email, contraseña -> email_texto = email
                    contraseña_texto = contraseña
                    mostrarRegistro = false}
                ) //Si presiona el icono de Cerrar devuelve una funcion a la pantalla principal y en este caso hemos dicho que la funcion asigne mostrarRegistro=false


            }
        }
    }

}