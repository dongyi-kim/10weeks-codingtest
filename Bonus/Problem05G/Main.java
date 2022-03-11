import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


	public static void testCase(int caseIndex) throws Exception {
		int N = scanner.nextInt();

		int[][] map = new int[N][N];

		Robot robot = new Robot();

		int lastIndex = N * N;
		for (int index = 1; index <= lastIndex; index += 1) {
			Position2D curPos = robot.getPosition();
			map[curPos.r][curPos.c] = index;

			if( index == lastIndex ){
				break;
			}

			while(true){
				Position2D nextPos = robot.getNextPosition();
				if(isPossible(map, N, nextPos) == true){
					break;
				}

				robot.turnNext();
			}

			robot.goStraight();

			if(robot.getDirection() == 0 || robot.getDirection() == 2){
				robot.turnNext();
			}
		}


		printMap(map, N);
	}


	public static boolean isPossible(int[][] map, int N, Position2D pos){
		if( pos.r < 0 || pos.r >= N){
			return false;
		}

		if( pos.c < 0 || pos.c >= N){
			return false;
		}

		if(map[pos.r][pos.c] != 0){
			return false;
		}

		return true;
	}

	public static void printMap(int[][] map, int N) throws Exception {
		for (int r = 0; r < N; r += 1) {
			for (int c = 0; c < N; c += 1) {
				if (c > 0) {
					writer.write(" ");
				}
				writer.write(String.valueOf(map[r][c]));
			}
			writer.write("\n");
		}
		writer.flush();
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}

		writer.flush();
		writer.close();
	}

}

class Robot {
	public static final int[] deltaR = new int[]{0, 1, 1, -1};
	public static final int[] deltaC = new int[]{1, -1, 0, 1};

	private int direction;
	private Position2D position;

	public Robot() {
		this.position = new Position2D(0, 0);
		this.direction = 0;
	}

	public void goStraight() {
		this.position = getNextPosition();
	}

	public Position2D getNextPosition() {
		int newR = position.r + deltaR[direction];
		int newC = position.c + deltaC[direction];

		Position2D newPosition = new Position2D(newR, newC);
		return newPosition;
	}

	public Position2D getPosition() {
		return this.position;
	}

	public int getDirection() {
		return this.direction;
	}

	public void turnNext() {
		this.direction = (this.direction + 1) % deltaC.length;
	}
}

class Position2D {
	public final int r;
	public final int c;

	public Position2D(int r, int c) {
		this.r = r;
		this.c = c;
	}

	public static boolean isInside(Position2D pos, int rowSize, int columnSize) {
		if (pos.r < 0 || pos.r >= rowSize) {
			return false;
		}
		if (pos.c < 0 || pos.r >= columnSize) {
			return false;
		}
		return true;
	}
}