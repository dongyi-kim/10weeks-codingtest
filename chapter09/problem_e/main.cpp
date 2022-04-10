#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

class DisjointSet {
public:
	int size;
	vector<int> groupBoss;
	vector<int> groupSize;

	DisjointSet(int size) {
		this->size = size;
		this->groupBoss = vector<int>(size + 1);
		for (int nodeIndex = 1; nodeIndex <= size; nodeIndex += 1) {
			groupBoss[nodeIndex] = nodeIndex;
		}
	}

	int getGroupBoss(int u) {
		if (groupBoss[u] == u) {
			return u;
		} else {
			groupBoss[u] = getGroupBoss(groupBoss[u]);
			return groupBoss[u];
		}
	}

	void unionGroup(int u, int v) {
		int uBoss = getGroupBoss(u);
		int vBoss = getGroupBoss(v);
		if(uBoss == vBoss) {
			return;
		}
		groupBoss[vBoss] = uBoss;		
	}
};

class Edge {
public:
	int nodeU;
	int nodeV;
	int weight;

	Edge(int nodeU, int nodeV, int weight) {
		this->nodeU = nodeU;
		this->nodeV = nodeV;
		this->weight = weight;
	}

	bool operator < (const Edge& other) const {
		return this->weight < other.weight;
	}
};

vector<Edge> getMinimumSpanningTree(int V, int E, vector<Edge>& edges) {
	vector<Edge> spanningTree;

	// 빈 그래프 G에 대한 disjointSet을 선언한다
	DisjointSet disjointSet(V);

	// 간선들을 오름차순으로 정렬한다
	sort(edges.begin(), edges.end());

	for (int i = 0; i < E; i += 1) {
		const Edge& edge = edges[i];

		if(disjointSet.getGroupBoss(edge.nodeU) == disjointSet.getGroupBoss(edge.nodeV)) {
			// 이미 해당 두 정점이 연결성이 존재한다면 이 간선은 무시한다.
			continue;
		} else {
			// 그렇지 않다면 이 간선을 그래프 G에 추가한다.
			spanningTree.push_back(edge);
			disjointSet.unionGroup(edge.nodeU, edge.nodeV);
		}
	}

	return spanningTree;
}

int main() {
	int V, E;
	scanf("%d%d", &V, &E);

	vector<Edge> edges;
	for(int i = 0 ; i < E; i += 1){
		int u, v, w;
		scanf("%d%d%d", &u, &v, &w);
		edges.push_back(Edge(u, v, w));
	}

	vector<Edge> spanningTree = getMinimumSpanningTree(V, E, edges);

	long long weightSum = 0;
	for (int i = 0; i < spanningTree.size(); i += 1) {
		weightSum += spanningTree[i].weight;
	}

	printf("%lld\n", weightSum);
}
