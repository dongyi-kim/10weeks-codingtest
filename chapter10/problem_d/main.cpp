#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 987654321;

class Solution {
public:
	vector<vector<int> > cost;
	vector<vector<int> > memo;
	int n;

	Solution(vector<vector<int> > costMap) {
		this->n = costMap.size();
		this->cost = costMap;
		this->memo = vector<vector<int> >(this->n, vector<int>(this->n, -1));
	}


	/**
	 * (0, 0) ~ (lastRow, lastCol)까지만 고려했을 때 문제의 정답
	 * 
	 * @param lastRow		
	 * @param lastCol
	 * @return	최단경로들 중 가장 작은 난이도 합
	 */
	int f(int lastRow, int lastCol) {
		if (lastCol < 0 || lastRow < 0) {
			return INF;
		} else if (memo[lastRow][lastCol] != -1) {
			return memo[lastRow][lastCol];
		} else if (lastRow == 0 && lastCol == 0) {
			return cost[0][0];
		}

		int answer = min(
			f(lastRow - 1, lastCol) + cost[lastRow][lastCol],
			f(lastRow, lastCol - 1) + cost[lastRow][lastCol]
		);

		memo[lastRow][lastCol] = answer;
		return answer;
	}

};

int main() {
	int n;
	scanf("%d", &n);

	vector<vector<int> > costMap(n, vector<int>(n));
	for (int i = 0; i < n ; i += 1) {
		for (int j = 0; j < n; j += 1) {
			scanf("%d", &costMap[i][j]);
		}
	}


	Solution solution(costMap);
	int answer = solution.f(n - 1, n - 1);

	printf("%d\n", answer);
}
