import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	// 단어를 검사하는 세 방향에 대하여 한 글자당 행/열에 대한 변화량
	public static int[] deltaR = new int[]{0, 1, 1};
	public static int[] deltaC = new int[]{1, 1, 0};

	public static void testCase(int caseIndex) {
		// 데이터를 입력받는다
		int N = scanner.nextInt();
		String P = scanner.next();
		int M = P.length();
		char[][] puzzle = new char[N][N];

		for (int row = 0; row < N; row += 1) {
			String line = scanner.next();
			puzzle[row] = line.toCharArray();
		}

		// 퍼즐에서 해당 단어가 등장하는 횟수
		int answer = 0;


		for (int row = 0; row + M - 1 < N; row += 1) {
			for (int col = 0; col + M - 1 < N; col += 1) {
				// 검사를 시작할 모든 칸 puzzle[row][col]에 대해

				for (int dir = 0; dir < 3; dir += 1) {
					// 검사를 수행할 각 방향 dir에 대하여

					// 해당 방향으로 찾고자하는 단어와 일치하는 문자의 수
					int matchedCount = 0;
					for (int i = 0; i < M; i += 1) {
						// [row][col]부터 dir방향의 i번째 글자의 좌표를 계산한다
						int targetRow = row + i * deltaR[dir];
						int targetCol = col + i * deltaC[dir];

						// 찾고자 하는 단어의 문자와 일치여부를 검사한다
						char cs = puzzle[targetRow][targetCol];
						char cp = P.charAt(i);
						if (cs == cp) {
							matchedCount += 1;
						} else {
							// 한 문자라고 일치하지 않는다면 검사를 종료한다
							break;
						}
					}

					// 찾고자 하는 단어가 완전히 일치하여 등장했다면 카운트
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
