#include<cstdio>
#include<algorithm>
#include<iostream>
#include<vector>

using namespace std;

const int INFINITE = 123456789;

int dr[4] = { -1, 1, 0, 0 };
int dc[4] = { 0, 0, -1, 1 };

int ABS(int x)
{
	if (x >= 0)
		return x;
	return -x;
}

class Segment
{
public:
	int sx, sy;
	int fx, fy;
	Segment(int sx, int sy, int fx, int fy)
	{
		this->sx = sx;
		this->sy = sy;
		this->fx = fx;
		this->fy = fy;
	}

	int length()
	{
		return ABS(fx - sx) + ABS(fy - sy);
	}

	int getLeftX()
	{
		return min(sx, fx);
	}

	int getRightX()
	{
		return max(sx, fx);
	}

	int getTopY()
	{
		return max(sy, fy);
	}

	int getBotY()
	{
		return min(sy, fy);
	}
};

bool isCross(Segment &a, Segment &b)
{

	int left_cross = max(a.getLeftX(), b.getLeftX());
	int right_cross = min(a.getRightX(), b.getRightX());
	int top_cross = min(a.getTopY(), b.getTopY());
	int bot_cross = max(a.getBotY(), b.getBotY());

	int width = right_cross - left_cross;
	int height = top_cross - bot_cross;

	if (width >= 0 && height >= 0)
	{
		return true;
	}
	return false;
}


void testCase(int caseIndex)
{
	int n, k;

	scanf("%d %d", &n, &k);

	int nowR, nowC;
	scanf("%d %d", &nowR, &nowC);

	vector<Segment> segments;

	Segment topBorder = Segment(0, 0, 0, n + 1);
	Segment botBorder = Segment(n + 1, 0, n + 1, n + 1);
	Segment leftBorder = Segment(0, 0, n + 1, 0);
	Segment rightBorder = Segment(0, n + 1, n + 1, n + 1);
	Segment startingPoint = Segment(nowC, nowR, nowC, nowR);

	segments.push_back(topBorder);
	segments.push_back(botBorder);
	segments.push_back(leftBorder);
	segments.push_back(rightBorder);
	segments.push_back(startingPoint);

	long long lastDir = -1;
	long long steps = 1;
	bool turnOn = true;
	for (int i = 0; i < k; i++)
	{
		int dir, len;
		scanf("%d %d", &dir, &len);
		dir -= 1;

		if ((dir == 1 && lastDir == 0) || (dir == 0 && lastDir == 1) || (dir == 2 && lastDir == 3) || (dir == 3 && lastDir == 2))
		{
			turnOn = false;
		}

		if (turnOn == false)
		{
			continue;
		}
		int nextR = nowR + dr[dir] * len;
		int nextC = nowC + dc[dir] * len;

		Segment move(nowC , nowR, nextC, nextR);
		bool able = true;

		for (int j = 0; j < (int)segments.size() - 1; j++)
		{
			if (isCross(segments[j], move) == true)
			{
				able = false;
				break;
			}
		}


		if (able)
		{
			nowR = nextR;
			nowC = nextC;
			steps += move.length();
			segments.push_back(move);
			lastDir = dir;
			continue;
		}

		int leftEnd = INFINITE;
		int rightEnd = -INFINITE;
		int botEnd = INFINITE;
		int topEnd = -INFINITE;

		for (int j = 0; j < (int) segments.size() - 1; j++)
		{
			if (isCross(segments[j], move) == false)
			{
				continue;
			}

			leftEnd = min(leftEnd, segments[j].getLeftX());
			rightEnd = max(rightEnd, segments[j].getRightX());

			botEnd = min(botEnd, segments[j].getBotY());
			topEnd = max(topEnd, segments[j].getTopY());
		}


		long long movedLength = 0;
		if (dir == 0 || dir == 1 )
		{
			int toBot = ABS(move.sy - botEnd);
			int toTop = ABS(move.sy - topEnd);
			movedLength = min(toBot, toTop);
		}
		else {
			int toLeft = ABS(move.sx - leftEnd);
			int toRight = ABS(move.sx - rightEnd);
			movedLength = min(toLeft, toRight);
		}

		steps += movedLength -1 ;
		turnOn = false;
	}


	printf("%lld\n", steps);
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