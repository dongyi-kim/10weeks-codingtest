#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

int main() {
	int N;
	cin >> N;
	// 현재까지 최다득표한 후보들의 목록
	vector<string> curMaxFreqName;

	// 각 후보 이름과 득표 수를 저장하는 key-value Map
	map<string, int> frequencyMap;

	int maxFrequency = 0; // 가장 많은 득표수
  
  //각이름이하나추가될때마다 현재까지의 최다 특표 값 갱신, 최다 득표 후보 리스트를 갱신해간다.
  //vector의 clear메소드는 O(1)이다.
	for (int i = 0; i<N; i++) {
		string st;
		cin >> st;
		frequencyMap[st]++;
		int k = frequencyMap[st];
		if (k>maxFrequency) {
			maxFrequency = k;
			curMaxFreqName.clear();
			curMaxFreqName.push_back(st);
		}
		else if (k == maxFrequency) {
			curMaxFreqName.push_back(st);
		}
	}

	// 최대 득표 후보 수를 출력한다.
	cout << maxFrequency << endl;

	// 최대 득표를한 동점 후보들 이름을 사전순으로 출력한다.
	sort(curMaxFreqName.begin(), curMaxFreqName.end());
	bool first = true;
	for (auto i : curMaxFreqName) {
		if(first == false){
			cout << ' ';
		}
		first = false;
		cout << i;
	}
}
