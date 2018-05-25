import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static int[] computeShortestPaths(Node[] nodes, Node origin) {

		PriorityQueue<State> bfsQueue = new PriorityQueue<>();
		State initialState = new State(origin, 0);
		bfsQueue.add(initialState);


		boolean[] visited = new boolean[nodes.length];
		int[] distance = new int[nodes.length];
		Arrays.fill(distance, Integer.MAX_VALUE);

		while(bfsQueue.isEmpty() == false){
			State current = bfsQueue.poll();
			Node currentNode = current.currentNode;

			if( visited[currentNode.index] == true ){
				continue;
			}

			visited[currentNode.index] = true;
			distance[currentNode.index] = current.totalWeight;

			for(Edge e : currentNode.edges){
				Node nextNode = e.dest;
				if(visited[nextNode.index] == true){
					continue;
				}

				int nextTotalWeight = current.totalWeight + e.weight;

				State nextState = new State(nextNode, nextTotalWeight);

				bfsQueue.add( nextState );
			}
		}

		return distance;
	}

	public static void main(String[] args) throws Exception {
		int V = scanner.nextInt();
		int E = scanner.nextInt();
		int origin = scanner.nextInt();

		Node[] nodes = new Node[V+1];
		for(int i = 1; i <= V; i+= 1){
			nodes[i] = new Node(i);
		}

		for(int i = 0 ; i < E; i += 1){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			Edge e = new Edge(nodes[u], nodes[v], w);
			nodes[u].edges.add(e);
		}

		int[] distances = computeShortestPaths(nodes, nodes[origin]);

		for(int i = 1; i <= V; i+= 1){
			if(distances[i] == Integer.MAX_VALUE){
				writer.write("INF\n");
			}else{
				writer.write(String.format("%d\n", distances[i]));
			}
		}
		writer.flush();
		writer.close();
	}

}


class Node{
	public final int index;
	public final ArrayList<Edge> edges;

	public Node(int index){
		this.index = index;
		this.edges = new ArrayList<Edge>();
	}
}

class Edge{
	public final Node origin;
	public final Node dest;
	public final int weight;

	public Edge(Node origin, Node dest, int weight){
		this.origin = origin;
		this.dest = dest;
		this.weight = weight;
	}
}

class State implements Comparable<State>{
	public final Node currentNode;
	public final int totalWeight;

	public State(Node currentNode, int totalWeight){
		this.currentNode = currentNode;
		this.totalWeight = totalWeight;
	}

	@Override
	public int compareTo(State other) {
		return this.totalWeight - other.totalWeight;
	}
}
