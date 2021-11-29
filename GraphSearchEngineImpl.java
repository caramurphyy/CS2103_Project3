import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * Implements the GraphSearchEngine interface.
 */
public class GraphSearchEngineImpl implements GraphSearchEngine {
	public GraphSearchEngineImpl() {
	}

	// TODO: add comments
	public List<Node> findShortestPath(Node s, Node t) {
		List<Node> shortestPath = new ArrayList<Node>();
		Map<Node, Node> parentNodes = new HashMap<Node, Node>();

		Set<Node> visited = new HashSet<Node>();
		Queue<Node> queue = new LinkedList<Node>();

		Boolean foundT = false;

		queue.add(s);

		while (!queue.isEmpty()) {
			Node node = queue.remove();
			visited.add(node);
			for (Node child : node.getNeighbors()) {
				if (!visited.contains(child)) {
					queue.add(child);
					parentNodes.put(child, node);
				}

				if (child == t) {
					queue.clear();
					foundT = true;
					break;
				}

			}

		}

		if (foundT) {

			Node currentNode = t;
			while (currentNode != s) {
				shortestPath.add(currentNode);
				currentNode = parentNodes.get(currentNode);
			}
			shortestPath.add(s);
			Collections.reverse(shortestPath);
			return shortestPath;
		}
		return null;
	}
}
