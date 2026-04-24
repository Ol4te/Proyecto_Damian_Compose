package com.example.proyecto_damian_compose.Modelo

data class Usuario(val nombre: String, val apellido: String, var email: String, var contraseña:String, val lista_Peliculas : MutableList<Pelicula> = mutableListOf()) {

    //Lista de peliculas para cada usuario


}