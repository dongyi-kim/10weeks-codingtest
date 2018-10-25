#include<bits/stdc++.h>
using namespace std;

int main() {
	int n;
	scanf("%d", &n);
	set<int>integers;
	for (int i = 0; i < n; i++) {
		int x;
		scanf("%d", &x);
    
    //C++ set의 find메소드는 찾는 수가 있다면 해당 iterator를, 없다면 end() iterator를 반환한다.
		if (integers.find(x) != integers.end()) { //찾는 수가 있다면
			printf("DUPLICATED\n");
		}
		else { //찾는 수가 없다면
			integers.insert(x); //set에 수를 등록한다.
			printf("OK\n");
		}
	}
	return 0;
}
