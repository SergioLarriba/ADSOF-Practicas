package grafos;

import java.io.*;

/**
 * Se implementa la clase GrafoDirigido que contiene una serie de vertices y arcos en un sentido.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Grafo
 */
public class GrafoDirigido extends Grafo {

    public GrafoDirigido(String etiqueta) {
        super(etiqueta);
    }


    public Grafo addArco(String origen, String destino, String etiquetaArco) {
        Vertice verticeOrigen = null;
        Vertice verticeDestino = null;

        verticeOrigen = this.getVertice(origen);
        verticeDestino = this.getVertice(destino);

        if (verticeOrigen == null || verticeDestino == null || origen.equals(destino) || origen.contains(",") || destino.contains(",")) {
            return this;
        }

        // Busco si el arco ya estaba en el grafo, para no volver e añadirlo
        if (this.getArco(origen, destino) != null) {
            return this;
        }

        Arco nuevoArco = new Arco(verticeOrigen, verticeDestino, etiquetaArco);
        this.arcos.add(nuevoArco);
        this.crearPotenciaMatrices();

        return this;
    }

    public static GrafoDirigido desdeFichero(String nombreFichero) {
        String nombreGrafo=null, auxVertice=null, auxArco1=null, auxArco2=null, tipoGrafo=null; 
        String[] etiquetasVertice=null, arco1, arco2; 

        if (nombreFichero == null) 
            return null; 

        // Lo pongo dentro de try para que no tenga que cerrar el fichero cuando acabe, se cierra automaticamente -> Leo los datos del fichero 
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nombreFichero)))) { 
            tipoGrafo = br.readLine(); 
            if (tipoGrafo.equals("NoDirigido")) 
                return null; 
            nombreGrafo = br.readLine(); 
            auxVertice = br.readLine(); 
            auxArco1 = br.readLine(); 
            auxArco2 = br.readLine(); 
        } catch (IOException e) { 
            System.out.println("Error en la lectura del fichero");
        } 

        // Identifico los datos leidos del grafo 
        etiquetasVertice = auxVertice.split(",", 3);  // Etiquetas de los vertices 
        arco1 = auxArco1.split(",", 3); // Datos del primer arco 
        arco2 = auxArco2.split(",", 3); // Datos del segundo arco 

        // Creo el grafo correspondiente con todos los datos
        GrafoDirigido gd = new GrafoDirigido(nombreGrafo);  
        gd.addVertices(etiquetasVertice[0], etiquetasVertice[1], etiquetasVertice[2]); 
        gd.addArco(arco1[0], arco1[1], arco1[2]).addArco(arco2[0], arco2[1], arco2[2]); 

        return gd; 
    }

    public void salvar(String nombreFichero) {
        if (nombreFichero == null) 
            return; 

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nombreFichero)))) {
            bw.write("Dirigido");
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

    public boolean addVertices(String... etiquetas) {
        return super.addVertices(etiquetas); 
    }

    @Override
    public String toString() {
        return "Grafo dirigido " + super.etiqueta + "\nVertices: " + this.vertices.toString() + "\nArcos: "
                + this.arcos.toString();
    }
}