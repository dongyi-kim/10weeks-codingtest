#include<cstdio>
#include<iostream>
using namespace std;

const int MAX_N = 100000;

int main()
{
	int n;
	int data[MAX_N];

	scanf("%d", &n);
	for (int i = 0; i< n; i++)
	{
		scanf("%d", &data[i]);
	}

	int count = 1; 

	for (int i = 1; i < n; i++)
	{	
		if (data[i - 1] != data[i])
		{	
			count++;
		}
	}

	printf("%d\n", count);

	return 0;
}