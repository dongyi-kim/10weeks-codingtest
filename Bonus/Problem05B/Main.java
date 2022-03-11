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
		// 가장 위의 칸이 (firstRow)행에 존재하는 영역들을 조사한다.
		for (int firstRow = 0; firstRow + K - 1 < N; firstRow += 1) {
			int lastRow = firstRow + K - 1;
			
			// 가장 왼쪽 K열로 구성된 영역에 대한 폐기물 수를 계산하고 갱신한다.
			int numberOfWastes = 0;
			for (int r = firstRow; r <= lastRow; r += 1) {
				for (int c = 0; c <= K - 1; c += 1) {
					if (wastes[r][c] == EXISTS) {
						numberOfWastes += 1;
					}
				}
			}
			answer = Math.min(answer, numberOfWastes);
			
			// 이후 열 방향으로 Sliding Window 기법으로 이동하며 영역의 정보를 갱신한다.
			for (int c = K; c < N; c += 1) 
			{   // 기존 영역을 열방향으로 오른쪽으로 이동한 경우 
				
				int newColumn = c; // 새롭게 추가되는 열의 번호 
				int oldColumn = c - K;  // 새롭게 제거되는 열의 번호 
				
				for (int r = firstRow; r <= lastRow; r += 1) {
					// 해당 열에서 추가되는 폐기물의 수를 반영한다 
					if (wastes[r][newColumn] == EXISTS) {
						numberOfWastes += 1;
					}
					
					// 해당 열에서 삭제되는 폐기물의 수를 반영한다 
					if (wastes[r][oldColumn] == EXISTS) {
						numberOfWastes -= 1;
					}
				}
				
				// 계산된 해당 영역의 폐기물 수를 갱신한다
				answer = Math.min(answer, numberOfWastes);
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
