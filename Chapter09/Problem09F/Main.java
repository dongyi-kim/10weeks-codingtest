import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static Edge[] getMinimumSpanningTree(int V, int E, Node[] nodes){

		int mstIndex = 0;
		Edge[] spanningTree = new Edge[V-1];

		int origin = 1;

		PriorityQueue<Edge> usableEdges = new PriorityQueue<>();
		for(Edge e : nodes[origin].edges){
			usableEdges.add(e);
		}

		boolean[] visited = new boolean[V+1];
		visited[origin] = true;

		while(usableEdges.isEmpty() == false){
			Edge currentEdge = usableEdges.poll();
			Node targetNode = currentEdge.dest;

			if( visited[targetNode.index] == true){
				continue;
			}

			visited[targetNode.index] = true;
			spanningTree[mstIndex++] = currentEdge;

			for(Edge e : targetNode.edges){
				Node nextNode = e.dest;
				if( visited[nextNode.index ] == true){
					continue;
				}

				usableEdges.add(e);
			}
		}

		return spanningTree;
	}

	public static void main(String[] args) throws Exception {
		int V = scanner.nextInt();
		int E = scanner.nextInt();

		Node[] nodes = new Node[V+1];
		for(int i = 1; i <= V; i += 1){
			nodes[i] = new Node(i);
		}

		for(int i = 0 ; i < E; i += 1){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int w = scanner.nextInt();

			nodes[u].edges.add( new Edge( nodes[u], nodes[v], w ) );
			nodes[v].edges.add( new Edge( nodes[v], nodes[u], w ) );

		}

		Edge[] spanningTree = getMinimumSpanningTree(V, E, nodes);

		long weightSum = 0;
		for(Edge e : spanningTree){
			weightSum += e.weight;
		}

		System.out.println(weightSum);
	}

}

class Node{
	public final int index;
	public final ArrayList<Edge> edges;
	public Node(int index){
		this.index = index;
		this.edges = new ArrayList<>();
	}
}

class Edge implements Comparable<Edge>{
	public final Node origin;
	public final Node dest;
	public final int weight;

	public Edge(Node origin, Node dest, int weight){
		this.origin = origin;
		this.dest = dest;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}
}
