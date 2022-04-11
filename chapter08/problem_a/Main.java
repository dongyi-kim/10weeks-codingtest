import java.lang.*;
import java.util.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * @param N     그래프의 정점의 수
	 * @param adj   2차원 인접 행렬
	 * @return      깊이 우선 탐색으로 탐색된 노드들의 번호 리스트
	 */
	public static ArrayList<Integer> getDfsOrder(int N, boolean[][] adj){
		ArrayList<Integer> visitedNodes = new ArrayList<>();

		Stack<State> dfsStack = new Stack<>();
		State initialState = new State(1, 1);
		dfsStack.add(initialState);

		boolean visited[] = new boolean[N+1];

		while(dfsStack.isEmpty() == false){
			State current = dfsStack.pop();

			if(visited[current.nodeIndex] == true){
				continue;
			}

			visited[current.nodeIndex] = true;
			visitedNodes.add( current.nodeIndex );

			for(int next = N; next >= 1; next -= 1){
				if(adj[current.nodeIndex][next] == true && visited[next] == false){
					State nextState = new State(next, current.depth + 1);
					dfsStack.push(nextState);
				}
			}
		}

		return visitedNodes;
	}

	/**
	 * @param N     그래프의 정점의 수
	 * @param adj   2차원 인접 행렬
	 * @return      너비 우선 탐색으로 탐색된 노드들의 번호 리스트
	 */
	public static ArrayList<Integer> getBfsOrder(int N, boolean[][] adj){
		ArrayList<Integer> visitedNodes = new ArrayList<>();

		Queue<State> bfsQueue = new LinkedList<>();
		State initialState = new State(1, 1);
		bfsQueue.add(initialState);

		boolean visited[] = new boolean[N+1];

		while(bfsQueue.isEmpty() == false){
			State current = bfsQueue.poll();

			if(visited[current.nodeIndex] == true){
				continue;
			}

			visited[current.nodeIndex] = true;
			visitedNodes.add( current.nodeIndex );

			for(int next = 1; next <= N; next += 1){
				if(adj[current.nodeIndex][next] == true && visited[next] == false){
					State nextState = new State(next, current.depth + 1);
					bfsQueue.add(nextState);
				}
			}
		}

		return visitedNodes;
	}

	public static void main(String[] args){
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		boolean[][] adj = new boolean[N+1][N+1];

		for(int i = 0 ; i < M; i += 1){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			adj[u][v] = true;
			adj[v][u] = true;
		}

		ArrayList<Integer> dfsOrders = getDfsOrder(N, adj);
		ArrayList<Integer> bfsOrders = getBfsOrder(N, adj);

		printArrayList(dfsOrders);
		printArrayList(bfsOrders);
	}

	public static void printArrayList(ArrayList<Integer> arr){
		for(int i = 0 ; i < arr.size(); i+= 1){
			if( i > 0 ){
				System.out.print("-");
			}

			int node = arr.get(i);
			System.out.print(node);
		}
		System.out.println();
	}
}

class State{
	public final int nodeIndex;     // 현재 정점의 수
	public final int depth;         // 탐색의 깊이
	public State(int nodeIndex, int depth){
		this.nodeIndex = nodeIndex;
		this.depth = depth;
	}
}