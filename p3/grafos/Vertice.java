package grafos;

/**
 * Se implementa la clase Vertice que forman parte de un grafo.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Arco
 * @see Grafo
 */
public class Vertice {
    private static int numVertices = 0;
    private String etiqueta;
    private int id;

    public Vertice(String etiqueta) {
        this.etiqueta = etiqueta;
        this.id = numVertices++;
    }

    public String getEtiqueta() {
        return this.etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public static int getNumVertices() {
        return numVertices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id_vertice) {
        this.id = id_vertice;
    }

    @Override
    public String toString() {
        return this.etiqueta + "(" + this.id + ")";
    }
}
