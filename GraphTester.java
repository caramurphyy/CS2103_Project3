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

	// check finding a path to itself
	@Test
	void testPathToSelf() {
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
		final Node actor1 = graph.getActor("Name2");
		final Node actor2 = graph.getActor("Name2");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actor2);
		assertEquals(1, shortestPath.size());
		final String[] correctNames = { "Name2" };
		int idx = 0;
		for (Node node : shortestPath) {
			assertEquals(correctNames[idx++], node.getName());
		}
	}

	// check that all actors added are actors and not another role
	@Test
	void testOnlyActors() {
		final IMDBGraph graph;
		try {
			graph = new IMDBGraphImpl("/Users/caramurphy/CS2103/Project3/myActors.tsv",
					"/Users/caramurphy/CS2103/Project3/myMovies.tsv");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			assertTrue(false);
			return;
		}
		final Node actor = graph.getActor("Name4");
		assertNull(actor);

	}

	// check path that only contains one intermediary node
	@Test
	void testOneNodePath() {
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
		final Node actor1 = graph.getActor("Name3");
		final Node actor2 = graph.getActor("Name2");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actor2);
		assertEquals(3, shortestPath.size());
		final String[] correctNames = { "Name3", "Blah3", "Name2" };
		int idx = 0;
		for (Node node : shortestPath) {
			assertEquals(correctNames[idx++], node.getName());
		}
	}

	//check that when there are multiple shortest paths?
	
}
