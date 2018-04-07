#include<stdio.h>
#include<string>
#include<iostream>

using namespace std;

/**
* 배열에서 소속이 "AJOU"인 첫 원소와 마지막 원소를 출력하는 함수
* @param school 각 사람들의 소속학교 정보 배열
* @param n      사람들의 수
*/
void printIndexes(string school[], int n)
{
	int first = -1; //존재하지 않으면 -1
	int last = -1;  //존재하지 않으면 -1

	for (int i = 0; i < n; i++)
	{
		if (school[i] == "AJOU")
		{
			if (first == -1)
			{
				first = i + 1;
			}

			last = i + 1;
		}
	}

	printf("%d %d\n", first, last);
}

int main()
{
	int n;
	char buff[11];
	string* school;

	scanf("%d", &n);
	school = new string[n];

	for (int i = 0; i < n; i++)
	{
		scanf("%s", buff);
		school[i] = buff;
	}

	printIndexes(school, n);

	delete[] school;
	return 0;
}