#include<cstdio>
#include<iostream>

using namespace std;

/**
* 중복을 제외한 숫자의 종류의 수를 계산하는 함수
* @param data  원본 배열
* @param n     원본 배열의 크기
* @return  숫자의 종류의 수
*/
int getElementTypeCount(int data[], int n)
{
	int countType = 0;
	for (int i = 0; i < n; i++)
	{
		if (i == 0 || data[i - 1] != data[i])
		{
			countType += 1;
		}
	}

	return countType;
}

int main()
{
	int n;
	int* data;

	scanf("%d", &n);
	data = new int[n];

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}

	int answer = getElementTypeCount(data, n);

	printf("%d\n", answer);

	delete[] data;
	return 0;
}