package matrices;

import java.util.*;

import grafos.*;


/**
 * Se implementa la clase Potenciamatriz que guarda las potencias de una matriz.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Matriz
 * @see Grafo
 */
public class PotenciaMatriz {
    List<Matriz> matrices = new ArrayList<>();

    public PotenciaMatriz(List<Vertice> vertices, List<Arco> arcos) {
        if (vertices == null) {
            return;
        }
        int fila, columna;
        Matriz matriz = new Matriz(vertices.size(), 1, vertices);

        for (Arco arco : arcos) {
            fila = vertices.indexOf(arco.getSalida());
            columna = vertices.indexOf(arco.getLlegada());
            matriz.setDato(fila, columna, 1);
        } 
        this.matrices.add(matriz);
    }

    public Matriz getMatriz(int potencia) {
        for (Matriz matriz : this.matrices) {
            if (matriz.getPotencia() == potencia) {
                return matriz;
            }
        }

        return null;
    }


    private Matriz mulMatrices(Matriz matriz1, Matriz matriz2) {
        Matriz resultado = Matriz.multiplicacionMatricial(matriz1, matriz2);
        this.matrices.add(resultado);

        return resultado;
    }

    public Matriz potencia(int potencia) {
        if (potencia < 1) {
            return null;
        }
        if (this.getMatriz(potencia) != null) {
            return this.getMatriz(potencia);
        }
        
        return mulMatrices(potencia(potencia - 1), potencia(1));
    } 

    @Override
    public String toString() {
        String s = ""; 
        for (Matriz m: this.matrices) {
            s += "[potencia "+m.getPotencia()+"]=\n";
            s += m.toString(); 
        }
        return s; 
    }
}
