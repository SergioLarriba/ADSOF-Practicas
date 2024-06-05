package classes;

import java.util.*;

/**
 * Implementa la clase abstracta {@link RecomendadorGeneral}.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see RecomendadorGeneral
 * @see IRecomendador
 */
public class RecomendadorPopularidad extends RecomendadorGeneral {

    public RecomendadorPopularidad(double corte) {
        super(corte);
    }

    @Override
    public void addValoracion(IUsuario usuario, IRecomendable elemento, Valoracion valoracion) {
        if (usuario == null || elemento == null || valoracion == null)
            return;

        Recomendacion recomendacion = this.getRecomendacionByElement(elemento);
        if (recomendacion == null) {
            recomendacion = new Recomendacion(elemento, valoracion.getValue());
            this.recomendaciones.add(recomendacion);
        } else {
            recomendacion.addValoracion(valoracion);
        }

        Map<IUsuario, IRecomendable> usuarioElemento = new HashMap<>();
        usuarioElemento.put(usuario, elemento);
        valoracionUsuario.put(usuarioElemento, valoracion);

        if (elemento.getClass() == Album.class) {
            Album album = (Album) elemento;
            for (IRecomendable cancion : album.getCanciones()) {
                Recomendacion nuevaRecomendacion = this.getRecomendacionByElement(cancion);
                if (nuevaRecomendacion == null) {
                    nuevaRecomendacion = new Recomendacion(cancion, valoracion.getValue());
                    this.recomendaciones.add(nuevaRecomendacion);

                    Map<IUsuario, IRecomendable> mapCancion = new HashMap<>();
                    mapCancion.put(usuario, cancion);
                    valoracionUsuario.put(mapCancion, valoracion);
                } else if (nuevaRecomendacion != null && (!this.haValorado(usuario, cancion))) {
                    nuevaRecomendacion.addValoracion(valoracion);

                    Map<IUsuario, IRecomendable> mapCancion = new HashMap<>();
                    mapCancion.put(usuario, cancion);
                    valoracionUsuario.put(mapCancion, valoracion);
                }

            }
        }

    }
}
