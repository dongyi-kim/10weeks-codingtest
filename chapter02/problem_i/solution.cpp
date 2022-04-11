#include<cstdio>
#include<iostream>

using namespace std;

/**
* 배열의 N개의 원소가 연속적인 정수 수열로 표현될 수 있는지 판단하는 함수
* @param data
* @param n
* @return
*/
bool isConsecutive(int data[], int n) {
	int maximum = data[0];
	int minimum = data[0];

	for (int i = 0; i < n; i++) {
		if (maximum < data[i]) {
			maximum = data[i];
		}
		if (minimum > data[i]) {
			minimum = data[i];
		}
	}

	if (maximum - minimum + 1 == n) {
		return true;
	} else {
		return false;
	}
}

int main() {
	int n;
	int *data;

	scanf("%d", &n);
	data = new int[n];
	for (int i = 0; i < n; i++) {
		scanf("%d", &data[i]);
	}

	bool result = isConsecutive(data, n);


	if (result) {
		printf("YES");
	} else {
		printf("NO");
	}

	delete[] data;
	return 0;
}