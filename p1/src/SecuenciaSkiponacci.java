package src;

import java.util.*;

/**
 * The SecuenciaSkiponacci program implements an application that
 * create some sequences of a different type and check if two lists
 * are a sequences of a given type.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class SecuenciaSkiponacci {
    private List<Integer> sequence;
    private List<Integer> startElems;
    private int n1;
    private int n2;

    /**
     * Creates a new Object of the SecuenciaSkiponacci Class.
     * 
     * @param num1 A number two calculate the next element of the sequence.
     * @param num2 A number two calculate the next element of the sequence.
     * @param firstElements The first elements of the sequence.
     * @param length The length of the sequence.
     */
    public SecuenciaSkiponacci(int num1, int num2, List<Integer> firstElements, int length) {
        this.sequence = new ArrayList<>(length);
        this.startElems = firstElements;
        this.n1 = num1;
        this.n2 = num2;

        for (Integer elem : this.startElems) {
            this.sequence.add(elem);
        }
        while (this.sequence.size() < length) {
            this.next();
        }

    }

    @Override
    public String toString() {
        return this.sequence.toString();
    }

    /**
     * The next function add a new element of a sequence.
     * 
     * @return int Returns the next element of the sequence.
     */
    public int next() {
        int numElems  = this.sequence.size();
        int nextElem = this.sequence.get(numElems - this.n1) + this.sequence.get(numElems - this.n2);
        this.sequence.add(nextElem);

        return nextElem;
    }

    /**
     * The esSecuencia function calculates if the sequence
     * has the same type of the object owner of the method.
     * 
     * @param seq A given sequence.
     * @return boolean Returns .
     */
    public boolean esSecuencia(List<Integer> seq) {
        SecuenciaSkiponacci sk = new SecuenciaSkiponacci(this.n1, this.n2, this.startElems, seq.size());

        return sk.sequence.equals(seq);
    }

    public static void main (String ...args) {
        SecuenciaSkiponacci padovan = new SecuenciaSkiponacci(2, 3, List.of(1, 1, 1), 10);
        SecuenciaSkiponacci perrin = new SecuenciaSkiponacci(2, 3, List.of(3, 0, 2), 10);
        SecuenciaSkiponacci fibonacci = new SecuenciaSkiponacci(1, 2, List.of(0, 1), 10);
        System.out.println("Padovan[10] : "+ padovan);
        System.out.println("Perrin[10]: "+ perrin);
        System.out.println("Fibonacci[10]: "+ fibonacci);
        System.out.println("Fibonacci(11): "+ fibonacci.next());
        System.out.println("Fibonacci[11]: "+ fibonacci);
        System.out.println("Es Fibonacci?: "+ fibonacci.esSecuencia(List.of(0, 1, 1, 2, 3)));
        System.out.println("Es Perrin?: "+ perrin.esSecuencia(List.of(3, 0, 2, 4)));
        }
}
