#include <cstdio>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>

using namespace std;

class State{
public:
	int nodeIndex;
	int depth;
	State(int nodeIndex, int depth){
		this->nodeIndex = nodeIndex;
		this->depth = depth;
	}
};

/**
 * @param N     그래프의 정점의 수
 * @param adj   각 노드들에 대한 인접 리스트의 배열
 * @return      깊이 우선 탐색으로 탐색된 노드들의 번호 리스트
 */
vector<int> getDfsOrder(int N, const vector<vector<int> >& adj) {
	vector<int> visitedNodes;

	stack<State> dfsStack;
	State initialState(1, 1);
	dfsStack.push(initialState);

	vector<bool> visited(N + 1);
	while(dfsStack.empty() == false) {
		State current = dfsStack.top();
		dfsStack.pop();

		if(visited[current.nodeIndex] == true) {
			continue;
		}

		visited[current.nodeIndex] = true;
		visitedNodes.push_back(current.nodeIndex);

		for(int ni = adj[current.nodeIndex].size() - 1 ; ni >= 0 ; ni -= 1) {
			int next = adj[current.nodeIndex][ni];
			if(visited[next] == false) {
				State nextState(next, current.depth + 1);
				dfsStack.push(nextState);
			}
		}
	}

	return visitedNodes;
}

/**
 * @param N     그래프의 정점의 수
 * @param adj   각 노드들에 대한 인접 리스트의 배열
 * @return      너비 우선 탐색으로 탐색된 노드들의 번호 리스트
 */
vector<int> getBfsOrder(int N, const vector<vector<int> >& adj) {
	vector<int> visitedNodes;

	queue<State> bfsQueue;
	State initialState(1, 1);
	bfsQueue.push(initialState);

	vector<bool> visited(N + 1);
	while(bfsQueue.empty() == false){
		State current = bfsQueue.front();
		bfsQueue.pop();

		if(visited[current.nodeIndex] == true) {
			continue;
		}

		visited[current.nodeIndex] = true;
		visitedNodes.push_back(current.nodeIndex);

		for(int ni = 0 ; ni < adj[current.nodeIndex].size() ; ni += 1) {
			int next = adj[current.nodeIndex][ni];
			if(visited[next] == false) {
				State nextState(next, current.depth + 1);
				bfsQueue.push(nextState);
			}
		}
	}

	return visitedNodes;
}

void printArrayList(const vector<int>& vi){
	for(int i = 0 ; i < vi.size(); i+= 1){
		if(i > 0) {
			printf("-");
		}
		printf("%d", vi[i]);
	}
	printf("\n");
}

int main() {
	int N, M;
	scanf("%d%d", &N, &M);
	vector<vector<int> > adj(N + 1);

	for(int i = 0 ; i < M; i += 1){
		int u, v;
		scanf("%d%d", &u, &v);
		adj[u].push_back(v);
		adj[v].push_back(u);
	}

	for (int i = 1; i <= N; i += 1) {
		sort(adj[i].begin(), adj[i].end());
	}

	vector<int> dfsOrders = getDfsOrder(N, adj);
	vector<int> bfsOrders = getBfsOrder(N, adj);

	printArrayList(dfsOrders);
	printArrayList(bfsOrders);
}
