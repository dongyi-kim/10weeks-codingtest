#include<vector>
#include<cstdio>

using namespace std;

vector<int> factorize(int n)
{
	vector<int> answer;

	for (long long a = 2; a * a <= n; a++)
	{
		while (n % a == 0)
		{
			answer.push_back(a);
			n /= a;
		}
	}

	if (n > 1){
		answer.push_back(n);
	}
	return answer;
}

void testCase(int caseIndex)
{
	int n;
	scanf("%d", &n);

	vector<int> answer = factorize(n);

	printf("#%d:\n", caseIndex + 1);
	for (int i = 0; i < answer.size(); i+= 1)
	{
		if (i > 0) {
			printf(" ");
		}
		printf("%d", answer[i]);
	}
	printf("\n");
}

int main()
{
	int caseNum;
	scanf("%d", &caseNum);

	for (int caseIndex = 0; caseIndex < caseNum; caseIndex+=1)
	{
		testCase(caseIndex);
	}
	return 0;
}