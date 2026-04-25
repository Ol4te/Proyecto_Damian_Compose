package com.example.proyecto_damian_compose.Modelo


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Usuario(val nombre: String, val apellido: String, var email: String, var contraseña:String, val lista_Peliculas : SnapshotStateList<Pelicula> = mutableStateListOf()) {
//Cambie de val lista_Peliculas : MutableList<Pelicula> = mutableListOf() -> SnapshotStateList<Pelicula> = mutableStateofList
    //Lista de peliculas para cada usuario


}