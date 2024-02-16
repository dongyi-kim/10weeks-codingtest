import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static int getShortestPathLength(int originR, int originC, int destR, int destC, int R, int C, boolean[][] passable) {
		boolean[][] visited = new boolean[R + 2][C + 2];
		int[][] distance = new int[R + 2][C + 2];

		Queue<State> bfsQueue = new LinkedList<>();
		State initialState = new State(originR, originC, 1);
		bfsQueue.add(initialState);

		while (!bfsQueue.isEmpty()) {
			State current = bfsQueue.poll();

			if (current.row < 1 || current.col < 1 || current.row > R || current.col > C) {
				continue;
			} else if (visited[current.row][current.col] == true || passable[current.row][current.col] == false) {
				continue;
			}

			visited[current.row][current.col] = true;
			distance[current.row][current.col] = current.depth - 1;

			bfsQueue.add(new State(current.row + 1, current.col, current.depth + 1));
			bfsQueue.add(new State(current.row - 1, current.col, current.depth + 1));
			bfsQueue.add(new State(current.row, current.col + 1, current.depth + 1));
			bfsQueue.add(new State(current.row, current.col - 1, current.depth + 1));
		}

		if(visited[destR][destC] == false){
			return -1;
		}
		return distance[destR][destC];
	}

	public static void main(String[] args) throws Exception {
		int R = scanner.nextInt();
		int C = scanner.nextInt();

		int originR = -1;
		int originC = -1;
		int destR = -1;
		int destC = -1;

		boolean[][] passable = new boolean[R + 2][C + 2];

		for (int i = 1; i <= R; i += 1) {
			String line = scanner.next();
			for (int j = 1; j <= C; j += 1) {
				char c = line.charAt(j - 1);
				if (c != '#') {
					passable[i][j] = true;
				}
				if (c == 'S') {
					originR = i;
					originC = j;
				} else if (c == 'E') {
					destR = i;
					destC = j;
				}
			}
		}

		int answer = getShortestPathLength(originR, originC, destR, destC, R, C, passable);

		System.out.println(answer);
	}

}

class State {
	public final int row;
	public final int col;
	public final int depth;

	public State(int row, int col, int depth) {
		this.row = row;
		this.col = col;
		this.depth = depth;
	}
}