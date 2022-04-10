#include<stdio.h>
#include<vector>
#include<queue>
#include<functional>
using namespace std;

template<typename T>
class MidianQueue {
private:
	priority_queue<T>maxQ;
	priority_queue<T, vector<T>, greater<T>>minQ;

	void balancing() {
		while (minQ.size() > maxQ.size()) {
			maxQ.push(minQ.top());
			minQ.pop();
		}
		while (minQ.size() + 1 < maxQ.size()) {
			minQ.push(maxQ.top());
			maxQ.pop();
		}
	}
public:
	size_t size() {
		return maxQ.size() + minQ.size();
	}
	bool empty() {
		return size() == 0;
	}
	void push(T value) {
		if (maxQ.empty() == false && maxQ.top() >= value) {
			maxQ.push(value);
		}
		else {
			minQ.push(value);
		}
		balancing();
	}
	void pop() {
		maxQ.pop();
		balancing();
	}
	T findMidVal() {
		T retVal = maxQ.top();
		return retVal;
	}
};

int main() {
	int n; scanf("%d", &n);
	MidianQueue<int>queue;
	for (int i = 0; i < n; i++) {
		char c; scanf(" %c", &c);
		if (c == 'I') {
			int x; scanf("%d", &x);
			queue.push(x);
			printf("%d\n", queue.findMidVal());
		}
		else if (c == 'P') {
			if (queue.empty() == false) {
				queue.pop();
			}
			if (queue.empty() == false) {
				printf("%d\n", queue.findMidVal());
			}
			else {
				printf("ERROR\n");
			}
		}
		else if (c == 'S') {
			printf("%d\n", queue.size());
		}
	}
	return 0;
}
