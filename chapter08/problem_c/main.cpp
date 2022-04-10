#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;


int getLongestPathLength(int current, int goal, int depth, const vector<vector<int> >& adj, vector<bool>& visited) {
	if(visited[current] == true || current == goal) {
		return 0;
	}

	int maxLength = 0;

	visited[current] = true;
	for (int i = 0; i < adj[current].size(); i += 1) {
		int nextNode = adj[current][i];

		int length = 1 + getLongestPathLength(nextNode, goal, depth + 1, adj, visited);
		maxLength = max(maxLength , length);
	}
	visited[current] = false;

	return maxLength;
}

/**
 * 두 노드 current부터 goal까지의 최장 경로의 길이를 반환하는 함수
 *
 * @param current
 * @param goal
 * @param adj
 * @return
 */
int getLongestPathLength(int current, int goal, const vector<vector<int> >& adj) {
	int N = adj.size();

	vector<bool> visited(N + 1, false);

	return getLongestPathLength(current, goal, 1, adj, visited);
}

int main() {
	int N, M;
	scanf("%d%d", &N, &M);
	vector<vector<int> > adj(N + 1);

	int origin, dest;
	scanf("%d%d", &origin, &dest);

	for(int i = 0 ; i < M; i += 1){
		int u, v;
		scanf("%d%d", &u, &v);
		adj[u].push_back(v);
		adj[v].push_back(u);
	}

	int answer = getLongestPathLength(origin, dest, adj);
	printf("%d\n", answer);
}
