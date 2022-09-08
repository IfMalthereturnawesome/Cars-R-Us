package dat3.experiments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSanitizerTest {

    @Test
    void simpleSanitize() {
    }

    @Test
    void simpleSanitizeTest() {
        String result = SimpleSanitizer.simpleSanitize("Hello <b>World</b>");
        String result2 = SimpleSanitizer.simpleSanitize("Hello <h1>World</h1>");
        String result3 = SimpleSanitizer.simpleSanitize("Hello <tr>World</tr>");
        String result4 = SimpleSanitizer.simpleSanitize("Hello <p>World</p>");
        String result5 = SimpleSanitizer.simpleSanitize("Hello <article>World</article>");
        String result6 = SimpleSanitizer.simpleSanitize("Hello <main>WORLD</main>");

        assertEquals("Hello World",result);
        assertEquals("Hello World",result2);
        assertEquals("Hello World",result3);
        assertEquals("Hello World",result4);
        assertEquals("Hello World",result5);
        assertEquals("Hello WORLD",result6);

    }

}