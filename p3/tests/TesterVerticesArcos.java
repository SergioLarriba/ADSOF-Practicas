package tests;

import grafos.*; 

public class TesterVerticesArcos {
    public static void main(String[] args) {
        Vertice[] ciudades = {new Vertice("Madrid"),
            new Vertice("Barcelona"),
            new Vertice("Bilbao"),
            new Vertice("Valencia")};
   
        for (Vertice v : ciudades)
            System.out.print(v+" ");
        System.out.println();

        Arco carreteras[] = {new Arco(ciudades[0], ciudades[1], "506 km"),
                            new Arco(ciudades[1], ciudades[2], "645 km"),
                            new Arco(ciudades[0], ciudades[3], "350 km"),
                            new Arco(ciudades[3], ciudades[1], "350 km")};
        for (Arco a : carreteras)
            System.out.println(a);
    }
}