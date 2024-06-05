package grafos;

/**
 * Se implementa la clase Arco que se encarga de unir dos vertices.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Vertice
 * @see Grafo
 */
public class Arco {
    public static int numArcos = 0;
    private Vertice salida;
    private Vertice llegada;
    private String etiqueta;
    private int id;

    public Arco(Vertice salida, Vertice llegada, String etiqueta) {
        this.salida = salida;
        this.llegada = llegada;
        this.etiqueta = etiqueta;

        this.id = Arco.numArcos++;
    }

    public Vertice getSalida() {
        return this.salida;
    }

    public Vertice getLlegada() {
        return this.llegada;
    }

    public String getEtiqueta() {
        return this.etiqueta;
    }

    public int getId() {
        return this.id;
    }

    public void setSalida(Vertice salida) {
        this.salida = salida;
    }

    public void setLlegada(Vertice llegada) {
        this.llegada = llegada;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.etiqueta + "(" + this.id + ")" + " : " + this.salida.getEtiqueta() + " -- "
                + this.llegada.getEtiqueta();
    }
}
