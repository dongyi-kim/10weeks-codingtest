#include <cstdio>
#include <vector>

using namespace std;

class Graph{
public:
	vector<vector<bool> > adj;
	int V;

	Graph(int V) {
		this->adj = vector<vector<bool> >(V + 1, vector<bool>(V + 1, false));
		this->V = V;
	}

	void addEdge(int u, int v){
		adj[u][v] = adj[v][u] = true;
	}

	bool hasEdge(int u, int v){
		return adj[u][v];
	}
};

bool hasHamiltonPath(int depth, int currentNode, vector<bool>& visited, const Graph& graph) {
	if(visited[currentNode]) {
		return false;
	}
	visited[currentNode] = true;

	bool hasPath = false;
	if(depth == graph.V) {
		hasPath = true;
	} else {
		for(int nextNode = 1; nextNode <= graph.V; nextNode += 1) {
			hasPath = hasHamiltonPath(depth + 1, nextNode, visited, graph);
			if(hasPath) {
				break;
			}
		}
	}

	visited[currentNode] = false;

	return hasPath;
}


bool hasHamiltonPath(const Graph& graph) {
	vector<bool> visited(graph.V + 1, false);
	for(int originNode = 1; originNode <= graph.V; originNode += 1) {
		if(hasHamiltonPath(1, originNode, visited, graph)){
			return true;
		}
	}
	return false;
}

int main() {
	int V, E;
	scanf("%d%d", &V, &E);

	Graph graph(V);
	for(int i = 0 ; i < E ; i += 1){
		int u, v;
		scanf("%d%d", &u, &v);
		graph.addEdge(u, v);
	}

	if(hasHamiltonPath(graph)) {
		puts("YES");
	} else {
		puts("NO");
	}
}
