package apartado3;

import java.util.ArrayList;
import java.util.List;

import apartado2.Intent;

/**
 * Implements a chatbot.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class Chatbot<T> {
    private String name;
    private List<Intent> intents;
    private String defaultAnswer;
    private T lastInteraction;

    public Chatbot(String name) {
        this.name = name;
        this.intents = new ArrayList<>();
    }

    /**
     * Add an intent to the chatbot database.
     * 
     * @param intentToAdd {@link Intent}
     * @return the modified chatbot
     */
    public Chatbot<T> withIntent(Intent intentToAdd) {
        if (intentToAdd == null) {
            return null;
        }

        this.intents.add(intentToAdd);
        return this;
    }

    /**
     * Set the default answer.
     * 
     * @param defaultAnswer string to be setted
     * @return the modified chatbot
     */
    public Chatbot<T> withFallback(String defaultAnswer) {
        if (defaultAnswer == null) {
            return null;
        }

        this.defaultAnswer = defaultAnswer;
        return this;
    }

    /**
     * Gives an input to the chatbot and the chatbot responds.
     * 
     * @param input a phrase
     * @return the chatbot
     */
    public Chatbot<T> reactTo(String input) {
        if (input == null) {
            return null;
        }

        System.out.println("User> " + input);
        for (Intent intent : this.intents) {
            if (intent.matches(input)) {
                System.out.println("Java Cafe> " + intent.process(input).getReply());
                if (intent.getObject() != null) {
                    this.lastInteraction = intent.getObject();
                }
                return this;
            }
        }

        System.out.println("Java Cafe> " + this.defaultAnswer);

        return this;
    }

    /**
     * Get the object of the last interaction.
     * 
     * @return the object created
     */
    public T getObject() {
        return this.lastInteraction;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the intents
     */
    public List<Intent> getIntents() {
        return this.intents;
    }

    /**
     * @param intents the intents to set
     */
    public void setIntents(List<Intent> intents) {
        this.intents = intents;
    }

    /**
     * @return the defaultAnswer
     */
    public String getDefaultAnswer() {
        return this.defaultAnswer;
    }

    /**
     * @param defaultAnswer the defaultAnswer to set
     */
    public void setDefaultAnswer(String defaultAnswer) {
        this.defaultAnswer = defaultAnswer;
    }

    /**
     * @return the lastInteraction
     */
    public T getLastInteraction() {
        return this.lastInteraction;
    }

    /**
     * @param lastInteraction the lastInteraction to set
     */
    public void setLastInteraction(T lastInteraction) {
        this.lastInteraction = lastInteraction;
    }

    
}
