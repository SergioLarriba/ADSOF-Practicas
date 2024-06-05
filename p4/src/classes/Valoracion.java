package classes;

/**
 * Enumeración de las dos posibles valoraciones (LIKE y DISLIKE).
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public enum Valoracion {
    LIKE(1),
    DISLIKE(-0.5);

    private final double value;

    private Valoracion(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" + name() + "]";
    }

    /**
     * @return el valor asignado a la valoración
     */
    public double getValue() {
        return this.value;
    }
}