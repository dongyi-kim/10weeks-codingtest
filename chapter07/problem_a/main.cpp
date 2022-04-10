#include <iostream>
#include <vector>
#include <string>

using namespace std;

const char ITEMS[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

/**
 * @brief   depth 번째 이후 알파벳 조합을 탐색하는 함수
 *          모든 알파벳이 선택되었다면, 선택 된 알파벳을 모두 출력한다
 *
 * @param depth             재귀함수의 깊이 (= 선택된 알파벳 수 + 1)
 * @param limit             재귀함수의 최대 깊이 제한
 * @param selectedIndex     현재까지 선택한 데이터들의 인덱스
 */
void findAllCombinations(int depth, int limit, vector<int> selectedIndex, vector<string>& answer) {
	// depth가 제한보다 커진 경우, limit개 만큼의 문자를 모두 선택했다는 뜻이다
	if (depth > limit) {
		// 선택한 문자들을 차례로 한 줄에 출력한다

		string str;
		for (int i = 1; i < depth; i += 1) {
			int index = selectedIndex[i - 1];
			char c = ITEMS[index];
			str += c;
		}
		answer.push_back(str);

		// 이제 종료한다
		return;
	}


	// 고려해야 할 가장 첫 알파벳의 인덱스
	// 초기값은 0
	int minIndex = 0;

	// 현재 이미 앞서 선택해온 알파벳이 있는 경우
	if(depth > 1) {
		// 그 알파벳보다 사전순으로 뒤에 있는 문자부터만 검사해주도록
		// 최소 인덱스를 제한한다
		minIndex = selectedIndex[selectedIndex.size() - 1] + 1;
	}

	for (int index = minIndex; index < 26; index += 1) {
		// 현재 사용할 수 있는 모든 알파벳의 번호 index에 대해

		// 현재 알파벳을 선택 목록 맨 뒤에 추가한다.
		selectedIndex.push_back(index);

		// 이후 다음 하위 조합들을 선택해본다
		findAllCombinations(depth + 1, limit, selectedIndex, answer);

		// 이번 페이즈에 선택했던 알파벳을 다시 제거해준다
		selectedIndex.pop_back();
	}
}

int main() {
	int N;
	cin >> N;

	vector<string> answer;
	findAllCombinations(1, N, vector<int>(), answer);
	
	for (int i = 0; i < answer.size(); i += 1) {
		cout << answer << endl;
	}
}

