#include <cstdio>
#include <vector>
#include <stack>
#include <cmath>

using namespace std;

class Sensor {
public:
	double xPos;    // 센서의 중심점의 x좌표
	double yPos;    // 센서의 중심점의 y좌표
	double radius; // 센서의 감지범위 반지름

	Sensor(double xPos, double yPos, double radius) {
		this->xPos = xPos;
		this->yPos = yPos;
		this->radius = radius;
	}
};

/**
 * 물체의 반지름이 R일 때 복도를 통과할 수 있는지 검사한다
 *
 * @param N       센서들의 수
 * @param W       복도의 폭
 * @param R       테스트할 물체의 반지름
 * @param sensors 각 센서의 정보
 * @return 통과할 수 있다면 True, 아니면 False를 반환한다.
 */
bool isPossible(int N, double W, double R, const vector<Sensor>& sensors) {
	// 각 센서들을 노드로 정의한다.
	// 좌/우 벽을 각각 그래프의 노드로 선언한다
	vector<vector<int> > nodes(N + 2);

	// 각 센서와 양쪽 벽 사이의 공간을 고려하여 그래프를 모델링한다
	for (int i = 0; i < N; i += 1) {

		// 해당 센서와 왼쪽 벽 사이의 거리가 2R보다 작다면? 그 사이는 물체가 통과할 수 없다
		if (sensors[i].xPos - sensors[i].radius - 2 * R < 0) {
			nodes[N].push_back(i);
			nodes[i].push_back(N);
		}

		// 해당 센서와 오른쪽 벽 사이의 거리가 2R보다 작다면? 그 사이는 물체가 통과할 수 없다
		if (sensors[i].xPos + sensors[i].radius + 2 * R > W) {
			nodes[N + 1].push_back(i);
			nodes[i].push_back(N + 1);
		}
	}

	// 서로 다른 두 센서 <i, j>사이에 대해 그래프를 모델링한다
	for (int i = 0; i < N; i += 1) {
		for (int j = i + 1; j < N; j += 1) {
			double deltaX = sensors[i].xPos - sensors[j].xPos;
			double deltaY = sensors[i].yPos - sensors[j].yPos;
			double distance = sqrt(deltaX * deltaX + deltaY * deltaY);

			// 두 센서 영역 사이의 빈 공간이 2R보다 작다면? 두 센서 사이에는 물체가 통과할 수 없다.
			if (distance - sensors[i].radius - sensors[j].radius < 2 * R) {
				nodes[i].push_back(j);
				nodes[j].push_back(i);
			}
		}
	}

	// 왼쪽 벽이 오른쪽 벽과 연결성이 존재한다면?
	// 해당 복도를 통과할 수 없다는 뜻이 된다.
	// 이를 DFS로 검사한다.
	vector<bool> visited(N + 2, false);

	stack<int> dfsStack;
	dfsStack.push(N);

	while (dfsStack.empty() == false) {
		int currentNode = dfsStack.top();
		dfsStack.pop();
		if (visited[currentNode] == true) {
			continue;
		}

		visited[currentNode] = true;

		for (int i = 0; i < nodes[currentNode].size() ; i += 1) {
			int nextNode = nodes[currentNode][i];
			if (!visited[nextNode]) {
				dfsStack.push(nextNode);
			}
		}
	}

	return !visited[N + 1];
}

/**
 * 해당 복도를 통과할 수 있는 가장 큰 반지름을 계산하여 반환한다
 *
 * @param N       센서들의 수
 * @param W       복도의 폭
 * @param sensors 각 센서들의 정보
 * @return 통과 가능한 가장 큰 반지름
 */
double getMaximumPassableRadius(int N, double W, const vector<Sensor>& sensors) {
	double lowerBound = 0;
	double upperBound = 100000;

	// 특정 횟수만큼 정밀도를 높여가며 최적의 반지름을 찾아간다
	for (int i = 0; i < 100; i += 1) {
		// 범위의 중앙값을 반지름으로 사용한다
		double radius = (lowerBound + upperBound) / 2.0;

		// 해당 반지름으로 통과가 가능한지 검사한다
		bool possible = isPossible(N, W, radius, sensors);

		if (possible) {
			// 통과할 수 있다면 하한선을 증가시킨다
			lowerBound = radius;
		} else {
			// 통과할 수 없다면 하한선을 감소시킨다
			upperBound = radius;
		}
	}

	return upperBound;
}

void testCase() {
	double W;
	int N;
	scanf("%lf%d", &W, &N);

	vector<Sensor> sensors;
	for (int i = 0; i < N; i += 1) {
		double x, y, r;
		scanf("%lf%lf%lf", &x, &y, &r);
		sensors.push_back(Sensor(x, y, r));
	}

	double answer = getMaximumPassableRadius(N, W, sensors);

	printf("%.3f\n", answer);
}

int main() {
	int caseNum;
	scanf("%d", &caseNum);
	for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
		testCase();
	}
}
