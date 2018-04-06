#include <cstdio>
#include <stdio.h>


using namespace std;

const int MAX_N = 10000;


int get_max(int data[], int length)
{
	int max_value = data[0];
	for (int i = 0; i < length; i++)
	{
		if (max_value > data[i])
		{
			max_value = data[i];
		}
	}

	return max_value;
}


int main() {
	int n;
	int data[MAX_N];

	scanf("%d", &n);

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}

	int answer = get_max(data, n);

	printf("%d\n", answer);
	return 0;
}
