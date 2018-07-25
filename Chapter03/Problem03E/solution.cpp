#include <cstdio>
#include <vector>

using namespace std;

class Paper {
public:
	int leftColumn;   //가장 왼쪽 격자의 열 번호
	int rightColumn;  //가장 오른쪽 격자의 열 번호
	int topRow;       //가장 위쪽 격자의 행 번호
	int bottomRow;    //가장 아래쪽 격자의 행 번호

	Paper(int xPos, int yPos) {
		this->leftColumn = xPos;
		this->rightColumn = this->leftColumn + 9;
		this->bottomRow = yPos;
		this->topRow = this->bottomRow + 9;
	}
};

/**
* 색종이들이 덮고 있는 영역의 총 넓이를 계산하는 함수
*
* @param papers
* @param n
* @return
*/
int getCoveredArea(const vector <Paper> &papers, int n) {
	int answer = 0; //색종이들이 덮은 영역의 총 넓이

	// 도화지의 모든 격자에 대하여
	// board[r][c] := <r, c>격자를 덮은 색종이의 갯수
	vector < vector<int> > board(100, vector<int>(100, 0));

	for (int i = 0; i < n; ++i) { // 모든 색종이 p에 대하여
		const Paper &p = papers[i];
		for (int row = p.bottomRow; row <= p.topRow; row += 1) {
			for (int col = p.leftColumn; col <= p.rightColumn; col += 1) {
				//이 색종이가 덮은 모든 위치 <row, col>에 대하여
				//한번씩 board[row][col]의 값을 1 증가시킨다.
				board[row][col] += 1;
			}
		}
	}

	//도화지에 존재하는 모든 격자에 대하여
	for (int row = 0; row < 100; row += 1) {
		for (int col = 0; col < 100; col += 1) {
			if (board[row][col] > 0) {    //한 개 이상의 색종이가 덮은 격자의 수를 계산한다
				answer += 1;
			}
		}
	}

	//각 격자의 넓이는 1이므로,
	//격자의 수가 곧 넓이가 된다.
	return answer;
}

int main() {
	int caseSize;
	scanf("%d", &caseSize);

	for (int caseIndex = 1; caseIndex <= caseSize; ++caseIndex) {
		int n;
		scanf("%d", &n);

		vector <Paper> papers;
		for (int i = 0; i < n; ++i) {
			int leftX, bottomY;
			scanf("%d%d", &leftX, &bottomY);
			papers.push_back(Paper(leftX, bottomY));
		}

		int answer = getCoveredArea(papers, n);

		printf("%d\n", answer);
	}
	return 0;
}
