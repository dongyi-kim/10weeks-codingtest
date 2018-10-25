#include<iostream>
#include<vector>
using namespace std;

vector<int> mergeArray(int n, int m, vector<int>&arrayA, vector<int>&arrayB) {
	int idxA = 0;
	int idxB = 0;
	vector<int>answer;

	while (idxA < n && idxB < m) {
		if (arrayA[idxA] < arrayB[idxB]) {
			answer.push_back(arrayA[idxA++]);
		}
		else {
			answer.push_back(arrayB[idxB++]);
		}
	}
	while (idxA < n) {
		answer.push_back(arrayA[idxA++]);
	}
	while (idxB < m) {
		answer.push_back(arrayB[idxB++]);
	}
	return answer;
}

int main() {
	int n, m;
	cin >> n >> m;
	vector<int>arrayA(n);
	vector<int>arrayB(m);
	for (int i = 0; i < n; i++) {
		cin >> arrayA[i];
	}
	for (int i = 0; i < m; i++) {
		cin >> arrayB[i];
	}
	vector<int>mergedArray = mergeArray(n, m, arrayA, arrayB);
	for (int i = 0; i < mergedArray.size(); i++) {
		if (i > 0) cout << ' ';
		cout << mergedArray[i];
	}
	cout << endl;
	return 0;
}
