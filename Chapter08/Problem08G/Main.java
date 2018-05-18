import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static final int EMPTY = -1;
	public static final int UNRIPE = 0;
	public static final int RIPE = 1;

	public static int getMinimumRequiredDays(int R, int C, int[][] tomato){
		boolean[][] visited = new boolean[R+2][C+2];
		int[][] distance = new int[R+2][C+2];

		Queue<State> bfsQueue = new LinkedList<>();
		for(int i = 1; i <= R; i += 1){
			for(int j = 1; j <= C; j += 1) {
				if(tomato[i][j] == RIPE){
					State state = new State(i, j, 1);
					bfsQueue.add(state);
				}
			}
		}

		while(!bfsQueue.isEmpty()){
			State current = bfsQueue.poll();

			if(current.row < 1 || current.col < 1 || current.row > R || current.col > C){
				continue;
			}else if(visited[current.row][current.col] == true){
				continue;
			}

			visited[current.row][current.col] = true;
			distance[current.row][current.col] = current.depth - 1;

			bfsQueue.add(new State(current.row + 1, current.col, current.depth + 1));
			bfsQueue.add(new State(current.row - 1, current.col, current.depth + 1));
			bfsQueue.add(new State(current.row, current.col + 1, current.depth + 1));
			bfsQueue.add(new State(current.row, current.col - 1, current.depth + 1));
		}


		int unripeCount = 0;
		int requiredDays = 0;
		for(int i = 1; i <= R; i += 1){
			for(int j = 1; j <= C; j += 1){
				if(tomato[i][j] == EMPTY){
					continue;
				}
				if(visited[i][j] == false){
					unripeCount += 1;
					break;
				}else{ // tomato[i][j] == RIPE
					requiredDays = Math.max(requiredDays, distance[i][j]);
				}
			}
		}

		if(unripeCount >= 1){
			return -1;
		}else{
			return requiredDays;
		}
	}

	public static void main(String[] args) throws Exception {
		int C = scanner.nextInt();
		int R = scanner.nextInt();

		int[][] tomato = new int[R+2][C+2];
		for(int i = 0 ; i < R + 2 ; i += 1){
			Arrays.fill(tomato[i], EMPTY);
		}

		for(int i = 1; i <= R; i += 1){
			for(int j = 1; j <= C ; j += 1){
				tomato[i][j] = scanner.nextInt();
			}
		}

		int answer = getMinimumRequiredDays(R, C, tomato);

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