import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int[] deltaR = { -1, -1, -1, 0, 0, 1, 1, 1 };
	public static final int[] deltaC = { -1, 0, 1, -1, 1, -1, 0, 1 };


	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		int M = scanner.nextInt();

		GameMap gameMap = new GameMap(N, N);

		for (int r = 0; r < N; r += 1) {
			for (int c = 0; c < N; c += 1) {
				int buildings = scanner.nextInt();
				gameMap.setBuildingsAt(r, c, buildings);
			}
		}

		int answer = 0;

		for(int i = 0 ; i < M ; i += 1){
			int r = scanner.nextInt() - 1;
			int c = scanner.nextInt() - 1;

			int buildings = 0;
			buildings += gameMap.getBuildingsAt(r, c);

			for(int di = 0 ; di < deltaR.length; di += 1){
				for(int length = 1; length <= N; length += 1){
					int newR = r + deltaR[di] * length;
					int newC = c + deltaC[di] * length;
					if(gameMap.isInside(newR, newC)){
						break;
					}

					buildings += gameMap.getBuildingsAt(newR, newC);
				}
			}

			answer = Math.max(answer, buildings);
		}

		System.out.println(answer);
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}

class GameMap{
	public final int rows;
	public final int columns;
	private final int[][] buildings;

	public GameMap(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		this.buildings = new int[rows][columns];
	}

	public int getBuildingsAt(int r, int c){
		if(this.isInside(r, c) == false)
			return 0;

		return this.buildings[r][c];
	}

	public void setBuildingsAt(int r, int c, int value){
		this.buildings[r][c] = value;
	}

	public boolean isInside(int r, int c){
		if(r < 0 || r >= this.rows)
			return false;
		if(c < 0 || c >= this.columns)
			return false;
		return true;
	}



}
