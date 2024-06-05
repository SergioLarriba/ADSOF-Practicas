package classes;

/**
 * Implementa la clase Cancion que está compuesta de un título y su duración.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Musica
 */
public class Cancion extends Musica implements IRecomendable {
    public Cancion(String titulo, int minutos, int segundos) {
        if (segundos < 0) {
            segundos = 0;
        }
        while (segundos > 60) {
            minutos++;
            segundos %= 60;
        }
        this.titulo = titulo;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    @Override
    public String getDescripcion() {
        return this.titulo;
    }

    @Override
    public String toString() {
        if (this.segundos < 10) {
            return this.titulo + " (" + this.minutos + ":0" + this.segundos + ")\n";
        }
        return this.titulo + " (" + this.minutos + ":" + this.segundos + ")\n";
    }

    public String toString(boolean idented) {
        return this.toString();
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getMinutos() {
        return this.minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return this.segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Musica other = (Musica) obj;
        if (this.titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!this.titulo.equals(other.titulo))
            return false;
        return true;
    }

}
