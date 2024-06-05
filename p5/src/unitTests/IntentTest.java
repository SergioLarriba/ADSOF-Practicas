package unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import apartado1.StructuredPhrase;
import apartado2.Intent;

public class IntentTest {
    Intent intent;
    List<StructuredPhrase> phrases;
    StructuredPhrase phrase1;

    @Before
    public void setUp() {
        phrases = new ArrayList<>();
        phrase1 = new StructuredPhrase().with("Prueba");
        phrases.add(phrase1);
        intent = new Intent("test", phrases);
    }

    @Test
    public void testMatches() {
        assertTrue(intent.matches("Prueba"));
        assertFalse(intent.matches("Fallo"));
    }

    @Test
    public void testReplies() {
        intent.replies("Reply");
        assertEquals("Reply", intent.getReply());
    }
}
