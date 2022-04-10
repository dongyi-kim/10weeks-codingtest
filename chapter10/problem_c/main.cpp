#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;


class SolutionA {
public:
	vector<vector<int> > memo;
	vector<int> profits;
	int n;

	SolutionA(vector<int> profits) {
		this->n = profits.size();
		this->profits = profits;
		this->memo = vector<vector<int> >(profits.size(), vector<int>(3, -1));
	}

	/**
	 * lastIndex번째 날에 연속으로 count번째 근무한다면 얻을 수 있는 최대 인센티브
	 *
	 * @param lastIndex 고려할 마지막 날짜의 인덱스
	 * @param count     연속으로 근무한 횟수
	 * @return 해당 가정에서의 정답
	 */
	int f(int lastIndex, int count) {
		if (lastIndex < 0 || count < 0 || count >= 3) {
			return 0;
		} else if (memo[lastIndex][count] != -1) {
			return memo[lastIndex][count];
		}

		int answer = 0;
		if (count == 0) {
			int caseA = f(lastIndex - 1, 0);
			int caseB = f(lastIndex - 1, 1);
			int caseC = f(lastIndex - 1, 2);
			answer = max(caseA, max(caseB, caseC));
		} else {
			answer = f(lastIndex - 1, count - 1) + profits[lastIndex];
		}

		memo[lastIndex][count] = answer;
		return memo[lastIndex][count];
	}

};

class SolutionB {
public:
	vector<int> memo;
	vector<int> profits;
	int n;

	SolutionB(vector<int> profits) {
		this->n = profits.size();
		this->profits = profits;
		this->memo = vector<int>(profits.size(), -1);
	}

	/**
	 * profits[0] ~ profits[lastIndex]까지만 고려했을 때의 정답
	 * 단, profits[lastIndex]는 무조건 마지막으로 사용한다.
	 *
	 * @param lastIndex 마지막으로 근무할 날의 인덱스
	 * @return 'lastIndex'번째 날에 마지막으로 근무 했을 때 얻을 수 있는 최대 인센티브
	 */
	int f(int lastIndex) {
		if (lastIndex < 0) {
			return 0;
		} else if (memo[lastIndex] != -1) {
			return memo[lastIndex];
		}

		int answer = max(
			f(lastIndex - 2) + profits[lastIndex],
			f(lastIndex - 3) + profits[lastIndex - 1] + profits[lastIndex]
		);
		
		memo[lastIndex] = answer;
		return memo[lastIndex];
	}
};

int main() {
	int n;
	scanf("%d", &n);

	vector<int> profits(n);
	for (int i = 0; i < n; i += 1) {
		scanf("%d", &profits[i]);
	}

	int answer;
	// 두 가지 방법을 완성해보세요
	//[Solution A]
	// SolutionA solutionA(profits);
	// answer = max(solutionA.f(n - 1, 0), max(solutionA.f(n - 1, 1), solutionA.f(n - 1, 2)));

	// //[Solution B]
	// SolutionB solutionB(profits);
	// answer = 0;
	// for(int i = 0 ; i < n ; i+= 1){
	// 	answer = max(answer, solutionB.f(i));
	// }
}

