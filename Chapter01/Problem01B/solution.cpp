#include<stdio.h>
#include<iostream>

using namespace std;

/**
* 배열의 최대값을 계산하는 함수.
*
* @param data
* @param n
* @return data[0] ~ data[n-1]중 최대값.
*/
int getMax(int data[], int n)
{
	int answer = data[0];

	for (int i = 0; i < n; i++)
	{
		if (answer < data[i])
		{
			answer = data[i];
		}
	}

	return answer;
}

int main()
{
	int n;
	int *data;

	scanf("%d", &n);
	data = new int[n];
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}

	int answer = getMax(data, n);

	printf("%d\n", answer);
	delete[] data;
	return 0;
}