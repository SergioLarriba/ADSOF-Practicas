package classes;

import java.util.*;

/**
 * Clase abstracta que implementa métodos básicos de un recomendador.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see IRecomendador
 */
public abstract class RecomendadorGeneral implements IRecomendador {
    protected Set<Recomendacion> recomendaciones = new LinkedHashSet<>();
    protected Set<IUsuario> usuarios = new LinkedHashSet<>();
    protected Map<Map<IUsuario, IRecomendable>, Valoracion> valoracionUsuario = new HashMap<>();
    protected double corte;

    /**
     * @param corte valor mínimo de confianza para realizar una recomendación
     */
    public RecomendadorGeneral(double corte) {
        this.recomendaciones = new LinkedHashSet<>();
        this.usuarios = new LinkedHashSet<>();
        this.valoracionUsuario = new LinkedHashMap<>();
        this.corte = corte;
    }

    /**
     * Añade una valoración a un elemento hecha por un usuario
     * 
     * @param usuario usuario que realiza la valoración
     * @param elemento elemento valorado
     * @param valoracion valoración dada
     */
    public abstract void addValoracion(IUsuario usuario, IRecomendable elemento, Valoracion valoracion);

    @Override
    public Collection<Recomendacion> getRecomendaciones(IUsuario usuario) {
        if (usuario == null) {
            return null;
        }

        Collection<Recomendacion> recomendacionesUsuario = new ArrayList<>();

        for (Recomendacion recomendacion : this.recomendaciones) {
            if (!this.haValorado(usuario, recomendacion.getElemento())) {
                if (recomendacion.getConfianza() / this.usuarios.size() > this.corte) {
                    recomendacionesUsuario.add(new Recomendacion(recomendacion.getElemento(),
                            (Math.round(recomendacion.getConfianza() / this.usuarios.size() * 100d) / 100d)));
                }
            }
        }

        return recomendacionesUsuario;
    }

    /**
     * Cambia el valor mínimo de confianza para realizar una recomendación.
     * 
     * @param corte valor mínimo de confianza para realizar una recomendación.
     */
    @Override
    public void setCorte(double corte) {
        this.corte = corte;
    }

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

        Recomendacion recomendacion = new Recomendacion(elemento, 0);
        this.recomendaciones.add(recomendacion);

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

    /**
     * Dado un elemento, devuelve la dupla Recomendacion(elemento, confianza).
     * 
     * @param element
     * @return objecto que contiene la dupla (elemento, confianza)
     * 
     * @see Recomendacion
     */
    public Recomendacion getRecomendacionByElement(IRecomendable element) {
        if (element == null) {
            return null;
        }

        Recomendacion nuevaRecomendacion = new Recomendacion(element, 0);

        for (Recomendacion recomendacion : this.recomendaciones) {
            if (recomendacion.getElemento().equals(nuevaRecomendacion.getElemento())) {
                return recomendacion;
            }
        }

        return null;
    }
}
