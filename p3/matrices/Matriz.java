package matrices;

import java.util.*; 
import grafos.*; 


/**
 * Se implementa la clase Matriz que implementa la matriz de adyacencia de un grafo.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Vertice
 * @see Grafo
 */
public class Matriz {
    private int datos[][];
    private int tam; 
    private int potencia;
    private List<Vertice> vertices = new ArrayList<Vertice>(); 

    public Matriz(int tam, int potencia, List<Vertice> vertices) {
        this.datos = new int[tam][tam];
        this.tam = tam; 
        this.potencia = potencia;
        this.vertices=vertices; 
    }

    public static Matriz multiplicacionMatricial(Matriz matriz1, Matriz matriz2) {
        if (matriz1 == null || matriz2 == null) {
            return null;
        }

        Matriz matrizFinal = new Matriz(matriz1.tam, matriz1.potencia + matriz2.potencia, matriz1.vertices);

        for (int i = 0; i < matriz1.tam; i++) {
            for (int j = 0; j < matriz2.tam; j++) {
                int suma = 0;
                for (int k = 0; k < matriz2.tam; k++) {
                    suma += matriz1.getDato(i, k) * matriz2.getDato(k, j);
                }
                matrizFinal.setDato(i, j, suma);
            }
        }

        return matrizFinal;
    }

    public void setDato(int fila, int columna, int dato) {
        this.datos[fila][columna] = dato;
    }
    
    public int getDato(int fila, int columna) {
        return this.datos[fila][columna];
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getPotencia() {
        return this.potencia;
    }   

    public int getTam() {
        return this.tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public List<Vertice> getVertices() {
        return this.vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }
    
    
    @Override
    public String toString() {
        int tam = this.tam; 
        String s=""; 
        for (int i=0; i<tam; i++) {
            s +=String.format("|%10s: ", vertices.get(i).getEtiqueta());   
            for (int j=0; j<tam; j++) {
                s += String.format("%2d", datos[i][j]); 
            }
            s += "|\n"; 
        }
        return s; 
    }
}

