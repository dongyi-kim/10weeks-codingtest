#include <cstdio>
#include <vector>
#include <queue>

using namespace std;

const int MAX_NUMBER = 10000;
vector<bool> isPrime;

class State {
public:
	int value;
	int depth;

	State(int value, int depth) {
		this->value = value;
		this->depth = depth;
	}
};

void fillSieve() {
	isPrime.assign(MAX_NUMBER, true);
	isPrime[0] = isPrime[1] = false;

	for (int p = 2; p <= MAX_NUMBER; p += 1) {
		if (isPrime[p] == false) {
			continue;
		}

		for(int mul = p * p ; mul < MAX_NUMBER; mul += p) {
			isPrime[mul] = false;
		}
	}
}


/**
 * "number"에서 한 글자를 변경해 만들 수 있는 모든 1000~9999사이의 소수를 반환해주는 함수 
 * 
 * @param number
 * @return
 */
vector<int> getAdjacentPrimeNumbers(int number){
	vector<int> adjacentPrimeNumbers;

	vector<int> orgDigits(4);
	orgDigits[0] = number / 1000;
	orgDigits[1] = (number % 1000) / 100;
	orgDigits[2] = (number % 100) / 10;
	orgDigits[3] = (number % 10);

	for(int pos = 0 ; pos < 4 ; pos += 1) {
		vector<int> newDigits = orgDigits;
		for(int digit = 0 ; digit <= 9; digit += 1) {
			newDigits[pos] = digit;

			int newInteger = newDigits[0] * 1000 + newDigits[1] * 100 + newDigits[2] * 10 + newDigits[3];
			if(1000 <= newInteger && newInteger <= 9999 && isPrime[newInteger] == true && newInteger != number){
				adjacentPrimeNumbers.push_back(newInteger);
			}
		}
	}

	return adjacentPrimeNumbers;
}


void testCase(int caseIndex) {
	int origin, dest;
	scanf("%d%d", &origin, &dest);

	vector<bool> visited(MAX_NUMBER, false);
	vector<int> distance(MAX_NUMBER, 0);

	queue<State> bfsQueue;
	State initialState(origin, 1);
	bfsQueue.push(initialState);

	while(!bfsQueue.empty()){
		State current = bfsQueue.front();
		bfsQueue.pop();

		if (visited[current.value] == true) {
			continue;
		}

		visited[current.value] = true;
		distance[current.value] = current.depth - 1;

		vector<int> nextIntegers = getAdjacentPrimeNumbers(current.value);
		for (int i = 0; i < nextIntegers.size(); i += 1) {
			int next = nextIntegers[i];
			if(visited[next] == false) {
				State nextState(next, current.depth + 1);
				bfsQueue.push(nextState);
			}
		}
	}

	if (visited[dest] == false) {
		puts("Impossible");
	} else {
		int answer = distance[dest];
		printf("%d\n", answer);
	}
}

int main() {
	int caseSize;
	scanf("%d", &caseSize);

	fillSieve();
	for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
		testCase(caseIndex);
	}
}
