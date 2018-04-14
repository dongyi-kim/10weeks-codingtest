#include <cstdio>
#include <vector>

using namespace std;

class FrequencyTable {
public:
  const int MAX_SIZE = 1000000;

  int uniqueElements; //현재 중복이 존재하지 않는 unique한 생일의 수
  vector<int> frequency; //frequency[b] := 생일이 b인 정보의 수

  FrequencyTable() {
    this->uniqueElements = 0;
    this->frequency = vector<int>(MAX_SIZE, 0);
  }

  /**
   * 새로운 생일을 등록하고 빈도수를 갱신한다.
   * @param birthDate
   */
  void addBirthDate(int birthDate) {
  	++frequency[birthDate];

  	if (frequency[birthDate] == 1) { // 빈도수가 0에서 1이 되었다면? 새로 추가될 데이터는 unique한 데이터가 될 것이다.
  		++uniqueElements;
  	} else if (frequency[birthDate] == 2) {  // 빈도수가 1에서 2가 되었다면? 새로 추가될 데이터로 인해 unique해지지 않을 것이다.
  		--uniqueElements;
  	}
  }

  /**
   * 기존의 생일을 제거하고 빈도수를 갱신한다.
   * @param birthDate
   */
  void removeBirthDate(int birthDate) {
  	--frequency[birthDate];

  	if (frequency[birthDate] == 1) { // 빈도수가 2에서 1이 되었다면? 빈도수가 하나 줄어들어 unique한 데이터가 될 것이다.
  		++uniqueElements;
  	} else if (frequency[birthDate] == 0) { // 빈도수가 1에서 0이 이었다면? 제거 될 데이터로 인해 빈도수가 0이 될 것이다.
  		--uniqueElements;
  	}
  }
};

int getUniqueRangeNumber(const vector<int>& birthDate, int n, int k) {
  int answer = 0; //모든 원소가 서로 다른 범위의 수
  FrequencyTable frequencyTable;

	// 가장 처음의 k개의 데이터에 대한 정보를 미리 등록한다.
  for (int i = 0; i < k; ++i) {
  	frequencyTable.addBirthDate(birthDate[i]);
  }

  // 처음 연속된 k개가 가능한 경우인지 체크한다.
  if (frequencyTable.uniqueElements == k) {
  	++answer;
  }

  // 나머지 인덱스에 대해 가능한 경우를 조사한다.
  for (int i = k; i < n; ++i) {

  	// 조사하는 범위가 [i - k, i - 1]에서 [i - k + 1, i]로 변화를 했다.
  	// 따라서 i - k 번 째 인덱스는 관찰 대상이 아니다.
  	frequencyTable.removeBirthDate(birthDate[i - k]);

  	// 그렇다면 i번째 인덱스는 새롭게 관찰해야할 대상이다.
  	frequencyTable.addBirthDate(birthDate[i]);

  	// 처음 연속된 k개가 가능한 경우인지 체크한다.
  	if (frequencyTable.uniqueElements == k) {
  		++answer;
  	}
  }

  return answer;
}

int main() {
  int n, k;
  scanf("%d%d", &n, &k);

  vector<int> birthDate(n);
  for (int i = 0; i < n; ++i) {
    scanf("%d", &birthDate[i]);
  }

  int answer = getUniqueRangeNumber(birthDate, n, k);

  printf("%d\n", answer);
}
