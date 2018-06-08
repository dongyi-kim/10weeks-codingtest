import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int N = scanner.nextInt();
		int M = scanner.nextInt();

		Edge[] edges = new Edge[M];
		for (int i = 0; i < M; i += 1) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			edges[i] = new Edge(u, v);
		}

		int answer = Solution.getMinimumCut(N, M, edges);

		System.out.println(answer);
	}
}

class Solution{
	public static final int ITERATION = 100;

	public static int getMinimumCut(int N, int M, Edge[] edges){
		int minCut = Integer.MAX_VALUE;
		for(int i = 0 ; i < ITERATION ; i += 1){
			int cut = getRandomCut(N, M, edges);
			minCut = Math.min(minCut, cut);
		}
		return minCut;
	}

	public static int getRandomCut(int N, int M, Edge[] edges){
		edges = randomShuffledEdges(M, edges);

		DisjointSet disjointSet = new DisjointSet(N);
		int components = N;
		for(int i = 0 ; i < M; i += 1){
			int u = edges[i].nodeU;
			int v = edges[i].nodeV;

			if(disjointSet.find(u) != disjointSet.find(v)){
				components -= 1;
				disjointSet.union(u, v);
			}

			if(components == 2){
				break;
			}
		}

		int minCut = 0;
		for(int i = 0 ; i < M ; i += 1){
			int u = edges[i].nodeU;
			int v = edges[i].nodeV;

			if(disjointSet.find(u) != disjointSet.find(v)){
				minCut += 1;
			}
		}

		return minCut;
	}

	public static Edge[] randomShuffledEdges(int M, Edge[] edges){
		List<Edge> a = Arrays.asList(edges);
		Collections.shuffle(a);
		return (Edge[])a.toArray();
	}
}

class Edge {
	public final int nodeU;
	public final int nodeV;

	public Edge(int nodeU, int nodeV) {
		this.nodeU = nodeU;
		this.nodeV = nodeV;
	}
}

class DisjointSet {
	private final int[] group;

	public DisjointSet(int size) {
		this.group = new int[size];
		for (int i = 0; i < size; i += 1) {
			this.group[i] = i;
		}
	}

	public int find(int u) {
		if (this.group[u] != u) {
			this.group[u] = find(this.group[u]);
		}
		return this.group[u];
	}

	public void union(int u, int v) {
		int uBoss = find(u);
		int vBoss = find(v);
		this.group[uBoss] = vBoss;
	}
}
