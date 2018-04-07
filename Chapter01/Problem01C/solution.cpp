#include<stdio.h>
#include<iostream>

using namespace std;

/**
*
* @param data 각 사람들의 키를 저장한 배열
* @param n     사람들의 수
* @param m     미주의 키
* @param s     지수의 키
* @return      미주 혹은 지수와  키가 일치하는 사람의 수
*/
int getCount(int data[], int n, int m, int s)
{
	int count = 0; //확인해보아야 할 후보의 수
	for (int i = 0; i < n; i++)
	{
		if (data[i] == m || data[i] == s)
		{
			count += 1;
		}
	}
	return count;
}

int main()
{
	int n, m, s;
	int *data;

	scanf("%d %d %d", &n, &m, &s);
	data = new int[n];
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}

	int answer = getCount(data, n, m, s);

	printf("%d\n", answer);
	delete[] data;
	return 0;
}