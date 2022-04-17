import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * @brief   DFS기법으로 경우의 수를 탐색하여 스도쿠 판을 채우는 함수
	 *          채울 방법이 존재한다면 해당 스도쿠 판을 2차원 배열 형태로 반환한다
	 *
	 * @param depth     현재 탐색의 깊이
	 * @param sudoku    수도쿠 게임 정보 객체
	 * @return          채울 방법이 존재한다면 해당 판을, 그렇지 않다면 null을 반환한다
	 */
	public static int[][] fillSudoku(int depth, Sudoku sudoku){
		if(depth > 81){
			return sudoku.copyBoard();
		}

		int r = (depth-1) / 9 + 1;
		int c = (depth-1) % 9 + 1;

		int[][] answerBoard = null;

		if(sudoku.isEmpty(r, c) == false){
			// 이미 해당 칸에 숫자가 채워져 있는 경우
			// 다음 칸부터 다시 탐색을 시작한다
			answerBoard = fillSudoku(depth+1, sudoku);
		}else{
			// 해당 칸이 비어있는 경우, 1~9사이의 숫자들 중 하나를 선택해 시도해본다
			for(int value = 1; value <= 9 ; value += 1){
				// 현재 해당 칸에 넣을 수 있는 숫자라면
				if(sudoku.isSettable(r, c, value)){
					// 해당 칸에 숫자를 넣고
					sudoku.setBoard(r, c, value);

					// 그 이후를 탐색해본다
					answerBoard = fillSudoku(depth+1, sudoku);

					// 탐색이 종료된 후 다시 해당 숫자를 제거해준다 (추가 탐색을 위해)
					sudoku.clearBoard(r, c);
				}
				// 이미 정답을 찾았다면 종료한다
				if(answerBoard != null){
					break;
				}
			}
		}

		return answerBoard;
	}

	public static void main(String[] args) {
		// 스도쿠 객체 선언
		Sudoku sudoku = new Sudoku();

		// 스도쿠 판 초기 상태를 입력받는다
		for (int r = 1; r <= 9; r += 1) {
			for (int c = 1; c <= 9; c += 1) {
				int value = scanner.nextInt();
				sudoku.setBoard(r, c, value);
			}
		}

		// DFS로 가능한 방법을 찾는다
		int[][] answer = fillSudoku(1, sudoku);

		// 정답을 출력한다
		StringBuilder stringBuilder = new StringBuilder();
		for (int r = 1; r <= 9; r += 1) {
			for (int c = 1; c <= 9; c += 1) {
				if (c > 0) {
					stringBuilder.append(" ");
				}
				stringBuilder.append(answer[r][c]);
			}
			stringBuilder.append("\n");
		}

		String output = stringBuilder.toString();
		System.out.print(output);
	}
}

class Sudoku {
	public static final int EMPTY = 0;

	private int[][] board;
	private int[][] rowFrequency;
	private int[][] columnFrequency;
	private int[][] groupFrequency;


	public Sudoku() {
		this.board = new int[10][10];
		this.rowFrequency = new int[10][10];
		this.columnFrequency = new int[10][10];
		this.groupFrequency = new int[10][10];
	}

	// r행 c열 칸에 value숫자를 채워넣는 메소드
	public void setBoard(int r, int c, int value) {
		// 기존에 있던 숫자는 제거한다
		clearBoard(r, c);

		// 빈도수를 갱신해준다
		int g = ((r - 1) / 3) * 3 + (c - 1) / 3 + 1;

		this.rowFrequency[r][value] += 1;
		this.columnFrequency[c][value] += 1;
		this.groupFrequency[g][value] += 1;
		this.board[r][c] = value;
	}

	// r행 c열에 있던 칸을 지우고 빈 칸으로 만든다
	public void clearBoard(int r, int c) {
		// 이미 비어져 있다면 스킵한다
		if (board[r][c] == EMPTY) {
			return;
		}

		// 기존에 있던 숫자의 빈도수를 감소시키고
		int value = board[r][c];
		int g = ((r - 1) / 3) * 3 + (c - 1) / 3 + 1;

		this.rowFrequency[r][value] -= 1;
		this.columnFrequency[c][value] -= 1;
		this.groupFrequency[g][value] -= 1;

		// 빈 칸으로 채운다
		this.board[r][c] = EMPTY;
	}

	// r행 c열에 놓인 숫자를 반환한다
	public int getBoard(int r, int c) {
		return board[r][c];
	}

	// r행 c열에 value라는 숫자를 스도쿠 규칙상 둘 수 있는지 검사하는 함수
	public boolean isSettable(int r, int c, int value) {
		if(isEmpty(r,c) == false){
			return false;
		}

		int g = ((r - 1) / 3) * 3 + (c - 1) / 3 + 1;

		return this.rowFrequency[r][value] == 0
				&& this.columnFrequency[c][value] == 0
				&& this.groupFrequency[g][value] == 0;
	}

	// r행 c열 칸이 비어있는지 검사하는 함수
	public boolean isEmpty(int r, int c){
		return this.board[r][c] == EMPTY;
	}

	// 현재 스도쿠 보드의 상태를 2차원 배열 형태로 저장하여 반환해주는 함수
	public int[][] copyBoard(){
		int[][] copiedBoard = new int[10][10];
		for(int r = 1; r <= 9; r += 1){
			for(int c = 1; c<= 9; c += 1){
				copiedBoard[r][c] = this.board[r][c];
			}
		}
		return copiedBoard;
	}
}