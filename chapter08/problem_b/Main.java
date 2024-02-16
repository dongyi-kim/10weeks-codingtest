import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static int getShortestHamiltonCircuit(Graph graph){
		int origin = 1;
		ArrayList<Integer> path = new ArrayList<>();
		path.add(origin);
		int answer = getShortestHamiltonCircuit(origin, 1, 0, path, graph);
		return answer;
	}

	public static int getShortestHamiltonCircuit(int currentNode, int depth, int totalLength, ArrayList<Integer> path, Graph graph){
		int origin = path.get(0);

		if( depth == graph.V ){
			if(graph.hasEdge( currentNode, origin)){
				int pathLength = totalLength + graph.getDistance(currentNode, origin);
				return pathLength;
			}else{
				return Integer.MAX_VALUE;
			}
		}

		int minLength = Integer.MAX_VALUE;
		for(int nextNode = 1; nextNode <= graph.V; nextNode += 1){
			if(graph.hasEdge(currentNode, nextNode) == false || path.contains(nextNode) == true ){
				continue;
			}

			int nextLength = totalLength + graph.getDistance(currentNode, nextNode);
			if(nextLength >= minLength){
				continue;
			}

			path.add(nextNode);

			int length = getShortestHamiltonCircuit(nextNode, depth+1, nextLength, path, graph);
			minLength = Math.min(length, minLength);

			path.remove(path.size()-1);
		}

		return minLength;
	}

	public static void main(String[] args) throws Exception {
		int V = scanner.nextInt();

		Graph graph = new Graph(V);
		for(int from = 1; from <= V; from += 1){
			for(int to = 1; to <= V ; to += 1){
				int weight = scanner.nextInt();
				graph.addEdge(from, to, weight);
			}
		}

		int answer = getShortestHamiltonCircuit(graph);

		if(answer == Integer.MAX_VALUE){
			System.out.println("NO PATH");
		}else{
			System.out.println(answer);
		}
	}

}


class Graph{
	public static final int DISCONNECTED = 0;

	private int[][] adj;

	public final int V;

	public Graph(int V){
		this.adj = new int[V+1][V+1];
		this.V = V;
	}

	public void addEdge(int from, int to, int weight){
		adj[from][to] = weight;
	}

	public boolean hasEdge(int from, int to){
		return adj[from][to] != DISCONNECTED;
	}

	public int getDistance(int from, int to){
		if(hasEdge(from, to)){
			return adj[from][to];
		}else{
			return Integer.MAX_VALUE;
		}
	}
}