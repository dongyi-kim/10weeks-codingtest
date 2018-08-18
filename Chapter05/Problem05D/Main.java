
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int[] deltaR = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static final int[] deltaC = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	
	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		
		GameMap gameMap = new GameMap(N, N);
		
		for (int r = 0; r < N; r += 1) {
			for (int c = 0; c < N; c += 1) {
				int buildings = scanner.nextInt();
				gameMap.setBuildingsAt(r, c, buildings);
			}
		}
		
		int answer = 0;
		
		for (int r = 0; r < N; r += 1) {
			for (int c = 0; c < N; c += 1) {
				answer = Math.max(answer, gameMap.getBuildingsAt(r, c));
			}
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

class GameMap {
	public final int rows;
	public final int columns;
	private final int[][] buildings;
	
	private final int[] rowSums;
	private final int[] colSums;
	private final int[] diagonalSumA, diagonalSumB;
	
	
	public GameMap(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.buildings = new int[rows][columns];
		this.rowSums = new int[rows];
		this.colSums = new int[columns];
		this.diagonalSumA = new int[rows + columns];
		this.diagonalSumB = new int[rows + columns];
	}
	
	public int getBuildingsAt(int r, int c) {
		if (this.isInside(r, c) == false)
			return 0;
		
		return this.buildings[r][c];
	}
	
	public int getTotalPointAt(int r, int c) {
		int buildings = 0;
		buildings += this.rowSums[r];
		buildings += this.colSums[c];
		buildings += this.diagonalSumA[r + c];
		buildings += this.diagonalSumB[c - r + this.rows - 1];
		buildings -= getBuildingsAt(r, c) * 3;
		return buildings;
	}
	
	public void setBuildingsAt(int r, int c, int value) {
		int origin = getBuildingsAt(r, c);
		int delta = value - origin;
		this.buildings[r][c] = value;
		this.rowSums[r] += delta;
		this.colSums[c] += delta;
		this.diagonalSumA[r + c] += delta;
		this.diagonalSumB[c - r + this.rows - 1] += delta;
	}
	
	public boolean isInside(int r, int c) {
		if (r < 0 || r >= this.rows)
			return false;
		if (c < 0 || c >= this.columns)
			return false;
		return true;
	}
}

