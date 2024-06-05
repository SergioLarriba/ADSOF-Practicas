package classes;

import java.util.*;

/**
 * La clase ListaMusica contiene una lista de elementos musicales.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Musica
 */
public class ListaMusica extends Musica {
    private List<Musica> elementosMusicales;

    ListaMusica(String titulo) {
        this.titulo = titulo;
        this.elementosMusicales = new ArrayList<>();
    }

    /**
     * Añade un elemento musical a la lista.
     * 
     * @param elemento musica a añadir
     */
    public void addMusica(Musica elemento) {
        this.elementosMusicales.add(elemento);
        this.segundos += elemento.getSegundos();
        if (this.segundos > 60) {
            this.segundos %= 60;
            this.minutos++;
        }
        this.minutos += elemento.getMinutos();
    }

    @Override
    public String toString() {
        int id = 1;
        String s;
        s = "LISTA: " + this.titulo + ", DURACION: " + this.minutos + ":" + this.segundos + "\n";
        for (Musica m : this.elementosMusicales) {
            s += "   " + id + "." + m.toString(true);
            id++;
        }
        return s;
    }

    public String toString(boolean indented) {
        return this.toString();
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Musica> getElementosMusicales() {
        return this.elementosMusicales;
    }

    public void setElementosMusicales(List<Musica> elementosMusicales) {
        this.elementosMusicales = elementosMusicales;
    }
}
