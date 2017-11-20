#include <cstdio>
const int MAX_N = 1000;

int get_minimum_index(int data[], int begin, int end)
{	
	int answer = begin;

	for (int i = begin; i <= end; i++)
	{
		if (data[i] < data[answer])
		{
			answer = i;
		}
	}

	return answer;
}

void selection_sort(int data[], int length)
{  
	int n = length;
	for (int i = 0; i < n; i++)
	{ 


		int min_index = get_minimum_index(data, i, length - 1);


		int temp = data[i];
		data[i] = data[min_index];
		data[min_index] = temp;
	}
}

int main() {
	int n;
	int data[MAX_N];
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}
	selection_sort(data, n);
	for (int i = 0; i < n; i++)
	{
		if (i > 0)
		{	
			printf(" ");
		}
		printf("%d", data[i]);
	}
	return 0;
}
