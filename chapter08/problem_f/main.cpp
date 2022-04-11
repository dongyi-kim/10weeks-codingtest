#include <iostream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

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

int getShortestPathLength(int originR, int originC, int destR, int destC, int R, int C, const vector<vector<bool> >& passable) {
	vector<vector<bool> > visited(R + 2, vector<bool>(C + 2, false));
	vector<vector<int> > distance(R + 2, vector<int>(C + 2, 0));

	queue<State> bfsQueue;
	State initialState(originR, originC, 1);
	bfsQueue.push(initialState);

	while (!bfsQueue.empty()) {
		State current = bfsQueue.front();
		bfsQueue.pop();

		if (current.row < 1 || current.col < 1 || current.row > R || current.col > C) {
			continue;
		} else if (visited[current.row][current.col] == true || passable[current.row][current.col] == false) {
			continue;
		}

		visited[current.row][current.col] = true;
		distance[current.row][current.col] = current.depth - 1;

		bfsQueue.push(State(current.row + 1, current.col, current.depth + 1));
		bfsQueue.push(State(current.row - 1, current.col, current.depth + 1));
		bfsQueue.push(State(current.row, current.col + 1, current.depth + 1));
		bfsQueue.push(State(current.row, current.col - 1, current.depth + 1));
	}

	if(visited[destR][destC] == false){
		return -1;
	}
	return distance[destR][destC];
}

int main() {
	int R, C;
	cin >> R >> C;

	int originR = -1;
	int originC = -1;
	int destR = -1;
	int destC = -1;

	vector<vector<bool> > passable(R + 2, vector<bool>(C + 2, false));

	for (int i = 1; i <= R; i += 1) {
		string str;
		cin >> str;
		for (int j = 1; j <= C; j += 1) {
			char c = str[j - 1];
			if (c != '#') {
				passable[i][j] = true;
			}
			if (c == 'S') {
				originR = i;
				originC = j;
			} else if (c == 'E') {
				destR = i;
				destC = j;
			}
		}
	}

	int answer = getShortestPathLength(originR, originC, destR, destC, R, C, passable);

	cout << answer << endl;
}
