#include <cstdio>
#include <vector>
#include <cmath>

using namespace std;

const int SAMPLING_NUMBER = 1000;

class Circle {
public:
	double xPos;	// 원 중심의 x좌표
	double yPos;	// 원 중심의 y좌표
	double radius;	// 원 영역의 반지름

	Circle(double x, double y, double r) {
		this->xPos = x;
		this->yPos = y;
		this->radius = r;
	}

	// 해당 점 (x,  y)가 원 내부에 포함되는지 검사하는 함수
	bool contains(double x, double y) {
		double dx = x - this->xPos;
		double dy = y - this->yPos;
		double distance = sqrt(dx * dx + dy * dy);

		return distance <= this->radius;
	}
};

/**
 * 기준 원 영역 중 커버리지에 의해 덮혀진 영역의 비율을 계산하는 함수
 *
 * @param N				커버리지들의 수
 * @param globalRange	기준 원 영역
 * @param coverages		각 와이파이들의 커버리지
 * @return				덮혀진 영역의 비율
 */
double getCoveredRatio(int N, Circle globalRange, const vector<Circle>& coverages) {
	// globalRange를 덮는 x, y좌표의 상/하한 범위를 구한다
	double maxY = globalRange.yPos + globalRange.radius;
	double minY = globalRange.yPos - globalRange.radius;
	double minX = globalRange.xPos - globalRange.radius;
	double maxX = globalRange.xPos + globalRange.radius;

	// 각 x와 y범위를 샘플링할 길이를 계산한다
	double unitLength = abs(maxX - minX) / SAMPLING_NUMBER;

	int globalCount = 0;
	int coverageCount = 0;

	// X:[minX, maxX], Y:[minY, maxY] 범위의 점들을 샘플링하여 모두 조회한다
	for (double x = minX; x <= maxX; x += unitLength) {
		for (double y = minY; y <= maxY; y += unitLength) {
			// 해당 영역의 모든 점들 중

			if (globalRange.contains(x, y) == true) {
				// 기준 영역에 속한 점의 수를 계산한다
				globalCount += 1;

				// 하나 이상의 커버리지에 속한 점의 수를 계산한다
				bool isInside = false;
				for (int i = 0; i < N ; i += 1) {
					Circle c = coverages[i];
					if (c.contains(x, y) == true) {
						isInside = true;
						break;
					}
				}

				if (isInside) {
					coverageCount += 1;
				}
			}
		}
	}

	// 두 수를 나누어 근사 비율을 계산한다
	return (double) coverageCount / globalCount;
}


void testcase() {
	int N;
	scanf("%d", &N);

	double x, y, r;

	scanf("%lf%lf%lf", &x, &y, &r);
	Circle globalRange = Circle(x, y, r);

	vector<Circle> coverages;
	for (int i = 0; i < N; i += 1) {
		scanf("%lf%lf%lf", &x, &y, &r);
		coverages.push_back(Circle(x, y, r));
	}

	double ratio = getCoveredRatio(N, globalRange, coverages);

	printf("%.2f\n", ratio);
}

int main() {
	int caseNum;
	scanf("%d", &caseNum);
	for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
		testcase();
	}
}
