package apartado2;

import java.util.List;

import apartado1.StructuredPhrase;

/**
 * Implements a basic intent without parameters.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class Intent {
    protected String name;
    protected List<StructuredPhrase> trainingPhrases;
    protected String output;

    public Intent(String name, List<StructuredPhrase> input) {
        this.name = name;
        this.trainingPhrases = input;
        this.output = "";
    }

    /**
     * Returns if the input matches with any intent registered in the chatbot.
     * 
     * @param input the input to be analized
     * @return true if they match, false otherwise
     */
    public boolean matches(String input) {
        if (input == null) {
            return false;
        }

        return IntentHelper.containsIgnoreCase(input, this.trainingPhrases.toArray());
    }

    /**
     * Set an answer to an intent.
     * 
     * @param output the answer
     * @return the object itself
     */
    public Intent replies(String output) {
        if (output == null) {
            return null;
        }

        this.output = output;

        return this;
    }

    public Intent process(String phrase) {
        if (phrase == null) {
            return null;
        }

        if (!this.matches(phrase)) {
            return null;
        }

        return this;
    }

    /**
     * Get the answer.
     * 
     * @return the response to an input
     */
    public String getReply() {
        return this.output;
    }

    public <T> T getObject() {
        return null;
    }
}
