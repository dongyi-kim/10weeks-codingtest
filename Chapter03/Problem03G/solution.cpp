#include <stdio.h>

const int MAX_N = 200000;
const int MAX_BIRTHDAY = 991231;

int data[MAX_N];
int birthCount[MAX_BIRTHDAY];

int main()
{
	int n, k;	
	int answer = 0;	
	int uniqueDates = 0;	

	scanf("%d %d", &n, &k);
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}


	for (int birth = 000000; birth <= MAX_BIRTHDAY; birth++)
	{
		birthCount[birth] = 0;
	}


	for (int i = 0; i < n; i++)
	{
		int right = i;	
		int left = right - k + 1; 
		int removed = left - 1;	
		int added = right;	

		int newBirth = data[added];

		birthCount[newBirth] += 1;

		if (birthCount[newBirth] == 1)
		{
			uniqueDates += 1;
		}
		else if (birthCount[newBirth] == 2)
		{	
			uniqueDates -= 1;
		}


		if (removed >= 0)
		{	
			int birth = data[removed];
			birthCount[birth] -= 1;


			if (birthCount[birth] == 0)
			{
				uniqueDates -= 1;
			}
			else if (birthCount[birth] == 1)
			{	
				uniqueDates += 1;
			}
		}

		if (left >= 0 && uniqueDates == k)
		{
			answer += 1;
		}
	}

	printf("%d\n", answer);
	return 0;
}
