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

class Solution {
	public static final int ITERATION = 100;    //karger's algorithm의 반복 횟수

	/**
	 * 그래프를 두 개의 그룹으로 나누기 위해 제거해야할 최소의 간선 수를 계산하는 함수
	 *
	 * @param N     정점의 수
	 * @param M     간선의 수
	 * @param edges 전체 간선의 집합
	 * @return
	 */
	public static int getMinimumCut(int N, int M, Edge[] edges) {
		int minCut = Integer.MAX_VALUE;
		// 여러 번 임의의 cut을 구하여 이 중 최소값을 계산한다
		for (int i = 0; i < ITERATION; i += 1) {
			int cut = getRandomCut(N, M, edges);
			minCut = Math.min(minCut, cut);
		}
		return minCut;
	}

	/**
	 * 그래프를 분할하는 임의의 우선순위에 따라 cut을 계산하는 함수
	 *
	 * @param N     정점의 수
	 * @param M     간선의 수
	 * @param edges 전체 간선의 집합
	 * @return 그래프를 두 개의 그룹으로 분할하기 위해 제거해야 할 간선의 수
	 */
	public static int getRandomCut(int N, int M, Edge[] edges) {
		// 모든 간선을 임의의 순서로 셔플링한다
		randomShuffleEdges(M, edges);

		// 간선이 없는 그래프에서 시작한다.
		// 그룹의 수는 N이다.
		DisjointSet disjointSet = new DisjointSet(N);
		int components = N;

		// 임의의 순서로 구성된 간선들에 대해 차례로 Spanning Tree를 만들어 나간다.
		for (int i = 0; i < M; i += 1) {
			int u = edges[i].nodeU;
			int v = edges[i].nodeV;

			if (disjointSet.find(u) != disjointSet.find(v)) {
				// 두 정점을 이어도 Tree가 된다면 이어준다
				disjointSet.union(u, v);

				// 이 때 두 그룹은 Connected 되므로 전체 그룹의 수는 하나 준다.
				components -= 1;
			}

			// 만약 전체 그래프가 두 개의 그룹만 남았다면 종료한다.
			if (components == 2) {
				break;
			}
		}

		int minCut = 0;
		for (int i = 0; i < M; i += 1) {
			// 전체 간선들에 대하여
			int u = edges[i].nodeU;
			int v = edges[i].nodeV;

			// 두 개의 서로 다른 그룹을 가로지르는 간선은
			// 두 그룹에 대한 cut-edge이므로 카운팅한다
			if (disjointSet.find(u) != disjointSet.find(v)) {
				minCut += 1;
			}
		}

		// 이 때 cut edge의 수를 반환한다
		return minCut;
	}

	/**
	 * 모든 간선을 임의의 순서에 따라 셔플링해주는 함수
	 *
	 * @param M     간선의 수
	 * @param edges 간선 정보
	 */
	public static void randomShuffleEdges(int M, Edge[] edges) {
		Random random = new Random();
		// 모든 간선에 임의의 우선순위를 부여한다.
		for (int i = 0; i < M; i += 1) {
			edges[i].priority = random.nextInt();
		}

		// 우선 순위에 따라서 정렬한다.
		Arrays.sort(edges);

		// 결과적으로 모든 간선이 임의의 순서로 셔플된다.
	}
}

class Edge implements Comparable<Edge> {
	public final int nodeU;
	public final int nodeV;
	public int priority;

	public Edge(int nodeU, int nodeV) {
		this.nodeU = nodeU;
		this.nodeV = nodeV;
	}

	@Override
	public int compareTo(Edge other) {
		return this.priority - other.priority;
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
