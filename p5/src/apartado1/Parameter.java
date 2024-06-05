package apartado1;

/**
 * Implements a generic parameter.
 * 
 * @author Sergio Larriba
 * @author Miguel Lozano
 */
public class Parameter<T> {
    private T value;

    public Parameter(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.getClass().getSimpleName();
    }
}
