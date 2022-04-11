#include <cstdio>
#include <vector>

using namespace std;

class FrequencyTable {
public:
	const int MAX_SIZE = 1000000;

	int uniqueElements; //현재 중복이 존재하지 않는 unique한 생일의 수
	vector<int> frequency; //frequency[b] := 생일이 b인 정보의 수

	FrequencyTable() {
		this->uniqueElements = 0;
		this->frequency = vector<int>(MAX_SIZE, 0);
	}

	/**
	* 새로운 생일을 등록하고 빈도수를 갱신한다.
	* @param birthDate
	*/
	void addBirthDate(int birthDate) {
		// 기존의 빈도수를 계산한다.
		int count = frequency[birthDate];
		if (count == 0) { // 기존에 빈도수가 0이었다면? 새로 추가될 데이터는 unique한 데이터가 될 것이다.
			uniqueElements += 1;
		} else if (count == 1) { // 기존에 빈도수가 1이었다면? 새로 추가될 데이터로 인해 unique해지지 않을 것이다.
			uniqueElements -= 1;
		}

		frequency[birthDate] += 1;
	}

	/**
	* 기존의 생일을 제거하고 빈도수를 갱신한다.
	* @param birthDate
	*/
	void removeBirthDate(int birthDate) {
		int count = frequency[birthDate];
		if (count == 1) { // 기존에 빈도수가 1이었다면? 제거 될 데이터로 인해 빈도수가 0이 될 것이다..
			uniqueElements -= 1;
		} else if (count == 2) { // 기존에 빈도수가 2이었다면? 빈도수가 하나 줄어들어 unique한 데이터가 될 것이다.
			uniqueElements += 1;
		}

		frequency[birthDate] -= 1;
	}
};

int getUniqueRangeNumber(const vector<int> &birthDate, int n, int k) {
	int answer = 0;

	FrequencyTable table = FrequencyTable();
	// 가장 처음의 k-1개의 데이터에 대한 정보를 미리 등록한다.
	for (int i = 0; i < k - 1; i++) {
		table.addBirthDate(birthDate[i]);
	}

	for (int i = 0; i + k - 1 < n; i++) { // 각 범위의 가장 왼쪽 인덱스 i에 대하여
		// 모든 [i, i+k-1] 범위를 조사한다.

		int left = i;
		int right = i + k - 1;

		// 가장 오른쪽에 새로 추가된 인덱스의 정보를 등록한다.
		table.addBirthDate(birthDate[right]);

		// 범위의 길이가 k개를 초과한 경우, 가장 왼쪽 인덱스의 정보를 제거한다.
		if (left > 0) {
			table.removeBirthDate(birthDate[left - 1]);
		}

		// 이제 table에는 birthDate[left] ~ birthDate[right]에 대한 연속된 k개의 생일에 대한 정보만 기록되어 있다.
		if (table.uniqueElements == k) { // unique한 원소의 갯수가 전체 원소의 갯수와 같다
			// <==> 모든 원소가 서로 다르다

			// 이러한 모든 범위에 대하여, 경우의 수를 증가시킨다.
			answer += 1;
		}
	}

	return answer;
}

int main() {
	int n, k;
	scanf("%d%d", &n, &k);

	vector<int> birthDate(n);
	for (int i = 0; i < n; ++i) {
		scanf("%d", &birthDate[i]);
	}

	int answer = getUniqueRangeNumber(birthDate, n, k);

	printf("%d\n", answer);
}
