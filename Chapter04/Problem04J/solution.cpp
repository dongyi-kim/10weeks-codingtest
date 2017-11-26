#include<cstdio>
#include<vector>
using namespace std;

const int MAX_N = 1000000;

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

	if (n > 1) {
		answer.push_back(n);
	}
	return answer;
}


int cnt[MAX_N + 1];

int main()
{
	int n;
	scanf("%d", &n);

	for (int i = 0; i <= MAX_N; i += 1)
	{
		cnt[i] = 0;
	}

	for (int i = 0; i < n; i++)
	{
		int card;
		scanf("%d", &card);

		vector<int> factors = factorize(card);
		for (int j = 0; j < factors.size(); j += 1)
		{
			int factor = factors[j];
			cnt[factor] += 1;
		}
	}

	int answer = 1;

	for (int factor = 1; factor <= MAX_N; factor += 1)
	{
		int commonPower = (cnt[factor] / n);
		for (int i = 0; i < commonPower; i++)
		{
			answer *= factor;
		}
	}

	printf("%d\n", answer);

	return 0;
}