package apartado4;

import apartado2.Intent;
import apartado3.Chatbot;

/**
 * Is used as a frame to build chatbots.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * @see Chatbot
 * @see Intent
 */
public class ChatbotBuilder<T> {
    protected Chatbot<T> chatbot;

    public ChatbotBuilder(String name) {
        this.chatbot = new Chatbot<>(name);
    }

    /**
     * Add an intent to the chatbot.
     * 
     * @param intentToAdd intent to by added
     * @return the ChatbotBuilder with the modified chatbot
     */
    public ChatbotBuilder<T> withIntent(Intent intentToAdd) {
        if (intentToAdd == null) {
            return null;
        }

        this.chatbot.withIntent(intentToAdd);

        return this;
    }

    /**
     * Add a default answer to the chatbot.
     * 
     * @param defaultAnswer answer to by added
     * @return the ChatbotBuilder with the modified chatbot
     */
    public ChatbotBuilder<T> withFallback(String defaultAnswer) {
        if (defaultAnswer == null) {
            return null;
        }

        this.chatbot.withFallback(defaultAnswer);

        return this;
    }

    /**
     * Return the created chatbot.
     * 
     * @return the chatbot
     */
    public Chatbot<T> build() {
        return this.chatbot;
    }

}
