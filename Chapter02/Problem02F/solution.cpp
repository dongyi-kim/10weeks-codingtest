#include<cstdio>
#include<cmath>
#include<climits>
#include<iostream>
using namespace std;

class Point2D{
private: 
	int x;
	int y;

public:
	Point2D(int x = 0, int y = 0)
	{
		this->x = x;
		this->y = y;
	}

	/**
	* 2차원 평면 상에서 점 this부터 점 target까지 거리의 제곱을 계산하는 함수
	* @param target
	* @return
	*/
	int getSquaredDistanceTo(const Point2D &target) const
	{
		int dx = abs(this->x - target.x);
		int dy = abs(this->y - target.y);
		return dx*dx + dy*dy;
	}

	double getDistanceTo(const Point2D &target) const{
		double sqd = (double)this->getSquaredDistanceTo(target);
		return sqrt(sqd);
	}
};



int main()
{
	int n;
	Point2D* points;

	scanf("%d", &n);
	points = new Point2D[n];

	for (int i = 0; i < n; i++)
	{
		int x, y;
		scanf("%d %d", &x, &y);

		points[i] = Point2D(x, y);
	}

	int min_sqd = INT_MAX;
	int min_cnt = 0;

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < i; j++)
		{
			int sqd = points[i].getSquaredDistanceTo(points[j]);
			if (sqd < min_sqd)
			{
				min_sqd = sqd;
				min_cnt = 1;
			}
			else if (sqd == min_sqd)
			{
				min_cnt++;
			}
		}
	}

	double distance = sqrt(min_sqd);
	printf("%.1f\n", distance);
	printf("%d\n", min_cnt);

	delete[] points;
	return 0;
}