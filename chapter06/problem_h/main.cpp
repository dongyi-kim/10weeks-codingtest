#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

class Histogram {
public:
	int height;    // 히스토그램의 높이
	int leftX;     // 인덱스 혹은 히스토그램의 왼쪽 변의 x 좌표
	int rightX;    // 히스토그램의 오른쪽 변의 x좌표

	Histogram() {}

	Histogram(int index, int height) {
		this->leftX = index;
		this->rightX = this->leftX + 1;   // 각 히스토그램은 너비가 1이므로
		this->height = height;
	}
};

/**
 * @brief           주어진 범위에 대해 가장 큰 직사각형의 크기를 반환하는 함수
 *
 * @param histograms
 * @param left
 * @param right
 * @return          histograms[left] ~ histograms[right] 범위의 막대 내에 존재할 수 있는 가장 큰 직사각형의 크기
 */
long long getLargestRectangleArea(const vector<Histogram>& histograms, int left, int right) {
	if (left > right) {
		// 히스토그램이 없다면 넓이는 0이 된다
		return 0;
	} else if (left == right) {
		// 히스토그램 하나라면 그 자체가 된다
		return histograms[left].height;
	}

	// 영역을 나눌 중간 지점 인덱스
	int mid = (left + right) / 2;

	// 좌 우를 나누어 해당 영역만으로 계산한 정답을 각각 계산한다
	long long leftMax = getLargestRectangleArea(histograms, left, mid);
	long long rightMax = getLargestRectangleArea(histograms, mid + 1, right);

	// 두 범위들 중 더 작은 값을 저장해둔다
	long long globalMax = max(leftMax, rightMax);

	// 좌우 범위에 걸친 범위 [ begin, end ] 정보를 저장한다
	int begin = mid;
	int end = mid+1;

	// 초기 영역의 높이와 넓이를 저장해 계산한다
	int height = min(histograms[mid].height , histograms[mid+1].height);
	globalMax = max(globalMax, height * 2LL);

	// 좌/우로 넓이를 늘려가며 확장해나간다
	int maxWidth = right - left + 1;
	for (int width = 3; width <= maxWidth; width += 1) {
		// 좌/우 각각 해당 방향으로 범위를 확대했을 때 만날 수 있는 막대의 높이를 구한다
		// 더 이상 해당 방향으로 확대할 수 없다면 -1을 저장한다
		int lnh = (left < begin) ? histograms[begin - 1].height : -1;
		int rnh = (end < right) ? histograms[end + 1].height : -1;


		if (lnh >= rnh) {
			// 왼쪽으로 확장이 더 유리한 경우
			begin -= 1;
			height = min(height, histograms[begin].height);
		} else {
			// 오른쪽으로 확장이 더 유리한 경우
			end += 1;
			height = min(height, histograms[end].height);
		}

		// 이 때의 넓이를 계산하고 갱신한다
		long long area = (long long)height * width;
		globalMax = max(globalMax, area);
	}

	return globalMax;
}

long long getLargestRectangleArea(const vector<Histogram>& histograms) {
	return getLargestRectangleArea(histograms, 0, histograms.size() - 1);
}


void testCase(int caseIndex) {
	int n;
	scanf("%d", &n);

	vector<Histogram> histograms;
	for (int i = 0; i < n; i++) {
		int height;
		scanf("%d", &height);
		histograms.push_back(Histogram(i, height));
	}

	long long answer = getLargestRectangleArea(histograms);
	printf("%lld\n", answer);
}

int main() {
	int caseSize;
	scanf("%d", &caseSize);

	for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
		testCase(caseIndex);
	}
}
