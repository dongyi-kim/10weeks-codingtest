#include<cstdio>
#include<iostream>

using namespace std;

/**
 * 게임의 규칙에 따라 현무가 승리할 수 있는 경우의 수가 존재하는지 검사하는 함수  
 *
 * @param data 
 * @param n 
 * @param k 
 * @return true   현무가 승리할 수 있는 경우의 수가 하나 이상 존재한다면
 * @return false  else
 */
bool isWinnable(int data[], int n, int k) {

	int winCount = 0;
	long sum = 0;

	// 첫 (k-1)개의 원소에 대한 합을 계산한다.
	for (int i = 0; i < k - 1; i++)
	{
		sum += data[i];
	}

	for (int i = 0; i + k - 1 < n; i++)
	{   //영역의 왼쪽 끝 인덱스 i에 대해

		if (i > 0)
		{   //영역을 벗어나게 되는 원소 제외
			sum -= data[i - 1];
		}

		//새로 영역에 들어온 원소 추가
		sum = sum + data[i + k - 1];

		if (sum % 2 == 0)
		{
			winCount += 1;

			//승리 가능한 지 여부만 중요하므로 탈출
			break;
		}
	}

	if (winCount > 0)
	{
		return true;
	}
	else{
		return false;
	}
}

int main()
{
	int n, k;
	int* data;

	scanf("%d %d", &n, &k);
	data = new int[n];
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}

	bool result = isWinnable(data, n, k);

	if (result)
	{
		printf("YES");
	}else{
		printf("NO");
	}

	delete[] data;
	return 0;
}