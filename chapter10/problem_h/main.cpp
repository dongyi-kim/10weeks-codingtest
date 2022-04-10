#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 987654321;

class DTW {
public:
	vector<int> signA, signB;
	vector<vector<int> > memo;

	DTW(vector<int> signA, vector<int> signB) {
		this->signA = signA;
		this->signB = signB;
		this->memo = vector<vector<int> >(signA.size(), vector<int>(signB.size(), -1));
	}

	/**
	 * 두 부분 파형 signA[0..lastIndexA], signB[0..lastIndexB]에 대한 최소의 거리
	 * signA[lastIndexA]와 signB[lastIndexB]는 반드시 대응되어야 한다.
	 *
	 * @param lastIndexA
	 * @param lastIndexB
	 * @return 두 부분 파형에대한 최소 거리
	 */
	int f(int lastIndexA, int lastIndexB) {
		if (lastIndexA < 0 || lastIndexB < 0) {
			return INF;
		} else if (memo[lastIndexA][lastIndexB] != -1) {
			return memo[lastIndexA][lastIndexB];
		} else if (lastIndexA == 0 && lastIndexB == 0) {
			int diff = signA[0] - signB[0];
			return diff * diff;
		}

		int diff = signA[lastIndexA] - signB[lastIndexB];
		int error = diff * diff;

		int answer = error + min(f(lastIndexA - 1, lastIndexB), min(f(lastIndexA, lastIndexB - 1), f(lastIndexA - 1, lastIndexB - 1)));
		memo[lastIndexA][lastIndexB] = answer;
		
		return answer;
	}
};

int main() {
	int n;
	scanf("%d", &n);
	vector<int> signA(n), signB(n);

	for (int i = 0; i < n; i += 1) {
		scanf("%d", &signA[i]);
	}

	for (int i = 0; i < n; i += 1) {
		scanf("%d", &signB[i]);
	}

	DTW dtw(signA, signB);

	int answer = dtw.f(n - 1, n - 1);
	printf("%d\n", answer);
}
