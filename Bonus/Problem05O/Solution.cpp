#include<cstdio>
#include<cstdlib>
#include<memory.h>
#include<iostream>
#include<algorithm>
#include<vector>
#include<cstring>

using namespace std;

const int deltaR[3] = {0, 1, 1}; //dr[d] := d번 방향으로 한 번 움직일때 이동하는 행방향 거리
const int deltaC[3] = {1, 1, 0}; //dc[d] := //  ---- 열방향 거리

const int MAX_ROW = 100;
const int MAX_COL = 100;


void test_case(int caseIndex) {
	int N;
	scanf("%d", &N);

	char P[101];
	scanf("%s", P);
	int M = (int) strlen(P);

	char puzzle[MAX_ROW + 1][MAX_COL + 1];

	for (int row = 0; row < N; row += 1) {
		scanf("%s", puzzle[row]);
	}

	int answer = 0;
	for (int row = 0; row < N; row += 1) {
		for (int col = 0; col < N; col += 1) {
			// 검사를 시작할 모든 칸 puzzle[row][col]에 대해

			for (int dir = 0; dir < 3; dir += 1) {
				// 검사를 수행할 각 방향 dir에 대하여
				int lastRow = row + (M - 1) * deltaR[dir];
				int lastCol = col + (M - 1) * deltaC[dir];

				if (lastRow >= N || lastCol >= N) {
					// 해당 방향으로 M글자 단어를 비교할 수 없는 경우
					// => 퍼즐을 벗어나는 경우
					continue;
				}

				// 해당 방향으로 찾고자하는 단어와 일치하는 문자의 수
				int matchedCount = 0;
				for (int i = 0; i < M; i += 1) {
					// [row][col]부터 dir방향의 i번째 글자의 좌표를 계산한다
					int targetRow = row + i * deltaR[dir];
					int targetCol = col + i * deltaC[dir];

					// 찾고자 하는 단어의 문자와 일치여부를 검사한다
					char cs = puzzle[targetRow][targetCol];
					char cp = P[i];
					if (cs == cp) {
						matchedCount += 1;
					} else {
						// 한 문자라고 일치하지 않는다면 검사를 종료한다
						break;
					}
				}

				// 찾고자 하는 단어가 완전히 일치하여 등장했다면 카운트
				if (matchedCount == M) {
					answer += 1;
				}

			}
		}
	}

	printf("%d\n", answer);
}

int main() {
	int caseSize;
	scanf("%d", &caseSize);

	for (int caseIndex = 0; caseIndex < caseSize; caseIndex += 1) {
		test_case(caseIndex);
	}
	return 0;
}