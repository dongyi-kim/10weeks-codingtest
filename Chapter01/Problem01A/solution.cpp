#include<stdio.h>
#include<iostream>

using namespace std;

/**
* 두 정수 a와 b중 더 큰 값을 반환하는 함수
*
* @param a
* @param b
* @return a와 b중 더 큰 값
*/
int getMax(int a, int b)
{
	if (a > b)
	{
		return a;
	}
	else{
		return b;
	}
}

int main()
{
	int p, q;
	
	scanf("%d %d", &p, q);
    
    int answer = getMax(p, q);

    printf("%d\n", answer);
	return 0;
}