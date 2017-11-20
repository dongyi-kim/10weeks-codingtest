#include<cstdio>
#include<algorithm>
#include<vector>

using namespace std;

const int MAX_N = 100000;

bool canMake(int arr[], int n, int s)
{
	for (int i = 0; i < n; i++)
	{
		int x = arr[i];
		int y = s - x;

		if (binary_search(arr, arr + n, y) == true)
		{
			return true;
		}
	}
	return false;
}

int main()
{
	int n, m;
	int data[MAX_N];

	scanf("%d %d", &n, &m);
	
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}

	sort(data, data + n);

	int answer = 0;
	
	for (int i = 0; i < m; i++)
	{
		int s;
		scanf("%d", &s);
		
		bool possible = canMake(data, n, s);

		if (possible)
		{
			answer += 1;
		}
	}

	printf("%d\n", answer);
	return 0;
}