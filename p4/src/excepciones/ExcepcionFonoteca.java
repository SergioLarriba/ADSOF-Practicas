package excepciones;

import classes.Musica;

/**
 * Clase que implementa de forma general todas las excepciones de la
 * {@link classes.Fonoteca}.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class ExcepcionFonoteca extends Exception {
    protected Musica musica;

    public ExcepcionFonoteca(Musica musica) {
        this.musica = musica;
    }

    @Override
    public String toString() {
        return "La cancion " + this.musica.getTitulo() + " ";
    }
}
