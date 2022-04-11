#include <cstdio>
#include <vector>
#include <stack>
#include <queue>

using namespace std;

class State {
public:
	int nodeIndex;     // 현재 정점의 수 
	int depth;         // 탐색의 깊이 

	State(int nodeIndex, int depth){
		this->nodeIndex = nodeIndex;
		this->depth = depth;
	}
};


/**
 * @param N     그래프의 정점의 수 
 * @param adj   2차원 인접 행렬
 * @return      깊이 우선 탐색으로 탐색된 노드들의 번호 리스트  
 */
vector<int> getDfsOrder(int N, const vector<vector<bool> >& adj) {
	vector<int> visitedNodes;

	stack<State> dfsStack;
	State initialState(1, 1);
	dfsStack.push(initialState);

	vector<bool> visited(N + 1, false);
	while(dfsStack.empty() == false){
		State current = dfsStack.top();
		dfsStack.pop();

		if(visited[current.nodeIndex] == true){
			continue;
		}

		visited[current.nodeIndex] = true;
		visitedNodes.push_back(current.nodeIndex);

		for(int next = N; next >= 1; next -= 1){
			if(adj[current.nodeIndex][next] == true && visited[next] == false){
				State nextState(next, current.depth + 1);
				dfsStack.push(nextState);
			}
		}
	}

	return visitedNodes;
}

/**
 * @param N     그래프의 정점의 수 
 * @param adj   2차원 인접 행렬
 * @return      너비 우선 탐색으로 탐색된 노드들의 번호 리스트  
 */
vector<int> getBfsOrder(int N, const vector<vector<bool> >& adj) {
	vector<int> visitedNodes;

	queue<State> bfsQueue;
	State initialState(1, 1);
	bfsQueue.push(initialState);

	vector<bool> visited(N + 1, false);
	while(bfsQueue.empty() == false){
		State current = bfsQueue.front();
		bfsQueue.pop();

		if(visited[current.nodeIndex] == true){
			continue;
		}

		visited[current.nodeIndex] = true;
		visitedNodes.push_back(current.nodeIndex);

		for(int next = 1; next <= N; next += 1){
			if(adj[current.nodeIndex][next] == true && visited[next] == false){
				State nextState(next, current.depth + 1);
				bfsQueue.push(nextState);
			}
		}
	}

	return visitedNodes;
}

void printArrayList(const vector<int>& vi){
	for (int i = 0; i < vi.size(); i += 1) {
		if (i > 0) {
			printf("-");
		}
		printf("%d", vi[i]);
	}
	printf("\n");
}

int main() {
	int N, M;
	scanf("%d%d", &N, &M);
	vector<vector<bool> > adj(N + 1, vector<bool>(M + 1, false));

	for (int i = 0; i < M; i += 1) {
		int u, v;
		scanf("%d%d", &u, &v);
		adj[u][v] = adj[v][u] = true;
	}

	vector<int> dfsOrders = getDfsOrder(N, adj);
	vector<int> bfsOrders = getBfsOrder(N, adj);

	printArrayList(dfsOrders);
	printArrayList(bfsOrders);
}
