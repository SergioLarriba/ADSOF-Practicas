package junits;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import classes.Album;
import classes.AlmacenValoraciones;
import classes.Cancion;
import classes.Fonoteca;
import classes.IRecomendable;
import classes.IUsuario;
import classes.Valoracion;
import excepciones.ExcepcionCancionRepetida;

public class AlmacenValoracionesTest {
    private AlmacenValoraciones almacen;
    private Fonoteca fonoteca;

    @Before
    public void setUp() throws Exception {
        this.almacen = new AlmacenValoraciones();
        this.fonoteca = new Fonoteca();
    }

    @Test
    public void testAddRecomendable() {
        IRecomendable elemento;
        try {
            elemento = fonoteca.crearAlbum("The Dark Side of the Moon", "Pink Floyd", new Cancion("Tear it up", 3, 28));
            assertTrue(almacen.addRecomendable(elemento));
            assertTrue(almacen.getRecomendables().contains(elemento));
        } catch (ExcepcionCancionRepetida e) {
        }
    }

    @Test
    public void testAddUsuario() {
        IUsuario usuario = fonoteca.registrarUsuario("Sonia Melero Vegas", "smelero");
        assertTrue(almacen.addUsuario(usuario));
        assertTrue(almacen.getUsuarios().contains(usuario));
    }

    @Test
    public void testAddValoracion() {
        IUsuario usuario = fonoteca.registrarUsuario("Sonia Melero Vegas", "smelero");
        IRecomendable elemento;
        try {
            elemento = fonoteca.crearAlbum("The Dark Side of the Moon", "Pink Floyd", new Cancion("Tear it up", 3, 28));
            Valoracion valoracion = Valoracion.LIKE;

            almacen.addValoracion(usuario, elemento, valoracion);

            assertTrue(almacen.haValorado(usuario, elemento));
            assertEquals(valoracion, almacen.valoracion(usuario, elemento));

            // Comprobamos que se han añadido las valoraciones de las canciones del álbum
            if (elemento.getClass() == Album.class) {
                Album album = (Album) elemento;
                for (Cancion cancion : album.getCanciones()) {
                    assertTrue(almacen.haValorado(usuario, cancion));
                    assertEquals(valoracion, almacen.valoracion(usuario, cancion));
                }
            }
        } catch (ExcepcionCancionRepetida e) {
        }
    }
}
