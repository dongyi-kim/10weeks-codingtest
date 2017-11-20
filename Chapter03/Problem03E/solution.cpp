#include<cstdio>

const int MAX_SIZE = 100;

int get_area(int board[][MAX_SIZE])
{
	int area = 0;
	
	for (int r = 0; r < MAX_SIZE; r++)
	{
		for (int c = 0; c < MAX_SIZE; c++)
		{
			if (board[r][c] >= 1)
			{
				area += 1;
			}
		}
	}
	return area;
}


void test_case(int caseIndex)
{
	int n;
	scanf("%d", &n);


	int board[MAX_SIZE][MAX_SIZE];

	for (int r = 0; r < MAX_SIZE; r++) {
		for (int c = 0; c < MAX_SIZE; c++) {
			board[r][c] = 0;
		}
	}

	for (int i = 0; i < n; i++)
	{
		int sr, sc;
		scanf("%d %d", &sc, &sr);
		for (int dr = 0; dr < 10; dr++) 
		{
			for (int dc = 0; dc < 10; dc++) 
			{
				board[sr + dr][sc + dc] += 1;
			}
		}
	}

	int answer = get_area(board);
	
	printf("%d\n", answer);
}
int main()
{   
	int caseNum;
	scanf("%d", &caseNum);

	for (int caseIndex = 0; caseIndex <caseNum; caseIndex++)
	{   
		test_case(caseIndex);
	}

	return 0;
}