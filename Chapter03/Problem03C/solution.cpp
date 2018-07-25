#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int MAX_SERIAL_NUMBER = 100000;

/**
* data[0] ~ data[n-1]에 등장한 시리얼 번호들에 대한 빈도수 테이블을 채우는 함수
* @param data
* @param n
* @param table  table[x] := data배열에서 x가 등장한 횟수
*/
void fillFrequencyTable(const vector<int> &data, int n, vector<int> &table) {
	table.clear();
	table.resize(MAX_SERIAL_NUMBER + 1, 0);

	for (int i = 0; i < n; ++i) {
		int serial = data[i];
		table[serial] += 1;
	}
}


/**
* data[0] ~ data[n-1]에서 중복이 존재하지 않는 원소들을 반환한다.
* 단, 각 원소들은 오름차순으로 정렬되어 있어야 한다.
* @param data  data[0] ~ data[n-1]에는 10만 이하의 자연수다.
* @param n
* @return
*/
vector<int> getUniqueElements(const vector<int> &data, int n) {

	vector<int> ret; //유일한 원소들 배열

	//data에 대한 빈도수 테이블을 계산한다
	vector<int> table;
	fillFrequencyTable(data, n, table);

	for (int number = 1; number <= MAX_SERIAL_NUMBER; number += 1) { //존재할 수 있는 모든 시리얼 넘버에 대해 차례로 조회한다.

		//전체 데이터에서 한 번만 등장한 number를 차례로 정답 리스트에 추가한다.
		if (table[number] == 1) {
			ret.push_back(number);
		}
	}

	//오름차순 순서로 추가했기 때문에 ret에 대한 정렬은 불필요하다.
	return ret;
}

//vector<int> getUniqueElements(const vector<int>& data, int n) {
//	//  <algorithm2> 정렬을 사용한 방법
//	vector<int> ret; //유일한 원소들 배열
//
//	//data를 오름차순으로 정렬한다.
//	//정렬을 하면, 같은 값을 가진 원소는 항상 인접해있다.
//	sort(data.begin(), data.end());
//
//	for (int i = 0; i < n; i++)
//	{	//모든 원소 data[i]를 오름차순으로 한 번씩 조회한다.
//		if ((i == 0 || data[i - 1] != data[i])
//			&& (i == n - 1 || data[i + 1] != data[i]))
//		{
//			ret.push_back(data[i]);
//		}
//	}
//
//	//오름차순 순서로 추가했기 때문에 ret에 대한 정렬은 불필요하다.
//	return ret;
//}

int main() {
	int n;
	scanf("%d", &n);

	vector<int> data = vector<int>(n);
	for (int i = 0; i < n; ++i) {
		scanf("%d", &data[i]);
	}

	const vector<int> answer = getUniqueElements(data, n);

	// 각 원소들을 출력한다.
	for (int i = 0; i < answer.size(); ++i) {
		if (i > 0) { //첫 번째 원소가 아니라면 앞에 공백을 하나 추가한다.
			printf(" ");
		}
		printf("%d", answer[i]);
	}

	return 0;
}
