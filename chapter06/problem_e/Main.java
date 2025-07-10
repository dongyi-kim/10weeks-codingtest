class Sudoku {

	public static boolean[][] rowCheck;		// rowCheck[r][num] := r번 행에 num이 이미 존재하는지 여부
	public static boolean[][] colCheck; 	// colCheck[c][num] := c번 열에 num이 이미 존재하는지 여부
	public static boolean[][] groupCheck; 	// groupCheck[g][num] := g번 그룹에 num이 이미 존재하는지 여부
	public static int[][] answer;	// 재귀 함수에서 발견한 정답을 저장하는 변수

	public static void makeSudoku(int depth, int[][] board){
		if(depth >= 81) { // 9x9 칸을 모두 채운 경우.
			// 지금 현재 보드 상황을 복제하여 반환한다
			answer = new int[9][9];
			for(int r = 0 ; r < 9 ; r += 1){
				answer[r] = board[r].clone();
			}
			return; // 탐색 종료
		}else if(answer != null){ // 이미 한 개 이상의 정답을 구한 경우 더 탐색할 필요가 없다.
			return; // 탐색 종료
		}

		int r = depth / 9;  // 행 번호 계산
		int c = depth % 9;	// 열 번호 계산
		int g = (( r / 3 ) * 3) + (c / 3); // 그룹 번호 계산

		if(board[r][c] == 0){ // 직접 시뮬레이션 해야하는 경우
			for(int num = 1; num <= 9; num += 1){ // [1, 9] 범위의 모든 num에 대해
				if(rowCheck[r][num]) continue;  // 이미 이 행에 존재하는 숫자면 skip
				if(colCheck[c][num]) continue;	// 이미 이 열에 존재하는 숫자면 skip
				if(groupCheck[g][num]) continue;// 이미 이 그룹에 존재하는 숫자면 skip

				// 다음 단계 탐색 전, row, col, group에 대한 체크
				rowCheck[r][num] = true;
				colCheck[c][num] = true;
				groupCheck[g][num] = true;
				board[r][c] = num;

				makeSudoku(depth+1, board);

				// 모든 경우 탐색 종료 후, 체크 해둔 값들에 대한 복원
				board[r][c] = 0;
				rowCheck[r][num] = false;
				colCheck[c][num] = false;
				groupCheck[g][num] = false;
			}

		}else{ // 이미 숫자가 정해져 있는 경우
			makeSudoku(depth+1, board);
		}

	}

	public static int[][] solve(int[][] input) {
		// 전역변수를 활용할 경우 항상 초기화를 해준다
		answer = null;
		rowCheck = new boolean[9][10];
		colCheck = new boolean[9][10];
		groupCheck = new boolean[9][10];

		for(int r = 0 ; r < 9 ; r += 1){ // 모든 행 r에 대해
			for(int c = 0 ; c < 9 ; c += 1){ // 모든 열 c에 대해
				int num = input[r][c];
				if( num == 0 ) continue;

				int g = (( r / 3 ) * 3) + (c / 3); // 그룹 번호 계산

				// 입력에 이미 존재하는 값들에 대한 중복 방지 값 주입
				rowCheck[r][num] = true;
				colCheck[c][num] = true;
				groupCheck[g][num] = true;
			}
		}

		// 재귀함수 수행
		makeSudoku(0, input);
		return answer;
	}
}

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] input_board = {
				{0, 3, 5, 4, 6, 9, 2, 7, 8},
				{7, 8, 2, 1, 0, 5, 6, 0, 9},
				{0, 6, 0, 2, 7, 8, 1, 3, 5},
				{3, 2, 1, 0, 4, 6, 8, 9, 7},
				{8, 0, 4, 9, 1, 3, 5, 0, 6},
				{5, 9, 6, 8, 2, 0, 4, 1, 3},
				{9, 1, 7, 6, 5, 2, 0, 8, 0},
				{6, 0, 3, 7, 0, 1, 9, 5, 2},
				{2, 5, 8, 3, 9, 4, 7, 6, 0}
		};
		int[][] output_board = Sudoku.solve(input_board);

		for(int i = 0 ; i < 9 ; i += 1){
			for(int j = 0 ; j < 9 ; j += 1){
				System.out.print(output_board[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
