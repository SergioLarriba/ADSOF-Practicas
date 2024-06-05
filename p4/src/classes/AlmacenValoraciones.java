package classes;

import java.util.*;

/**
 * Implementa la interfaz IAlmacenValoraciones.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class AlmacenValoraciones implements IAlmacenValoraciones {
    Set<IRecomendable> recomendables = new LinkedHashSet<>();
    Set<IUsuario> usuarios = new LinkedHashSet<>();
    Map<Map<IUsuario, IRecomendable>, Valoracion> valoracionUsuario = new HashMap<>();

    /**
     * Añade un nuevo elemento al conjutno de elementos recomendables.
     * 
     * @param elemento elemento a añadir
     * @return boolean
     */
    @Override
    public boolean addRecomendable(IRecomendable elemento) {
        if (elemento == null)
            return false;

        this.recomendables.add(elemento);
        return true;
    }

    /**
     * Añade un nuevo usuario.
     * 
     * @param usuario usuario a añadir
     * @return boolean
     */
    @Override
    public boolean addUsuario(IUsuario usuario) {
        if (usuario == null)
            return false;

        this.usuarios.add(usuario);
        return true;
    }

    /**
     * Añade la valoración de un usuario a un elemento.
     * 
     * @param usuario    usuario que realiza la valoración
     * @param elemento   elemento valorado
     * @param valoracion LIKE o DISLIKE
     */
    @Override
    public void addValoracion(IUsuario usuario, IRecomendable elemento, Valoracion valoracion) {
        if (usuario == null || elemento == null || valoracion == null)
            return;

        Map<IUsuario, IRecomendable> usuarioElemento = new HashMap<>();
        usuarioElemento.put(usuario, elemento);
        valoracionUsuario.put(usuarioElemento, valoracion);

        if (elemento.getClass() == Album.class) {
            Album album = (Album) elemento;
            for (Cancion cancion : album.getCanciones()) {
                Map<IUsuario, IRecomendable> mapCancion = new HashMap<>();
                mapCancion.put(usuario, cancion);
                valoracionUsuario.put(mapCancion, valoracion);
            }
        }

    }

    /**
     * Devuelve los elementos valorados por un usuario.
     * 
     * @param usuario
     * @return una coleccion de elementos valorados
     */
    @Override
    public Collection<IRecomendable> elementosValorados(IUsuario usuario) {
        if (usuario == null)
            return null;

        Collection<IRecomendable> elemValorados = new LinkedHashSet<>();

        for (Map<IUsuario, IRecomendable> map : valoracionUsuario.keySet()) {
            if (map.containsKey(usuario)) {
                elemValorados.add(map.get(usuario));
            }
        }
        return elemValorados;
    }

    /**
     * Comprueba si un usuario a valorado el elemento.
     * 
     * @param usuario
     * @param elemento
     * @return true si lo ha valorado; false si no lo ha valorado
     */
    @Override
    public boolean haValorado(IUsuario usuario, IRecomendable elemento) {
        Map<IUsuario, IRecomendable> map = new HashMap<>();
        map.put(usuario, elemento);

        return this.valoracionUsuario.containsKey(map);
    }

    /**
     * Devuelve la valoración que ha realizado un usuario a un elemento específico.
     * 
     * @param usuario
     * @param elemento
     * @return la valoración o null si no lo ha valorado
     */
    @Override
    public Valoracion valoracion(IUsuario usuario, IRecomendable elemento) {
        if (usuario == null || elemento == null)
            return null;

        Map<IUsuario, IRecomendable> dupla = new HashMap<>();
        dupla.put(usuario, elemento);

        return this.valoracionUsuario.get(dupla);
    }

    public Set<IUsuario> getUsuarios() {
        return this.usuarios;
    }

    public Set<IRecomendable> getRecomendables() {
        return this.recomendables;
    }
}
