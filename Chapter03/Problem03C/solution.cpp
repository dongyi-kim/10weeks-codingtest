#include <cstdio>
#include <vector>

using namespace std;

const int MAX_SERIAL_NUMBER = 100000;

/**
 * data[0] ~ data[n-1]에서 중복이 존재하지 않는 원소들을 반환한다.
 * 단, 각 원소들은 오름차순으로 정렬되어 있어야 한다.
 * @param data  data[0] ~ data[n-1]에는 10만 이하의 자연수다.
 * @param n
 * @return
 */
vector<int> getUniqueElements (const vector<int>& data, int n) {
  // 존재하는 시리얼 넘버를 체크하기 위한 배열을 선언한다. 크기와 초기값에 유의한다.
  vector<int> count(MAX_SERIAL_NUMBER + 1, 0);

  for (int i = 0; i < n; ++i) {
  	++count[data[i]];
  }

  vector<int> ret;
  for (int i = 0; i <= MAX_SERIAL_NUMBER; ++i) {
  	if (count[i]) {
  		ret.push_back(i);
  	}
  }

  return ret;
}

int main() {
  int n;
  scanf("%d", &n);

  vector<int> data = vector<int>(n);
  for (int i = 0; i  < n; ++i) {
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
}
