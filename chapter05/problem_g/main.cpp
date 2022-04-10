#include <iostream>
#include <queue>
#include <vector>
#include <functional>
#include <algorithm>

using namespace std;

class City {
public:
	int index;     // 도시의 인덱스
	int income;    // 해당 도시의 소득

	City(int index, int income) {
		this->index = index;
		this->income = income;
	}

	bool operator < (const City& o) const {
		return this->income < o.income;
	}
	bool operator > (const City& o) const {
		return this->income > o.income;
	}
};

int getMaximumRangeDifference(int n, int k, const vector<City>& cities) {
	int answer = 0;

	// 소득이 가장 작은 도시부터 pop되는 우선순위 큐
	priority_queue<City, vector<City>, greater<City>> rangeMinimum;

	// 소득이 가장 높은 도시부터 pop되는 우선순위 큐
	priority_queue<City> rangeMaximum;

	for (int i = 0; i < k - 1; i++) {
		rangeMaximum.push(cities[i]);
		rangeMinimum.push(cities[i]);
	}
	for (int i = k - 1; i < n; i++) {
		rangeMaximum.push(cities[i]);
		rangeMinimum.push(cities[i]);
		while (rangeMaximum.top().index < i - k + 1) rangeMaximum.pop();
		while (rangeMinimum.top().index < i - k + 1) rangeMinimum.pop();
		answer = max(answer, rangeMaximum.top().income - rangeMinimum.top().income);
	}

	return answer;
}

void process(int caseIndex) {
	int n, k;
	cin >> n >> k;
	vector<City> cities;

	for (int i = 0; i < n; i += 1) {
		int income;
		cin >> income;
		cities.push_back(City(i, income));
	}

	int answer = getMaximumRangeDifference(n, k, cities);

	cout << answer << endl;
}

int main() {
	int caseSize;
	cin >> caseSize;

	for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
		process(caseIndex);
	}
}
