#include<stdio.h>
#include<map>
using namespace std;

int main() {
	int n;scanf("%d", &n);
	map<int, int>M;
	for (int i = 1; i <= n; i++) {
		int x; scanf("%d", &x);
		M[x]++;
		printf("%d %d\n", M.size(), M[x]);
	}
	return 0;
}
