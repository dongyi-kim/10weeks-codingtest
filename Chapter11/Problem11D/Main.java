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

	public static double getCoveredRatio(int N, Circle globalRange, Circle[] coverages) {
		double maxY = globalRange.yPos + globalRange.radius;
		double minY = globalRange.yPos - globalRange.radius;
		double minX = globalRange.xPos - globalRange.radius;
		double maxX = globalRange.xPos + globalRange.radius;

		double unitLength = (maxX - minX) / SAMPLING_NUMBER;

		int globalCount = 0;
		int coverageCount = 0;

		for (double x = minX; x <= maxX; x += unitLength) {
			for (double y = minY; y <= maxY; y += unitLength) {
				if (globalRange.contains(x, y) == true) {
					globalCount += 1;

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

		return (double) coverageCount / globalCount;
	}
}

class Circle {
	public final double xPos;
	public final double yPos;
	public final double radius;

	public Circle(double x, double y, double r) {
		this.xPos = x;
		this.yPos = y;
		this.radius = r;
	}

	public boolean contains(double x, double y) {
		double dx = x - this.xPos;
		double dy = y - this.yPos;
		double distance = Math.sqrt(dx * dx + dy * dy);

		return distance <= this.radius;
	}
}