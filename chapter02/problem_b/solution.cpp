#include<cstdio>
#include<iostream>

using namespace std;

/**
* 주어진 배열이 오름차순인지 검사하는 함수
* @param data
* @param n     데이터의 수
* @return      data[0] ~ data[n-1]이 오름차순이라면 true, else false
*/
bool isOrdered(int data[], int n) {
	int count = 0; //오름차순을 부정하는 페어의 수 -> data[i] > data[i+1];

	for (int i = 0; i + 1 < n; i++) {
		if (data[i] > data[i + 1]) {
			count += 1;
			break;
		}
	}

	if (count >= 1) {
		return false;
	} else {
		return true;
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

	bool result = isOrdered(data, n);

	if (result) {
		printf("YES");
	} else {
		printf("NO");
	}

	delete[] data;
	return 0;
}