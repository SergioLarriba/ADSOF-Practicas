package apartado2;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Store the methods to extract the parameters value of a {@link ContextIntent}.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class ParameterOfContext<T> {
    private String name;
    private Predicate<String> matches;
    private Function<String, T> valueOf;

    public ParameterOfContext(String name, Predicate<String> matches, Function<String, T> valueOf) {
        this.name = name;
        this.matches = matches;
        this.valueOf = valueOf;
    }

    public String getName() {
        return this.name;
    }

    public Predicate<String> getMatches() {
        return this.matches;
    }

    public Function<String, T> getValueOf() {
        return this.valueOf;
    }

}