#include<cstdio>
#include<cmath>
#include<iostream>

using namespace std;


/**
 * 평균과의 차가 가장 작은 데이터의 번호를 반환하는 함수
 * 여러 가지라면 가장 빠른 번호를 반환한다.
 *
 * @param data 
 * @param n 
 * @return int  가장 평균과 가까운 데이터의 번호 (1번부터 시작)
 */
int findIndex(int data[], int n)
{
	int x = 0; //존재하지 않는 경우는 없으므로, 일단 0이라고 가정
	int S = 0; //모든 데이터의 합

	for (int i = 0; i < n; i++)
	{
		S += data[i];
	}

	for (int i = 0; i < n; i++)
	{
		int dx = abs(n * data[x] - S); //i까지의 원소들 중 평균과의 최소거리
		int di = abs(n * data[i] - S); //현재 원소와 평균과의 거리
		if (di < dx)
		{
			x = i;
		}
	}

	return x + 1; //실제 번호는 1부터 시작하므로 증가
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

	int answer = findIndex(data, n);
	printf("%d\n", answer);

	delete[] data;
	return 0;
}