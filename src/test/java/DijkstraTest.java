
import org.junit.Test;
import com.MIPT.Dijkstra;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public final class DijkstraTest {

    @Test
    public void failsAfterNegativeWeight() {
        ByteArrayInputStream input = new ByteArrayInputStream(("3\n3\n-8\n0\n1\n1\n1\n2\n2\n2\n0\n0").getBytes());
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        new Dijkstra(input, output).run();
        System.setOut(new PrintStream(output));
        assertThat(output.toString(), containsString("отрицательный вес не разрешен!"));
    }

    @Test
    public void positiveAnswerTreeEdges() {
        ByteArrayInputStream input = new ByteArrayInputStream(("3\n3\n8\n0\n1\n1\n1\n2\n2\n2\n0\n0").getBytes());
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        new Dijkstra(input, output).run();
        System.setOut(new PrintStream(output));
        assertThat(output.toString(), containsString("0 3 2"));
    }

    @Test
    public void positiveAnswerFiveEdges() {
        ByteArrayInputStream input = new ByteArrayInputStream(("5\n7\n1\n0\n1\n2\n1\n2\n0\n2\n3\n1\n3\n4\n13\n4\n0\n6\n0\n2\n10\n0\n3\n0").getBytes());
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        new Dijkstra(input, output).run();
        System.setOut(new PrintStream(output));
        assertThat(output.toString(), containsString("0 1 3 3 4"));
    }

}