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
{   // 숫자의 개수 n과 
	// n개의 정수를 입력받아서 그 합을 출력하는 프로그램

	int n;
	int data[MAX_N];

	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &data[i]);
	}

	//배열에서 가장 빈도수가 높은 정수를 반환하는 함수가 있다고 가정하자
	int answer = get_frequent_number(data, n);

	//그렇다면 우리가 할 일은 출력 뿐이다 
	printf("%04d\n", answer);

	return 0;
}