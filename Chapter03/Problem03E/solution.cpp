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
int getCoveredArea(const vector<Paper>& papers, int n) {
  int answer = 0; //색종이들이 덮은 영역의 총 넓이


	// 도화지의 모든 격자에 대하여
	// check[r][c] := <r, c>격자를 덮은 색종이의 갯수
  vector<vector<bool> > check(110, vector<bool>(110, false));
  
  for (int i = 0; i < n; ++i) { // 모든 색종이 p에 대하여
  	const Paper& paper = papers[i];
  	for (int j = paper.leftColumn; j <= paper.rightColumn; ++j) {
  		for (int k = paper.bottomRow; k <= paper.topRow; ++k) {
  			// 이 색종이가 덮은 모든 위치 <row, col>에 대하여
				// check[row][col]의 값을 true로 만든다.
  			check[j][k] = true;
  		}
  	}
  }

  for (int i = 0; i < 110; ++i) {
  	for (int j = 0; j < 110; ++j) {
  		if (check[i][j]) {
  			++answer;
  		}
  	}
  }

  return answer;
}

int main() {
  int caseSize;
  scanf("%d", &caseSize);

  for (int caseIndex = 1; caseIndex <= caseSize; ++caseIndex) {
    int n;
    scanf("%d", &n);

    vector<Paper> papers;
    for (int i = 0; i < n; ++i) {
      int leftX, bottomY;
      scanf("%d%d", &leftX, &bottomY);
      papers.push_back(Paper(leftX, bottomY));
    }

    int answer = getCoveredArea(papers, n);

    printf("%d\n", answer);
  }
}
