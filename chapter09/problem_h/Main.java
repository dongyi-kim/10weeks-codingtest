import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static int[] computeShortestPaths(int V, Node[] nodes, Node origin) {
		int[] distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		distance[origin.index]  = 0;

		boolean updated = false;
		for(int times = 1; times <= V; times += 1){
			updated = false;

			for(int current = 1; current <= V; current += 1){
				if(distance[current] == Integer.MAX_VALUE){
					continue;
				}

				for(Edge e : nodes[current].edges){
					int next = e.dest.index;
					int nextCost = distance[current] + e.weight;
					if(distance[next] > nextCost ){
						distance[next] = nextCost;
						updated = true;
					}
				}
			}

			if(updated == false){
				break;
			}else if(times == V){
				Arrays.fill(distance, Integer.MIN_VALUE);
			}
		}

		return distance;
	}

	public static void testCase(int caseIndex){
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int W = scanner.nextInt();
		int origin = 1;

		Node[] nodes = new Node[N+1];
		for(int i = 1; i <= N; i+= 1){
			nodes[i] = new Node(i);
		}

		for(int i = 0 ; i < M; i += 1){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			Edge e1 = new Edge(nodes[u], nodes[v], w);
			Edge e2 = new Edge(nodes[v], nodes[u], w);
			nodes[u].edges.add(e1);
			nodes[v].edges.add(e2);
		}

		for(int i = 0 ; i < W; i += 1){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			Edge e = new Edge(nodes[u], nodes[v], -w);
			nodes[u].edges.add(e);
		}

		int[] distances = computeShortestPaths(N, nodes, nodes[origin]);

		if(distances[origin] == Integer.MIN_VALUE){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();
		for(int caseIndex = 1; caseIndex <= caseSize ; caseIndex += 1){
			testCase(caseIndex);
		}
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
