package excepciones;

import classes.Musica;

/**
 * Extiende la clase {@link ExcepcionFonoteca} para lanzar excepciones
 * relacionadas con la el uso de música no disponible en la
 * {@link classes.Fonoteca}.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class ExcepcionMusicaNoExistente extends ExcepcionFonoteca {
    public ExcepcionMusicaNoExistente(Musica musica) {
        super(musica);
    }

    @Override
    public String toString() {
        return super.toString() + "no existe";
    }
}
