#include<cstdio>
#include<iostream>

const int MAX_N = 100000;

using namespace std;

int get_rev(int arr[], int n)
{
	int cnt = 0;

	for (int i = 0; i + 1 < n; i++)
	{	
		if (arr[i] > arr[i + 1])
		{	
			cnt++;
		}
	}

	return cnt;
}

int main()
{
	int n;
	int data[MAX_N];

	scanf("%d %d %d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &data[i]);
	}

	int revcnt = get_rev(data, n);

	if (revcnt == 0)
	{	
		printf("YES\n");
	}
	else
	{	
		printf("NO\n");
	}
	return 0;
}