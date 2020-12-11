import com.MIPT.Kruskal;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class KruskalTest {
    @Test
    public void positiveAnswerFourEdges() {
        ByteArrayInputStream input = new ByteArrayInputStream(("4\n4\n1\n0\n1\n2\n1\n2\n3\n2\n3\n10\n3\n0").getBytes());
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        new Kruskal(input, output).run();
        System.setOut(new PrintStream(output));
        assertThat(output.toString(), containsString(
                "Start: 0 Weight: 1 End: 1\n" +
                        "Start: 1 Weight: 2 End: 2\n" +
                        "Start: 2 Weight: 3 End: 3"));
    }

    @Test
    public void positiveAnswerSevenEdges() {
        ByteArrayInputStream input = new ByteArrayInputStream(("7\n6\n1\n0\n5\n13\n0\n4\n0\n4\n1\n1\n1\n2\n5\n2\n3\n2\n4\n3\n10\n1\n3").getBytes());
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        new Kruskal(input, output).run();
        System.setOut(new PrintStream(output));
        assertThat(output.toString(), containsString(
                "Start: 4 Weight: 0 End: 1\n" +
                        "Start: 0 Weight: 1 End: 5\n" +
                        "Start: 1 Weight: 1 End: 2\n" +
                        "Start: 4 Weight: 2 End: 3\n" +
                        "Start: 0 Weight: 13 End: 4"));
    }

}
