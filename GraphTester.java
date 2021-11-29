import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.io.*;

/**
 * Test suite for Project 3.
 */
public class GraphTester {
	/**
	 * Verifies that there is no shortest path between a specific pair of actors.
	 */
	@Test
	@Timeout(5) // 5sec timeout on the test, in case there's a bug during search.
	public void findShortestPathWhenNoneExists() {
	}

	// TODO write tests...

	// check when there is no path
	@Test
	void testNoPath() {
		final GraphSearchEngine searchEngine = new GraphSearchEngineImpl();
		final IMDBGraph graph;
		try {
			graph = new IMDBGraphImpl("/Users/caramurphy/CS2103/Project3/myActors.tsv",
					"/Users/caramurphy/CS2103/Project3/myMovies.tsv");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			assertTrue(false);
			return;
		}
		final Node actor1 = graph.getActor("Name8");
		final Node actor2 = graph.getActor("Name2");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actor2);
		assertEquals(null, shortestPath);

	}
}
