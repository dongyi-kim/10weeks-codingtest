#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 1e9;

class Matrix {
public:
	int rowSize, columnSize;

	Matrix(int rowSize, int columnSize) {
		this->rowSize = rowSize;
		this->columnSize = columnSize;
	}
};

class Solution{
public:
	vector<Matrix> matrices;
	vector<vector<int> > memo;
	int n;

	Solution(vector<Matrix> matrices){
		this->matrices = matrices;
		this->memo = vector<vector<int> >(matrices.size(), vector<int>(matrices.size(), -1));
		this->n = matrices.size();
	}

	/**
	 * matrices[leftMost...rightMost] 범위의 모든 행렬을 곱할때 발생하는 최소의 곱하기 연산의 수
	 *
	 * @param leftMost		가장 왼쪽 끝 인덱스
	 * @param rightMost		가장 오른쪽 끝 인덱스
	 * @return
	 */
	int f(int leftMost, int rightMost){
		if(leftMost < 0 || rightMost >= n || leftMost > rightMost) {
			return INF;
		} else if(memo[leftMost][rightMost] != -1) {
			return memo[leftMost][rightMost];
		} else if(leftMost == rightMost){
			return 0;
		}

		int answer = INF;

		// [leftMost, mid] 와 [mid+1, rightMost]로 나누었을때
		for(int mid = leftMost; mid < rightMost; mid += 1){

			// 좌우 영역을 모두 곱하는데 소모되는 비용
			int leftCost =  f(leftMost, mid);
			int rightCost = f(mid + 1, rightMost);

			// 좌우 결과 행렬을 곱하는데 소요되는 비용
			int concatCost =  matrices[leftMost].rowSize * matrices[mid].columnSize * matrices[rightMost].columnSize;

			// 전체 비용
			int totalCost = leftCost + rightCost + concatCost;

			answer = min(answer, totalCost);
		}

		memo[leftMost][rightMost] = answer;
		return answer;
	}
};

/**
 * 배열으로 주어진 행렬이 모두 곱하기가 가능한지 판단하는 함수
 *
 * @param matrices
 * @return			모두 곱하기가 가능하다면 true, 그렇지 않다면 false를 반환한다
 */
bool isMultiplicable(vector<Matrix> matrices) {
	bool multiplicable = true;
	for(int i = 0; i + 1 < matrices.size(); i += 1){
		if(matrices[i].columnSize != matrices[i + 1].rowSize){
			multiplicable = false;
			break;
		}
	}
	return multiplicable;
}

void testCase(int caseIndex) {
	int n;
	scanf("%d", &n);
	vector<Matrix> matrices;

	for (int i = 0; i < n; i += 1) {
		int r, c;
		scanf("%d%d", &r, &c);
		matrices.push_back(Matrix(r, c));
	}


	bool multiplicable = isMultiplicable(matrices);
	if(!multiplicable) {
		puts("-1");
		return;
	}

	Solution solution(matrices);
	int ansnwer = solution.f(0, n - 1);
	printf("%d\n", ansnwer);
}

int main() {
	int caseNum;
	scanf("%d", &caseNum);

	for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
		testCase(caseIndex);
	}
}
