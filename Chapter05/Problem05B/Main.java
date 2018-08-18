import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		int K = scanner.nextInt();

		int[][] wastes = new int[N][N];
		for (int r = 0; r < N; r += 1) {
			for (int c = 0; c < N; c += 1) {
				wastes[r][c] = scanner.nextInt();
			}
		}

		int answer = Integer.MAX_VALUE;

		for (int r = 0; r + K - 1 < N; r += 1) {
			int minRow = r;
			int maxRow = r + K - 1;
			int sumOfWastes = 0;

			for (int c = 0; c < N; c += 1) {
				int minCol = c - K + 1;
				int maxCol = c;

				for(int i = minRow; i <= maxRow; i+= 1){
					if(wastes[i][maxCol] >= 1){
						sumOfWastes += 1;
					}
					if(minCol >= 1){
						if(wastes[i][minCol-1] >= 1){
							sumOfWastes -= 1;
						}
					}
				}

				if(minCol >= 0){
					answer = Math.min(sumOfWastes, answer);
				}
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