import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	
	public static final int EMPTY = 0;  // 폐기물이 존재하지 않는 칸 
	public static final int EXISTS = 1; // 폐기물이 존재하는 칸 
	
	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		
		// 각 칸의 정보를 입력받는다.
		int[][] wastes = new int[N][N];
		for (int r = 0; r < N; r += 1) {
			for (int c = 0; c < N; c += 1) {
				// wastes[r][c] := (r행 c열)칸의 폐기물 존재 여부 
				wastes[r][c] = scanner.nextInt();
			}
		}
		
		int answer = Integer.MAX_VALUE;
		
		// K*K 크기의 영역이 존재할 수 있는 모든 지점을 탐색한다.
		// 왼쪽 위 모서리 칸을 (firstRow, firstCol)로 기준을 두고 탐색한다.
		for (int firstRow = 0; firstRow + K - 1 < N; firstRow += 1) {
			for (int firstCol = 0; firstCol + K - 1 < N; firstCol += 1) {
				
				int lastRow = firstRow + K - 1; 
				int lastCol = firstCol + K - 1;
				
				// 해당 영역 내부에 존재하는 폐기물의 수를 계산한다 
				int numberOfWastes = 0;
				
				for (int r = firstRow; r <= lastRow; r += 1) {
					for (int c = firstCol; c <= lastCol; c += 1) {
						if (wastes[r][c] == EXISTS) {
							numberOfWastes += 1;
						}
					}
				}
				
				// 최소값을 갱신한다.
				answer = Math.min(numberOfWastes, answer);
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
