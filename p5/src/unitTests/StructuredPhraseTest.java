package unitTests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import apartado1.StructuredPhrase;

public class StructuredPhraseTest {
    StructuredPhrase phrases;

    @Before
    public void setUp() {
        phrases = new StructuredPhrase();
    }

    @Test
    public void testSetting() {
        phrases.setting("typeInt", 1);
        assertTrue(phrases.getParameters().containsKey("typeInt"));
        assertTrue(phrases.getParameters().containsValue(1));

        phrases.setting("typeString", "string");
        assertTrue(phrases.getParameters().containsKey("typeString"));
        assertTrue(phrases.getParameters().containsValue("string"));

        phrases.setting("typeDouble", 3.5);
        assertTrue(phrases.getParameters().containsKey("typeDouble"));
        assertTrue(phrases.getParameters().containsValue(3.5));
    }

    @Test
    public void testWithLiterals() {
        phrases.with("literal");
        assertTrue(phrases.getPhrase().contains("literal"));
        phrases.with("anotherLiteral");
        assertTrue(phrases.getPhrase().contains("anotherLiteral"));
    }

    @Test
    public void testWithAttributes() {
        phrases.with("integer", 1);
        phrases.with("string", "stringValue");
        phrases.with("double", 3.3);

        assertTrue(phrases.getPhrase().contains("1"));
        assertTrue(phrases.getPhrase().contains("stringValue"));
        assertTrue(phrases.getPhrase().contains("3.3"));

        assertTrue(phrases.getParameters().containsKey("integer"));
        assertTrue(phrases.getParameters().containsKey("string"));
        assertTrue(phrases.getParameters().containsKey("double"));

        assertTrue(phrases.getParameters().containsValue(1));
        assertTrue(phrases.getParameters().containsValue("stringValue"));
        assertTrue(phrases.getParameters().containsValue(3.3));
    }
}

