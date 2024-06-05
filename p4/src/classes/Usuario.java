package classes;

import java.util.*;

/**
 * Implementa la interfaz {@link IUsuario}.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class Usuario implements IUsuario {
    String nombre;
    String nickname;
    Map<IRecomendable, Valoracion> votos;

    /**
     * Instancia un nuevo usuario.
     * 
     * @param nombre   nombre del nuevo usuario
     * @param nickname nick del nuevo usuario
     */
    public Usuario(String nombre, String nickname) {
        this.nombre = nombre;
        this.nickname = nickname;
        votos = new HashMap<>();
    }

    /**
     * Devuelve el identificador único del usuario.
     */
    @Override
    public String getId() {
        return this.nickname;
    }

    /**
     * Añade un voto a la lista de votaciones del usuario.
     * 
     * @param elemento elemento a valorar
     * @param voto     valoración del usuario
     */
    public void votar(IRecomendable elemento, Valoracion voto) {
        votos.put(elemento, voto);
    }

    public Map<IRecomendable, Valoracion> getVotos() {
        return votos;
    }

}
