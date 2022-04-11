#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

const int EMPTY = -1;
const int UNRIPE = 0;
const int RIPE = 1;

class State {
public:
	int row;
	int col;
	int depth;

	State(int row, int col, int depth) {
		this->row = row;
		this->col = col;
		this->depth = depth;
	}
};

int getMinimumRequiredDays(int R, int C, const vector<vector<int> >& tomato) {
	vector<vector<bool> > visited(R + 2, vector<bool>(C + 2, false));
	vector<vector<int> > distance(R + 2, vector<int>(C + 2, 0));

	queue<State> bfsQueue;
	for (int i = 1; i <= R; i += 1) {
		for (int j = 1; j <= C; j += 1) {
			if (tomato[i][j] == RIPE) {
				State state(i, j, 1);
				bfsQueue.push(state);
			}
		}
	}

	while (!bfsQueue.empty()) {
		State current = bfsQueue.front();
		bfsQueue.pop();

		if (current.row < 1 || current.col < 1 || current.row > R || current.col > C) {
			continue;
		} else if (visited[current.row][current.col] == true) {
			continue;
		}

		if (tomato[current.row][current.col] == EMPTY) {
			continue;
		}

		visited[current.row][current.col] = true;
		distance[current.row][current.col] = current.depth - 1;

		bfsQueue.push(State(current.row + 1, current.col, current.depth + 1));
		bfsQueue.push(State(current.row - 1, current.col, current.depth + 1));
		bfsQueue.push(State(current.row, current.col + 1, current.depth + 1));
		bfsQueue.push(State(current.row, current.col - 1, current.depth + 1));
	}

	int unripeCount = 0;
	int requiredDays = 0;
	for (int i = 1; i <= R; i += 1) {
		for (int j = 1; j <= C; j += 1) {
			if (tomato[i][j] == EMPTY) {
				continue;
			}
			if (visited[i][j] == false) {
				unripeCount += 1;
				break;
			} else { // tomato[i][j] == RIPE
				requiredDays = max(requiredDays, distance[i][j]);
			}
		}
	}

	if (unripeCount >= 1) {
		return -1;
	} else {
		return requiredDays;
	}
}

int main() {
	int C, R;
	scanf("%d%d", &C, &R);

	vector<vector<int> > tomato(R + 2, vector<int>(C + 2, EMPTY));
	for(int i = 1; i <= R; i += 1){
		for(int j = 1; j <= C ; j += 1){
			scanf("%d", &tomato[i][j]);
		}
	}

	int answer = getMinimumRequiredDays(R, C, tomato);
	printf("%d\n",answer);
}