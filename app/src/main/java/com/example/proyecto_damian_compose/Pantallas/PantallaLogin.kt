package com.example.proyecto_damian_compose.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_damian_compose.R

@Preview //TODO MODIFICAR UI
@Composable
fun PantallaLogin() {
    //TODO quitar color gray
    Column(modifier = Modifier.fillMaxSize().background(Color.Blue), horizontalAlignment = Alignment.CenterHorizontally ){

        //Titulo
        //TODO quitar background de color
        Box(modifier = Modifier.fillMaxWidth().weight(0.9f)){
            Image(painterResource(id = R.drawable.login_superior), contentDescription = "Imagen Login Superior")
            Box(modifier= Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)))
            Column() {
                Row(
                    Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        " REGISTRARSE",
                        modifier = Modifier.background(
                            Color.Gray.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(15.dp)
                        ).padding(7.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 12.sp
                    ) //TODO CLICKEABLE
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Exit Application",
                        modifier = Modifier.size(20.dp).background(
                            color = Color.White.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(15.dp)
                        )
                    )
                }
                Spacer(modifier = Modifier.fillMaxWidth().height(30.dp))
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(5.dp) ) {
                    Text("LOGIN", fontWeight = FontWeight.Bold, fontSize = 35.sp, color = Color.White)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(75.dp).height(4.dp).background(color = Color.White))
                        Text("  TO  ", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(modifier = Modifier.width(75.dp).height(4.dp).background(color = Color.White))
                    }
                    Text(" YOUR ACCOUNT", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                }




                //LINEAS DE TITULO LOGIN
            }
        }

        Column(modifier = Modifier.fillMaxSize().weight(2f).background(Color.White)) { }

    }
}