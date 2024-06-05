package classes;

/**
 * La clase abstracta Musica sirve como clase de la que heredaran
 * todos los elementos musicales de la Fonoteca.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Fonoteca
 * @see Album
 * @see Cancion
 * @see ListaMusica
 */
public abstract class Musica {
    protected String titulo;
    protected int minutos = 0;
    protected int segundos = 0;

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

    public abstract String toString(boolean idented);

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
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        return true;
    }

}