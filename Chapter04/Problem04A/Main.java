import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex) {

		SudokuBoard board = new SudokuBoard();
		int index = scanner.nextInt();

		// 칸의 번호로 행, 열, 그룹 번호를 계산한다
		int row =   board.getRowByIndex(index);
		int col =   board.getColByIndex(index);
		int group=  board.getGroupByIndex(index);

		// 정답을 출력한다
		System.out.printf("Case #%d:\n", caseIndex);
		System.out.printf("%d %d %d\n", row, col, group);
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}

class SudokuBoard{
	static final int MAX_ROW = 9;
	static final int MAX_COL = 9;

	// 칸의 번호로 행의 번호를 계산하는 메소드
	public int getRowByIndex(int index)
	{
		return (index-1) / 9 + 1;
	}

	// 칸의 번호로 열의 번호를 계산하는 메소드
	public int getColByIndex(int index)
	{
		return (index-1) % 9 + 1;
	}

	// 칸의 번호로 그룹 번호를 계산하는 메소드
	public int getGroupByIndex(int index)
	{
		int r = getRowByIndex(index);
		int c = getColByIndex(index);
		return getGroupByPosition(r, c);
	}

	// 칸의 위치 (행, 열)로 그룹 번호를 계산하는 메소드
	public int getGroupByPosition(int row, int column)
	{
		int left =   ((row-1)/3)*3 + 1;
		int offset = ((column-1)/3);
		return left + offset;
	}

	// 칸의 위치 (행, 열)로 칸의 번호를 계산하는 메소드
	public int getIndexByPosition(int row, int column)
	{
		return (row-1) * 9 + (column -1) % 9 + 1;
	}
}
