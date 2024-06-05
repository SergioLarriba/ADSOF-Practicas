package excepciones;

import classes.Musica;

/**
 * Extiende la clase {@link ExcepcionFonoteca} para lanzar excepciones
 * relacionadas con la repetición de canciones.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class ExcepcionCancionRepetida extends ExcepcionFonoteca {
    public ExcepcionCancionRepetida(Musica musica) {
        super(musica);
    }

    @Override
    public String toString() {
        return super.toString() + "está repetida";
    }
}