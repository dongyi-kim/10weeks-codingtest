#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 1e9;

class Item {
public:
	int value;
	int cost;

	Item(int value, int cost) {
		this->value = value;
		this->cost = cost;
	}
};

class Knapsack {
public:
	vector<Item> items;
	vector<vector<int> > memo;
	int n, capacity;

	Knapsack(int n, int capacity, vector<Item> items) {
		this->n = n;
		this->capacity = capacity;
		this->items = items;
		this->memo = vector<vector<int> >(n, vector<int>(capacity + 1, -1));
	}

	/**
	 * items[0..lastItemIndex]의 보석만을 고려하여 정확히 totalCost의 무게를 채웠을 때 얻을 수 있는 최대 가치
	 *
	 * @param lastItemIndex 마지막 보석의 인덱스
	 * @param totalCost     사용할 모든 보석의 무게 합
	 * @return
	 */
	int f(int lastItemIndex, int totalCost) {
		if (lastItemIndex == -1) {
			return 0;
		}
		if (lastItemIndex < 0 || totalCost < 0 || totalCost > capacity) {
			// 불가능한 경우
			return -INF;
		} else if (memo[lastItemIndex][totalCost] != -1) {
			// 이미 계산한 경우
			return memo[lastItemIndex][totalCost];
		} else if (totalCost == 0) {
			// 더 이상 아무 보석도 담을 수 없는 경우
			return 0;
		}

		// 이번 아이템을 사용하지 않는 경우
		int answer = f(lastItemIndex - 1, totalCost);

		if (items[lastItemIndex].cost <= totalCost) {
			// 이번 아이템을 사용하는 경우
			int previousCost = totalCost - items[lastItemIndex].cost;
			int previousValue = f(lastItemIndex - 1, previousCost);
			int currentValue = previousValue + items[lastItemIndex].value;

			answer = max(answer, currentValue);
		}

		memo[lastItemIndex][totalCost] = answer;
		return answer;
	}
};

int main() {
	int n, capacity;
	scanf("%d%d", &n, &capacity);

	vector<Item> items;
	for (int i = 0; i < n; i += 1) {
		int weight, price;
		scanf("%d%d", &weight, &price);
		items.push_back(Item(price, weight));
	}

	Knapsack knapsack(n, capacity, items);
	int answer = 0;
	for (int totalCost = 0; totalCost <= capacity; totalCost += 1) {
		answer = max(answer, knapsack.f(n - 1, totalCost));
	}

	printf("%d\n", answer);
}
