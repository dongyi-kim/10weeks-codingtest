#include<iostream>
#include<map>
using namespace std;

int main() {
	int n;
  cin >> n;
	map<int, int>M;
	for (int i = 1; i <= n; i++) {
		int x; cin >> x;
		M[x]++;
    cout << M.size() << ' ' << M[x] << endl;
	}
	return 0;
}
