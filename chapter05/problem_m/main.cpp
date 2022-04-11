#include<bits/stdc++.h>
using namespace std;

class DualPriorityQueue {
private:
	//데이터를 저장 할 class
	class Element {
	public:
		int value;
		bool deleted;
		Element(int _value) {
			value = _value;
			deleted = false;
		}
	};

	//priority queue 에서 사용할 min, max queue의 operator class
	class CmpMin {
	public:
		bool operator()(Element *a, Element *b) {
			return a->value > b->value;
		}
	};
	class CmpMax {
	public:
		bool operator()(Element *a, Element *b) {
			return a->value < b->value;
		}
	};

	priority_queue<Element*, vector<Element*>, CmpMax>maxHeap;
	priority_queue<Element*, vector<Element*>, CmpMin>minHeap;
	int aliveElements;

public:

	//생성자
	DualPriorityQueue() {
		aliveElements = 0;
	}

	//남은 원소의 수
	int size() {
		return aliveElements;
	}

	//데이터 추가 메소드
	void push(int value) {
		Element *newElement = new Element(value);
		minHeap.push(newElement);
		maxHeap.push(newElement);
		aliveElements += 1;
	}

	//저장된 원소 중 최솟값
	int getMinimum() {
		while (minHeap.size() > 0 && minHeap.top()->deleted == true) {
			Element *Temp = minHeap.top();
			minHeap.pop();
			delete Temp;
		}
		if (minHeap.size() > 0) {
			return minHeap.top()->value;
		}
	}

	//저장된 원소 중 최댓값
	int getMaximum() {
		while (maxHeap.size() > 0 && maxHeap.top()->deleted == true) {
			Element *Temp = maxHeap.top();
			maxHeap.pop();
			delete Temp;
		}
		if (maxHeap.size() > 0) {
			return maxHeap.top()->value;
		}
	}

	//최솟값 삭제
	void popMin() {
		while (minHeap.size() > 0 && minHeap.top()->deleted == true) {
			Element *Temp = minHeap.top();
			minHeap.pop();
			delete Temp;
		}
		if (minHeap.size() > 0) {
			minHeap.top()->deleted = true;
			minHeap.pop();
			aliveElements -= 1;
		}
	}

	//최댓값 삭제
	void popMax() {
		while (maxHeap.size() > 0 && maxHeap.top()->deleted == true) {
			Element *Temp = maxHeap.top();
			maxHeap.pop();
			delete Temp;
		}
		if (maxHeap.size() > 0) {
			maxHeap.top()->deleted = true;
			maxHeap.pop();
			aliveElements -= 1;
		}
	}

	//소멸자 (queue안의 원소는 동적할당으로 생성했으므로 소멸자를 통해 할당 해줘야함, pop연산에서 할당 해제를 함)
	~DualPriorityQueue() {
		while (minHeap.size() > 0) {
			popMin();
		}
		while (maxHeap.size() > 0) {
			popMax();
		}
	}
};

void testCase(int coseIdx) {
	int n; scanf("%d", &n);
	DualPriorityQueue queue;
	for (int i = 0; i < n; i += 1) {
		char c; int x;
		scanf(" %c %d", &c, &x);
		if (c == 'I') {
			queue.push(x);
		}
		else if (c == 'D') {
			if (queue.size() > 0) {
				if (x == 1) {
					queue.popMax();
				}
				else if (x == -1) {
					queue.popMin();
				}
			}
		}
	}
	if (queue.size() == 0) {
		printf("EMPTY\n");
	}
	else {
		int minimum = queue.getMinimum();
		int maximum = queue.getMaximum();
		printf("%d %d\n", maximum, minimum);
	}
}

int main() {
	int caseSize; scanf("%d", &caseSize);
	for (int caseIdx = 1; caseIdx <= caseSize; caseIdx++) {
		testCase(caseIdx);
	}
	return 0;
}
