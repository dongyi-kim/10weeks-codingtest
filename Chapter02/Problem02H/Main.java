import java.util.Scanner;


public class Main {

    private static int N;
    private static int[] R;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        R = new int[N];
        for (int i = 0; i < N; i++) {
            R[i] = scanner.nextInt();
        }

        for (int i = 0; i < N; i++) {
            System.out.printf("#%d\n", i);
            calculate(R[i]);
        }
    }


    private static void calculate(int input) {

        int r = input;
        long h = r;
        long sum = 0;
        for (int x = 0; x < r; x++) {
            while (x * x + (h - 1) * (h - 1) < r * r) {
                h--;
            }
            sum += h;
        }
        System.out.println(sum*4);
    }
}