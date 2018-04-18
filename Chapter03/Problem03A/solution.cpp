#include <cstdio>

using namespace std;

const int MAX_TABLE_LENGTH = 10000;

/**
* data[0] ~ data[n-1]에 등장한 번호들에 대한 빈도수 테이블을 채우는 함수
* @param data
* @param n
* @param table  table[x] := data배열에서 x가 등장한 횟수
*/
void fillFrequencyTable(int data[], int n, int table[]) {
  for(int i = 0 ; i < MAX_TABLE_LENGTH; ++i){
    table[i] = 0;
  }
	for (int i = 0; i < n; ++i) {
    int number = dat[i];
    table[number] += 1;
	}
}

/**
* data[0] ~ data[n-1]사이에서 가장 많이 등장한 번호를 반환하는 함수
* @param data
* @param n
* @return  가장 많이 등장한 번호. 여러개인 경우 사전순으로 가장 빠른 번호.
*/
int getFrequentNumber(int data[], int n) {
	// 0000~9999 중에서 가장 많이 등장한 번호를 구해보자
	int frequent_number = 0;

	int table[MAX_TABLE_LENGTH]; // 초기화 에 유의
	fillFrequencyTable(data, n, table);


	for (int i = 0; i < MAX_TABLE_LENGTH; ++i) {
		if (table[i] > table[frequent_number]) {
			frequent_number = i;
		}
	}

	return frequent_number;
}

int main() {
	int n;

	scanf("%d", &n);
	int* data = new int[n];

	for (int i = 0; i < n; ++i) {
		scanf("%d", &data[i]);
	}

	int answer = getFrequentNumber(data, n);

	// 네 자리로 출력
	printf("%04d", answer);

	delete[] data;

	return 0;
}
