package classes;

import java.util.*;

import excepciones.ExcepcionCancionRepetida;
import excepciones.ExcepcionMusicaNoExistente;

/**
 * Implementa la clase Fonoteca que almacena una lista de Musica
 * y los métodos para gestionarlos.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Musica
 */
public class Fonoteca {
    private List<Musica> musica;
    private Set<Cancion> cancionesAniadidas;
    private IAlmacenValoraciones almacen;
    private IRecomendador recomendador;

    /**
     * Fonoteca con recomendador.
     * 
     * @param corte minimo valor para ser recomendado
     */
    public Fonoteca(double corte) {
        this.musica = new ArrayList<>();
        this.cancionesAniadidas = new HashSet<>();
        this.almacen = new AlmacenValoraciones();
        this.recomendador = new RecomendadorPopularidad(corte);
    }

    /**
     * Fonoteca sin recomendador.
     */
    public Fonoteca() {
        this.musica = new ArrayList<>();
        this.cancionesAniadidas = new HashSet<>();
        this.almacen = new AlmacenValoraciones();
        this.recomendador = new RecomendadorPopularidad(0);
    }

    /**
     * Fonoteca a la que se le asigna explícitamente un recomendador.
     * 
     * @param recomendador recomendador asignado
     */
    public Fonoteca(RecomendadorAfinidad recomendador) {
        this.musica = new ArrayList<>();
        this.cancionesAniadidas = new HashSet<>();
        this.almacen = new AlmacenValoraciones();
        this.recomendador = recomendador;
    }

    /**
     * @param titulo    el titulo del album a crear
     * @param autor     el autor del album a crear
     * @param estilo    el estilo del album a crear
     * @param canciones las canciones que contiene el album, si contiene alguna
     * 
     * @return el nuevo album creado
     */
    public Album crearAlbum(String titulo, String autor, EstiloMusical estilo, Cancion... canciones)
            throws ExcepcionCancionRepetida {
        if (canciones.length == 0) {
            return null;
        }

        for (int i = 0; i < (canciones.length - 1); i++) {
            for (int j = i + 1; j < canciones.length; j++) {
                if (canciones[i].equals(canciones[j])) {
                    throw new ExcepcionCancionRepetida(canciones[i]);
                }
            }
        }

        for (Cancion cancion : canciones) {
            this.almacen.addRecomendable(cancion);
        }
        this.addCancionesAniadidas(canciones);
        Album nuevoAlbum = new Album(titulo, autor, estilo, canciones);
        this.musica.add(nuevoAlbum);
        this.almacen.addRecomendable(nuevoAlbum);
        return nuevoAlbum;
    }

    /**
     * @param titulo    el titulo del album a crear
     * @param autor     el autor del album a crear
     * @param canciones las canciones que contiene el album, si contiene alguna
     * 
     * @return el nuevo album creado
     */
    public Album crearAlbum(String titulo, String autor, Cancion... canciones) throws ExcepcionCancionRepetida {
        if (canciones.length == 0) {
            return null;
        }

        for (int i = 0; i < (canciones.length - 1); i++) {
            for (int j = i + 1; j < canciones.length; j++) {
                if (canciones[i].equals(canciones[j])) {
                    throw new ExcepcionCancionRepetida(canciones[i]);
                }
            }
        }

        for (Cancion cancion : canciones) {
            this.almacen.addRecomendable(cancion);
        }
        this.addCancionesAniadidas(canciones);
        Album nuevoAlbum = new Album(titulo, autor, canciones);
        this.musica.add(nuevoAlbum);
        this.almacen.addRecomendable(nuevoAlbum);
        return nuevoAlbum;
    }

    /**
     * @param lista         lista a la que se añadirá la musica
     * @param nuevoElemento musica que se añadirá a la lista
     * 
     * @return la fonoteca que contiene la lista actualizada
     */
    public Fonoteca aniadirMusicaALista(ListaMusica lista, Musica nuevoElemento)
            throws ExcepcionCancionRepetida, ExcepcionMusicaNoExistente {
        if (lista.getElementosMusicales().contains(nuevoElemento)) {
            throw new ExcepcionCancionRepetida(nuevoElemento);
        }
        if (!this.musica.contains(nuevoElemento) && !this.cancionesAniadidas.contains(nuevoElemento)) {
            throw new ExcepcionMusicaNoExistente(nuevoElemento);
        }

        for (Musica elem : this.musica) {
            if (elem.getClass() == Album.class) {
                Album album = (Album) elem;
                if (album.getCanciones().contains(nuevoElemento)) {
                    throw new ExcepcionCancionRepetida(nuevoElemento);
                }
            }
        }

        if (nuevoElemento == null) {
            return this;
        }

        lista.addMusica(nuevoElemento);

        return this;
    }

