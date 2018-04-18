#include <cstdio>
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
int getPossibleTargetNumber(int n, int m, const vector<int>& cards, const vector<int>& targets) {
  int answer = 0; //만들 수 있는 당첨번호의 수

  for (int i = 0; i < m; ++i) { // 모든 타겟에 대하여
  	const int& target = targets[i];

  	for (int j = 0; j < n; ++j) { // 모든 카드를 순회하며 짝이 되는 또다른 카드가 있는지 검사

  		// target를 만들기 위해 card[j]를 선택했다면 또 다른 값은 target - card[j]가 되어야 한다.
  		// 그렇다면 target - card[j]가 cards 벡터 안에 존재한다면 가능한 경우다.
  		// for문으로 순회하면 시간이 오래 걸리므로 binary search를 사용한다.
  		// binary search를 사용하기 위해 main함수에서 cards배열을 정렬했음에 유의한다.
  		if (binary_search(cards.begin(), cards.end(), target - cards[j])) {
  			++answer;
  			break;
  		}
  	}
  }

  return answer;
}

int main() {
  int n, m;
  scanf("%d%d", &n, &m);

  vector<int> cards(n);
  vector<int> targets(m);

  // 각 카드 정보를 입력받는다
  for (int i = 0; i < n; ++i) {
    scanf("%d", &cards[i]);
  }

  // 각 당첨번호를 입력받는다
  for (int i = 0; i < m; ++i) {
    scanf("%d", &targets[i]);
  }

  sort(cards.begin(), cards.end());
  int answer = getPossibleTargetNumber(n, m, cards, targets);

  printf("%d\n", answer);
}
