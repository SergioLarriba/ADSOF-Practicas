package apartado2;

import java.util.*;

import apartado1.*;
import java.util.function.*;

/**
 * Extends {@link Intent} to add parameters and context.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class ContextIntent<T> extends Intent {
    private Map<String, Object> paramValues;
    private List<ParameterOfContext<? extends Object>> parameters;
    private String reply;
    private Function<ContextIntent<T>, T> methodToCreateObject;

    public ContextIntent(String name, List<StructuredPhrase> trainingPhrases) {
        super(name, trainingPhrases);
        this.paramValues = new LinkedHashMap<>();
        this.parameters = new ArrayList<>();
    }

    /**
     * Add a new parameter.
     * 
     * @param attributeName attribute name
     * @param matches       match function
     * @param valueOf       function to get the value of an attribute
     * @return the object itself
     */
    public <S> ContextIntent<T> withParameter(String attributeName, Predicate<String> matches,
            Function<String, S> valueOf) {
        if (attributeName == null || matches == null || valueOf == null) {
            return null;
        }

        ParameterOfContext<S> newParameter = new ParameterOfContext<S>(attributeName, matches, valueOf);
        this.parameters.add(newParameter);

        return this;
    }

    /**
     * Process the input to extract the parameters.
     * 
     * @param input the string to be procesed
     * @return the object itself
     */
    public ContextIntent<T> process(String input) {
        if (input == null) {
            return null;
        }

        for (StructuredPhrase trainingPhrase : this.trainingPhrases) {
            if (trainingPhrase.getNaturalLanguage().equalsIgnoreCase(input)) {
                String[] tokens = input.split("\s+");
                this.paramValues = trainingPhrase.getParameters(); // Establecemos los valores por defecto
                for (ParameterOfContext<?> param : this.parameters) {
                    for (String token : tokens) {
                        if (param.getMatches().test(token)) {
                            this.paramValues.put(param.getName(), param.getValueOf().apply(token));
                        }
                    }
                }
            }
        }

        return this;
    }

    /**
     * Get the answer.
     * 
     * @return the response to an input
     */
    public String getReply() {
        for (String key : this.paramValues.keySet()) {
            this.reply = this.reply.replaceAll("#" + key + "#", this.paramValues.get(key).toString());
        }

        return this.reply;
    }

    /**
     * Set an answer to an intent.
     * 
     * @param reply the answer
     * @return the object itself
     */
    @Override
    public ContextIntent<T> replies(String reply) {
        if (reply == null) {
            return null;
        }

        this.reply = reply;
        return this;
    }

    /**
     * Returns the object create through the interaction.
     * 
     * @return an object
     */
    public T getObject() {
        return this.methodToCreateObject.apply(this);
    }

    /**
     * Set the constructor to use to create the object.
     * 
     * @param instanceFunction the constructor
     * @return the object that called the method
     */
    public ContextIntent<T> resultObject(Function<ContextIntent<T>, T> instanceFunction) {
        if (instanceFunction == null) {
            return null;
        }

        this.methodToCreateObject = instanceFunction;

        return this;
    }

    /**
     * Returns a parameter extracted from the intent.
     * 
     * @param <S>       type of the parameter value
     * @param paramName parameter name
     * @return the parameter value
     */
    public <S extends Object> S getParam(String paramName) {
        if (paramName == null) {
            return null;
        }

        return (S) this.paramValues.get(paramName);
    }

    /**
     * Returns if the input matches with any intent registered in the chatbot.
     * 
     * @param input the input to be analized
     * @return true if they match, false otherwise
     */
    @Override
    public boolean matches(String input) {
        if (input == null) {
            return false;
        }

        for (StructuredPhrase phrase : this.trainingPhrases) {
            if (phrase.getNaturalLanguage().equalsIgnoreCase(input)) {
                return true;
            }
        }

        return false;
    }
}
