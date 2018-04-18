#include <cstdio>
#include <vector>

using namespace std;

class Range {
public:
	int index;
	int left;
	int right;
	long long totalPoint;
	Range(int index, int left, int right) {
		this->index = index;
		this->left = left;
		this->right = right;
		this->totalPoint = 0;
	}
};

/**
*
* @param n   카드의 수
* @param m   앨범을 구매한 팬의 수
* @param cards   각 카드에 적힌 숫자의 리스트 (cards[1] ~ card[n])
* @param ranges  각 팬이 선택한 범위의 리스트 (ranges[0] ~ ranges[m-1])
* @return        총 점수의 합이 가장 큰 범위 객체
*/
Range getBestRange(int n, int m, const vector<int>& cards, const vector<Range>& ranges) {
	Range answer = ranges[0];


	// 누적 합 배열을 계산한다
	// psum[i] := cards[1] ~ cards[i]의 합
	vector<long long> rangeSum(n + 1, 0);
	for (int i = 1; i <= n; ++i) {
		rangeSum[i] = rangeSum[i - 1] + cards[i];
	}

	for (int i = 0; i < m; ++i) { // 모든 범위 정보 r에 대하여
		Range range = ranges[i];

		// 해당 범위의 누적합을 계산한다
		range.totalPoint = rangeSum[range.right] - rangeSum[range.left - 1];

		// 현재까지 구한 가장 큰 누적합보다 더 크다면 갱신한다
		if (answer.totalPoint < range.totalPoint) {
			answer = range;
		}
	}

	return answer;
}

int main() {
	int n, m;
	scanf("%d%d", &n, &m);
	vector<int> cards(n + 1);
	vector<Range> ranges;

	// 각 카드의 정보를 입력받는다.
	for (int i = 1; i <= n; ++i) {
		scanf("%d", &cards[i]);
	}

	// 팬들의 정보를 입력받는다.
	for (int i = 1; i <= m; ++i) {
		int l, r;
		scanf("%d%d", &l, &r);
		ranges.push_back(Range(i, l, r));
	}

	// 범위의 합이 가장 큰 범위를 계산한다.
	Range answer = getBestRange(n, m, cards, ranges);

	printf("%d %lld\n", answer.index, answer.totalPoint);
}
