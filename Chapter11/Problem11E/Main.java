import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		Polygon[] polygons = new Polygon[N];

		for (int i = 0; i < N; i += 1) {
			polygons[i] = new Polygon(i + 1);

			int size = scanner.nextInt();
			for (int j = 0; j < size; j += 1) {
				polygons[i].addPoint(scanner.nextDouble(), scanner.nextDouble());
			}
		}

		Polygon[] pair = Solution.getClosetPolygonPair(N, polygons);

		System.out.printf("%d %d\n", pair[0].index, pair[1].index );
		System.out.printf("%.2f\n", pair[0].getDistanceTo(pair[1]));
	}

	public static void main(String[] args) {
		int caseNum = scanner.nextInt();
		for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}

class Solution {

	public static Polygon[] getClosetPolygonPair(int N, Polygon[] polygons) {

		double minimumDistance = Double.MAX_VALUE;
		Polygon[] closetPair = null;

		for (int i = 0; i < N; i += 1) {
			for (int j = i + 1; j < N; j += 1) {
				double approxDistance = polygons[i].getApproximateDistanceTo(polygons[j]);
				if(approxDistance >= minimumDistance){
					continue;
				}

				double realDistance = polygons[i].getDistanceTo(polygons[j]);
				if(realDistance < minimumDistance){
					minimumDistance = realDistance;
					closetPair = new Polygon[]{ polygons[i], polygons[j] };
				}
			}
		}

		return closetPair;
	}
}

class Polygon {
	public final int index;
	public final ArrayList<Point2D> points;
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;

	public Polygon(int index) {
		this.index = index;
		this.points = new ArrayList<>();
		this.minX = this.minY = Double.MAX_VALUE;
		this.maxX = this.maxY = Double.MIN_VALUE;
	}

	public int size() {
		return this.points.size();
	}

	public void addPoint(double x, double y) {
		points.add(new Point2D(x, y));

		this.minX = Math.min(this.minX, x);
		this.maxX = Math.max(this.maxX, x);
		this.minY = Math.min(this.minY, y);
		this.maxY = Math.max(this.maxY, y);
	}

	public double getDistanceTo(Polygon other) {
		double distance = Double.MAX_VALUE;
		for (int i = 0; i < this.size(); i += 1) {
			Point2D pa = this.points.get(i);
			for (int j = 0; j < other.size(); j += 1) {
				Point2D pb = other.points.get(j);

				double pDistance = pa.getDistanceTo(pb);

				distance = Math.min(distance, pDistance);
			}
		}
		return distance;
	}

	public double getApproximateDistanceTo(Polygon other) {
		double rightX = Math.max(this.minX, other.minX);
		double leftX = Math.min(this.maxX, other.maxX);
		double botY = Math.min(this.maxY, other.maxY);
		double topY = Math.max(this.minY, other.maxY);
		double dx = Math.max(0, rightX - leftX);
		double dy = Math.max(0, topY - botY);
		return Math.sqrt(dx * dx + dy * dy);
	}

	public static double MIN(double... arr) {
		double min = arr[0];
		for (int i = 0; i < arr.length; i += 1) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}
}

class Point2D {
	public final double x;
	public final double y;

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getDistanceTo(Point2D other) {
		double dx = this.x - other.x;
		double dy = this.y - other.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
}