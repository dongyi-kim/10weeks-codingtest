#include<stdio.h>
#include<iostream>
#include<algorithm>
#include<vector>
#include<string>
#include<map>
#include<set>
using namespace std;

//in, out의 속도와 개행, string 쪼개기등 stdio가 유리하다고 판단되어 stdio를 사용했습니다.

void testCase(int testIdx) {
	
	//피자의 수
	int nPizza; scanf("%d", &nPizza);

	//모든 피자에서 한 번 이상 등장한 재료들의 이름을 언어별로 모두 저장해둔다
	set<string>english;//모든 영어표기 재료
	set<string>foreign;//모든 외국어 표기 재료

	//각 피자별/언어별 사용된 재료의 목록을 저장한다 
	//englishPizza[i] := i번째 피자에 사용된 영어 재료명 집합 
	vector<set<string>>englishPizza;
	vector<set<string>>foreignPizza;

	//각 피자에 대하여 재료를 입력받는다
	for (int i = 0; i < nPizza; i++) {

		//피자 이름
		char buf[30]; scanf("%s", buf);
		string pizzaName(buf);

		//이 피자에서 사용되는 영어와 외국어 재료 명들을 저장할 집합 
		set<string>englishIngredients;
		set<string>foreignIngredients;
		int na, nb;
		
		scanf("%d", &na);//영어 표기 재료의 수 
		for (int j = 0; j < na; j++) {
			//각 영어 재료명에 대하여 
			char tmp[30]; scanf("%s", tmp);
			string ingredient(tmp);
			//이 피자에 대한 영어 재료명 집합에 추가하고 
			englishIngredients.insert(tmp);
			//전체 피자에 대한 영어 재료명 집합에 추가 
			english.insert(tmp);
		}

		scanf("%d", &nb);//외국어 표기 재료의 수 
		for (int j = 0; j < nb; j++) {
			//각 외국어 재료명에 대하여 
			char tmp[30]; scanf("%s", tmp);
			string ingredient(tmp);
			//이 피자에 대한 외국어 재료명 집합에 추가하고 
			foreignIngredients.insert(tmp);
			//전체 피자에 대한 외국어 재료명 집합에 추가 
			foreign.insert(tmp);
		}

		//현재 이 피자의 재료명 집합을 피자 리스트에 추가한다 
		englishPizza.push_back(englishIngredients);
		foreignPizza.push_back(foreignIngredients);
	}
	for (auto &ei : english) {//모든 한 번 이상 등장한 영어표기 재료명에 대하여 사전순으로 조회 
		for (auto &fi : foreign) {//모든 한 번 이상 등장한 외국어 표기 재료명에 대하여 사전순으로 조회
			//영어 표기 ei가 외국어 표기 fi와 같은 재료라고 할 수 있을까?

			bool flag = true;//그렇다면 true, 아니라면 false

			//일단 true라고 가정하고 이에 대한 반례를 찾자
			for (int i = 0; i < nPizza; i++) {
				//각 피자에 대하여

				// 해당 피자 영어표기 재료명에 ei가 존재하는지
				bool existInEnglish = englishPizza[i].find(ei) != englishPizza[i].end();

				//해당 피자 외국어표기 재료명에 fi가 존재하는지 
				bool existInForeign = foreignPizza[i].find(fi) != foreignPizza[i].end();

				//둘이 같은 재료라면 둘 다 존재하거나 둘 다 존재하지 않아야 한다
				if (existInEnglish != existInForeign) {
					//존재 여부가 서로 다르다면 이 쌍에 대한 반례가 되므로 flag를 false로 하고 종료 
					flag = false;
					break;
				}
			}

			//이 쌍에 대하여 반례가 없다면 올바른 쌍이므로 출력
			if (flag) {
				cout << '(' << ei << ", " << fi << ")\n";
			}
		}
	}
}

int main() {
	int testSize;
	scanf("%d", &testSize);
	for (int testIdx = 1; testIdx <= testSize; testIdx++) {
		testCase(testIdx);
	}
	return 0;
}
