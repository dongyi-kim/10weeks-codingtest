import java.util.ArrayList;
import java.util.Scanner;

class Main {

    static int N;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        ArrayList<Point> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < N; i++) {

            list.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }

        double min = Double.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                double distance = Point.distance(list.get(i), list.get(j));
                if (min > distance) {
                    min = distance;
                }
            }
        }
        System.out.println(min);

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                double distance = Point.distance(list.get(i), list.get(j));
                if (min == distance) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static double distance(Point a, Point b) {
            Double result = Math.sqrt(((a.x - b.x) * (a.x - b.x)) + ((a.y - b.y) * (a.y - b.y)));
            return Math.round(result * 100d) / 100d;
        }
    }
}
