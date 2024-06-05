package grafos;

import java.util.*; 
import matrices.*;

/**
 * Se implementa la clase Grafo que contiene una serie de vertices y arcos.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see GrafoNoDirigido
 * @see GrafoDirigido
 */
public abstract class Grafo {
    protected String etiqueta;
    protected List<Vertice> vertices;
    protected List<Arco> arcos;
    protected PotenciaMatriz potenciaMatrices;

    public Grafo(String etiqueta) {
        this.etiqueta = etiqueta;
        this.vertices = new ArrayList<>();
        this.arcos = new ArrayList<>();
        crearPotenciaMatrices();
    }

    public void crearPotenciaMatrices() {
        this.potenciaMatrices = new PotenciaMatriz(this.vertices, this.arcos);        
    }

    public int camino(int longCamino, String salida, String llegada) {
        if (longCamino < 1 || salida == null || llegada == null) {
            return 0;
        }

        Matriz matrizResultado = this.potenciaMatrices.potencia(longCamino);
        int fila = this.vertices.indexOf(this.getVertice(salida));
        int columna = this.vertices.indexOf(this.getVertice(llegada));
        
        return matrizResultado.getDato(fila, columna);
    }

    public PotenciaMatriz matricesPotencia() {
        return this.potenciaMatrices;
    }

    public boolean addVertices(String... etiquetas) {
        int flag = 0;
        Vertice v = null;
        
        for (String i: etiquetas) {
            if (i.contains(",") || this.containsVertice(i) == true) {
                flag = 1; 
            } else {
                v = new Vertice(i); 
                this.vertices.add(v); 
            }       
        }

        this.crearPotenciaMatrices();
        if (flag == 1)
            return false;
        else
            return true;
    }

    public Vertice getVertice(String etiqueta) {
        for (Vertice v : this.vertices) {
            if (v.getEtiqueta().equals(etiqueta) == true) {
                return v;
            }
        }
        return null;
    }

    public abstract Grafo addArco(String origen, String destino, String etiquetaArco);

    public Grafo borraArco(String origen, String destino) {
        Vertice vOrigen = null, vDestino = null;
        for (int i = 0; i < arcos.size(); i++) {
            vOrigen = arcos.get(i).getSalida();
            vDestino = arcos.get(i).getLlegada();
            if (vOrigen.getEtiqueta().equals(origen) && vDestino.getEtiqueta().equals(destino)) {
                this.arcos.remove(arcos.get(i));
            }
        }
        this.crearPotenciaMatrices();
        return this;
    }

    public Arco getArco(String salida, String llegada) {
        for (Arco arco : this.arcos) {
            if (arco.getSalida().getEtiqueta().equals(salida) && arco.getLlegada().getEtiqueta().equals(llegada)) {
                return arco;
            }
        }
        return null;
    }

    public boolean concatena(Arco arco1, Arco arco2) {
        if (arco1 == null || arco2 == null ||
                !(arco1.getLlegada().getEtiqueta().equals(arco2.getSalida().getEtiqueta())) ||
                arco1.getSalida().getEtiqueta().equals(arco2.getLlegada().getEtiqueta()) ||
                this.getArco(arco1.getSalida().getEtiqueta(), arco2.getLlegada().getEtiqueta()) != null) {
            return false;
        }

        this.addArco(arco1.getSalida().getEtiqueta(), arco2.getLlegada().getEtiqueta(),
                arco1.getEtiqueta() + "--" + arco2.getEtiqueta());

        this.crearPotenciaMatrices();
        return true;
    }

    public int grado(String etiqueta) {
        int num = 0;

        for (Arco a : this.arcos) {
            if (a.getLlegada().getEtiqueta().equals(etiqueta) || a.getSalida().getEtiqueta().equals(etiqueta))
                num++;
        }
        return num;
    }

    public boolean esRegular() {
        for (Vertice v : this.vertices) {
            int g = this.grado(v.getEtiqueta());
            for (Vertice ve : this.vertices) {
                if (this.grado(ve.getEtiqueta()) != g)
                    return false;
            }
        }
        return true;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public List<Vertice> getVertices() {
        return this.vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Arco> getArcos() {
        return this.arcos;
    }

    public void setArcos(List<Arco> arcos) {
        this.arcos = arcos;
    }

    public boolean containsVertice(String s) {
        for (Vertice v: this.vertices) {
            if (v.getEtiqueta().equals(s)) {
                return true; 
            }
        }
        return false; 
    }

}