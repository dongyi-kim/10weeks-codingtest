import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static long countAllPossibleWays(int depth, ChessBoard board) {
		if (depth > board.size) {
			// N개의 퀸을 배치한 후, Depth가 (N+1)이 되었다.
			// => 새로운 경우의 수 발견
			return 1;
		}

		int r = depth;
		long count = 0;
		// depth번째 행의 각 열에 차례로 퀸을 배치해본다.
		// 그 후 depth+1번째 행부터 차례로 재귀적으로 조사한다
		for (int c = 1; c <= board.size; c += 1) {
			if (board.isSettable(r, c)) {
				board.setQueen(r, c);

				count += countAllPossibleWays(depth + 1, board);

				board.removeQueen(r, c);
			}
		}
		return count;

	}

	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();
		ChessBoard chessBoard = new ChessBoard(N);

		long answer = countAllPossibleWays(1, chessBoard);

		System.out.println(answer);
	}

}

class ChessBoard {
	public static final int EMPTY = 0;
	public static final int QUEEN = 1;

	public final int size;
	private int[][] board;
	private int[] rowFrequency;
	private int[] columnFrequency;
	private int[] leftFrequency;
	private int[] rightFrequency;

	public ChessBoard(int size) {
		this.size = size;
		this.board = new int[size + 1][size + 1];
		this.rowFrequency = new int[size + 1];
		this.columnFrequency = new int[size + 1];
		this.leftFrequency = new int[size * 2];
		this.rightFrequency = new int[size * 2];
	}


	/**
	 * @brief  r행 c열 칸에 퀸이 존재하지 않고 비어있는지 확인하는 함수
	 */
	public boolean isEmpty(int r, int c) {
		return this.board[r][c] == EMPTY;
	}


	/**
	 * @brief  r행 c열 칸에 퀸을 배치할 수 있는지 판단하는 함수
	 */
	public boolean isSettable(int r, int c) {
		if (isEmpty(r, c) == false) {
			return false;
		}

		int lIndex = c + this.size - r;
		int rIndex = c + r - 1;

		return rowFrequency[r] == 0
				&& columnFrequency[c] == 0
				&& leftFrequency[lIndex] == 0
				&& rightFrequency[rIndex] == 0;
	}


	/**
	 * @brief  r행 c열 칸에 퀸을 놓는 함수
	 */
	public void setQueen(int r, int c) {
		removeQueen(r, c);

		int lIndex = c + this.size - r;
		int rIndex = c + r - 1;

		this.rowFrequency[r] += 1;
		this.columnFrequency[c] += 1;
		this.leftFrequency[lIndex] += 1;
		this.rightFrequency[rIndex] += 1;

		this.board[r][c] = QUEEN;
	}

	/**
	 * @brief  r행 c열 칸에 퀸이 존재한다면 제거하고 빈 칸으로 만드는 함수
	 */
	public void removeQueen(int r, int c) {
		if (this.board[r][c] == EMPTY) {
			return;
		}

		int lIndex = c + this.size - r;
		int rIndex = c + r - 1;

		this.rowFrequency[r] -= 1;
		this.columnFrequency[c] -= 1;
		this.leftFrequency[lIndex] -= 1;
		this.rightFrequency[rIndex] -= 1;

		this.board[r][c] = EMPTY;
	}
}