    /**
     * @param nombre el título de la nueva lista musical
     * 
     * @return la nueva lista musical creada
     */
    public ListaMusica crearListaMusica(String nombre) {
        if (nombre == null) {
            return null;
        }
        ListaMusica listaMusica = new ListaMusica(nombre);
        this.musica.add(listaMusica);
        return listaMusica;
    }

    /**
     * Imprime por pantalla la música almacenada en la fonoteca.
     */
    public void mostrar() {
        for (Musica m : this.musica) {
            System.out.print(m);
            System.out.println("-----------------");
        }
    }

    public List<Musica> getMusica() {
        return this.musica;
    }

    public void setMusica(List<Musica> musica) {
        this.musica = musica;
    }

    @Override
    public String toString() {
        return this.musica.toString();
    }

    /**
     * Añade un número indeterminado de canciones a una lista independiente con
     * todas las canciones añadidas.
     * 
     * @param canciones canciones a añadir
     */
    public void addCancionesAniadidas(Cancion... canciones) {
        if (canciones == null)
            return;
        for (Cancion cancion : canciones) {
            this.cancionesAniadidas.add(cancion);
        }
    }

    /**
     * Registra un nuevo usuario.
     * 
     * @param nombre   nombre del usuario
     * @param nickname nick del usuario
     * @return el usuario registrado; o null si los parámetros son incorrectos
     */
    public Usuario registrarUsuario(String nombre, String nickname) {
        if (nombre == null || nickname == null)
            return null;

        Usuario nuevoUsuario = new Usuario(nombre, nickname);
        this.almacen.addUsuario(nuevoUsuario);
        this.recomendador.addUsuario(nuevoUsuario);

        return nuevoUsuario;
    }

    /**
     * Añade una valoracion un un usuario a un elemento de la fonoteca.
     * 
     * @param usuario  usuario que realiza la valoración
     * @param elemento elemento a valorar
     * @param voto     valoración del usuario
     * @return la propia Fonoteca si no hay error; null si sí lo hay
     */
    public Fonoteca valorar(IUsuario usuario, IRecomendable elemento, Valoracion voto) {
        if (usuario == null || elemento == null || voto == null)
            return null;

        this.almacen.addValoracion(usuario, elemento, voto);
        this.recomendador.addValoracion(usuario, elemento, voto);

        return this;
    }

    /**
     * Muestra las valoraciones que ha realizado un usuario.
     * 
     * @param usuario
     */
    public void mostrarValoraciones(Usuario usuario) {
        Collection<IRecomendable> elementosValoradosPorUsuario = this.almacen.elementosValorados(usuario);

        System.out.println("Valoraciones de " + usuario.getId());
        for (IRecomendable iRecomendable : elementosValoradosPorUsuario) {
            Valoracion valoracion = almacen.valoracion(usuario, iRecomendable);
            if (iRecomendable.getClass() == Cancion.class) {
                Cancion cancion = (Cancion) iRecomendable;
                System.out.println("CANCION: " + cancion.getTitulo() + " " + valoracion);
            } else if (iRecomendable.getClass() == Album.class) {
                Album album = (Album) iRecomendable;
                System.out.println("ALBUM: " + album.getTitulo() + ", ARTISTA: " + album.getArtista() + ", DURACION: "
                        + album.getMinutos() + ":" + album.getSegundos() + ", ESTILO: " + album.getEstilo() + " "
                        + valoracion);
            }
        }
    }

    /**
     * Muestra las recomendaciones disponibles para un usuario.
     * 
     * @param u usuario
     */
    public void mostrarRecomendaciones(Usuario u) {
        Collection<Recomendacion> recomendaciones = this.recomendador.getRecomendaciones(u);

        System.out.println("RECOMENDACIONES PARA: " + u.nickname);
        for (Recomendacion recomendacion : recomendaciones) {
            System.out.println(recomendacion);
        }
    }

}
