package com.example.proyecto_damian_compose.Modelo

import com.example.proyecto_damian_compose.R


object DatosDemo {

    //Como primero hay que hacer una comprobacion es mejor usar un fun : Boolean
    fun registrar_Usuario(nombre: String, apellido: String, email: String, contraseña: String): Boolean {
        if(usuarios.any{it.email == email}) return false //Si ya hay alguien con ese email, devuelve false
        if (usuarios.add(Usuario(nombre, apellido, email, contraseña))) return true
        return false
    }

    fun confirmar_Email(email: String): Boolean{
        if (usuarios.any{it.email == email}) return false
        return true
    }

    fun confirmar_Contraseña(contraseña: String, email:String): Boolean{
        val usuario = usuarios.find{it.email == email}
        return usuario?.contraseña == contraseña //Nos aseguramos de que no sea nulo
        //Si es el usuario existe y su contraseña coincide devuelve true
    }

    fun comprobarLogin(email: String, contraseña: String): Boolean{
        return usuarios.find{ it.email == email}?.contraseña == contraseña
    }

    //Imagenes obtenidas de SensaCine
        val peliculas = mutableListOf(
            // Las que ya teníamos
            Pelicula("Inception", "Ciencia Ficción", "Christopher Nolan", 9.2, R.drawable.inception),
            Pelicula("Interstellar", "Ciencia Ficción", "Christopher Nolan", 8.9, R.drawable.interstellar),
            Pelicula("El Padrino", "Drama", "Francis Ford Coppola", 9.5, R.drawable.el_padrino),
            Pelicula("Pulp Fiction", "Thriller", "Quentin Tarantino", 8.7, R.drawable.pulp_fiction),
            Pelicula("The Dark Knight", "Acción", "Christopher Nolan", 9.0, R.drawable.dark_knight),


            Pelicula("Oppenheimer", "Drama Histórico", "Christopher Nolan", 8.8, R.drawable.oppenheimer),
            Pelicula("Pearl Harbour", "Drama Romántico", "Michael Bay", 6.5, R.drawable.pearl_harbor),
            Pelicula("Sherlock Holmes", "Acción", "Guy Ritchie", 7.9, R.drawable.sherlock_holmes),
            Pelicula("Sherlock Holmes: Juego de Sombras", "Acción", "Guy Ritchie", 7.7, R.drawable.sherlock_holmes_sombras),


            Pelicula("El Señor de los Anillos: La Comunidad del Anillo", "Fantasía", "Peter Jackson", 9.1, R.drawable.esdla_1),
            Pelicula("El Señor de los Anillos: Las Dos Torres", "Fantasía", "Peter Jackson", 9.0, R.drawable.esdla_2),
            Pelicula("Gladiator", "Acción", "Ridley Scott", 8.5, R.drawable.gladiator),
            Pelicula("Matrix", "Ciencia Ficción", "Lana y Lilly Wachowski", 8.7, R.drawable.matrix),
            Pelicula("Forrest Gump", "Drama", "Robert Zemeckis", 8.8, R.drawable.forrest_gump),
            Pelicula("El Club de la Lucha", "Thriller", "David Fincher", 8.8, R.drawable.fight_club),
            Pelicula("Joker", "Drama", "Todd Phillips", 8.4, R.drawable.joker),
            Pelicula("Avengers: Endgame", "Acción", "Anthony y Joe Russo", 8.4, R.drawable.endgame),
            Pelicula("Dune", "Ciencia Ficción", "Denis Villeneuve", 8.0, R.drawable.dune),
            Pelicula("1917", "Bélica", "Sam Mendes", 8.3, R.drawable.mil_novecientos_diecisiete),
            Pelicula("El Gran Gatsby", "Drama Romántico", "Baz Luhrmann", 7.2, R.drawable.gran_gatsby)
        )

        val usuarios = mutableListOf(
            Usuario("Damian", "García", "damian@gmail.com", "123456", peliculas),
            Usuario("Ana", "López", "ana@gmail.com", "123456", peliculas),
            Usuario("Carlos", "Martínez", "carlos@gmail.com", "123456", peliculas),
            Usuario("Laura", "Sánchez", "laura@gmail.com", "123456", peliculas),
            Usuario("Miguel", "Fernández", "miguel@gmail.com", "123456", peliculas),
            Usuario("Sofía", "Ramírez", "sofia@gmail.com", "123456", peliculas),
            Usuario("Pablo", "Torres", "pablo@gmail.com", "123456", peliculas),
            Usuario("Elena", "Díaz", "elena@gmail.com", "123456", peliculas),
            Usuario("Javier", "Moreno", "javier@gmail.com", "123456", peliculas),
            Usuario("Lucía", "Jiménez", "lucia@gmail.com", "123456", peliculas)
        )


    }
