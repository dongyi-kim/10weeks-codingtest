import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final int NO_PARENT = -1;

	public static final Scanner scanner = new Scanner(System.in);


	/**
	 * 두 노드 current부터 goal까지의 최장 경로의 길이를 반환하는 함수
	 *
	 * @param current
	 * @param goal
	 * @param adj
	 * @return
	 */
	public static int getLongestPathLength(int current, int goal, ArrayList<Integer>[] adj){
		int N = adj.length;
		boolean[] visited = new boolean[N];
		return getLongestPathLength(current, goal, 1, adj, visited);
	}

	public static final int NOPATH = -100;	// 경로가 없는 경우 길이를 음수로 표현하자 
	public static int getLongestPathLength(int current, int goal, int depth, ArrayList<Integer>[] adj, boolean[] visited){
		if( visited[current] == true ){	// 이미 방문한 노드라면 경로를 만들 수 없다 
			return NOPATH;
		} else if(current == goal){	// 목적지라면 0부터 역으로 길이를 가산해나가자
			return 0;
		}

		int maxLength = NOPATH;

		visited[current] = true;
		for(int nextNode : adj[current]){

			int length = 1 + getLongestPathLength(nextNode, goal, depth+1, adj, visited);
			maxLength = Math.max(maxLength , length);

		}
		visited[current] = false;
		return maxLength;

	}


	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		ArrayList<Integer>[] adj = new ArrayList[N+1];

		int origin = scanner.nextInt();
		int dest = scanner.nextInt();

		for(int i = 1; i <= N; i += 1){
			adj[i] = new ArrayList<>();
		}

		for(int i = 0 ; i < M; i += 1){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}

		int answer = getLongestPathLength(origin, dest, adj);
		if(answer < 0){
			System.out.println(0); // 경로가 존재하지 않는다
		}else{
			System.out.println(answer);
		}
	}

}
