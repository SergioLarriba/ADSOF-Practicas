package classes;

import java.util.*;

/**
 * La clase Album contiene el artista, canciones y el estilo musical.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see EstiloMusical
 * @see Musica
 */
public class Album extends Musica implements IRecomendable {
    private String artista;
    private List<Cancion> canciones;
    private EstiloMusical estilo;

    Album(String titulo, String artista, Cancion... canciones) {
        this.titulo = titulo;
        this.artista = artista;
        if (canciones != null) {
            this.canciones = List.of(canciones);
            for (Cancion cancion : canciones) {
                this.segundos += cancion.getSegundos();
                if (this.segundos > 60) {
                    this.segundos %= 60;
                    this.minutos++;
                }
                this.minutos += cancion.getMinutos();
            }
        } else {
            this.canciones = new ArrayList<>();
        }
    }

    Album(String titulo, String artista, EstiloMusical estilo, Cancion... canciones) {
        this(titulo, artista, canciones);
        this.estilo = estilo;
    }

    @Override
    public String getDescripcion() {
        return this.titulo;
    }

    @Override
    public String toString() {
        String s;
        int id = 1;
        if (this.segundos < 10) {
            s = "ALBUM: " + this.titulo + ", ARTISTA: " + this.artista + ", DURACION: " + this.minutos + ":0"
                    + this.segundos + ", ESTILO: ";
        } else {
            s = "ALBUM: " + this.titulo + ", ARTISTA: " + this.artista + ", DURACION: " + this.minutos + ":"
                    + this.segundos + ", ESTILO: ";
        }
        if (this.estilo == null) {
            s += "SIN ESTILO";
        } else {
            s += this.estilo;
        }
        s += "\n";
        for (Cancion c : this.canciones) {
            s += "   " + id + "." + c.toString();
            id++;
        }
        return s;
    }

    public String toString(boolean idented) {
        if (!idented) {
            return this.toString();
        }
        String s;
        int id = 1;
        if (this.segundos < 10) {
            s = "ALBUM: " + this.titulo + ", ARTISTA: " + this.artista + ", DURACION: " + this.minutos + ":0"
                    + this.segundos + ", ESTILO: ";
        } else {
            s = "ALBUM: " + this.titulo + ", ARTISTA: " + this.artista + ", DURACION: " + this.minutos + ":"
                    + this.segundos + ", ESTILO: ";
        }
        if (this.estilo == null) {
            s += "SIN ESTILO";
        } else {
            s += this.estilo;
        }
        s += "\n";
        for (Cancion c : this.canciones) {
            s += "      " + id + "." + c.toString();
            id++;
        }
        return s;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return this.artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public List<Cancion> getCanciones() {
        return this.canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public EstiloMusical getEstilo() {
        return this.estilo;
    }

    public void setEstilo(EstiloMusical estilo) {
        this.estilo = estilo;
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Album))
            return false;

        Album toCompare = (Album) obj;
        for (Cancion elem : toCompare.getCanciones()) {
            if (this.canciones.contains(elem))
                return true;
        }
        return this.titulo.equals(toCompare.titulo);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        return result;
    }

}
