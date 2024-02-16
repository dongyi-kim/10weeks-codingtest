import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static boolean hasHamiltonPath(Graph graph){
		boolean[] visited = new boolean[graph.V+1];
		for(int originNode = 1; originNode <= graph.V; originNode += 1){
			boolean has = hasHamiltonPath(1, originNode, visited, graph);
			if(has){
				return true;
			}
		}
		return false;
	}

	public static boolean hasHamiltonPath(int depth, int currentNode, boolean[] visited, Graph graph) {
		if(visited[currentNode]){
			return false;
		}
		visited[currentNode] = true;

		boolean hasPath = false;
		if(depth == graph.V){
			hasPath = true;
		}else{
			for(int nextNode = 1; nextNode <= graph.V; nextNode += 1) {
				hasPath = hasHamiltonPath(depth+1, nextNode, visited, graph);
				if(hasPath){
					break;
				}
			}
		}

		visited[currentNode] = false;

		return hasPath;
	}

	public static void main(String[] args) throws Exception {
		int V = scanner.nextInt();
		int E =scanner.nextInt();

		Graph graph = new Graph(V);
		for(int i = 0 ; i < E ; i += 1){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			graph.addEdge(u, v);
		}

		boolean hasPath = hasHamiltonPath(graph);

		if(hasPath){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}

}


class Graph{
	private boolean[][] adj;
	public final int V;

	public Graph(int V){
		this.adj = new boolean[V+1][V+1];
		this.V = V;
	}

	public void addEdge(int u, int v){
		adj[u][v] = adj[v][u] = true;
	}

	public boolean hasEdge(int u, int v){
		return adj[u][v];
	}


}