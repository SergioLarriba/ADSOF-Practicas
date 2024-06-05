package testers;

import classes.*;
import excepciones.*;

public class FonotecaTester {
    public static void main(String[] args) {
        FonotecaTester main = new FonotecaTester();
        Fonoteca fonoteca = main.crearMusica();
        fonoteca.mostrar();

    }

    public Fonoteca crearMusica() {
        Cancion[] canciones = {
                new Cancion("Radio ga ga", 5, 48), // Canción Radio ga ga, con duración 5:48
                new Cancion("Tear it up", 3, 28),
                new Cancion("It's a hard life", 4, 8),
                new Cancion("Resistire", 4, 4),
                new Cancion("Dos corazones", 3, 6) };

        Fonoteca fonoteca = new Fonoteca();
        try {
            Album album1 = fonoteca.crearAlbum("The Works", "Queen", EstiloMusical.ROCK,
                    canciones[0], canciones[1], canciones[2]);
            fonoteca.crearAlbum("Resistire", "Duo dinamico", canciones[3], canciones[4]); // sin estilo musical
            ListaMusica favoritas = fonoteca.crearListaMusica("Mis favoritas");
            fonoteca.aniadirMusicaALista(favoritas, album1)
                    .aniadirMusicaALista(favoritas, canciones[3]) // La canción Resistiré está repetida
                    .aniadirMusicaALista(favoritas, canciones[4]); // La canción Dos corazones está repetida

            System.out.println(fonoteca.crearAlbum("Las 4 estaciones", "Vivaldi", EstiloMusical.CLASICA));

        } catch (ExcepcionCancionRepetida | ExcepcionMusicaNoExistente excepcion) {
        } finally {
            try { // Hacemos esto para que se imprima el null del principio
                System.out.println(fonoteca.crearAlbum("Las 4 estaciones", "Vivaldi", EstiloMusical.CLASICA));
            } catch (ExcepcionCancionRepetida e) {
            }

        }

        return fonoteca;
    }
}