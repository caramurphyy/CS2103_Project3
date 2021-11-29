import java.util.*;

/**
 * Implements the GraphSearchEngine interface.
 */
public class GraphSearchEngineImpl implements GraphSearchEngine {
	public GraphSearchEngineImpl() {
	}

	/**
	 * Finds the shortest path between source and target.
	 * Returns an empty null if no path found.
	 * 
	 * @param source is the first node to start the path from
	 * @param target is where to end the path
	 * @return List<Node>
	 */
	public List<Node> findShortestPath(Node source, Node target) {
		List<Node> shortestPath = new ArrayList<Node>();
		Map<Node, Node> parentNodes = new HashMap<Node, Node>();

		Set<Node> visited = new HashSet<Node>();
		Queue<Node> queue = new LinkedList<Node>();

		Boolean foundTarget = false;

		queue.add(source);

		while (!queue.isEmpty()) {
			Node node = queue.remove();
			visited.add(node);
			for (Node child : node.getNeighbors()) {
				if (!visited.contains(child)) {
					queue.add(child);
					parentNodes.put(child, node);
				}

				if (child == target) {
					// We have found a path between the source and target
					queue.clear();
					foundTarget = true;
					break;
				}
			}
		}

		if (foundTarget) {
			// Backtrack using parentNodes from target until we reach source
			Node currentNode = target;
			while (currentNode != source) {
				shortestPath.add(currentNode);
				currentNode = parentNodes.get(currentNode);
			}

			shortestPath.add(source);
			Collections.reverse(shortestPath);
			return shortestPath;
		}

		return null; // No path found
	}
}
