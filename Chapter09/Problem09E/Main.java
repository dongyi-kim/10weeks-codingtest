import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static Edge[] getMinimumSpanningTree(int V, int E, Edge[] edges){
		Edge[] spanningTree = new Edge[V-1];

		DisjointSet disjointSet = new DisjointSet(V);
		Arrays.sort(edges);

		int mstIndex = 0;
		for(Edge e : edges){
			if(disjointSet.find(e.nodeU) == disjointSet.find(e.nodeV)){
				continue;
			}else{
				spanningTree[mstIndex ++] = e;
			}
		}

		return spanningTree;
	}

	public static void main(String[] args) throws Exception {
		int V = scanner.nextInt();
		int E = scanner.nextInt();

		Edge[] edges = new Edge[E];
		for(int i = 0 ; i < E; i += 1){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			edges[i] = new Edge(u, v, w);
		}

		Edge[] spanningTree = getMinimumSpanningTree(V, E, edges);

		long weightSum = 0;
		for(Edge e : spanningTree){
			weightSum += e.weight;
		}

		System.out.println(weightSum);
	}

}

class Edge implements Comparable<Edge>{
	public final int nodeU;
	public final int nodeV;
	public final int weight;

	public Edge(int nodeU, int nodeV, int weight){
		this.nodeU = nodeU;
		this.nodeV = nodeV;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}
}

class DisjointSet {
	private final int size;
	private int[] groupBoss;

	public DisjointSet(int size) {
		this.size = size;
		this.groupBoss = new int[size + 1];
		for (int nodeIndex = 1; nodeIndex <= size; nodeIndex += 1) {
			groupBoss[nodeIndex] = nodeIndex;
		}
	}

	public int find(int u) {
		if (groupBoss[u] == u) {
			return u;
		} else {
			groupBoss[u] = find(groupBoss[u]);
			return groupBoss[u];
		}
	}

	public void union(int u, int v) {
		int uBoss = find(u);
		int vBoss = find(v);
		groupBoss[vBoss] = uBoss;
	}
}