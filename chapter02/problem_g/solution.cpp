#include<cstdio>
#include<iostream>

using namespace std;


void bubbleSort(int data[], int n) {
	for (int i = 0; i < n; i++) {
		int negativeCount = 0;
		for (int j = 0; j < n - 1 - i; j++) {
			//오름차순을 부정하는 쌍이 등장하며면
			if (data[j] > data[j + 1]) {    //두 쌍의 자리를 변경한다
				int temp = data[j];
				data[j] = data[j + 1];
				data[j + 1] = temp;

				//그리고 그런 쌍의 수를 기록한다
				negativeCount += 1;
			}
		}

		//이 값이 0이라는 건? 이미 모두 정렬이 되었다는 것
		if (negativeCount == 0) {
			break;
		}
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

	bubbleSort(data, n);

	for (int i = 0; i < n; i++) {
		if (i > 0) {
			printf(" ");
		}
		printf("%d", data[i]);
	}

	delete[] data;
	return 0;
}