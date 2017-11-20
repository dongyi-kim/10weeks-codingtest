#include<cstdio>
#include<algorithm>
#include<vector>

using namespace std;

const int MAX_N = 100000;

bool canMake(const vector<int> &arr, int n, int s)
{
	for (int i = 0; i < n; i++)
	{
		int x = arr[i];
		int y = s - x;
			
		if (binary_search(arr.begin(), arr.end(), y) == true)
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

	vector<int> twoSums;
	for (int i = 0; i < n; i++)
	{
		for (int j = i; j < n; j++)
		{
			int twoSum = data[i] + data[j];
			twoSums.push_back(twoSum);
		}
	}

	sort(twoSums.begin(), twoSums.end());

	vector<int> answer;
	for (int i = 0; i < m; i++)
	{
		int s;
		scanf("%d", &s);
		
		bool possible = canMake(twoSums, (int)twoSums.size(), s);

		if (possible)
		{
			answer.push_back(s);
		}
	}

	sort(answer.begin(), answer.end());

	if (answer.size() == 0)
	{
		printf("NO\n");
	}
	else
	{
		for (int i = 0; i < answer.size(); i++)
		{
			if (i > 0)
			{
				printf(" ");
			}
			printf("%d", answer[i]);
		}
	}
	return 0;
}