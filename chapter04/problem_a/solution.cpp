#include <cstdio>

using namespace std;

const int MAX_ROW = 9;
const int MAX_COL = 9;


class SudokuBoard {
public:
	// 칸의 번호로 행의 번호를 계산하는 메소드
	int getRowByIndex(int index) {
		// 칸의 번호에 대해 마디를 가지고 증가하므로 몫으로 계산할 수 있다.
		return (index - 1) / 9 + 1;
	}

	// 칸의 번호로 열의 번호를 계산하는 메소드
	int getColByIndex(int index) {
		// 칸의 번호에 대해 규칙적으로 순환하므로 나머지로 계산할 수 있다.
		return (index - 1) % 9 + 1;
	}

	// 칸의 번호로 그룹 번호를 계산하는 메소드
	int getGroupByIndex(int index) {
		int r = getRowByIndex(index);
		int c = getColByIndex(index);
		return getGroupByPosition(r, c);
	}

	// 칸의 위치 (행, 열)로 그룹 번호를 계산하는 메소드
	int getGroupByPosition(int row, int column) {
		// 행의 번호를 통해, 해당 행에 존재하는 그룹들 중 가장 왼쪽 그룹의 번호를 알 수 있다.
		int left = ((row - 1) / 3) * 3 + 1;
		// 열의 번호를 통해, 해당 행에 존재하는 그룹들 중 몇 번째 그룹에 속하는지 알 수 있다.
		int offset = ((column - 1) / 3);
		return left + offset;
	}

	// 칸의 위치 (행, 열)로 칸의 번호를 계산하는 메소드
	int getIndexByPosition(int row, int column) {
		// 행과 열 번호를 알면 반대로 인덱스도 쉽게 계산할 수 있다.
		return (row - 1) * 9 + (column - 1) % 9 + 1;
	}
};


void process(int caseIndex) {
	int index;
	scanf("%d", &index);

	SudokuBoard board = SudokuBoard();

	// 칸의 번호로 행, 열, 그룹 번호를 계산한다
	int row = board.getRowByIndex(index);
	int col = board.getColByIndex(index);
	int group = board.getGroupByIndex(index);

	printf("Case #%d:\n", caseIndex);
	printf("%d %d %d\n", row, col, group);
}

int main() {
	int caseSize;
	scanf("%d", &caseSize);

	for (int caseIndex = 1; caseIndex <= caseSize; ++caseIndex) {
		process(caseIndex);
	}
}