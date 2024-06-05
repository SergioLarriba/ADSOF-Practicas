package grafos;

import java.io.*;

/**
 * Se implementa la clase GrafoNoDirigid que contiene una serie de vertices y arcos en ambos sentidos.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Grafo
 */
public class GrafoNoDirigido extends Grafo {

    public GrafoNoDirigido(String etiqueta) {
        super(etiqueta);
    }

    public Grafo addArco(String origen, String destino, String etiquetaArco) {
        Vertice verticeOrigen = null;
        Vertice verticeDestino = null;

        for (Vertice vertice : this.vertices) {
            if (vertice.getEtiqueta().equals(origen)) {
                verticeOrigen = vertice;
            } else if (vertice.getEtiqueta().equals(destino)) {
                verticeDestino = vertice;
            }
            if (verticeOrigen != null && verticeDestino != null) {
                break;
            }
        }
        if (verticeOrigen == null || verticeDestino == null || origen.equals(destino) || origen.contains(",") || destino.contains(",")) {
            return this;
        }

        // Busco si el arco ya estaba en el grafo, para no volver e añadirlo 
        for (Arco a: this.arcos) {
            if ((a.getLlegada().getEtiqueta().equals(destino) && a.getSalida().getEtiqueta().equals(origen)) || (a.getLlegada().getEtiqueta().equals(destino) && a.getSalida().getEtiqueta().equals(origen))) {
                return this; 
            }
        }

        Arco arcoIda = new Arco(verticeOrigen, verticeDestino, etiquetaArco);
        Arco arcoVuelta = new Arco(verticeDestino, verticeOrigen, etiquetaArco);

        this.arcos.add(arcoIda);
        this.arcos.add(arcoVuelta);

        this.crearPotenciaMatrices();
        return this;
    }

    public Grafo borraArco(String origen, String destino) {
        super.borraArco(origen, destino);
        super.borraArco(destino, origen);
        return this;
    }

    public int grado(String etiqueta) {
        return super.grado(etiqueta) / 2;
    }

    public boolean addVertices(String... etiquetas) {
        return super.addVertices(etiquetas); 
    }

    public static GrafoNoDirigido desdeFichero(String nombreFichero) {
        String nombreGrafo=null, auxVertice=null, auxArco1=null, auxArco2=null, auxArco3=null, auxArco4=null, tipoGrafo=null; 
        String[] etiquetasVertice=null, arco1, arco2, arco3, arco4; 

        if (nombreFichero == null) 
            return null; 

        // Lo pongo dentro de try para que no tenga que cerrar el fichero cuando acabe, se cierra automaticamente -> Leo los datos del fichero
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nombreFichero)))) { 
            tipoGrafo = br.readLine(); 
            if (tipoGrafo.equals("Dirigido")) 
                return null; 
            nombreGrafo = br.readLine(); 
            auxVertice = br.readLine(); 
            auxArco1 = br.readLine(); 
            auxArco2 = br.readLine(); 
            auxArco3 = br.readLine(); 
            auxArco4 = br.readLine(); 
        } catch (IOException e) { 
            System.out.println("Error en la lectura del fichero");
        } 

        // Identifico los datos leidos del grafo 
        etiquetasVertice = auxVertice.split(",", 3);  // Etiquetas de los vertices 
        arco1 = auxArco1.split(",", 3); // Datos del primer arco 
        arco2 = auxArco2.split(",", 3); // Datos del segundo arco 
        arco3 = auxArco3.split(",", 3); // Datos del tercer arco
        arco4 = auxArco4.split(",", 3); // Datos del cuarto arco

        // Creo el grafo correspondiente con todos los datos
        GrafoNoDirigido gnd = new GrafoNoDirigido(nombreGrafo);  
        gnd.addVertices(etiquetasVertice[0], etiquetasVertice[1], etiquetasVertice[2]); 
        gnd.addArco(arco1[0], arco1[1], arco1[2]).addArco(arco2[0], arco2[1], arco2[2]).addArco(arco3[0], arco3[1], arco3[2]).addArco(arco4[0], arco4[1], arco4[2]); 

        return gnd; 
    }

    public void salvar(String nombreFichero) {
        if (nombreFichero == null) 
            return; 

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nombreFichero)))) {
            bw.write("NoDirigido");
            bw.newLine();
            bw.write(this.getEtiqueta()); 
            bw.newLine();
            for (Vertice ve: this.vertices) {
                bw.write(ve.getEtiqueta() + ",");
            } 
            bw.newLine();
            for (Arco a: this.arcos) {
                bw.write(a.getSalida().getEtiqueta() + "," + a.getLlegada().getEtiqueta() + "," + a.getEtiqueta());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error en la escritura del fichero");
        }

    }

    @Override
    public String toString() {
        return "Grafo no dirigido " + super.etiqueta + "\nVertices: " + this.vertices.toString() + "\nArcos: "
                + this.arcos.toString();
    } 
}