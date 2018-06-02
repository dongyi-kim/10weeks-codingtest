#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 987654321;

class Quilting {
public:
	vector<vector<int> > imgA, imgB, memo;
	int r, c;

	Quilting(int r, int c, vector<vector<int> > imgA, vector<vector<int> > imgB) {
		this->r = r;
		this->c = c;
		this->imgA = imgA;
		this->imgB = imgB;
		this->memo = vector<vector<int> >(r, vector<int>(c, -1));
	}

	/**
	 * 현재 0번째 행부터 이어온 경계선의 마지막이 (lastRow, lastCol)일 때 최소의 부자연스러움 수치를 계산하는 함수
	 *
	 * @param lastRow 경계선 마지막 행
	 * @param lastCol 경계선 마지막 열
	 * @return 이때의 최소 부자연스러움
	 */
	int f(int lastRow, int lastCol) {
		if (lastRow < 0 || lastCol < 0 || lastCol >= c) {
			return INF;
		} else if (memo[lastRow][lastCol] != -1) {
			return memo[lastRow][lastCol];
		} else if (lastRow == 0) {
			int diff = imgA[lastRow][lastCol] - imgB[lastRow][lastCol];
			return diff * diff;
		}

		// 일단 현재 픽셀의 차이값을 계산한다
		int diff = imageA[lastRow][lastCol] - imageB[lastRow][lastCol];
		int error = diff * diff;

		// 이전 행 까지의 세가지 경우의 수중 최적해와 더한다
		int answer = error + min(f(lastRow - 1, lastCol - 1), min(f(lastRow - 1, lastCol), f(lastRow - 1, lastCol + 1)));
		memo[lastRow][lastCol] = answer;
		return answer;
	}
};

int main() {
	int r, c;
	scanf("%d%d", &r, &c);
	vector<vector<int> > imgA(r, vector<int>(c));
	vector<vector<int> > imgB(r, vector<int>(c));

	for (int i = 0; i < r; i += 1) {
		for (int j = 0; j < c; j += 1) {
			scanf("%d", &imgA[i][j]);
		}
	}

	for (int i = 0; i < r; i += 1) {
		for (int j = 0; j < c; j += 1) {
			scanf("%d", &imgB[i][j]);
		}
	}

	Quilting quilting(r, c, imgA, imgB);

	int answer = quilting.f(r - 1, c - 1);

	printf("%d\n", answer);
}
