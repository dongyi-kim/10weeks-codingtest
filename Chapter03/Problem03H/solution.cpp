#include <stdio.h>
#include <vector>
#include <algorithm>

using namespace std;

/**
* 중복을 포함해 두 카드의 합으로 만들 수 있는 당첨번호의 수를 계산하는 함수
* @param n     카드의 수
* @param m     검사하려는 당첨번호의 수
* @param cards   각 카드에 적힌 숫자들
* @param target  검사하려는 각 당첨번호 리스트
* @return
*/
int getPossibleTargetNumber(int n, int m, int * cards, int* targets) {
	int answer = 0; //만들 수 있는 당첨번호의 수

	// 모든 카드를 오름차순으로 정렬한다
	sort(cards, cards + n);

	for (int i = 0; i < m; ++i) { 
		int k = targets[i]; // 모든 타겟 k에 대하여
		bool possible = false;

		for (int j = 0; j < n; ++j)
		{
			int x = cards[j];  // 모든 카드 중 하나인 x에 대하여

			// x와 k가 상수이므로 y를 계산할 수 있다.
			int y = k - x;
			
			if (binary_search(cards, cards+n, y) == true) 
			{
				//배열에 y가 존재한다 <==> x+y=k가 되는 <x, y>가 존재한다.
				//이를 표시하고 나간다.
				possible = true;
				break;
			}
		}

		if (possible)
		{	// z가 두 수의 조합으로 표현될 수 있다면,
			// 가능한 숫자의 갯수를 증가시킨다.
			answer += 1;
		}
	}

	return answer;
}

int main() {
	int n;	// 카드의 수 
	int m;	// 검사 할 후보 당첨번호의 수 
	scanf("%d %d", &n, &m);

	int* cards = new int[n];
	int* targets = new int[m];

	// 각 카드 정보를 입력받는다
	for (int i = 0; i < n; i++){
		scanf("%d", &cards[i]);
	}

	// 각 후보 당첨번호를 입력받는다
	for (int i = 0; i < m; i++){
		scanf("%d", &targets[i]);
	}

	int answer = getPossibleTargetNumber(n, m, cards, targets);

	printf("%d\n", answer);


	delete[] cards;
	delete[] targets;

	return 0;
}
