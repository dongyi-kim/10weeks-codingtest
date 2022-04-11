#include <cstdio>
#include <vector>
#include <queue>

using namespace std;

class State {
public:
	int nodeIndex;
	int depth;

	State(int nodeIndex, int depth) {
		this->nodeIndex = nodeIndex;
		this->depth = depth;
	}
};

/**
 * 그래프의 두 노드 org에서 dest로 이동하는 가장 짧은 경로의 길이를 반환하는 함수
 * 
 * @param org
 * @param dest
 * @param adj
 * @return
 */
int getShortestPathLength(int org, int dest, const vector<vector<int> >& adj) {
	int N = adj.size();

	vector<bool> visited(N + 1, false);
	vector<int> distance(N + 1, -1);

	State initialState(org, 1);
	queue<State> bfsQueue;
	bfsQueue.push(initialState);

	while (!bfsQueue.empty()) {
		State current = bfsQueue.front();
		bfsQueue.pop();

		if (visited[current.nodeIndex] == true) {
			continue;
		}

		visited[current.nodeIndex] = true;
		distance[current.nodeIndex] = current.depth - 1;

		for (int i = 0; i < adj[current.nodeIndex].size(); i += 1) {
			int next = adj[current.nodeIndex][i];	
			if (visited[next] == false) {
				State nextState(next, current.depth + 1);
				bfsQueue.push(nextState);
			}
		}
	}

	return distance[dest];
}

int main() {
	int N, M, origin, dest;
	scanf("%d%d%d%d", &N, &M, &origin, &dest);

	vector<vector<int> > adj(N + 1);

	for (int i = 0; i < M; i += 1) {
		int u, v;
		scanf("%d%d", &u, &v);
		adj[u].push_back(v);
		adj[v].push_back(u);
	}

	int answer = getShortestPathLength(origin, dest, adj);
	printf("%d\n", answer);
}
