import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void testCase(int caseIndex) throws Exception{
		int N = scanner.nextInt();

		int[][] map = new int[N][N];

		Robot robot = new Robot();

		for(int i = 1 ; i <= N * N ; i+= 1){
			Position2D curPos = robot.getPosition();
			Position2D nextPos = robot.getNextPosition();

			map[curPos.r][curPos.c] = i;

			if(nextPos.r < 0 || nextPos.c < 0 || nextPos.r >= N || nextPos.c >= N || map[nextPos.r][nextPos.c] >= 1){
				robot.turnRight();
			}
			robot.goStraight();
		}

		for(int r = 0 ;r < N ; r += 1){
			for(int c = 0; c < N ; c += 1){
				if(c > 0){
					writer.write(" ");
				}
				writer.write(String.valueOf(map[r][c]));
			}
			writer.write("\n");
		}
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

class Robot{
	public static final int[] deltaR = new int[]{ 0, 1, 0, -1 };
	public static final int[] deltaC = new int[]{ 1, 0, -1, 0 };

	private int direction;
	private Position2D position;

	public Robot(){
		this.position = new Position2D(0,0);
		this.direction = 0;
	}

	public void goStraight(){
		this.position = getNextPosition();
	}

	public Position2D getNextPosition(){
		int newR = position.r + deltaR[direction];
		int newC = position.c + deltaC[direction];

		Position2D newPosition = new Position2D( newR, newC );
		return newPosition;
	}

	public Position2D getPosition() {
		return this.position;
	}


	public void turnRight(){
		this.direction = (this.direction+1)%4;
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