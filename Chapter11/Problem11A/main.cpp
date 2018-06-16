#include <cstdio>
#include <vector>

using namespace std;

/**
 * 총 M의 예산으로 최저 속도 하한선 달성 여부를 검사하는 함수
 * @param N				열차의 수
 * @param M				총 예산
 * @param minimumSpeed	속도 하한선
 * @param S				각 열차의 초기 속도
 * @param V				각 열차의 예산당 속도 증가량
 * @return				실현 가능 여부
 */
bool isPossible(int N, long M, long long minimumSpeed, const vector<long long>& S, const vector<long long>& V) {
	long long totalBudget = M;	// 총 예산

	for (int i = 0 ; i < N ; i += 1) { // 모든 열차에 대해
		if(minimumSpeed <= S[i]) { // 이미 하한 속도를 넘겼다면 건너뛴다.
			continue;
		}

		long long diff = minimumSpeed - S[i]; 		// 늘려야 할 속도 량
		long long budgetCost = (diff-1) / V[i] + 1;	// 그리고 그 때 필요한 비용

		totalBudget -= budgetCost;		// 해당 비용을 전체 예산에서 감산한다
	}

	return totalBudget >= 0;	// 예산 M이내로 모두 충당이 가능하면 true
}

/**
 * @brief 총 M의 예산을 투자하여 가장 느린 기차의 속도 최대값
 *
 * @param N  기차의 수
 * @param M  총 예산
 * @param S  S[i] := 각 열차의 초기 속력
 * @param V  V[i] := 각 열차의 단위 예산당 속도 증가량
 * @return
 */
long long getMaximumSpeed(int N, long M, const vector<long long>& S, const vector<long long>& V) {
	long long lowerBound = 0;				// 속도는 최소 0이므로
	long long upperBound = 200000000000L;	// 이론상 최대 속력

	while (lowerBound < upperBound){ // 범위가 하나로 정해질 때 까지
		long long minimumSpeed = (lowerBound + upperBound + 1) / 2; // 중간값 계산 후 테스트
		bool possible = isPossible(N, M, minimumSpeed, S, V);

		if (possible) {	 // 가능하다면, 그 이상의 속도로 범위를 좁힌다
			lowerBound = minimumSpeed;
		} else {			// 불가능하다면, 그 이하의 속도로 범위를 좁힌다.
			upperBound = minimumSpeed - 1;
		}
	}

	// 결과를 반환한다
	return lowerBound;
}

void testCase() {
	int N, M;
	scanf("%d%d", &N, &M);

	vector<long long> S(N), V(N);
	for (int i = 0; i < N; i += 1) {
		scanf("%lld", &S[i]);
	}
	for (int i = 0; i < N; i += 1) {
		scanf("%lld", &V[i]);
	}

	long long maximumSpeed = getMaximumSpeed(N, M, S, V);
	printf("%lld\n", maximumSpeed);
}

int main() {
	int caseNum;
	scanf("%d", &caseNum);
	for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
		testCase();
	}
}
