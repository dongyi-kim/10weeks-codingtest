import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);


	public static void testcase(int caseIndex) {
		int N = scanner.nextInt();

		Circle globalRange = new Circle(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
		Circle[] coverages = new Circle[N];
		for (int i = 0; i < N; i += 1) {
			coverages[i] = new Circle(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
		}

		double ratio = Solution.getCoveredRatio(N, globalRange, coverages);

		System.out.printf("%.2f\n", ratio);
	}

	public static void main(String[] args) {
		int caseNum = scanner.nextInt();
		for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
			testcase(caseIndex);
		}
	}
}

class Solution {
	public static final double SAMPLING_NUMBER = 1000;

	/**
	 * 기준 원 영역 중 커버리지에 의해 덮혀진 영역의 비율을 계산하는 함수
	 *
	 * @param N				커버리지들의 수
	 * @param globalRange	기준 원 영역
	 * @param coverages		각 와이파이들의 커버리지
	 * @return				덮혀진 영역의 비율
	 */
	public static double getCoveredRatio(int N, Circle globalRange, Circle[] coverages) {
		// globalRange를 덮는 x, y좌표의 상/하한 범위를 구한다
		double maxY = globalRange.yPos + globalRange.radius;
		double minY = globalRange.yPos - globalRange.radius;
		double minX = globalRange.xPos - globalRange.radius;
		double maxX = globalRange.xPos + globalRange.radius;

		// 각 x와 y범위를 샘플링할 길이를 계산한다
		double unitLength = Math.abs(maxX - minX) / SAMPLING_NUMBER;

		int globalCount = 0;
		int coverageCount = 0;

		// X:[minX, maxX], Y:[minY, maxY] 범위의 점들을 샘플링하여 모두 조회한다
		for (double x = minX; x <= maxX; x += unitLength) {
			for (double y = minY; y <= maxY; y += unitLength) {
				// 해당 영역의 모든 점들 중

				if (globalRange.contains(x, y) == true) {
					// 기준 영역에 속한 점의 수를 계산한다
					globalCount += 1;

					// 하나 이상의 커버리지에 속한 점의 수를 계산한다
					boolean isInside = false;
					for (Circle c : coverages) {
						if (c.contains(x, y) == true) {
							isInside = true;
							break;
						}
					}

					if (isInside) {
						coverageCount += 1;
					}
				}
			}
		}

		// 두 수를 나누어 근사 비율을 계산한다
		return (double) coverageCount / globalCount;
	}
}

class Circle {
	public final double xPos;	// 원 중심의 x좌표
	public final double yPos;	// 원 중심의 y좌표
	public final double radius;	// 원 영역의 반지름

	public Circle(double x, double y, double r) {
		this.xPos = x;
		this.yPos = y;
		this.radius = r;
	}

	// 해당 점 (x,  y)가 원 내부에 포함되는지 검사하는 함수
	public boolean contains(double x, double y) {
		double dx = x - this.xPos;
		double dy = y - this.yPos;
		double distance = Math.sqrt(dx * dx + dy * dy);

		return distance <= this.radius;
	}
}