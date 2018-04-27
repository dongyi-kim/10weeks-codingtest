#include <cstdio>
#include <vector>

using namespace std;

/**
 * 자연수 N을 구성하는 모든 소인수를 반환하는 함수
 *
 * @param N
 * @return
 */
vector<long long> factorize(long long n) {
	vector<long long> factors;

	for(long long div = 2; div * div <= n; div += 1) { // sqrt(N)이하의 자연수 div에 대해서
		while(n % div == 0) {   
			// div이 N의 약수라면?
			// 이 때, div보다 작은 약수는 모두 처리되었으므로 div은 소인수임이 보장된다
			
			// 소인수 목록에 div을 추가한다.
			factors.push_back(div);
			
			// N에서 div을 소거해준다. 
			// 그러므로 이후에는 div을 제외한 소인수를 찾게 된다.
			n /= div;
		}
	}
	if(n > 1) {   
		// 소인수를 찾지 못하고 남아있는 N이 존재한다면, 소수일 수 밖에 없다. 
		// 이를 소인수에 추가한다.
		factors.push_back(n);
	}

	return factors;
}

void process(int caseIndex) {
	long long n;
	scanf("%lld", &n);

	vector<long long> factors = factorize(n);

	printf("#%d:\n", caseIndex);
	for (int i = 0; i < factors.size(); ++i) {
		if (i > 0) {
			printf(" ");
		}
		printf("%lld", factors[i]);
	}
	printf("\n");
}

int main() {
	int caseSize;
	scanf("%d", &caseSize);

	for (int caseIndex = 1; caseIndex <= caseSize; ++caseIndex) {
		process(caseIndex);
	}
}