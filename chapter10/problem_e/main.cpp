#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;


class RangeSum {
public:
	vector<vector<int> > memo;
	vector<vector<int> > values;
	int n;

	RangeSum(vector<vector<int> > values) {
		this->n = values.size();
		this->values = values;
		this->memo = vector<vector<int> >(this->n, vector<int>(this->n, -1));
	}

	/**
	 * values[0][0] ~ values[lastRow][lastCol] 까지의 직사각형 누적합을 계산하는 함수
	 *
	 * @param lastRow 마지막 행 번호
	 * @param lastCol 마지막 열 번호
	 * @return 해당 범위의 누적합
	 */
	int f(int lastRow, int lastCol) {
		if (lastCol < 0 || lastRow < 0) {
			return 0;
		} else if (memo[lastRow][lastCol] != -1) {
			return memo[lastRow][lastCol];
		} else if (lastRow == 0 && lastCol == 0) {
			return values[0][0];
		}

		memo[lastRow][lastCol] = values[lastRow][lastCol] + f(lastRow - 1, lastCol) + f(lastRow, lastCol - 1) - f(lastRow - 1, lastCol - 1);

		return memo[lastRow][lastCol];
	}
};

int main() {
	int n, m;
	scanf("%d%d", &n, &m);

	vector<vector<int> > values(n, vector<int>(n));
	for (int i = 0; i < n; i += 1) {
		for (int j = 0; j < n; j += 1) {
			scanf("%d", &values[i][j]);
		}
	}

	RangeSum rangeSum(values);

	for (int i = 0; i < m; i += 1) {
		int minRow, minCol, maxRow, maxCol;
		scanf("%d%d%d%d", &minRow, &minCol, &maxRow, &maxCol);
		minRow -= 1; minCol -= 1; maxRow -= 1; maxCol -= 1;

		int sum = 0;
		sum += rangeSum.f(maxRow, maxCol);
		sum -= rangeSum.f(maxRow, minCol - 1);
		sum -= rangeSum.f(minRow - 1, maxCol);
		sum += rangeSum.f(minRow - 1, maxCol - 1);

		printf("%d\n", sum);
	}
}
