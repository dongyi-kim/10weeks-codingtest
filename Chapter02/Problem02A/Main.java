import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        N = sc.nextInt();

        int[] totoriKey = new int[N];
        int[] month = new int[N];

        for (int i = 0; i < N; i++) {
            totoriKey[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            month[i] = sc.nextInt();
        }
        M = sc.nextInt();
        int tallest = -1;

        int index = Arrays.binarySearch(month, M);
        if (index > 0) tallest = index;
        System.out.println(tallest);

    }
}
