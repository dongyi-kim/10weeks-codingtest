#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;


class LIS {
public:
	vector<int> memo;         // DP 상태공간
	vector<int> array;        // 수열의 원소
	int n;              // 수열의 길이

	LIS(vector<int> array) {
		this->array = array;
		this->n = array.size();
		this->memo.assign(n, -1);
	}

	/**
	 * array[lastIndex]가 마지막 원소인 모든 LIS 길이를 계산하는 함수
	 *
	 * @param lastIndex 부분 수열의 마지막 원소의 인덱스
	 * @return array[lastIndex]가 마지막 원소인 LIS의 길이
	 */
	int f(int lastIndex) {
		if (lastIndex < 0) {
			// 예외인 경우는 길이 0으로 취급한다.
			return 0;
		} else if (memo[lastIndex] != -1) {
			// 이미 계산된 적 있는 결과라면 반환한다.
			return memo[lastIndex];
		} else if (lastIndex == 0) {
			return 1;
		}

		int answer = 1;

		for (int previousIndex = 0; previousIndex < lastIndex; previousIndex += 1) {
			int newLength = this->f(previousIndex) + 1;
			if (array[previousIndex] < array[lastIndex] && newLength > answer) {
				answer = newLength;
			}
		}
		
		memo[lastIndex] = answer;
		return memo[lastIndex];
	}
};

int main() {
	int n;
	scanf("%d", &n);

	vector<int> arr(n);
	for (int i = 0; i < n; i += 1) {
		scanf("%d", &arr[i]);
	}

	LIS lis(arr);
	int answer = 0;
	for (int i = 0; i < n; i += 1) {
		answer = max(answer, lis.f(i));
	}

	printf("%d\n", answer);
}
