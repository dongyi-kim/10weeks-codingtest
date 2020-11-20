#include<cstdio>
#include<cstdlib>
#include<memory.h>
#include<iostream>
#include<algorithm>


using namespace std;


int getTile(int** waste, int K, int row, int col) {
	int answer = 0;

	for (int i = row; i < row + K; ++i) {
		for (int j = col; j < col + K; ++j) {
			if (waste[i][j]) answer++;
		}
	}
	return answer;

}
int get_minimum_trashes(int** waste, int N, int K) {

	int answer = getTile(waste, K, 0, 0);
	for (int row = 0; row <= N - K; ++row) {
		for (int col = 0; col <= N - K; ++col) {
			answer = std::min(answer, getTile(waste, K, row, col));
		}

	}

	return answer;
}

void test_case(int caseIndex) {
	int N, K;
	std::cin >> N >> K;

	int** wastes = new int* [N];
	for (int r = 0; r < N; r += 1) {
		wastes[r] = new int[N];
		for (int c = 0; c < N; c += 1) {
			std::cin >> wastes[r][c];
		}
	}

	int answer = get_minimum_trashes(wastes, N, K);
	printf("%d\n", answer);

	for (int r = 0; r < N; r += 1) {
		delete[] wastes[r];
	}
	delete[] wastes;
}

int main() {
	int caseSize;
	std::cin >> caseSize;

	for (int caseIndex = 0; caseIndex < caseSize; caseIndex += 1) {
		test_case(caseIndex);
	}
	return 0;
}