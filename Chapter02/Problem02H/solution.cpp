#include<cstdio>


void testCase(int caseIndex)
{
	long long r;
	scanf("%lld", &r);

	long long top = r;
	long long totalPixels = 0;
	for(long long x = 0; x < r ; x ++)
	{
		while( x * x + top * top >= r * r )
		{
			top --;
		}

		long long height = top + 1;

		totalPixels += height;
	}

	printf("#%d\n", caseIndex + 1);
	printf("%lld\n", totalPixels * 4);
}

int main()
{
	int caseNum;
	scanf("%d", &caseNum);
	for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
	{
		testCase(caseIndex);
	}

	return 0;
}