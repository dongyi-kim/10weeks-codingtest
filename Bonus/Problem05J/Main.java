import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static boolean isPossible(boolean[][] visited, int N, Position2D pos) {
		if(pos.r < 0 || pos.r >= N){
			return false;
		}
		if(pos.c < 0 || pos.c >= N){
			return false;
		}

		if(visited[pos.r][pos.c] == true){
			return false;
		}
		return true;
	}

	public static int getTotalLength(int N, int K, int initR, int initC, int[] lengths, int[] directions) {
		Robot robot = new Robot(initR, initC);
		boolean[][] visited = new boolean[N][N];
		boolean continued = true;

		visited[initR][initC] = true;

		for(int i = 0 ; i < K && continued; i+= 1){
			int D = directions[i];
			int L = lengths[i];

			robot.setCurrentDirection(D);
			for(int j=0; j < L; j+=1){
				robot.goStraight(1);

				Position2D pos = robot.getCurrentPosition();
				if(isPossible( visited, N, pos ) == false){
					continued = false;
					break;
				}

				visited[pos.r][pos.c] = true;
			}
		}

		int count = 0;
		for(int r = 0 ; r < N; r += 1){
			for(int c = 0; c < N; c += 1){
				if(visited[r][c] == true){
					count += 1;
				}
			}
		}
		return count;
	}


	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		int initRow = scanner.nextInt() - 1;
		int initCol = scanner.nextInt() - 1;

		int[] lengths = new int[K];
		int[] directions = new int[K];
		for(int i = 0 ; i < K ; i += 1){
			directions[i] = scanner.nextInt() - 1;
			lengths[i] = scanner.nextInt();
		}

		int answer = getTotalLength(N, K, initRow, initCol, lengths, directions);

		System.out.println(answer);
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}

class Robot{
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_SOUTH = 1;
	public static final int DIRECTION_WEST  = 2;
	public static final int DIRECTION_EAST  = 3;

	public static final int[] deltaRow = new int[]{ -1, 1, 0, 0 };
	public static final int[] deltaColumn = new int[]{0, 0, -1, 1 };

	private Position2D currentPosition;
	private int currentDirection;

	public Robot(int initRow, int initColumn){
		this.currentPosition = new Position2D(initRow, initColumn);
		this.currentDirection = 0;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}

	public void goStraight(int length){
		int nextR = getCurrentRow() + length * deltaRow[getCurrentDirection()];
		int nextC = getCurrentColumn() + length * deltaColumn[getCurrentDirection()];
		this.currentPosition = new Position2D(nextR, nextC);
	}

	public Position2D getNextPosition(){
		int nextR = getCurrentRow() + deltaRow[getCurrentDirection()];
		int nextC = getCurrentColumn() + deltaColumn[getCurrentDirection()];
		return new Position2D(nextR, nextC);
	}

	public int getCurrentColumn() {
		return this.currentPosition.c;
	}

	public int getCurrentRow() {
		return this.currentPosition.r;
	}

	public Position2D getCurrentPosition() {
		return currentPosition;
	}
}

class Position2D{
	public final int r;
	public final int c;
	public Position2D(int r, int c){
		this.r = r;
		this.c = c;
	}
}