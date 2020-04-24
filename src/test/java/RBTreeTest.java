import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class RBTreeTest {
    @Test
    public void checkCorrectnessOfInsertion() {
        ByteArrayInputStream input = new ByteArrayInputStream(("5\n1\n2\n3\n4\n5").getBytes());
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        new com.RBTreeBuilder(input, output).run();
        System.setOut(new PrintStream(output));
        assertThat(output.toString(), containsString(
                "1 black\n" +
                        "2 black\n" +
                        "3 red\n" +
                        "4 black\n" +
                        "5 red\n" +
                        "Корень: 2"));
    }
}
