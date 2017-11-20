#include<cstdio>

const int MAX_N = 1000;

void bubble_sort(int arr[], int length)
{	
	for (int i = 0; i < length; i++)
	{   
		int back = length - i - 1; 
		int changed = 0;            
		for (int j = 0; j < back; j++)
		{
			if (arr[j] > arr[j + 1])
			{   
				int temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;

				changed++;
			}
		}

		if (changed == 0)
		{
			break;
		}
	}
}

int main()
{
	int arr[MAX_N];
	int n;

	scanf("%d", &n);
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &arr[i]);
	}

	bubble_sort(arr, n);


	for (int i = 0; i < n; i++)
	{
		if (i > 0)
			printf(" ");
		printf("%d", arr[i]);
	}

	return 0;
}
