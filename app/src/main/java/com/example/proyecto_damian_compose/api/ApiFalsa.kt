package com.example.proyecto_damian_compose.api

import com.example.proyecto_damian_compose.controller.PeliculaApiService
import com.example.proyecto_damian_compose.modelo.DatosDemo
import com.example.proyecto_damian_compose.modelo.Pelicula
import kotlinx.coroutines.delay
import java.util.UUID

private const val PAQUETE = "com.example.proyecto_damian_compose"

object FakeUserApiService: UserApiService{
    //Esta es una mock database que se ejecuta en el propio movil
    //En términos de seguridad si fuese una app Real seria una catastrofe de seguridad pero para este ejemplo hara el apaño
    private val usuariosDiccionarioLogin = DatosDemo.usuarios.associate { it.email to it.contraseña }.toMutableMap()


    //Podriamos usar un fun : String pero como es una simulacion de Api es mejor usar Map<String, String>
    //Que es lo mismo que json, asi la respuesta de crear un usuario seria:
    //"message": "Usuario creado"
    override suspend fun crearCuenta(usuario: UsuarioApi): Map<String, String> {
        delay(400);
        if(usuariosDiccionarioLogin.containsKey(usuario.email)){
            throw Exception("El email ya está registrado")
        }

        usuariosDiccionarioLogin[usuario.email] = usuario.password
        //Es lo mismo que poner usuarios.put(usuario.email, usuario.password)
        //Usuarios es el diccionario
        return mapOf("message" to "Usuario creado")
    }


    override suspend fun login(usuario: UsuarioApi): Map<String, String> {
        delay(400)
        if(usuariosDiccionarioLogin[usuario.email] != usuario.password){
            throw Exception("Credenciales incorrectas")
        }
        return mapOf("token" to UUID.randomUUID().toString())
    }
}

object FakePeliculaApiService: PeliculaApiService {
    //Convertimos las peliculas de DatosDEmo al formato de la api
    private val peliculasFakeApi = DatosDemo.peliculas.map { p ->
        PeliculaApi(
            id= p.id.toString(),
            titulo = p.titulo,
            genero = p.genero,
            director = p.director,
            puntuacion = p.puntuacion,
            imagen = "android.resource://$PAQUETE/${p.imagen}"
        )
    }.toMutableList()

    //metodo para comprobar token, se reusa luego. Es muy simple, ya que no simular la asignacion real de la API
    private fun comprobarToken(token:String){
        if(!token.startsWith("Bearer ")) throw Exception("HTTP 401")
    }

    override suspend fun obtenerTodas(token:String): List<PeliculaApi>{
        comprobarToken(token)
        delay(300)
        return peliculasFakeApi.toList()
    }

    override suspend fun obtenerPorId(token:String, idPelicula:String): PeliculaApi{
        comprobarToken(token)
        delay(300)
        return peliculasFakeApi.find { it.id == idPelicula } ?: throw Exception("HTTP 404, pelicula no encontrada")
    }

    override suspend fun agregarPelicula(token: String, pelicula: PeliculaApi): PeliculaApi {
        comprobarToken(token)
        delay(300)
        val nueva = pelicula.copy(
            id = UUID.randomUUID().toString(),
            imagen = pelicula.imagen?.takeIf { it.isNotBlank() }
        )
        peliculasFakeApi.add(nueva)
        return nueva
    }

    override suspend fun borrarPelicula(token: String, id: String) {
        comprobarToken(token)
        delay(300)
        peliculasFakeApi.removeAll { it.id == id }
    }

}

