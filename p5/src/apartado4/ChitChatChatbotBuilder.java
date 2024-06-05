package apartado4;

import java.util.ArrayList;
import java.util.List;

import apartado1.StructuredPhrase;
import apartado2.Intent;

/**
 * Extends {@link ChatbotBuilder} to add by default greeting, courtesy and
 * goodbye intents.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * @see Intent
 */
public class ChitChatChatbotBuilder<T> extends ChatbotBuilder<T> {

    public ChitChatChatbotBuilder(String name) {
        super(name);
        this.addDefaultIntents();
        this.chatbot.withFallback("Sorry, I did not understand that, but I may help you ordering coffee.");
    }

    /**
     * Add the default intents.
     */
    public void addDefaultIntents() {
        List<StructuredPhrase> greetingPhrases = new ArrayList<>();
        StructuredPhrase gPhrase1 = new StructuredPhrase();
        gPhrase1.with("Hi");
        greetingPhrases.add(gPhrase1);
        StructuredPhrase gPhrase2 = new StructuredPhrase();
        gPhrase2.with("Hello");
        greetingPhrases.add(gPhrase2);
        StructuredPhrase gPhrase3 = new StructuredPhrase();
        gPhrase3.with("Good morning");
        greetingPhrases.add(gPhrase3);
        StructuredPhrase gPhrase4 = new StructuredPhrase();
        gPhrase4.with("Good evening");
        greetingPhrases.add(gPhrase4);
        this.chatbot.withIntent(
                new Intent("greeting", greetingPhrases).replies("Welcome to Java Cafe, how can I help you?"));

        List<StructuredPhrase> courtesyPhrases = new ArrayList<>();
        StructuredPhrase cPhrase1 = new StructuredPhrase();
        cPhrase1.with("How are you?");
        courtesyPhrases.add(cPhrase1);
        this.chatbot
                .withIntent(new Intent("courtesy", courtesyPhrases).replies("I'm good, what can I do for you today?"));

        List<StructuredPhrase> byePhrases = new ArrayList<>();
        StructuredPhrase bPhrase1 = new StructuredPhrase();
        bPhrase1.with("Bye");
        byePhrases.add(bPhrase1);
        StructuredPhrase bPhrase2 = new StructuredPhrase();
        bPhrase2.with("Bye bye");
        byePhrases.add(bPhrase2);
        StructuredPhrase bPhrase3 = new StructuredPhrase();
        bPhrase3.with("See you soon");
        byePhrases.add(bPhrase3);
        StructuredPhrase bPhrase4 = new StructuredPhrase();
        bPhrase4.with("Bye bye!");
        byePhrases.add(bPhrase4);
        StructuredPhrase bPhrase5 = new StructuredPhrase();
        bPhrase5.with("Bye!");
        byePhrases.add(bPhrase5);
        this.chatbot.withIntent(new Intent("goodbye", byePhrases).replies("Thank you, please call again!"));
    }

}
