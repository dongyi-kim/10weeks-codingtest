import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static boolean isConnectedGraph(Graph graph) {
		Queue<Integer> reachableNodes = new LinkedList();

		boolean[] visited = new boolean[graph.V + 1];

		int originNode = 1;
		int visitCount = 0;
		reachableNodes.add(originNode);
		while (reachableNodes.isEmpty() == false) {
			int currentNode = reachableNodes.poll();

			if (visited[currentNode] == true) {
				continue;
			}

			visited[currentNode] = true;
			visitCount += 1;

			for (int nextNode = 1; nextNode <= graph.V; nextNode += 1) {
				if (graph.hasEdge(currentNode, nextNode)) {
					reachableNodes.add(nextNode);
				}
			}
		}

		return visitCount == graph.V;
	}

	public static boolean hasEulerPath(Graph graph) {
		if (false == isConnectedGraph(graph)) {
			return false;
		}

		int oddDegree = 0;
		for (int node = 1; node <= graph.V; node += 1) {
			int degree = graph.getDegree(node);
			if (degree % 2 == 1) {
				oddDegree += 1;
			}
		}

		return oddDegree == 0 || oddDegree == 2;
	}

	public static boolean hasEulerCircuit(Graph graph) {
		if (false == isConnectedGraph(graph)) {
			return false;
		}

		int oddDegree = 0;
		for (int node = 1; node <= graph.V; node += 1) {
			int degree = graph.getDegree(node);
			if (degree % 2 == 1) {
				oddDegree += 1;
				break;
			}
		}

		return oddDegree == 0;
	}

	public static void main(String[] args) throws Exception {
		int V = scanner.nextInt();

		Graph graph = new Graph(V);
		for (int u = 1; u <= V; u += 1) {
			for (int v = 1; v <= V; v += 1) {
				int exist = scanner.nextInt();
				if (exist == 1) {
					graph.addEdge(u, v);
				}
			}
		}

		if (hasEulerPath(graph)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

		if (hasEulerCircuit(graph)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}

class Graph {
	public final int V;
	private boolean[][] adj;
	private int[] degree;

	public Graph(int V) {
		this.adj = new boolean[V + 1][V + 1];
		this.V = V;
		this.degree = new int[V + 1];
	}

	public void addEdge(int u, int v) {
		adj[u][v] = adj[v][u] = true;

		degree[u] += 1;
		degree[v] += 1;
	}

	public boolean hasEdge(int u, int v) {
		return adj[u][v];
	}

	public int getDegree(int nodeIndex) {
		return this.degree[nodeIndex];
	}

}