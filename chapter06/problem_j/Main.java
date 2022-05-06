import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static final int MAX_N = 100000;
	public static final Point2D[] buffer = new Point2D[MAX_N];

	public static void mergeTwoOrderedArray(int left, int mid, int right, Point2D[] points) {
		int i = left;
		int j = mid + 1;
		int k = left;

		while (i <= mid && j <= right) {
			if (points[i].y < points[j].y) {
				buffer[k++] = points[i++];
			} else {
				buffer[k++] = points[j++];
			}
		}

		while (i <= mid) {
			buffer[k++] = points[i++];
		}

		while (j <= right) {
			buffer[k++] = points[j++];
		}

		for (int index = left; index <= right; index += 1) {
			points[index] = buffer[index];
		}
	}


	/**
	 * @brief points[left] ~ points[right]에서 x좌표가 standardX기준으로 maximumSQD이내 거리에 존재하는 점 만을 골라낸다
	 *
	 * @param points
	 * @param begin
	 * @param end
	 * @param standardX     거리를 계산할 기준 x좌표
	 * @param maximumSQD    최대 제한 거리의 제곱
	 * @return              해당 조건을 만족하는 거리에 존재하는 점들의 리스트
	 */
	public static ArrayList<Point2D> filterPoints(Point2D[] points, int begin, int end, int standardX, int maximumSQD) {
		ArrayList<Point2D> filteredPoints = new ArrayList<>();

		// 해당 범위의 모든 점들에 대하여
		for (int i = begin; i <= end; i += 1) {
			Point2D p = points[i];
			int dx = Math.abs(p.x - standardX);

			//  제한 거리보다 더 가까운 거리에 존재한다면 추가한다
			if (dx * dx < maximumSQD) {
				filteredPoints.add(p);
			}
		}

		// 필터링 된 점들을 반환한다
		return filteredPoints;
	}

	/**
	 * points[left] ~ points[right] 범위에 존재하는 모든 점 사이에 최소 거리를 계산하는 함수
	 *
	 * @param left
	 * @param right
	 * @param points
	 * @return
	 */
	public static int getClosetDistance(int left, int right, Point2D[] points) {
		if (right <= left) {
			// 점이 하나거나 없다면 무한대를 반환한다
			return Integer.MAX_VALUE;
		}

		// 범위를 반으로 나눌 기준 인덱스
		int mid = (left + right) / 2;

		// x좌표의 중앙값을 기준 좌표로 잡는다
		int standardX = points[mid].x;

		// 좌우 절반 내부의 쌍들에 대해서 최소 거리를 계산하고 저장한다
		int leftAnswer = getClosetDistance(left, mid, points);
		int rightAnswer = getClosetDistance(mid + 1, right, points);
		int globalAnswer = Math.min(leftAnswer, rightAnswer);

		// 각 부분 집합에 대한 최단거리보다 더 짧은 거리를 찾아야 한다
		// 즉, 기준 x좌표보다 이보다 더 멀리 있는 점들은 정답이 될 수 없으므로 제거한다
		ArrayList<Point2D> leftPoints = filterPoints(points, left, mid, standardX, globalAnswer);
		ArrayList<Point2D> rightPoints = filterPoints(points, mid + 1, right, standardX, globalAnswer);

		int j_from = 0;
		for (int i = 0; i < leftPoints.size(); i += 1) {
			// 기준점보다 왼쪽에 있는 점 pi에 대해서 (y에 대해 오름차순으로 본다)
			Point2D pi = leftPoints.get(i);

			// y좌표가 globalAnswer보다 멀리 떨어진 점들은 볼 필요가 없다
			// minY, maxY := 검사해 볼 만한 y좌표의 범위
			int minY = pi.y - (int) Math.sqrt(globalAnswer) - 1;
			int maxY = pi.y + (int) Math.sqrt(globalAnswer) + 1;

			// j_from := pi에 대해서 minY보다 큰 y좌표를 가지는 최초의 pj의 인덱스
			for (int j = j_from; j < rightPoints.size(); j += 1) {
				// 오른쪽 점 pj에 대해서 (y좌표에 대해 오름차순)

				Point2D pj = rightPoints.get(j);
				if (pj.y < minY) {
					// minY 보다 작은 y좌표를 가지는 점들은 어차피 이후 pi에서 이용되지 않는다
					// j_from을 증가시켜 이후에 고려되지 않도록 한다.
					j_from += 1;
					continue;
				}else if (pj.y > maxY) {
					// maxY를 벗어난 경우, 이후는 더 볼 필요가 없다
					// 어차피 y좌표 거리는 점점 더 멀어진다
					break;
				}

				// 계산한 거리를 갱신한다
				int distance = pi.getDistanceTo(pj);
				globalAnswer = Math.min(globalAnswer, distance);
			}
		}

		// 주어진 범위를 y좌표로 병합정렬한다
		mergeTwoOrderedArray(left, mid, right, points);

		return globalAnswer;
	}

	public static void main(String[] args) {
		int N = scanner.nextInt();

		Point2D[] points = new Point2D[N];
		for (int i = 0; i < N; i += 1) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			points[i] = new Point2D(x, y);
		}

		Arrays.sort(points);

		int answer = getClosetDistance(0, N - 1, points);

		System.out.println(answer);
	}
}

class Point2D implements Comparable<Point2D> {
	public final int x;
	public final int y;

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getDistanceTo(Point2D other) {
		int dx = this.x - other.x;
		int dy = this.y - other.y;
		return dx * dx + dy * dy;
	}

	@Override
	public int compareTo(Point2D other) {
		if (this.x != other.x) {
			return this.x - other.x;
		}
		return this.y - other.y;
	}
}
