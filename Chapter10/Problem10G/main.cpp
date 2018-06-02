#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class LCS {
public:
	vector<vector<int> > memo;
	string strA, strB;

	LCS(string strA, string strB) {
		this->strA = strA;
		this->strB = strB;
		this->memo = vector<vector<int> >(strA.length(), vector<int>(strB.length(), -1));
	}

	/**
	 * 부분 문자열 A[0..lastIndexA]와 B[0..lastIndexB] 사이의 LCS길이
	 *
	 * @param lastIndexA 문자열 A의 마지막 문자 인덱스
	 * @param lastIndexB 문자열 B의 마지막 문자 인덱스
	 * @return 해당 부분 문자 열사이의 LCS길이
	 */
	int f(int lastIndexA, int lastIndexB) {
		if (lastIndexA < 0 || lastIndexB < 0) {
			// 둘 중 하나가 빈 문자열인 경우는 0
			return 0;
		} else if (memo[lastIndexA][lastIndexB] != -1) {
			// 이미 계산된 값인 경우
			return memo[lastIndexA][lastIndexB];
		}

		int caseA = f(lastIndexA - 1, lastIndexB);	// A[i]를 제외하고 계산한 LCS
		int caseB = f(lastIndexA, lastIndexB - 1);	// B[j]를 제외하고 계산한 LCS
		int caseC = f(lastIndexA - 1, lastIndexB - 1);	// A[i], B[j]를 모두 제외하고 계산한 LCS
		if (strA[lastIndexA] == strB[lastIndexB]) {
			caseC += 1;		// 만약 A[i], B[j]가 일치한다면 LCS길이 + 1
		}

		int answer = max(caseA, max(caseB, caseC));
		
		memo[lastIndexA][lastIndexB] = answer;
		return answer;
	}
};

int main() {
	string strA, strB;
	cin >> strA >> strB;

	LCS lcs(strA, strB);
	int answer = lcs.f(strA.length() - 1, strB.length() - 1);

	printf("%d\n", answer);
}
