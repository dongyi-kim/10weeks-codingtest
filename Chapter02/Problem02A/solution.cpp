#include<cstdio>
#include<iostream>

using namespace std;

/**
* 생일이 m월인 가장 큰 키의 도토리를 찾는 함수
* @param height  각 도토리의 키
* @param month   각 도토리의 출생 월
* @param n   도토리의 수
* @param m   찾고자 하는 달
* @return    month[k] == m인 가장 큰 height[k]
*/
int getMaximumHeight(int height[], int month[], int n, int m)
{
	int maxHeight = -1; //생일이 m월인 사람이 아무도 없다면 -1일 것이다.

	for (int i = n - 1; i >= 0; i -= 1)
	{   //모든 도토리의 출생 월 month[i]에 대해
		//키가 큰 도토리부터 고려하다가
		if (month[i] == m)
		{   // 생일이 일치하는 도토리가 등장했다! 저장하자
			maxHeight = height[i];

			//그 이후 (i가 작아지는 방향)에는 어차피 더 큰 키의 사람이 없다.
			//그러므로 더 수행할 필요가 없음이 자명하다.
			break;
		}
	}

	return maxHeight;
}

int main()
{
	int n, m;
	int* height;
	int* month;

	scanf("%d", &n);
	height = new int[n];
	month = new int[n];

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &height[i]);
	}

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &month[i]);
	}

	scanf("%d", &m);

	int answer = getMaximumHeight(height, month, n, m);

	printf("%d\n", answer);

	delete[] height;
	delete[] month;
	return 0;
}