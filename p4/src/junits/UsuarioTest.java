package junits;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import classes.Cancion;
import classes.IRecomendable;
import classes.Usuario;
import classes.Valoracion;

public class UsuarioTest {

    private Usuario usuario;

    @Before
    public void setUp() {
        usuario = new Usuario("Nombre", "Nickname");
    }

    @Test
    public void testVotar() {
        IRecomendable elemento = new Cancion("Prueba", 5, 5);

        usuario.votar(elemento, Valoracion.LIKE);

        Map<IRecomendable, Valoracion> votos = usuario.getVotos();
        assertEquals(votos.size(), 1);
        assertEquals(votos.get(elemento), Valoracion.LIKE);
    }
}
