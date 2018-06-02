#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;


class Solution {
public:
	string brackets;
	vector<vector<int> > memo;
	int n;

	Solution(string str) {
		this->n = str.length();
		this->brackets = str;
		this->memo = vector<vector<int> >(this->n, vector<int>(this->n, -1));
	}

	/**
	 * 부분 문자열 brackets[leftMost...rightMost]에 대한 가장 긴 올바른 괄호 문자열의 길이 
	 * 
	 * @param leftMost		가장 왼쪽 인덱스
	 * @param rightMost		가장 오른쪽 인덱스
	 * @return				주어진 범위에 대한 가장 긴 올바른 괄호 문자열의 길이 
	 */
	int f(int leftMost, int rightMost) {
		if (leftMost < 0 || rightMost >= n || leftMost > rightMost) {
			return 0;
		} else if (memo[leftMost][rightMost] != -1) {
			return memo[leftMost][rightMost];
		} else if (leftMost == rightMost) {
			// 한 글자는 괄호 문자열이 될 수 없다 
			return 0;
		}

		int answer = 0;
		
		// 양쪽 끝이 짝이 맞는 경우, 해당 괄호짝이 감싼 경우를 고려한다 
		if (true == isPaired(leftMost, rightMost)) {
			answer = 2 + f(leftMost + 1, rightMost - 1);
		}

		// 어떤 한 지점으로부터 양쪽으로 분할된 경우를 고려한다.
		for (int mid = leftMost; mid < rightMost; mid += 1) {
			int leftLength = f(leftMost, mid);
			int rightLength = f(mid + 1, rightMost);
			int totalLength = leftLength + rightLength;
			answer = max(answer, totalLength);
		}

		memo[leftMost][rightMost] = answer;
		return answer;
	}

	bool isPaired(int leftIndex, int rightIndex) {
		if (brackets[leftIndex] == '(') {
			return brackets[rightIndex] == ')';
		} else if (brackets[leftIndex] == '[') {
			return brackets[rightIndex] == ']';
		}
		return false;
	}
};

void testCase(int caseIndex) {
	string str;
	cin >> str;
	int n = str.length();

	Solution solution(str);
	int answer = solution.f(0, n - 1);

	cout << answer << endl;
}

int main() {
	int caseNum;
	cin >> caseNum;
	for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
		testCase(caseIndex);
	}
}
