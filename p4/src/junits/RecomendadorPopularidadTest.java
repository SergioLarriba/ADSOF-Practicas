package junits;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.Cancion;
import classes.IRecomendable;
import classes.IUsuario;
import classes.Recomendacion;
import classes.RecomendadorPopularidad;
import classes.Usuario;
import classes.Valoracion;

public class RecomendadorPopularidadTest {
    private IUsuario usuario;
    private IRecomendable elemento;
    private IRecomendable elemento2;
    private RecomendadorPopularidad recomendador;

    @Before
    public void setUp() {
        usuario = new Usuario("Miguel", "miguelo");
        elemento = new Cancion("Prueba", 3, 3);
        elemento2 = new Cancion("Prueba2", 3, 3);
        recomendador = new RecomendadorPopularidad(0);

    }

    @Test
    public void testAddValoracion() {
        recomendador.addValoracion(usuario, elemento, Valoracion.LIKE);
        recomendador.addValoracion(usuario, elemento2, Valoracion.DISLIKE);

        Recomendacion recomendacion = recomendador.getRecomendacionByElement(elemento);
        assertNotNull(recomendacion);
        assertEquals(1.0, recomendador.valoracion(usuario, elemento).getValue(), 0.0);
        assertTrue(recomendador.haValorado(usuario, elemento));

        Recomendacion recomendacion2 = recomendador.getRecomendacionByElement(elemento);
        assertNotNull(recomendacion2);
        assertEquals(-0.5, recomendador.valoracion(usuario, elemento2).getValue(), 0.0);
        assertTrue(recomendador.haValorado(usuario, elemento2));
    }
}
