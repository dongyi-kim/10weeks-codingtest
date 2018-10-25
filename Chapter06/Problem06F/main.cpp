#include<bits/stdc++.h>
using namespace std;

class Bomb {
public:
	int index;
	int childCnt;
	vector<Bomb*>parentBombs;

	Bomb() {
		childCnt = 0;
	};
	Bomb(int _index) {
		index = _index;
		childCnt = 0;
	}

	void addParentBombs(Bomb* parentBomb) {
		parentBombs.push_back(parentBomb);
		parentBomb->childCnt++;
	}
};

class Cmp {
public:
	bool operator()(Bomb *a, Bomb *b) {
		return a->index > b->index;
	}
};

vector<Bomb*> isAllRemoveable(int n, vector<Bomb*> &bombs) {

	//제거된 폭탄 목록
	vector<Bomb*>removedList;

	//제거 가능한 폭탄 목록
	//priority queue를 사용한다. 폭탄들의 우선순위는 Cmp class로 정의했다.
	priority_queue<Bomb*, vector<Bomb*>, Cmp>removableBombs;

	// 현재 연쇄 폭탄이 없는 폭탄들은 제거 가능한 목록에 추가한다.
	for (int i = 0; i < n; i++) {
		if (bombs[i]->childCnt == 0) {
			removableBombs.push(bombs[i]);
		}
	}

	// 제거 가능한 폭탄이 남아있는 동안 반복한다.
	while (removableBombs.size()) {

		// 현재 연쇄폭탄이 없는 폭탄 b를 제거하고, 제거된 폭탄 갯수를 증가시킨다.
		Bomb *b = removableBombs.top();
		removableBombs.pop();
		removedList.push_back(b);

		//제거된 폭탄의 부모 폭탄들의 child갯수를 1씩 내린다.
		//만약 child갯수가 0이 된 폭탄이 있으면 제거 가능한 폭탄 목록에 추가한다.
		vector<Bomb*> &parents = b->parentBombs;
		for (int i = 0; i < parents.size(); i++) {
			Bomb *p = parents[i];
			p->childCnt--;
			if (p->childCnt == 0) {
				removableBombs.push(p);
			}
		}
	}

	//제거한 폭탄들 목록은 return한다.
	return removedList;
}

void testCase() {
	int n, m;
	scanf("%d %d", &n, &m);

	vector<Bomb*>bombs(n);

	for (int i = 0; i < n; i++) {
		bombs[i] = new Bomb(i + 1);
	}

	for (int i = 0; i < m; i++) {
		int u, v;
		scanf("%d %d", &u, &v);
		u -= 1;
		v -= 1;
		Bomb* parent = bombs[u];
		Bomb* child = bombs[v];
		child->addParentBombs(parent);
	}
	vector<Bomb*> removable = isAllRemoveable(n, bombs);
	if (removable.size() == n) {
		for (int i = 0; i < removable.size(); i++) {
			if (i)printf(" ");
			printf("%d", removable[i]->index);
		}
	}
	else {
		printf("NO");
	}
	printf("\n");

	for (int i = 0; i < n; i++) {
		delete bombs[i];
	}
}

int main() {
	int t; scanf("%d", &t);
	while (t--) {
		testCase();
	}
	return 0;
}
