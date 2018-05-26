import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static Edge[] getMinimumSpanningTree(int V, int E, Edge[] edges){
		// 트리는 항상 V-1개의 간선이 있다.
		Edge[] spanningTree = new Edge[V-1];

		// 빈 그래프 G에 대한 disjointSet을 선언한다
		DisjointSet disjointSet = new DisjointSet(V);

		// 간선들을 오름차순으로 정렬한다
		Arrays.sort(edges);

		int mstIndex = 0;
		for(Edge e : edges){
			// 모든 간선 e에 대해
			if(disjointSet.find(e.nodeU) == disjointSet.find(e.nodeV)){
				// 이미 해당 두 정점이 연결성이 존재한다면 이 간선은 무시한다.
				continue;
			}else{
				// 그렇지 않다면 이 간선을 그래프 G에 추가한다.
				spanningTree[mstIndex ++] = e;
				disjointSet.union(e.nodeU, e.nodeV);
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