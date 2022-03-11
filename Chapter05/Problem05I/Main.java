import java.lang.*;
import java.util.*;
import java.io.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static long getMaximumSquareArea(int n, Point2D[] points) {
		long answer = 0;

		// 모든 점을 Set에 저장한다
		TreeSet<Point2D> pSet = new TreeSet<>();
		for (int i = 0; i < n; i += 1) {
			pSet.add(points[i]);
		}

		for (int i = 0; i < n; i += 1) {
			Point2D pa = points[i];
			for (int j = 0; j < n; j += 1) {
				Point2D pb = points[j];
				// 두 기준점 pa와 pb를 지정한다.
				// 선분 pa-pb가 정사각형의 한 변이라고 하자.

				// 두 점의 거리(선분의 길이)의 제곱은 정사각형의 넓이가 된다.
				long area = pa.getSquaredDistanceTo(pb);

				// 이미 구한 사각형보다 넓이가 작을 수 밖에 없다면 건너뛴다.
				if (area < answer) {
					continue;
				}

				// pa->pb방향의 x, y 좌표에 대한 거리를 구한다
				int dx = pb.x - pa.x;
				int dy = pb.y - pa.y;

				// 벡터 <dx, dy>를 90도로 회전시키면 <-dy, dx>가 된다.
				// pa와 pb에 벡터 <-dy, dx>를 각각 더해 정사각형을 구성하는 두 점
				// pd, pc를 계산할 수 있다.
				Point2D pd = new Point2D(pa.x - dy, pa.y + dx);
				Point2D pc = new Point2D(pb.x - dy, pb.y + dx);

				// pd, pc와 결정적이므로 이 점이 pSet에 존재하는 점인지 검사하면 된다.
				if(pSet.contains(pc) && pSet.contains(pd)){
					answer = Math.max(answer, area);
				}

			}
		}


		return answer;
	}

	public static void testCase(int caseIndex) {
		int n = scanner.nextInt();

		Point2D[] points = new Point2D[n];

		for (int i = 0; i < n; i += 1) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			points[i] = new Point2D(i, x, y);
		}

		long answer = getMaximumSquareArea(n, points);

		System.out.printf("%.2f\n", (double) answer);
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}

class Point2D implements Comparable<Point2D> {
	public final int x;
	public final int y;
	public final int index;

	public Point2D(int index, int x, int y) {
		this.index = index;
		this.x = x;
		this.y = y;
	}

	public Point2D(int x, int y) {
		this(-1, x, y);
	}

	public long getSquaredDistanceTo(Point2D target) {
		// 두 좌표간의 제곱거리를 계산
		long dx = Math.abs(this.x - target.x);
		long dy = Math.abs(this.y - target.y);
		return dx * dx + dy * dy;
	}

	public double getDistanceTo(Point2D target) {
		// 두 좌표간의 실수 거리를 계산
		long sqd = this.getSquaredDistanceTo(target);
		return Math.sqrt(sqd);
	}

	@Override
	public int compareTo(Point2D other) {
		// 각 좌표의 우선순위를 비교하기 위한 비교 연산자

		// x좌표가 다르다면 x좌표를 기준으로 비교한다.
		if (this.x != other.x) {
			return this.x - other.x;
		}

		// x좌표가 같다면 y좌표를 기준으로 비교한다.
		return this.y - other.y;
	}
}