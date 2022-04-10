#include <iostream>
#include <vector>
#include <stack>
#include <cmath>
#include <algorithm>

using namespace std;

class Histogram {
public:
	int height;    // 히스토그램의 높이
	int leftX;     // 인덱스 혹은 히스토그램의 왼쪽 변의 x 좌표
	int rightX;    // 히스토그램의 오른쪽 변의 x좌표

	Histogram() { }

	Histogram(int index, int height) {
		this->leftX = index;
		this->rightX = this->leftX + 1;   // 각 히스토그램은 너비가 1이므로
		this->height = height;
	}

};

/**
 * 왼쪽에서 오른쪽으로 차례로 세워진 히스토그램들 내부에 만들 수 있는
 * 최대의 직사각형 넓이를 계산하는 함수
 *
 * @param n             히스토그램의 수
 * @param histograms    히스토그램이 차례로 저장된 배열
 * @return
 */
long long getLargestRectangleArea(const vector<Histogram>& histograms) {
	long long answer = 0;    // 최대 직사각형의 넓이

	// 현재 우측으로 확장 가능성이 있는 히스토그램들
	stack<Histogram> continuedHistograms;

	// 구현상 편의를 위해 가장 왼쪽에 높이 0까지 가상의 히스토그램 추가
	continuedHistograms.push(Histogram(-1, 0));
	for(int i = 0 ; i < histograms.size() + 1 ; i++) {
		// 왼쪽부터 오른쪽 히스토그램까지 차례로
		Histogram h;
		if(i < histograms.size()) {
			h = histograms[i];
		} else { 
			// if ( i == n )
			// 구현상 편의를 위해 가장 오른쪽에 높이 0까지 가상의 히스토그램 추가
			h = Histogram(histograms.size(), 0);
		}

		// 이전에 확장중이던 히스토그램들 중, h보다 높이가 높은 히스토그램들은
		// 더 이상 확장될 수 없다 => 최대 넓이가 결정된다.
		while (continuedHistograms.size() > 1
				&& continuedHistograms.top().height >= h.height){
			// 확장중이던 히스토그램
			Histogram lh = continuedHistograms.top();
			continuedHistograms.pop();

			// 그 이전의 히스토그램 (왼쪽 확장의 끝)
			Histogram bh = continuedHistograms.top();

			// 현재 추가된 h의 바로 왼쪽까지 확장중이었다.
			long long width = abs(h.leftX - bh.rightX);
			long long height = lh.height;
			long long area =  width * height;

			// 최대 값 갱신
			answer = max(answer, area);
		}

		// h를 새로이 추가한다
		continuedHistograms.push(h);
	}

	return answer;
}

void process(int caseIndex) {
	int n;
	cin >> n;

	vector<Histogram> histograms;
	for(int i = 0 ; i < n ; i ++) {
		int height;
		cin >> height;
		histograms.push_back(Histogram(i, height));
	}

	long long answer = getLargestRectangleArea(histograms);
	cout << answer << endl;
}

int main() {
	int caseSize;
	cin >> caseSize;

	for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
		process(caseIndex);
	}
}
