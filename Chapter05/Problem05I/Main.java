import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex) {
		Point2D[] points = new Point2D[4];
		for (int i = 0; i < 4; i += 1) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			points[i] = new Point2D(x, y);
		}

		Segment A = new Segment(points[0], points[1]);
		Segment B = new Segment(points[2], points[3]);

		if (A.inIntersected(B)) {
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}

class Segment {
	public final Point2D pa;
	public final Point2D pb;

	public Segment(Point2D pa, Point2D pb){
		this.pa = pa;
		this.pb = pb;
	}

	public long getSquaredLength(){
		long dx = getRightX() - getLeftX();
		long dy = getTopY() - getBottomY();
		return dx * dx + dy * dy;
	}

	public double length(){
		long sqd = this.getSquaredLength();
		return Math.sqrt(sqd);
	}

	public int getLeftX(){
		return Math.min(pa.x, pb.x);
	}

	public int getRightX(){
		return Math.max(pa.x, pb.x);
	}

	public int getTopY(){
		return Math.max(pa.y, pb.y);
	}

	public int getBottomY(){
		return Math.min(pa.y, pb.y);
	}

	public boolean inIntersected(Segment other){
		int crossLeftX = Math.max(this.getLeftX(), other.getLeftX());
		int crossRightX = Math.min(this.getRightX(), other.getRightX());
		int crossBottomY = Math.max(this.getBottomY(), other.getBottomY());
		int crossTopY = Math.min(this.getTopY(), other.getTopY());

		if(crossLeftX > crossRightX || crossBottomY > crossTopY){
			return false;
		}
		return true;
	}
}

class Point2D {
	public final int x;
	public final int y;

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
