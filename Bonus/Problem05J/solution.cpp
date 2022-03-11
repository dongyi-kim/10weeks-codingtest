#include<cstdio>

const int MAX_SIZE = 100;

void initTile(int tile[][MAX_SIZE + 2], int n)
{
	for (int r = 0; r <= n + 1; r++)
	{
		tile[r][0] = tile[r][n + 1] = 1;
	}
	for (int c = 0; c <= n + 1; c++)
	{
		tile[0][c] = tile[n + 1][c] = 1;
	}

	for (int r = 1; r <= n; r++)
	{
		for (int c = 1; c <= n; c++)
		{
			tile[r][c] = 0;
		}
	}
}

int dr[4] = { -1, 1, 0, 0 };
int dc[4] = { 0, 0, -1, 1 };

void testCase(int caseIndex)
{
	int n, k;
	int tile[MAX_SIZE + 2][MAX_SIZE + 2];

	scanf("%d %d", &n, &k);

	initTile(tile, n);


	int nowR, nowC;
	scanf("%d %d", &nowR, &nowC);

	tile[nowR][nowC] = 1;

	bool turnOn = true;
	int steps = 1;
	for (int i = 0; i < k; i++)
	{
		int dir;
		int len;
		scanf("%d %d", &dir, &len);

		//이미 로봇이 동작을 정지했다면 다음 명령들은 그냥 스킵한다
		if (turnOn == false)
		{
			continue;
		}

		dir -= 1;

		for (int j = 0; j < len; j++)
		{
			int nextR = nowR + dr[dir];
			int nextC = nowC + dc[dir];

			if (nextR < 1 || nextC < 1 || nextR > n || nextC > n || tile[nextR][nextC] != 0)
			{
				turnOn = false;
				break;
			}
			else
			{
				steps += 1;
				nowR = nextR;
				nowC = nextC;
				tile[nowR][nowC] = 1;
			}
		}
	}

	printf("%d\n", steps);
}

int main()
{
	int caseNum;
	scanf("%d", &caseNum);

	for (int caseIndex = 0; caseIndex < caseNum; caseIndex++)
	{
		testCase(caseIndex);
	}
	return 0;
}