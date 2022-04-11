#include <cstdio>
#include <vector>

using namespace std;

const int INF = 987654321;

class Graph{
public:
	vector<vector<int> > adj;
	int V;
	Graph(int V) {
		this->adj = vector<vector<int> >(V + 1, vector<int>(V + 1, 0));
		this->V = V;
	}

	void addEdge(int from, int to, int weight) {
		adj[from][to] = weight;
	}

	bool hasEdge(int from, int to) const {
		return adj[from][to] != 0;
	}

	int getDistance(int from, int to) const {
		if(hasEdge(from, to)) {
			return adj[from][to];
		} else {
			return INF;
		}
	}
};

int getShortestHamiltonCircuit(int currentNode, int depth, int totalLength, vector<int>& path, vector<bool>& used, const Graph& graph) {
	int origin = path[0];

	if(depth == graph.V) {
		if(graph.hasEdge(currentNode, origin)) {
			int pathLength = totalLength + graph.getDistance(currentNode, origin);
			return pathLength;
		} else {
			return INF;
		}
	}

	int minLength = INF;
	for(int nextNode = 1; nextNode <= graph.V; nextNode += 1) {
		if(graph.hasEdge(currentNode, nextNode) == false || used[nextNode]) {
			continue;
		}

		int nextLength = totalLength + graph.getDistance(currentNode, nextNode);
		if(nextLength >= minLength){
			continue;
		}

		path.push_back(nextNode);
		used[nextNode] = true;

		int length = getShortestHamiltonCircuit(nextNode, depth+1, nextLength, path, used, graph);
		minLength = min(length, minLength);

		path.pop_back();
		used[nextNode] = false;
	}

	return minLength;
}

int getShortestHamiltonCircuit(const Graph& graph) {
	int origin = 1;
	vector<int> path;
	vector<bool> used(graph.V);
	path.push_back(origin);
	used[origin] = true;
	int answer = getShortestHamiltonCircuit(origin, 1, 0, path, used, graph);
	return answer;
}

int main() {
	int V;
	scanf("%d", &V);

	Graph graph(V);
	for(int from = 1; from <= V; from += 1) {
		for(int to = 1; to <= V ; to += 1) {
			int weight;
			scanf("%d", &weight);
			graph.addEdge(from, to, weight);
		}
	}

	int answer = getShortestHamiltonCircuit(graph);

	if(answer == INF) {
		puts("NO PATH");
	} else {
		printf("%d\n", answer);
	}
}
