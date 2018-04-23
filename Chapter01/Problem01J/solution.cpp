#include<cstdio>
#include<iostream>

using namespace std;


/**
* 1부터 N까지의 자연수의 합을 계산하는 함수
* @param i
* @return
*/
int getRangeSumFromOne(int i) {
	int sum = 0;

	for (int j = 1; j <= i; j += 1)
	{
		sum += j;
	}

	return sum;
}


long long getAnswer(int N)
{
	long answer = 0;
	for (int i = 1; i <= N; i++)
	{
		int rangeSum = getRangeSumFromOne(i);
		answer += rangeSum;
	}

	return answer;
}

int main()
{
	int n;

	scanf("%d", &n);

	long long answer = getAnswer(n);
	
	printf("%lld\n", answer);

	return 0;
}