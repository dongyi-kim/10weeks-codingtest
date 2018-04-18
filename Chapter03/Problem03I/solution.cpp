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
vector<int> getPossibleTargets(int n, int m, int * cards, int* targets) {
	vector<int> possibleTargets; // 만들 수 있는 당첨 번호들

	// 모든 카드를 오름차순으로 정렬한다
	sort(cards, cards + n);

	for (int i = 0; i < m; ++i) { 
		int k = targets[i]; // 모든 타겟 k에 대하여
		bool possible = false;

		for (int j = 0; j < n; ++j)
		{
			int x = cards[j];  // 모든 카드 중 하나인 x에 대하여
			for (int p = 0; p <= j; p += 1)
			{
				int y = cards[p]; // 카드 중 하나 y를 선택한다
				int z = k - (x + y); // k = (x +  y )+ z 가 되는 z를 계산한다

				// z가 cards[]에 존재한다면?
				if (binary_search(cards, cards + n, z) == true)
				{
					// k = x + y + z 가 가능한 <x, y, z>가 존재한다.
					possible = true;
					break;
				}
			}
			if (possible)
			{	// 이미 찾았다면 탈출한다
				break;
			}

		}

		if (possible)
		{	// 세 카드의 합으로 k를 만들 수 있다면, 추가한다.
			possibleTargets.push_back(k);
		}
	}

	sort(possibleTargets.begin(), possibleTargets.end());
	return possibleTargets;
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

	vector<int> answers = getPossibleTargets(n, m, cards, targets);

	if (answers.size() == 0)
	{ // 가능한 당첨번호가 없다면 NO를 출력한다
		printf("NO");
	}
	else
	{ //가능한 당첨번호가 존재한다면 그 목록을 출력한다.
		for (int i = 0; i < answers.size(); i++)
		{
			if (i > 0)
			{
				printf(" ");
			}
			printf("%d", answers[i]);
		}
	}

	delete[] cards;
	delete[] targets;

	return 0;
}
