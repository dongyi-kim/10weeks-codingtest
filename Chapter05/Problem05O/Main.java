import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static int[] deltaR = new int[]{0, 1, 1};
	public static int[] deltaC = new int[]{1, 1, 0};


	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		String P = scanner.next();
		int M = P.length();
		char[][] puzzle = new char[N][N];

		for (int row = 0; row < N; row += 1) {
			String line = scanner.next();
			puzzle[row] = line.toCharArray();
		}

		int answer = 0;
		for (int row = 0; row + M - 1 < N; row += 1) {
			for (int col = 0; col + M - 1 < N; col += 1) {
				for (int dir = 0; dir < 3; dir += 1) {
					int matchedCount = 0;
					for (int i = 0; i < M; i += 1) {
						int targetRow = row + i * deltaR[dir];
						int targetCol = col + i * deltaC[dir];

						char cs = puzzle[targetRow][targetCol];
						char cp = P.charAt(i);
						if (cs == cp) {
							matchedCount += 1;
						} else {
							break;
						}
					}

					if (matchedCount == M) {
						answer += 1;
					}

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
