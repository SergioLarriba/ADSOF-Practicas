package apartado2;

import apartado1.CoffeeType;

/**
 * This class is used as a context in {@link apartado1.StructuredPhrase}.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class CoffeeOrder {
    private int num;
    private CoffeeType ct;

    public CoffeeOrder(int num, CoffeeType ct) {
        this.num = num;
        this.ct = ct;
    }

    @Override
    public String toString() {
        return "CoffeeOrder[" + num + ", " + this.ct + "]";
    }
}