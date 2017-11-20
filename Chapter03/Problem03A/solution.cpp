#include<cstdio>

const int MAX_N = 100000;

void make_table(int arr[], int n, int table[])
{

	for (int number = 0; number <= 9999; number++)
	{
		table[number] = 0;
	}

	for (int i = 0; i < n; i++)
	{   
		int number = arr[i];

		table[number] ++;
	}
}



int get_frequent_number(int arr[], int n)
{
	int table[10000];
	int answer = arr[0];
	int frequency = 0;

	make_table(arr, n, table);

	for (int number = 0; number <= 9999; number++)
	{
		int count = table[number];

		if (count > frequency)
		{
			answer = number;
			frequency = count;
		}
	}

	return answer;
}


int main()
{   
	int n;
	int data[MAX_N];

	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &data[i]);
	}

	int answer = get_frequent_number(data, n);
	
	printf("%04d\n", answer);

	return 0;
}